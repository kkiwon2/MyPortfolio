package com.myportfolio.web.dbTest;

import com.myportfolio.web.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //에가 자동으로 ApplciationContext를 자동으로 만들어준다.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //이걸가지고 AC를 자동설정한다
public class DBConnectionTest2Test {

    @Autowired
    DataSource ds;

    @Test
    public void springJdbcConnectionTest() throws Exception {

//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null);
    }

    // 사용자 정보를 user_info테이블에 저장하는 메서드
    public int insertUser(UserDto user) throws Exception {
        Connection conn = ds.getConnection();

//        insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//        values ('asdf22', '1234', 'smith', 'aaa@aaa.com', '2022-01-01', 'facebook', now());

        String sql = "insert into user_info values (?, ?, ?, ?,?,?, now()) ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        System.out.println("user.getBirth().getTime() = " + user.getBirth().getTime());

        int rowCnt = pstmt.executeUpdate(); //  insert, delete, update만 사용가능

        return rowCnt;
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.executeUpdate(); //  insert, delete, update에서만 사용
    }

    public UserDto selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id= ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery(); //  select에서만 사용

        if(rs.next()) {
            UserDto user = new UserDto();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
            return user;
        }
        return null;
    }

    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id= ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.setString(1, id);
//        int rowCnt = pstmt.executeUpdate(); //  insert, delete, update
//        return rowCnt;
        return pstmt.executeUpdate(); //  insert, delete, update
    }

    // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
    public int updateUser(UserDto user) throws Exception {
        Connection conn = ds.getConnection();

//        String sql = "update user_info set id = 'asdf4' where id = ?"; //이것도가능
        String sql = "update user_info set id = ? where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1,"asdf3");
        pstmt.setString(1,"asdf4");
        pstmt.setString(2, user.getId());
        return pstmt.executeUpdate();
    }

    @Test
    public void insertUserTest() throws Exception {
        UserDto user = new UserDto("asdf", "1234", "abc", "aaaa@aaa.com", new Date(), "fb");
        deleteAll();
        int rowCnt = insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        UserDto user = new UserDto("asdf2", "1234", "abc", "aaaa@aaa.com", new Date(), "fb");
        int rowCnt = insertUser(user);
        UserDto user2 = selectUser("asdf2");

        assertTrue(user.getId().equals("asdf2"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("asdf");

        assertTrue(rowCnt==0);

        UserDto user = new UserDto("asdf2", "1234", "abc", "aaaa@aaa.com", new Date(), "fb");
        rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt==1);

        assertTrue(selectUser(user.getId())==null);

    }

    @Test
    public void updateUserTest() throws Exception{
        deleteAll();
        UserDto user = new UserDto("asdf2", "1234", "abc", "aaaa@aaa.com", new Date(), "fb");
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        rowCnt = updateUser(user);
        assertTrue(rowCnt ==1);

        UserDto user2 = selectUser("asdf4");
        assertTrue(user2.getId().equals("asdf4"));

    }



}