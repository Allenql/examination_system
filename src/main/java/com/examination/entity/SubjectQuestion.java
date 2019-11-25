package com.examination.entity;

import lombok.Data;

@Data
public class SubjectQuestion {
    long id;
    String question;
    String refAnswer;
    byte secrecy;

    public SubjectQuestion() {
    }

    public SubjectQuestion(String question, String refAnswer) {
        this.question = question;
        this.refAnswer = refAnswer;
    }

}
