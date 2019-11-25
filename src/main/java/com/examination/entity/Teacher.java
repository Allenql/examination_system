package com.examination.entity;

import lombok.Data;

@Data
public class Teacher extends User {
    long id;
    String account;
    String password="123456";
    String name;
    final String permission = "Teacher";

    public Teacher(){}
    public Teacher(String account, String name) {
        this.account = account;
        this.name = name;
    }


}
