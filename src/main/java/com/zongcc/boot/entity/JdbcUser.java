package com.zongcc.boot.entity;


import java.io.Serializable;

public class JdbcUser implements Serializable{

    private Long id;
    private String userName;

    private Integer age;

    public JdbcUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JdbcUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
