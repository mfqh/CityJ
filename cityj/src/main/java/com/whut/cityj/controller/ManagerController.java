package com.whut.cityj.controller;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.Manager;
import com.whut.cityj.service.ExamPaperService;
import com.whut.cityj.service.ManagerService;
import com.whut.cityj.util.PaperUtil;
import com.whut.cityj.util.StateUtil;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.nimbus.State;
import java.time.LocalDateTime;


/**
 * 管理管理员状态
 */
@Controller
public class ManagerController {

    @Autowired
    ManagerService manageServiceImpl;

    @Autowired
    ExamPaperService examPaperServiceImpl;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 登陆
     * @param name 用户名
     * @param password 密码
     * @param session 内置Session
     * @return 验证结果
     */
    @GetMapping("/mgLogin")
    @ResponseBody
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String password,
                        HttpSession session){
        Manager manager = manageServiceImpl.seekManager(name, password);
        if(manager != null){
            //管理页地址
            session.setAttribute("manager", manager);
            return "admin";
        }
        return "用户名或密码有误";
    }

    /**
     * 删除试卷及其所对应的所有题目
     * @param id 试卷编号
     * @return 删除结果状态码
     */
    @GetMapping("/mgDelete/{eid}")
    @ResponseBody
    public Integer mgDelete(@PathVariable("eid") Integer id){
        if(examPaperServiceImpl.delPaper(id)){
            return StateUtil.SUCCESS_STATE;
        }
        return StateUtil.FAIL_STATE;
    }

    /**
     * 添加新试卷
     * @param name 试卷名
     * @param date 开考时间(格式:****-**-** **:**:**)
     * @param session 内置Session
     * @return 添加结果状态码
     */
    @GetMapping("/mgAddPaper")
    @ResponseBody
    public Integer mgAddPaper(@RequestParam("paperName") String name,
                             @RequestParam("paperDate") String date,
                             HttpSession session){
        //转换日期格式
        LocalDateTime localDateTime = PaperUtil.date2Format(date);
        //存入数据库，并获得id
        ExamPaper examPaper = new ExamPaper(name, localDateTime);
        int id = examPaperServiceImpl.addNewPaper(examPaper);
        if(id != StateUtil.FAIL_STATE){
            //将试卷id存入session
            session.setAttribute("newPaperId", id);
            return StateUtil.SUCCESS_STATE;
        }
        return StateUtil.FAIL_STATE;
    }

    /**
     * 向试卷中添加题目
     * @param queNumber 题号
     * @param sign 标志
     * @param question 问题
     * @param answer 答案
     * @param score 分数
     * @param session 内置Session对象
     * @return
     */
    @GetMapping("/mgAddQuestion")
    @ResponseBody
    public Integer mgAddQuestion(@RequestParam("number") Integer queNumber,
                                 @RequestParam("sign") Integer sign,
                                 @RequestParam("question") String[] question,
                                 @RequestParam("answer") String answer,
                                 @RequestParam("score") Integer score,
                                 HttpSession session){
        //对题目按照类型进行格式化
        String resultQuestion = PaperUtil.formatQuestion(question);
        //取出Session中的ID
        int eid = (int)session.getAttribute("newPaperId");
        ExamQuestion examQuestion = new ExamQuestion(eid, queNumber, resultQuestion, answer, sign, score);
        if(examPaperServiceImpl.addNewQuestion(examQuestion)){
            return StateUtil.SUCCESS_STATE;
        }
        return StateUtil.FAIL_STATE;
    }



}
