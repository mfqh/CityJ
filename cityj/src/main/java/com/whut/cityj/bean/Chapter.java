package com.whut.cityj.bean;

/**
 * 章节
 */
public class Chapter {

    private int id;

    private String name;

    private String bookVersion;

    public Chapter() {
    }

    public Chapter(String name, String bookVersion) {
        this.name = name;
        this.bookVersion = bookVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", bookVersion='" + bookVersion + '\'' +
                '}';
    }
}
