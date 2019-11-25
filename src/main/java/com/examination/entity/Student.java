package com.examination.entity;

import lombok.Data;

@Data
public class Student extends User {
    long id;
    String account;
    String name;
    String sex;
    String password="123456";
    String classid;

    public Student() {
    }

    public Student(String account, String name, String classid) {
        this.account = account;
        this.name = name;
        this.classid = classid;
    }

}
