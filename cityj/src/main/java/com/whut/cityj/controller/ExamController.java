package com.whut.cityj.controller;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
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
import java.time.Duration;
import java.time.LocalDateTime;
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
     * 根据试卷返回题目及个数
     * @param id 试卷编号
     * @return 返回第一道题目和题目个数（创建答题卡）
     */
    @GetMapping("/examBegin/{id}")
    public List<String> examBegin(@PathVariable("id") int id, HttpSession session){

        Map<Integer, ExamQuestion> questions = examPaperServiceImpl.getExamPaper(id);
        //将试卷及其ID存入Session，减少IO压力
        session.setAttribute("questions", questions);
        session.setAttribute("paperId",id);
        //同时在Redis中保存第一题答案，保存一场考试的时间
        User user = (User)session.getAttribute("user");
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":answer",
                questions.get(1).getAnswer(), 60*60*2, TimeUnit.SECONDS);
        //放入初始分数及第一题分数
        stringRedisTemplate.opsForValue().set("question:"+user.getId()+":score",
                "0"+StateUtil.QUES_DIVISION+questions.get(1).getScore(), 60*60*2, TimeUnit.SECONDS);
        //分析第一道题输出
        List<String> list = PaperUtil.typeAnalysis(questions.get(1).getQuestion(), questions.get(1).getSign());
        list.add(questions.size()+"");
        return list;
    }

    /**
     * 判断正误，返回指定题目
     * @param id 下一题编号
     * @param answer 用户输入的答案
     * @param session 内置Session
     * @return 下一题
     */
    @GetMapping("/examSelectQuestion/{id}/{answer}")
    public List<String> examSelectQuestion(@PathVariable("id") int id,
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
        if(answer.trim().equals(queryAnswer.trim())){
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

    /**
     * 判断当前题答案，并提交
     * @param answer 当前题答案
     * @param session 内置Session对象
     * @return 成绩统计页面
     */
    @GetMapping("/examCommit/{answer}")
    public String examCommit(@PathVariable("answer") String answer,
                             HttpSession session){
        //拿出用户
        User user = (User)session.getAttribute("user");
        //判断上一题正误
        String score = stringRedisTemplate.opsForValue().get("question:"+user.getId()+":score");
        String queryAnswer = stringRedisTemplate.opsForValue().get("question:"+user.getId()+":answer");
        String scores[] = score.split(StateUtil.QUES_DIVISION);
        Integer totalScore = Integer.parseInt(scores[0]);
        Integer queScore = Integer.parseInt(scores[1]);
        //防止前后空格影响判断
        if(answer.trim().equals(queryAnswer.trim())){
            totalScore += queScore;
        }
        //取出试卷Id
        int id = (int)session.getAttribute("paperId");
        //将成绩存入分数排行数据库
        LocalDateTime localDateTime = LocalDateTime.now();
        PaperRank paperRank = new PaperRank(id, user.getId(), totalScore, localDateTime);
        if(examPaperServiceImpl.addNewScore(paperRank)){
            //提交成功
            //设置过期时间为0
            stringRedisTemplate.expire("question:"+user.getId()+":score", Duration.ZERO);
            stringRedisTemplate.expire("question:"+user.getId()+":answer", Duration.ZERO);
            //从session中移除试卷
            session.removeAttribute("questions");
            session.removeAttribute("paperId");
            //返回成绩统计页面
            return "result";
        }
        //重新提交
        return "error";
    }

    /**
     * 根据试卷编号，来查询具体分数和使用时间
     * @param eid 试卷编号
     * @param session 内置Session对象
     * @return 用户分数状态
     */
    @GetMapping("/examScore/{eid}")
    public PaperRank examScore(@PathVariable("eid") Integer eid,
                            HttpSession session){
        //拿出用户
        User user = (User)session.getAttribute("user");
        PaperRank paperRank = new PaperRank(eid, user.getId());
        paperRank = examPaperServiceImpl.getOneScore(paperRank);
        paperRank.setUser(user);
        return paperRank;
    }







}
