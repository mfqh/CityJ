package com.whut.cityj.service.impl;

import com.whut.cityj.bean.User;
import com.whut.cityj.mapper.UserMapper;
import com.whut.cityj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User seekUser(String name, String password) {
        return userMapper.selUser(name, password);
    }

    @Override
    public Boolean judgeUser(String name) {
        if(userMapper.selUserExist(name) != null){
            return false;
        }
        return true;
    }

    @Override
    public Boolean insUser(User user) {
        return userMapper.insUser(user);
    }


}
