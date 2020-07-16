package com.whut.cityj;

import com.whut.cityj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CityjApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        //设置过期时间：20S
      /*  stringRedisTemplate.opsForValue().set("小李", "898989", 20, TimeUnit.SECONDS);

        System.out.println(Math.random()*10000);*/
    }

}
