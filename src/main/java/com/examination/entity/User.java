package com.examination.entity;

import lombok.Data;

@Data
public class User {
    long id;
    String account;
    String password;
    String name;
    String permission = "User";
}
