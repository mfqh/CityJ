package com.whut.cityj.controller;

import com.whut.cityj.bean.*;
import com.whut.cityj.service.AfcQuestionService;
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
public class AfcController {

    @Autowired
    private AfcQuestionService afcQuestionServiceImpl;

    /**
     * 根据选中的版本和年级返回所有章节
     * @param version 书籍版本
     * @param grade  年级
     * @return  所有的章节
     */
    @GetMapping("/afcChapter")
    public List<Chapter> afcChapter(@RequestParam("version") String version,
                                    @RequestParam("grade") String grade){
        //拼接版本
        String bookVersion = version+ StateUtil.QUES_DIVISION+grade;
        return afcQuestionServiceImpl.getAllChapter(bookVersion);
    }

    /**
     * 根据传入的章节Id来查询该章节所有题目，并返回第一道题目
     * @param id 章节id
     * @param session 内置Session
     * @return 题目总数&第一道题目
     */
    @GetMapping("/afcSelChapter/{id}")
    public List<String> afcSelChapter(@PathVariable("id") Integer id,
                                      HttpSession session){
        Map<Integer, AfcQuestion> afcQuestions = afcQuestionServiceImpl.getChapterQuesions(id);
        //将所有题目和当前题id存入Session，减少IO压力
        session.setAttribute("afcQuestions", afcQuestions);
        session.setAttribute("afcID", afcQuestions.get(1).getQueNumber());

        //分析第一道题输出
        List<String> list = new ArrayList<String>();
        //放入题目总数
        list.add(afcQuestions.size()+"");
        list = PaperUtil.typeAnalysis(list, afcQuestions.get(1).getQuestion(), afcQuestions.get(1).getSign());
        return list;
    }

    /**
     * 判断正误，返回解析，错题入错题库
     * @param answer 用户输入的答案
     * @param session 内置Session
     * @return 判断结果及题目解析
     */
    @GetMapping("/afcAnalyAnswer/{answer}")
    public List<String> afcAnalyAnswer(@PathVariable("answer") String answer,
                                           HttpSession session){
        //从session获取所有题目及上一题id
        Map<Integer, AfcQuestion> afcQuestions = (Map<Integer, AfcQuestion>)session.getAttribute("afcQuestion");
        Integer id = (Integer)session.getAttribute("afcId");
        List<String> list = new ArrayList<>();
        //防止前后空格影响判断
        if(afcQuestions.get(id).getAnswer().trim().equals(answer.trim())){
            list.add("true");
        }else{
            //错题入库
            afcQuestionServiceImpl.addErrorQuestion(new ErrorBase(
                    ((User)session.getAttribute("user")).getId(), id));
            list.add("false");
        }
        //获取解析
        list.add(afcQuestions.get(id).getAnaly());
        //返回结果及解析
        return list;
    }

    /**
     * 根据id返回下一题
     * @param id 下一题id
     * @param session 内置Session
     * @return 下一题题目
     */
    @GetMapping("/afcSelQuestion/{id}")
    public List<String> afcSelQuestion(@PathVariable("id") Integer id,
                                       HttpSession session){
        //从session中取出所有题目
        Map<Integer, AfcQuestion> afcQuestions = (Map<Integer, AfcQuestion>)session.getAttribute("afcQuestion");
        //放入新id
        session.setAttribute("afcID", id);

        //构造返回值
        ArrayList<String> list = new ArrayList<>();
        PaperUtil.typeAnalysis(list, afcQuestions.get(id).getQuestion(), afcQuestions.get(id).getSign());
        return list;
    }

}
