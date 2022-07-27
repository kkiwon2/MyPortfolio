package com.myportfolio.web.controller;

import com.myportfolio.web.domain.CommentDto;
import com.myportfolio.web.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    CommentService service;
    CommentController(CommentService service){
        this.service = service;
    }

    //댓글 목록보여주기
    @GetMapping("/comments")        // /comments?bno=1080
    @ResponseBody
    public List<CommentDto> list(Integer bno)  {
        List<CommentDto> list = null;
        try {
             list = service.getList(bno);
            System.out.println("list = " + list);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return list;
        }
    }


    //댓글 삭제 → /comments/{cno}?bno=1085
    @ResponseBody
    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) { //자원이 리소스로 할당
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        try {
            int rowCnt = service.remove(cno,bno,commenter);
            if(rowCnt != 1)
                throw  new Exception("Delete Comment Failed");
            return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PostMapping("/comments")   // /comments?bno=644
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
        //        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            if(service.write(dto) !=1)
                throw new Exception("Comment Write Failed");
            return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @PatchMapping("/comments/{cno}")   // /comments?bno=644
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto, HttpSession session ) {
        //        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            if(service.modify(dto) !=1)
                throw new Exception("Comment Write Failed");
            return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

}
