package com.whut.cityj.service;

import com.whut.cityj.bean.AfcQuestion;
import com.whut.cityj.bean.Chapter;
import com.whut.cityj.bean.ErrorBase;

import java.util.List;
import java.util.Map;

public interface AfcQuestionService {

    public List<Chapter> getAllChapter(String bookVersion);

    public Map<Integer, AfcQuestion> getChapterQuesions(Integer id);

    public Boolean addErrorQuestion(ErrorBase errorBase);
}
