package com.whut.cityj.controller;

import com.whut.cityj.bean.*;
import com.whut.cityj.mapper.SpeQuestionMapper;
import com.whut.cityj.service.SpeQuestionService;
import com.whut.cityj.util.PaperUtil;
import com.whut.cityj.util.StateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SpecilController {

    @Autowired
    SpeQuestionService speQuestionServiceImpl;

    /**
     * 根据传入的版本号查出所有专项
     * @param version 书籍版本
     * @param grade 年级
     * @return 所有专项
     */
    @GetMapping("/speType")
    public List<SpecilType> speType(@RequestParam("version") String version,
                                       @RequestParam("grade") String grade){
        //拼接版本
        String bookVersion = version+ StateUtil.QUES_DIVISION + grade;
        return speQuestionServiceImpl.getALlType(bookVersion);
    }

    /**
     * 根据专项id，返回第一题及题目总数
     * @param id 专项id
     * @param session 内置Session
     * @return 题目总数&第一题
     */
    @GetMapping("/speSelType/{id}")
    public List<String> speSelType(@PathVariable("id") Integer id,
                                      HttpSession session){
        Map<Integer, SpeQuestion> speQuestions = speQuestionServiceImpl.getAllQuestions(id);
        //将所有题目和当前题id存入Session，减少IO压力
        session.setAttribute("speQuestions", speQuestions);
        session.setAttribute("speID", speQuestions.get(1).getQueNumber());

        //分析第一道题输出
        List<String> list = new ArrayList<String>();
        //放入题目总数
        list.add(speQuestions.size()+"");
        list = PaperUtil.typeAnalysis(list, speQuestions.get(1).getQuestion(), speQuestions.get(1).getSign());
        return list;
    }

    /**
     * 判断正误，返回解析
     * @param answer 用户输入的答案
     * @param session 内置Session
     * @return 判断结果及题目解析
     */
    @GetMapping("/speAnalyAnswer/{answer}")
    public List<String> speAnalyAnswer(@PathVariable("answer") String answer,
                                           HttpSession session){
        //从session获取所有题目及上一题id
        Map<Integer, AfcQuestion> speQuestions = (Map<Integer, AfcQuestion>)session.getAttribute("speQuestion");
        Integer id = (Integer)session.getAttribute("speId");
        List<String> list = new ArrayList<>();
        //防止前后空格影响判断
        if(speQuestions.get(id).getAnswer().trim().equals(answer.trim())){
            list.add("true");
        }else{
            list.add("false");
        }
        //获取解析
        list.add(speQuestions.get(id).getAnaly());
        //返回结果及解析
        return list;
    }

    /**
     * 根据id返回下一题
     * @param id 下一题id
     * @param session 内置Session
     * @return 下一题题目
     */
    @GetMapping("/speSelQuestion/{id}")
    public List<String> speSelQuestion(@PathVariable("id") Integer id,
                                       HttpSession session){
        //从session中取出所有题目
        Map<Integer, AfcQuestion> speQuestions = (Map<Integer, AfcQuestion>)session.getAttribute("speQuestion");
        //放入新id
        session.setAttribute("afcID", id);

        //构造返回值
        ArrayList<String> list = new ArrayList<>();
        PaperUtil.typeAnalysis(list, speQuestions.get(id).getQuestion(), speQuestions.get(id).getSign());
        return list;
    }


}
