package com.whut.cityj.mapper;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamPaperMapper {

      //查询所有试卷
      public List<ExamPaper> selAllPaper();

      //查询某一试卷所有题目
      @MapKey("queNumber")
      public Map<Integer, ExamQuestion> selAllQuestion(int id);

      //查询某一试卷及其所有题目

}
