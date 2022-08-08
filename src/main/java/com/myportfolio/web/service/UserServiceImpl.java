package com.myportfolio.web.service;

import com.myportfolio.web.dao.UserDao;
import com.myportfolio.web.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public int insertUser(UserDto user)throws Exception{
        return userDao.insertUser(user);
    }

    @Override
    public UserDto selectUser(String id) throws Exception {
        return userDao.selectUser(id);
    }

    public int updateUser(UserDto user) throws Exception{
        return userDao.updateUser(user);
    }
}
