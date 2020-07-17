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

}
