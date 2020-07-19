package com.whut.cityj.service.impl;

import com.whut.cityj.bean.SpeQuestion;
import com.whut.cityj.bean.SpecilType;
import com.whut.cityj.mapper.SpeQuestionMapper;
import com.whut.cityj.service.SpeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpeQuestionServiceImpl implements SpeQuestionService {

    @Autowired
    private SpeQuestionMapper speQuestionMapper;

    @Override
    public List<SpecilType> getALlType(String book_version) {
        return speQuestionMapper.selType(book_version);
    }

    @Override
    public Map<Integer, SpeQuestion> getAllQuestions(Integer id) {
        return speQuestionMapper.selSpeQuestions(id);
    }

}
