package com.whut.cityj.mapper;

import com.whut.cityj.bean.AfcQuestion;
import com.whut.cityj.bean.Chapter;
import com.whut.cityj.bean.ErrorBase;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AfcQuestionMapper {

    //根据选中的课本版本返回所有章节
    public List<Chapter> selChapter(String bookVersion);

    //根据章节ID返回该章节所有题目
    @MapKey("queNumber")
    public Map<Integer, AfcQuestion> selChapterQuestions(Integer id);

    //根据题目id，将错题入库
    public Boolean insErrorQuestion(ErrorBase errorBase);

}
