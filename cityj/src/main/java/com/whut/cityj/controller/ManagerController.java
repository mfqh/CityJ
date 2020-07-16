package com.whut.cityj.controller;

import com.whut.cityj.bean.Manager;
import com.whut.cityj.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 管理管理员状态
 */
@Controller
public class ManagerController {

    @Autowired
    ManagerService manageServiceImpl;


    @GetMapping("/uLogin")
    @ResponseBody
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password){
        Manager manager = manageServiceImpl.seekManager(name, password);
        if(manager != null){
            //管理页地址
            return "admin";
        }
        return "用户名或密码有误";
    }

}
