package com.whut.cityj.bean;

public class User {

    private int id;

    private String name;

    private String password;

    private Integer testCount;

    private Integer averScore;

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    public Integer getAverScore() {
        return averScore;
    }

    public void setAverScore(Integer averScore) {
        this.averScore = averScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", testCount=" + testCount +
                ", averScore=" + averScore +
                '}';
    }
}