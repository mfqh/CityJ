package com.whut.cityj;

import com.whut.cityj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityjApplicationTests {

    @Autowired
    UserService userServiceImpl;

    @Test
    void contextLoads() {

        System.out.println(userServiceImpl.seekUser("小王", "123456"));
    }

}
