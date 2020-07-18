package com.whut.cityj.bean;

/**
 * 章节
 */
public class Chapter {

    private int id;

    private Integer number;

    private String bookVersion;

    public Chapter() {
    }

    public Chapter(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", number=" + number +
                ", bookVersion='" + bookVersion + '\'' +
                '}';
    }
}
