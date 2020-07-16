package com.whut.cityj.bean;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * 试卷
 */
public class ExamPaper {

    private Integer id;

    private String name;

    private LocalDateTime date;

    private Integer sign;

    //题号--题目
    private HashMap<Integer, ExamQuestion> questions;

    public ExamPaper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public ExamQuestion getQuestion(int id) {
        return questions.get(id);
    }

    public void addQuestion(int id, ExamQuestion questions) {
        if(this.questions == null){
            this.questions = new HashMap<Integer, ExamQuestion>();
        }
        this.questions.put(id, questions);
    }

    @Override
    public String toString() {
        return "ExamPaper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", sign=" + sign +
                ", questions=" + questions +
                '}';
    }
}
