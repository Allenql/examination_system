package com.examination.dao;

import com.examination.entity.Paper;
import com.examination.entity.Question.Judgedba;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/30 0:13
 */
public interface ExamMapper {
    List<Map> listPaper(long tid);

    Paper getPaper(long pid);

    Judgedba getJudgedbaById_Ans(long id);
}
