package com.examination.service;

import com.examination.entity.JudgeQuestion;
import com.examination.entity.Page;
import com.examination.entity.Paper;
import com.examination.entity.SubjectQuestion;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:45
 */
public interface TeacherService {
    int addJudgeQuestionByExcel(InputStream inputStream);

    int addChoiceQuestionByExcel(InputStream inputStream);

    List<JudgeQuestion> getJudgeQuestionList(Page page);

    boolean deleteJudgeQuestionById(long id);

    boolean updateJudgeQuestion(JudgeQuestion judgeQuestion);

    List<SubjectQuestion> getSubjectQuestionList(Page page);

    int addSubjectQuestionByExcel(InputStream inputStream);

    boolean deleteSubjectQuestionById(long id);

    boolean updateSubjectQuestion(SubjectQuestion subjectQuestion);

    List<Map> listPaper(long tid);

    Paper getPaper(long pid);

}
