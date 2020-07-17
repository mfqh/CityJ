package com.whut.cityj.bean;

import java.time.LocalDateTime;

public class PaperRank {

    private Integer eid;

    private Integer uid;

    private Integer score;

    private LocalDateTime time;

    private User user;

    private ExamPaper examPaper;

    public PaperRank() {
    }

    public PaperRank(Integer eid, Integer uid) {
        this.eid = eid;
        this.uid = uid;
    }

    public PaperRank(Integer eid, Integer uid, Integer score, LocalDateTime time) {
        this.eid = eid;
        this.uid = uid;
        this.score = score;
        this.time = time;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }


    @Override
    public String toString() {
        return "PaperRank{" +
                "eid=" + eid +
                ", uid=" + uid +
                ", score=" + score +
                ", time=" + time +
                ", user=" + user +
                ", examPaper=" + examPaper +
                '}';
    }
}
