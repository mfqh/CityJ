package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
import com.whut.cityj.mapper.ExamPaperMapper;
import com.whut.cityj.service.ExamPaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@SpringBootTest
class ExamPaperServiceImplTest {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Autowired
    ExamPaperService examPagerServiceImpl;

    @Test
    public void testSelAll(){
//        System.err.println(examPagerServiceImpl);
//        System.err.println(examPagerServiceImpl.getAllPaper());
//        System.err.println(examPagerServiceImpl.getExamPaper(1));
//        System.err.println(examPagerServiceImpl.delPaper(1));
    }

    @Test
    public void testMapper(){
        System.err.println(examPaperMapper);
//        System.err.println(examPaperMapper.selAllPaper());
//        System.out.println(examPaperMapper.selAllQuestion(1));
//
//        System.err.println(examPaperMapper.selOnePaper(1));
//        examPaperMapper.insUserScore(new PaperRank(1, 1 , 80, LocalDateTime.now()));
//        System.err.println(examPaperMapper.selOneScore(new PaperRank(1,1)));
//        System.err.println(examPaperMapper.delExamPaper(1));
//        System.err.println(examPaperMapper.delExamQuestion(1));
//        ExamPaper paper = new ExamPaper("测试", LocalDateTime.now());
//        System.err.println(examPaperMapper.insNewPaper(paper));
//        System.err.println(paper.getId());
//        System.err.println(examPaperMapper.insNewQuestion(new ExamQuestion(1,2," "," ",0,2)));
    }

}