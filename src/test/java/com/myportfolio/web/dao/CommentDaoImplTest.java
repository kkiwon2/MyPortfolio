package com.myportfolio.web.dao;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.domain.CommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDaoImplTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void count() throws Exception {
        commentDao.deleteAll(614);
        assertTrue(commentDao.count(614)==0);
    }

    @Test
    public void delete() throws Exception {
        commentDao.deleteAll(614);
        CommentDto commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==1);
    }

    @Test
    public void insert() throws Exception {
        commentDao.deleteAll(614);
        CommentDto commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==1);

        commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==2);
    }

    @Test
    public void selectAll() throws Exception {
        commentDao.deleteAll(614);
        CommentDto commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==1);

        List<CommentDto> list = commentDao.selectAll(614);
        assertTrue(list.size()==1);

        commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==2);

        list = commentDao.selectAll(614);
        assertTrue(list.size()==2);
    }

    @Test
    public void select() throws Exception {
        commentDao.deleteAll(614);
        CommentDto commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==1);

        List<CommentDto> list = commentDao.selectAll(614);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }

    @Test
    public void update() throws Exception {
        commentDao.deleteAll(614);
        CommentDto commentDto = new CommentDto(614, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(614)==1);

        List<CommentDto> list = commentDao.selectAll(614);
        commentDto.setCno(list.get(0).getCno());
        commentDto.setComment("comment2");
        assertTrue(commentDao.update(commentDto)==1);

        list = commentDao.selectAll(614);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }
}