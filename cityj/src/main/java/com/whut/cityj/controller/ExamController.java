package com.whut.cityj.controller;

import com.whut.cityj.bean.ExamPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamController {



    /**
     * 查询所有试卷
     * @return
//     */
//    @GetMapping("/examPageList")
//    public List<ExamPaper> examPageList(){
//        return examPaperServiceImpl.getAllPaper();
//    }

    @GetMapping("/beginExam/{id}")
    public String beginExam(@PathVariable("id") int id){
        return "";
    }



}
