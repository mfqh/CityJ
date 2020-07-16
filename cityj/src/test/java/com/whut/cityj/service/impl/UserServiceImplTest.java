package com.whut.cityj.service.impl;

import com.whut.cityj.bean.User;
import com.whut.cityj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userServiceImpl;

    @Test
    public void testUser(){
        System.err.println(userServiceImpl.seekUser("小王", "123456").toString());
    }

    @Test
    public void testJudgeUser(){
        System.out.println(userServiceImpl.judgeUser("小王"));
    }

    @Test
    public void testInsertUser(){
    //    System.out.println(userServiceImpl.insUser(new User("六六","123456")));
    }

}