package com.myportfolio.web.dao;

import com.myportfolio.web.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.myportfolio.web.dao.UserMapper.";

    @Override
    public UserDto selectUser(String id) throws Exception {
        UserDto user = null;
        user = session.selectOne(namespace + "select", id);
        return user;
    }

    @Override
    public int deleteUser(String id) throws Exception {
        return session.delete(namespace+"delete",id);

    }

    @Override
    public int insertUser(UserDto user) throws Exception {
        return session.insert(namespace + "insert", user);
//        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
    }

    @Override
    public int updateUser(UserDto user) throws Exception {
        return session.update(namespace + "update", user);
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }



//    @Override
//    public int count() throws Exception {
//        String sql = "SELECT count(*) FROM user_info ";
//
//        try(
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                ResultSet rs = pstmt.executeQuery();
//        ){
//            rs.next();
//            int result = rs.getInt(1);
//
//            return result;
//        }
//    }
//

}