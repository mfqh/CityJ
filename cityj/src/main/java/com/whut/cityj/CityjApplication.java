package com.whut.cityj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.whut.cityj.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class CityjApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityjApplication.class, args);
    }

}
