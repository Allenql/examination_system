package com.examination.entity;

import lombok.Data;

@Data
public class JudgeQuestion {
    long id;
    String question;
    String rightAnswer;
    byte secrecy;

    public JudgeQuestion() {
    }

    public JudgeQuestion(String question, String rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

}
