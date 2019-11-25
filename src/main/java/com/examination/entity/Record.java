package com.examination.entity;

import lombok.Data;

@Data
public class Record {
    long sid;
    long pid;
    String record;
    String status;
    long score;

}
