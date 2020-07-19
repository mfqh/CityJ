package com.whut.cityj.bean;

/**
 * 专项类型
 */
public class SpecilType {

    private int id;

    private String type;

    private String bookVersion;

    public SpecilType() {
    }

    public SpecilType(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    @Override
    public String toString() {
        return "SpecilType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bookVersion='" + bookVersion + '\'' +
                '}';
    }
}
