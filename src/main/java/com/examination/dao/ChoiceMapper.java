package com.examination.dao;

import com.examination.entity.ChoiceQuestion;
import com.examination.entity.JudgeQuestion;

import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:54
 */
public interface ChoiceMapper {
    int addByList(List<ChoiceQuestion> choiceQuestions);

}
