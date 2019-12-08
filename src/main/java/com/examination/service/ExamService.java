package com.examination.service;

import com.examination.entity.Paper;
import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Judgedba;
import com.examination.entity.Question.Subdba;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/1 23:25
 */
public interface ExamService {

    Judgedba getJudgedbaById_Ans(long id);

    List<Paper> listPaperBySid(long sid);

    Choicedba getChoicedbaById_noAns(long id);

    Judgedba getJudgedbaById_noAns(Long id);

    public Subdba getSubdbaById_noAns (Long id);

    Boolean paperTested(long sid, long pid);

    Paper getPaperById(long id);

    String submitPapre(long uid, long pid, String ans);

    List<Map> listGradeBySid(long uid);
}
