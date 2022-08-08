package com.myportfolio.web.error;
import com.myportfolio.web.domain.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidator implements Validator {
    @Override
    //이 검증기로 검증가능한 객체인지 알려주는 메서드
    public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
        return UserDto.class.isAssignableFrom(clazz); // 위에대신 이렇게해도 됨 -> clazz가 UserDTO 또는 그 자손인지 확인
    }

    @Override
    // 객체를 검증하는 메서드 - target: 검증할 객체, errors : 검증시 발생한 에러저장소
    public void validate(Object target, Errors errors) {
        System.out.println("error패키지의 UserValidator.validate() is called");

//        supports에서 형변환검사를 했기때문에 instanceof로 확인안해도됨
//        if(!(target instanceof UserDto)) return;
        UserDto user = (UserDto)target;

        System.out.println("UserValidator검증중 : user = " + user);
        String id = user.getId();
        String pwd = user.getPwd();

//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
        //위에 if문대신 이렇게 짧게 가능 -> errors객체에다가 필드이름은 id로하고 에러코드를 required로 저장한다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "required");


        Pattern idpwd_pattern = Pattern.compile("^[A-Za-z0-9]{4,12}$");

        Matcher matcher = idpwd_pattern.matcher(id);
        if(!matcher.matches() && (id != "")){
            errors.rejectValue("id", "invalidLength", new String[]{"4","12"}, null);
        }
        matcher = idpwd_pattern.matcher(pwd);
        if(!matcher.matches() && (pwd != "")){
            errors.rejectValue("pwd", "invalidLength", new String[]{"4","12"}, null);
        }
    }
}