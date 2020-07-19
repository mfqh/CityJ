package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
import com.whut.cityj.mapper.ExamPaperMapper;
import com.whut.cityj.service.ExamPaperService;
import com.whut.cityj.util.StateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    @Transactional()
    public List<ExamPaper> getAllPaper() {
        List<ExamPaper>  papers = examPaperMapper.selAllPaper();
        //根据时间修改试卷状态：未到时间，已过时
        for (ExamPaper paper : papers) {
            if(paper.getSign() == 0){
                //当前时间超过试卷答题开始时间后两小时
                if(LocalDateTime.now().isAfter(paper.getDate().plusHours(2))){
                    //修改考试状态
                    paper.setSign(1);
                    //修改数据库数据
                    examPaperMapper.upPaperMessage(paper);
                }
            }
        }
        return papers;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Integer,ExamQuestion> getExamPaper(int id) {
        return examPaperMapper.selAllQuestion(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaperRank getOneScore(PaperRank paperRank) {
        return examPaperMapper.selOneScore(paperRank);
    }

    @Override
    public Boolean addNewScore(PaperRank paperRank) {
        return examPaperMapper.insUserScore(paperRank);
    }

    @Override
    @Transactional
    public Boolean delPaper(int id) {
        //删除试卷
        Boolean paSign = examPaperMapper.delExamPaper(id);
        //删除试卷对应的题目
        Boolean quSign = examPaperMapper.delExamQuestion(id);

        if(paSign && quSign){
            return true;
        }
        return false;
    }

    @Override
    public Integer addNewPaper(ExamPaper examPaper) {
        Boolean sign = examPaperMapper.insNewPaper(examPaper);
        if(sign){
            return examPaper.getId();
        }
        return StateUtil.FAIL_STATE;
    }

    @Override
    public Boolean addNewQuestion(ExamQuestion examQuestion) {
        return examPaperMapper.insNewQuestion(examQuestion);
    }


}
