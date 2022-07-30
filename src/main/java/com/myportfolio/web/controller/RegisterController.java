package com.myportfolio.web.controller;

import com.myportfolio.web.domain.UserDto;
import com.myportfolio.web.error.UserValidator;
import com.myportfolio.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller // ctrl+shift+o 자동 import
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    final int FAIL = 0;

    //타입을 변환할 때 우리가 등록한 registerCustomEditor를 먼저 확인한다.
    @InitBinder
    public void toDate(WebDataBinder binder) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록 - 자동검증
    }

    //회원가입 화면 호출
    @GetMapping("/add")
    public String register() {
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    //회원가입 처리
    @PostMapping("/add")
    public String add(@Valid UserDto user, BindingResult result, Model m, RedirectAttributes rattr) {
        System.out.println("RegisterController : add()메서드");
        System.out.println("result=" + result);
        System.out.println("user=" + user);

//        수동검증 - 위에 initBinder에 setValidator로 자동검증으로 등록함
//        기존 유효성 검사대신 검증기로 대체 -> if(!isValid(User)) { String msg = URLEncoder.encode("id를 잘못 입력","utf-8")
//        UserValidator userValidator = new UserValidator();
//        userValidator.validate(user, result);   //BindingResult인페이스는 Error인터페이스의 자손이라 2번째 매개변수로 넣어도됨

        //1. User객체를 검증한 결과 에러가없다면
        if (!result.hasErrors()) {
            // 2. DB에 신규회원 정보를 저장
            try {

                int rowCnt = userService.insertUser(user);
                //2022/07/28 새벽1시
                Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
                System.out.println("startOfToday = " + startOfToday);
                m.addAttribute("startOfToday", startOfToday.toEpochMilli());
                return "registerInfo";
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("msg", "중복된 ID입니다. 다시 입력해주세요.");
                m.addAttribute("user", user);
                return "registerForm";
            }//catch
        }// if (!result.hasErrors())

        //1. User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.
        return "registerForm";
    }//@PostMapping("/add")

}
