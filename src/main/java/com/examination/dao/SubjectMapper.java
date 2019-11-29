package com.examination.dao;

import com.examination.entity.JudgeQuestion;
import com.examination.entity.SubjectQuestion;

import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/29 22:43
 */
public interface SubjectMapper {
    int getCount();

    List<SubjectQuestion> getSubjectQuestionList(int currentPage, int pageNumber);

    int addByList(List<SubjectQuestion> subjectQuestions);

    int deleteSubjectQuestionById(long id);

    int updateSubjectQuestion(SubjectQuestion subjectQuestion);
}
