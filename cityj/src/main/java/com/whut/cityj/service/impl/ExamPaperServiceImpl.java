package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.mapper.ExamPaperMapper;
import com.whut.cityj.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    public List<ExamPaper> getAllPaper() {
        return examPaperMapper.selAllPaper();
    }

//    @Override
//    public ExamPaper getExamPaper(int id) {
//        return examPaperMapper.selOneExamPaper(id);
//    }





}
