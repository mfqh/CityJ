package com.whut.cityj.mapper;

import com.whut.cityj.bean.SpeQuestion;
import com.whut.cityj.bean.SpecilType;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpeQuestionMapper {

    //根据书籍版本返回专项类型
    public List<SpecilType> selType(String bookVersion);

    //根据专项ID返回该专项所有题目
    @MapKey("queNumber")
    public Map<Integer, SpeQuestion> selSpeQuestions(Integer id);

}
