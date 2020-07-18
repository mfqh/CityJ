package com.whut.cityj.service.impl;

import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.service.AfcQuestionService;
import com.whut.cityj.util.StateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AfcQuestionServiceImplTest {

    @Autowired
    AfcQuestionService afcQuestionServiceImpl;

    @Test
    public void testServiceImpl(){

//        System.err.println(afcQuestionServiceImpl.getAllChapter("人教版"+ StateUtil.QUES_DIVISION+"一年级上册"));
//        System.err.println(afcQuestionServiceImpl.getChapterQuesions(1));
//        System.err.println(afcQuestionServiceImpl.addErrorQuestion(new ErrorBase(1,1)));
    }


}