package com.whut.cityj.controller;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.User;
import com.whut.cityj.service.ExamPaperService;
import com.whut.cityj.util.PaperUtil;
import com.whut.cityj.util.StateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ExamController {

    @Autowired
    ExamPaperService examPaperServiceImpl;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 查询所有试卷
     * @return 返回试卷列表
     */
    @GetMapping("/examPageList")
    public List<ExamPaper> examPageList(HttpSession session){
        //判断是否登陆
        if(session.getAttribute("user") != null ) {
            return examPaperServiceImpl.getAllPaper();
        }
        ArrayList<String> list = new ArrayList<String>();
        list.add(StateUtil.LOGIN_NOT);
        return new ArrayList<>();
    }

    /**
     * 根据试卷返回题目
     * @param id 试卷编号
     * @return 返回第一道题目
     */
    @GetMapping("/beginExam/{id}")
    public List<String> beginExam(@PathVariable("id") int id, HttpSession session){

        Map<Integer, ExamQuestion> questions = examPaperServiceImpl.getExamPaper(id);
        //将试卷存入Session，减少IO压力
        session.setAttribute("questions", questions);
        //同时在Redis中保存第一题答案，保存一场考试的时间
        User user = (User)session.getAttribute("user");
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":answer",
                questions.get(1).getAnswer(), 60*60*2, TimeUnit.SECONDS);
        //放入初始分数及第一题分数
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":score",
                "0"+StateUtil.QUES_DIVISION+questions.get(1).getScore(), 60*60*2, TimeUnit.SECONDS);
        //分析第一道题输出
        List<String> list = PaperUtil.typeAnalysis(questions.get(1).getQuestion(), questions.get(1).getSign());
        return list;
    }

    /**
     * 判断正误，返回指定题目
     * @param id 下一题编号
     * @param answer 用户输入的答案
     * @param session 内置Session
     * @return 下一题
     */
    @GetMapping("/selectQuestion/{id}/{answer}")
    public List<String> selectQuestion(@PathVariable("id") int id,
                                       @PathVariable("answer") String answer,
                                       HttpSession session){
        //拿出用户
        User user = (User)session.getAttribute("user");
        //判断上一题正误
        String score = stringRedisTemplate.opsForValue().get("question:"+user.getId()+":score");
        String scores[] = score.split(StateUtil.QUES_DIVISION);
        Integer totalScore = Integer.parseInt(scores[0]);
        Integer queScore = Integer.parseInt(scores[1]);
        String queryAnswer = stringRedisTemplate.opsForValue().get("question:"+user.getId()+":answer");
        //防止前后空格影响判断
        if(answer.trim().equals(answer.trim())){
            totalScore += queScore;
        }

        //获取下一题目
        Map<Integer, ExamQuestion> questions = (Map<Integer, ExamQuestion>)session.getAttribute("questions");
        List<String> list = PaperUtil.typeAnalysis(questions.get(id).getQuestion(), questions.get(id).getSign());
        //放入答案及分数
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":answer",
                questions.get(id).getAnswer(), 60*60*2, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":score",
                ""+totalScore+StateUtil.QUES_DIVISION+questions.get(id).getScore(), 60*60*2, TimeUnit.SECONDS);
        //返回题目
        return list;
    }


}
