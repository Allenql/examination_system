package com.examination.entity;

import lombok.Data;

@Data
public class Admin extends User {
    long id;
    String account;
    String password;
    String name;

    final String permission = "Admin";

}
