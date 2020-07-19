package com.whut.cityj.controller;

import com.whut.cityj.bean.User;
import com.whut.cityj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.random;

/**
 * 管理用户状态
 */
@RestController
public class UserController {

    @Autowired
    UserService userServiceImpl;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 获取验证码
     * @param name 用户名
     * @return 验证码json串
     */
    @GetMapping("/verification")
    public String verification(@RequestParam("username") String name){
        String code = String.valueOf((int) (Math.random()*10000));
        //将数据存入Redis数据库，时长为120s
        stringRedisTemplate.opsForValue().set("code"+name+"verif", code, 120, TimeUnit.SECONDS);
        return code;
    }

    /**
     * 用户登陆
     * @param name 用户名
     * @param password 密码
     * @param code 验证码
     * @param session 内置Session对象
     * @return 登陆状态：失败，成功进行跳转
     */
    @PostMapping("/uLogin")
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        @RequestParam("verification") String code,
                        HttpSession session){
        User user = userServiceImpl.seekUser(name, password);
        String verification = stringRedisTemplate.opsForValue().get("code"+name+"verification");
        if( user != null){
            if( code !=null && code.equals(verification)){
                //登陆状态保留在Session
                session.setAttribute("user", user);
                //返回主页路径，跳转到主页
                return "main";
            }else{
                return "请输入正确的验证码";
            }
        }
        return "用户名或密码错误";
    }

    /**
     *主页回显登陆状态
     * @param session 内置Session对象
     * @return 若已登录回显用户名
     */
    @GetMapping("/uStates")
    public String states(HttpSession session){
        Object obj = session.getAttribute("user");
        if(obj != null){
            if(obj instanceof User){
                return ((User) obj).getName();
            }
        }
        return null;
    }

    /**
     * 判断该用户名是否已经存在
     * @param name 用户名
     * @return 判断结果
     */
    @GetMapping("/judgeUser")
    public String judgeUser(@RequestParam("username") String name){
        if(!userServiceImpl.judgeUser("name")){
            return "用户名已被使用";
        }
        return "用户名可用";
    }

    /**
     * 注册新用户
     * @param name 用户名
     * @param password 密码
     * @param session 内置Session对象
     * @return 注册成功，跳转到主页
     */
    @GetMapping("/uRegister")
    public String uRegister(@RequestParam("username") String name,
                            @RequestParam("password") String password,
                            HttpSession session){
        //判断用户名是否可以
        if(!userServiceImpl.judgeUser("name")){
            return "注册失败用户名已被使用";
        }
        User user = new User(name, password);
        Boolean result = userServiceImpl.insUser(user);
        if(result){
            //添加成功，跳转到主页，将用户名存于Session
            session.setAttribute("user", user);
            return "/main";
        }
        return "注册失败";
    }
}
