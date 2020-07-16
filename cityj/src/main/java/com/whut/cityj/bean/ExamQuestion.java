package com.whut.cityj.bean;

/**
 * 试卷题目
 */
public class ExamQuestion {

    private Integer id;

    private Integer eid;

    private Integer queNumber;

    private String question;

    private String answer;

    private Integer sign;

    private Integer score;

    public ExamQuestion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getQueNumber() {
        return queNumber;
    }

    public void setQueNumber(Integer queNumber) {
        this.queNumber = queNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ExamQuestion{" +
                "id=" + id +
                ", eid=" + eid +
                ", queNumber=" + queNumber +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", sign=" + sign +
                ", score=" + score +
                '}';
    }
}
