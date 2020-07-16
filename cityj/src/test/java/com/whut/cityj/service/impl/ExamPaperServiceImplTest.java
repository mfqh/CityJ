package com.whut.cityj.service.impl;

import com.whut.cityj.mapper.ExamPaperMapper;
import com.whut.cityj.service.ExamPaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamPaperServiceImplTest {

    @Autowired
    ExamPaperMapper examPaperMapper;

    @Autowired
    ExamPaperService examPagerServiceImpl;

    @Test
    public void testSelAll(){
        System.err.println(examPagerServiceImpl);
        System.err.println(examPagerServiceImpl.getAllPaper());
        System.err.println(examPagerServiceImpl.getExamPaper(1));
    }

    @Test
    public void testMapper(){
        System.err.println(examPaperMapper);
//        System.err.println(examPaperMapper.selAllPaper());
        System.out.println(examPaperMapper.selAllQuestion(1));

        System.err.println(examPaperMapper.selOnePaper(1));

    }

}