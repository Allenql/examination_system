package com.examination.dao;

import com.examination.entity.JudgeQuestion;

import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:54
 */
public interface JudgeMapper {
    int addByList(List<JudgeQuestion> JudgeQuestions);

    List<JudgeQuestion> getJudgeQuestionList(int currentPage, int pageNumber);

    int getCount();

    int deleteJudgeQuestionById(long id);

    int updateJudgeQuestion(JudgeQuestion judgeQuestion);
}
