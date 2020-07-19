package com.whut.cityj.service;

import com.whut.cityj.bean.SpeQuestion;
import com.whut.cityj.bean.SpecilType;

import java.util.List;
import java.util.Map;

public interface SpeQuestionService {

    public List<SpecilType> getALlType(String book_version);

    public Map<Integer, SpeQuestion> getAllQuestions(Integer id);
}
