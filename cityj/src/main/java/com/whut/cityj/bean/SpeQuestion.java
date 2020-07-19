package com.whut.cityj.bean;

/**
 * 专项习题
 */
public class SpeQuestion {

    private Integer id;

    private Integer sid;

    private Integer queNumber;

    private String question;

    private String answer;

    private String analy;

    private Integer sign;

    public SpeQuestion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public String getAnaly() {
        return analy;
    }

    public void setAnaly(String analy) {
        this.analy = analy;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "SpeQuestion{" +
                "id=" + id +
                ", sid=" + sid +
                ", queNumber=" + queNumber +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", analy='" + analy + '\'' +
                ", sign=" + sign +
                '}';
    }
}
