package com.myportfolio.web.controller;

import com.myportfolio.web.domain.UserDto;

import com.myportfolio.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.mail.internet.MimeMessage;


@RequestMapping("/search")
@Controller
public class MailController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @GetMapping("/passwordSearch")
    public String passwordSearch(){
        return "passwordSearch";
    }

    @ResponseBody
    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMailTest(@RequestBody UserDto userDto) throws  Exception{
        String id = userDto.getId();
        String email = userDto.getEmail();
            try {
                    userDto = userService.selectUser(id);
                if(id.equals(userDto.getId()) && email.equals(userDto.getEmail())) {
                    String subject = "임시비밀번호를 발송해드립니다.";
                    String content = "임시비밀번호 : ";
                    String from = "kkiwon2@gamil.com";
                    String to = "kkiwon2@naver.com";
                    String tmp_pwd = getTempPassword();
                    userDto.setPwd(tmp_pwd);
                    userService.updateUser(userDto);
                    to = userDto.getEmail();
                    content += tmp_pwd;

                    MimeMessage mail = mailSender.createMimeMessage();
                    MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
                    // true는 멀티파트 메세지를 사용하겠다는 의미

                    /*
                     * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능
                     * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
                     */

                    mailHelper.setFrom(from);
                    // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
                    // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
                    //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
                    mailHelper.setTo(to);
                    mailHelper.setSubject(subject);
                    mailHelper.setText(content);
//                mailHelper.setText(content, true);
                    // true는 html을 사용하겠다는 의미입니다.

                    /*
                     * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
                     */
                    mailSender.send(mail);
                    return new ResponseEntity<String>("success", HttpStatus.OK);
                }//if
            } catch(Exception e) {
                System.out.println("오류나면 여기 실행되고 돌아가야지");
            }
        return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
    }//senEmail

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }//getTempPassword

    @GetMapping("/boot")
    public String test() {
        return "ajaxtest/boot";
    }


}
