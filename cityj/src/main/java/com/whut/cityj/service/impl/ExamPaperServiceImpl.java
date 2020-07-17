package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
import com.whut.cityj.mapper.ExamPaperMapper;
import com.whut.cityj.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    public List<ExamPaper> getAllPaper() {
        return examPaperMapper.selAllPaper();
    }

    @Override
    public Map<Integer,ExamQuestion> getExamPaper(int id) {
        return examPaperMapper.selAllQuestion(id);
    }

    @Override
    public PaperRank getOneScore(PaperRank paperRank) {
        return examPaperMapper.selOneScore(paperRank);
    }


}
