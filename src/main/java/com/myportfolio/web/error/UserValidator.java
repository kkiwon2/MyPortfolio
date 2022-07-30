package com.myportfolio.web.error;

import com.myportfolio.web.domain.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
        return UserDto.class.isAssignableFrom(clazz); // 위에대신 이렇게해도 됨 -> clazz가 User 또는 그 자손인지 확인
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("error패키지의 UserValidator.validate() is called");

        //supports에서 형변환검사를 했기때문에 instanceOf로 확인안해도됨
//        if(!(target instanceof UserDto)) return;
        UserDto user = (UserDto)target;

        System.out.println("UserValidator검증중 : user = " + user);
        String id = user.getId();

//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
        //위에 if문대신 이렇게 짧게 가능 -> errors객체에다가 필드이름은 id로하고 에러코드를 required로 저장한다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if(id==null || id.length() <  5 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[]{"5","12"}, null);
        }
    }
}