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
import java.text.SimpleDateFormat;
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록

    }

    //servlet-context.xml에 view-controller로 등록해놨음
    @GetMapping("/add")
    public String register() {
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    @PostMapping("/add")
    public String add(@Valid UserDto user, BindingResult result, Model m, RedirectAttributes rattr) {
        System.out.println("result=" + result);
        System.out.println("user=" + user);

        //1. User객체를 검증한 결과 에러가없다면
        if (!result.hasErrors()) {
            // 2. DB에 신규회원 정보를 저장
            try {
                int rowCnt = userService.insertUser(user);
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
    }//save

}
