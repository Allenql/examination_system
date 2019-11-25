package com.examination.entity;

import lombok.Data;

@Data
public class ChoiceQuestion {
    long id;
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    String rightAnswer;
    byte secrecy;
    short chapters = 1;

    public ChoiceQuestion(String question, String option1, String option2, String option3, String option4, String rightAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.rightAnswer = rightAnswer;
    }

    public ChoiceQuestion() {
    }

}
