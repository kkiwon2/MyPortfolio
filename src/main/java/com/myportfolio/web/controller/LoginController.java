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

    //Login화면 요청
    //localhost/web/login/login GET
    @GetMapping("/login")
    public String loginForm() {

        return "loginForm";
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
        }//end if

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
//		       3. 홈으로 이동(로그인 이전에 게시판 접속을 시도했다면 바로 게시판으로 이동하기 위한 toURL값을 주었다.)
        toURL = (toURL == null || toURL.equals("") ? "/" : toURL);
        return "redirect:" + toURL;
    }// @PostMapping("/login")

    //localhost/web/login/logout
    @GetMapping("/logout")
    public String logout(HttpSession session) { //세션은 HttpServletRequest로부터 얻어도되지만 그냥 HttpSession클래스만 해도 자동으로 스프링이 넣어줌
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
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