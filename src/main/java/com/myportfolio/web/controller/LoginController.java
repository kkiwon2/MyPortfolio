package com.myportfolio.web.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myportfolio.web.domain.UserDto;
import com.myportfolio.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    //localhost/web/login/login GET
    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        session.invalidate();   //로그인 안했으니까 세션없앰
        return "loginForm";
    }

    //localhost/web/login/logout
    @GetMapping("/logout")
    public String logout(HttpSession session) { //세션은 request.getSession();로부터 얻어도되지만 그냥 HttpSession클래스만 해도 자동으로 스프링이 넣어줌
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    //localhost/web/login/login POST
    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. id와 pwd를 확인
        if (!loginCheck(id, pwd)) {
            // 2-1. 일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/login?msg=" + msg;
        }//if
        // 2-2. id와 pwd가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 id를 저장
        session.setAttribute("id", id);

        //아이디 기억 체크시
        if (rememberId) {
            // 1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id);
//		       2. 응답에 저장
            response.addCookie(cookie);
        } else {
            // 1. 쿠키를 삭제 -> 체크를 안했다면 Cookie에 저장시켜놓으면 안됨
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
            response.addCookie(cookie);
        }//else
        System.out.println("toURL = " + toURL);
//		       3. 홈으로 이동
        toURL = (toURL == null || toURL.equals("") ? "/" : toURL);

        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        UserDto user = null;

        try {
            user = userService.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return user != null && user.getPwd().equals(pwd);
    }
}