package com.examination.service;

import com.examination.entity.JudgeQuestion;
import com.examination.entity.Page;

import java.io.InputStream;
import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:45
 */
public interface TeacherService {
    int addJudgeQuestionByExcel(InputStream inputStream);

    int addChoiceQuestionByExcel(InputStream inputStream);

    List<JudgeQuestion> getJudgeQuestionList(Page page);

}
