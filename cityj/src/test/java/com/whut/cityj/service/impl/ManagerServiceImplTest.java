package com.whut.cityj.service.impl;

import com.whut.cityj.bean.Manager;
import com.whut.cityj.mapper.ManagerMapper;
import com.whut.cityj.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
class ManagerServiceImplTest {

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    ManagerService managerServiceImpl;

    @Test
    public void testManager(){
        System.err.println(managerMapper);
        System.err.println(managerServiceImpl);
        System.err.println(managerServiceImpl.seekManager("张三", "123456").toString());
    }


}