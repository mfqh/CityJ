package com.whut.cityj.service.impl;

import com.whut.cityj.service.SpeQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpeQuestionServiceImplTest {

    @Autowired
    private SpeQuestionService speQuestionServiceImpl;

    @Test
    public void testService(){
//        System.err.println(speQuestionServiceImpl.getALlType("人教版$一年级上册"));
//        System.err.println(speQuestionServiceImpl.getAllQuestions(1));
    }

}