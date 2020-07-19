package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.bean.User;
import com.whut.cityj.service.ErrorBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrorBaseServiceImplTest {

    @Autowired
    ErrorBaseService errorBaseServiceImpl;

    @Test
    public void testService(){
        User user = new User();
        user.setId(1);
//        System.err.println(errorBaseServiceImpl.getAllError(user));
//        System.err.println(errorBaseServiceImpl.removeError(new ErrorBase(1,2)));
    }

}