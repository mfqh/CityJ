package com.whut.cityj.service;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ExamPaperService {

    public List<ExamPaper> getAllPaper();

    public Map<Integer,ExamQuestion> getExamPaper(int id);

    public PaperRank getOneScore(PaperRank paperRank);

    public Boolean addNewScore(PaperRank paperRank);

    public Boolean delPaper(int id);

    public Integer addNewPaper(ExamPaper examPaper);

    public Boolean addNewQuestion(ExamQuestion examQuestion);

}
