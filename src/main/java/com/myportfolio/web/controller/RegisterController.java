package com.myportfolio.web.controller;

import com.myportfolio.web.domain.UserDto;
import com.myportfolio.web.error.UserValidator;
import com.myportfolio.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller // ctrl+shift+o 자동 import
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    final int FAIL = 0;

    //타입을 변환할 때 우리가 등록한 registerCustomEditor를 먼저 확인한다.
    @InitBinder
    public void toDate(WebDataBinder binder) {
        System.out.println("RegisertController toDate실행");
        System.out.println("binder = " + binder);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록 - 자동검증
    }

    //회원가입 화면 호출
    @GetMapping("/add")
    public String register() {
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }


//        수동검증 -> 위에 initBinder에 setValidator로 자동검증으로 등록함
//        기존 유효성 검사대신 검증기로 대체 -> if(!isValid(User)) { String msg = URLEncoder.encode("id를 잘못 입력","utf-8")
//        UserValidator userValidator = new UserValidator();
//        userValidator.validate(user, result);   //BindingResult인페이스는 Error인터페이스의 자손이라 2번째 매개변수로 넣어도됨
    //회원가입 처리
    @PostMapping("/add")
    public String add(@Valid UserDto user, BindingResult result, Model m) {
        System.out.println("RegisterController : add()메서드");
        System.out.println("result=" + result);
        System.out.println("user=" + user);
        if (!result.hasErrors()) {
            try {
                if(userService.selectUser(user.getId())!=null)
                    throw new Exception("ID중복");
                int rowCnt = userService.insertUser(user);
                if (rowCnt == FAIL)
                    throw new Exception();
                return "redirect:/";
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("msg", "중복된 ID입니다. 다시 입력해주세요.");
                m.addAttribute("user", user);
                return "registerForm";
            }//catch
        }// if (!result.hasErrors())
        m.addAttribute("user", user);
        return "registerForm";
    }//@PostMapping("/add")

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        try {
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute("id");
            UserDto user = userService.selectUser(id);


            if (user.getId() != null) {
                model.addAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "registerInfo";
    }//    @GetMapping("/update")

    @PostMapping("/update")
    public String update(UserDto user, Model m) {
        try {
            int error = userService.updateUser(user);
            if (error != 1) {
                m.addAttribute("msg", "회원정보 수정에 실패했습니다.");
                m.addAttribute("userDto", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register/update";
        }
        m.addAttribute("회원정보 수정을 완료했습니다.");
        return "index";
    }

    //ID중복검사
    @ResponseBody
    @GetMapping("/validate")
    public String list(String id) {
        System.out.println("중복검사 ID = " + id);
        String msg = "";
        try {
            Pattern idpwd_pattern = Pattern.compile("^[A-Za-z0-9]{4,12}$");
            Matcher matcher = idpwd_pattern.matcher(id);
            if(!matcher.matches()) {
                msg = URLEncoder.encode("아이디는 4~12 영대문자와 숫자만 가능합니다.", "utf-8");
                throw new Exception();
            }
            msg = URLEncoder.encode("사용 가능한 아이디 입니다.", "utf-8");
            userService.selectUser(id).getId();
            msg = URLEncoder.encode("중복된 아이디 입니다.", "utf-8");
        } catch (Exception e) {
            return msg;
        }
        return msg;
    }

}
