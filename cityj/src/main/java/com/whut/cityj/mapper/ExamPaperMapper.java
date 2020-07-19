package com.whut.cityj.mapper;

import com.whut.cityj.bean.ExamPaper;
import com.whut.cityj.bean.ExamQuestion;
import com.whut.cityj.bean.PaperRank;
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

      //查询某一试卷
      public ExamPaper selOnePaper(int id);

      //将排行信息存入试卷
      public Boolean insUserScore(PaperRank paperRank);

      //根据uid，eid查询分数信息
      public PaperRank selOneScore(PaperRank paperRank);

      //删除试卷信息
      public Boolean delExamPaper(int eid);
      //删除试卷题目
      public Boolean delExamQuestion(int eid);

      //插入一张新的试卷
      public Boolean insNewPaper(ExamPaper examPaper);

      //向试卷中插入题目
      public Boolean insNewQuestion(ExamQuestion examQuestion);

      //修改试卷信息
      public Boolean upPaperMessage(ExamPaper examPaper);
}
