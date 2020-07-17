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

import java.util.List;
import java.util.Map;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ExamPaper> getAllPaper() {
        return examPaperMapper.selAllPaper();
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
