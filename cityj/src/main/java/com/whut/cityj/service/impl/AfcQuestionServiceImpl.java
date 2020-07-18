package com.whut.cityj.service.impl;

import com.whut.cityj.bean.AfcQuestion;
import com.whut.cityj.bean.Chapter;
import com.whut.cityj.bean.ErrorBase;
import com.whut.cityj.mapper.AfcQuestionMapper;
import com.whut.cityj.service.AfcQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AfcQuestionServiceImpl implements AfcQuestionService {

    @Autowired
    AfcQuestionMapper afcQuestionMapper;

    @Override
    public List<Chapter> getAllChapter(String bookVersion) {
        return afcQuestionMapper.selChapter(bookVersion);
    }

    @Override
    public Map<Integer, AfcQuestion> getChapterQuesions(Integer id) {
        return afcQuestionMapper.selChapterQuestions(id);
    }

    @Override
    public Boolean addErrorQuestion(ErrorBase errorBase) {
        return  afcQuestionMapper.insErrorQuestion(errorBase);
    }
}
