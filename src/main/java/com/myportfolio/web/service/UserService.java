package com.myportfolio.web.service;

import com.myportfolio.web.domain.UserDto;

public interface UserService {
    int insertUser(UserDto user) throws Exception;
    UserDto selectUser(String id) throws Exception;
}
