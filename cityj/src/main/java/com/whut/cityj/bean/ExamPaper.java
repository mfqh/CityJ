package com.whut.cityj.bean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 试卷
 */
public class ExamPaper {

    private Integer id;

    private String name;

    private LocalDateTime date;

    private Integer sign;


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


    @Override
    public String toString() {
        return "ExamPaper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", sign=" + sign +
                '}';
    }
}
