package com.whut.cityj.service;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ExamPaperService {

    public List<ExamPaper> getAllPaper();

    public Map<Integer,ExamQuestion> getExamPaper(int id);


}
