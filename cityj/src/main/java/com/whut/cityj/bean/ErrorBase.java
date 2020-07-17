package com.whut.cityj.bean;

/**
 * 错题库
 */
public class ErrorBase {

    private Integer id;

    private Integer uid;

    private Integer aid;

    public ErrorBase() {
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

    @Override
    public String toString() {
        return "ErrorBase{" +
                "id=" + id +
                ", uid=" + uid +
                ", aid=" + aid +
                '}';
    }
}
