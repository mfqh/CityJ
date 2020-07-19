package com.whut.cityj.bean;

import java.util.List;

/**
 * 错题库
 */
public class ErrorBase {

    private Integer id;

    private Integer uid;

    private Integer aid;

    private AfcQuestion errQuestion;

    public ErrorBase() {
    }

    public ErrorBase(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public AfcQuestion getErrQuestion() {
        return errQuestion;
    }

    public void setErrQuestion(AfcQuestion errQuestion) {
        this.errQuestion = errQuestion;
    }

    @Override
    public String toString() {
        return "ErrorBase{" +
                "id=" + id +
                ", uid=" + uid +
                ", aid=" + aid +
                ", errQuestion=" + errQuestion +
                '}';
    }
}
