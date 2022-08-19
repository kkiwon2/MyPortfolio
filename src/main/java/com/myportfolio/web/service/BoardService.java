package com.myportfolio.web.service;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.handler.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;

    int remove(Integer bno, String writer) throws Exception;

    int write(BoardDto boardDto) throws Exception;

    List<BoardDto> getList() throws Exception;

    BoardDto read(Integer bno) throws Exception;

    List<BoardDto> getPage(Map map) throws Exception;

    int modify(BoardDto boardDto) throws Exception;

    int SearchResultCnt(SearchCondition sc) throws Exception;

    List<BoardDto> SearchResultPage(SearchCondition sc) throws Exception;
}
