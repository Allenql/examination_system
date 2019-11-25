package com.examination.entity;

import lombok.Data;

@Data
public class Status {
    long runid;
    long eid;
    long uid;
    String time;
    String sql2;
    String status;

}
