package com.examination.dao;

import com.examination.entity.Paper;
import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Judgedba;
import com.examination.entity.Question.Subdba;
import com.examination.entity.Record;

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

    List<Paper> listPaperByClass(String classid);

    Choicedba findChoicedbaById_noAns(long id);

    Judgedba findJudgedbaById_noAns(Long id);

    Subdba findSubdbaById_noAns(Long id);

    int paperTested(long sid, long pid);

    Paper findPaperById(long id);

    void submit(Record r);

    List<Map> listGradeBySid(long sid);
}
