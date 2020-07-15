package com.whut.cityj.controller;

import com.whut.cityj.bean.User;
import com.whut.cityj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userServiceImpl;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String name,@RequestParam("password") String password){
        System.out.println(name);
        System.out.println(password);
        User user = userServiceImpl.seekUser(name, password);
        if( user != null){
            return user.toString();
        }
        return "false";
    }

}
