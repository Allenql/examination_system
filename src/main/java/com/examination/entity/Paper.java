package com.examination.entity;

import lombok.Data;

@Data
public class Paper {
    private Long id;
    private String name;
    private String begintime;
    private String finishtime;
    private String choi;
    private String judg;
    private String sub;

    private String choiscore;
    private String judgscore;
    private String subscore;

    private long tid;
    private String classid;

    public Paper() {
    }

    public Paper(String name, String begintime, String finishtime, String choi, String judg, String sub, String choiscore, String judgscore, String subscore, long tid, String classid) {
        this.name = name;
        this.begintime = begintime;
        this.finishtime = finishtime;
        this.choi = choi;
        this.judg = judg;
        this.sub = sub;
        this.choiscore = choiscore;
        this.judgscore = judgscore;
        this.subscore = subscore;
        this.tid = tid;
        this.classid = classid;
    }
}
