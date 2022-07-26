package com.myportfolio.web.controller;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.handler.PageHandler;
import com.myportfolio.web.handler.SearchCondition;
import com.myportfolio.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    // 게시판 목록
    //  localhost:/board/list GET
    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {
        //Board 게시판에 가기전에 로그인을 체크 - 로그인을 하지 않았다면 로그인화면으로 redirect한다.
        if (!loginCheck(request)) {
//            System.out.println("request.getRequestURL() = " + request.getRequestURL()); //어디로 요청했는지 알 수 있음 to
//            System.out.println("request.getRequestURI() = " + request.getRequestURI()); //이건 Host빼고 ContextRoot부터나옴
            //http://localhost/web/login/login?toURL=http://localhost/web/board/list
//            HttpSession session = request.getSession();   //2022-08-13 주석처리
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
            // index페이지에서 로그인안한상태에서 board누르면 로그인하고 board로 바로가기 위해서는 to정보가 필요함
        }

        try {
            int totalCnt = boardService.SearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);
            // PageHandler 객체를 생성하여 시작 페이지, 끝 페이지, 검색어, 검색타입을 계산합니다.

            List<BoardDto> list = boardService.SearchResultPage(sc);
            //
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            //2022/07/24 새벽1시
            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            System.out.println("startOfToday = " + startOfToday);
            System.out.println("startOfToday.toEpochMilli = " + startOfToday.toEpochMilli());
            System.out.println("LocalDate.now().atStartOfDay = " + LocalDate.now().atStartOfDay());
            System.out.println("LocalDate.now().atStartOfDay(ZoneId.systemDefault = " + LocalDate.now().atStartOfDay(ZoneId.systemDefault()));
            System.out.println(LocalDate.now());
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
        }//catch
        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    //ID체크
    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }

    //게시글 읽기
    @GetMapping("/read")
    public String read(SearchCondition sc, Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            m.addAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    //게시글삭제
    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m,
                         HttpSession session, String test,
                         RedirectAttributes rattr) {
        System.out.println(test);
        String writer = (String) session.getAttribute("id");
        try {
            int rowCnt = boardService.remove(bno, writer);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            if (rowCnt != 1)
                throw new Exception("board remove Error");

            rattr.addFlashAttribute("msg", "DEL_OK");    //get방식으로 되돌려주는거 파라미터로 감 param

        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "DEL_ERR");  //한번만쓰고 없어짐
            e.printStackTrace();
        }
        return "redirect:/board/list";
    }

    //게시글 읽기
    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        String write = (String) session.getAttribute("id");
        boardDto.setWriter(write);

        try {
            int rowCnt = boardService.write(boardDto);
            if (rowCnt != 1)
                throw new Exception("write failed");
            rattr.addFlashAttribute("mode", "new");
            rattr.addFlashAttribute("msg", "WRT_OK");   //boardList.jsp에서 보여줄거임
            return "redirect:/board/list";

        } catch (Exception e) {
            System.out.println("글쓰기 에러");
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        String write = (String) session.getAttribute("id");
        boardDto.setWriter(write);

        try {
            int rowCnt = boardService.modify(boardDto);
            if (rowCnt != 1)
                throw new Exception("Modify failed");
            rattr.addFlashAttribute("mode", "new");
            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("msg", "MOD__ERR");
            return "board";
        }
    }
}