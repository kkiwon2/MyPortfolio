package com.myportfolio.web.dao;


import com.myportfolio.web.domain.UserDto;

public interface UserDao {
    UserDto selectUser(String id) throws Exception;

    int deleteUser(String id) throws Exception;

    int insertUser(UserDto user) throws Exception;

    int updateUser(UserDto user) throws Exception;

    int count() throws Exception;

    int deleteAll() throws Exception;
}