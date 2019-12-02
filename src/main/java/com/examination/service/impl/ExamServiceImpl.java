package com.examination.service.impl;

import com.examination.dao.ExamMapper;
import com.examination.entity.Question.Judgedba;
import com.examination.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/1 23:25
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public Judgedba getJudgedbaById_Ans(long id) {
        return examMapper.getJudgedbaById_Ans(id);
    }
}
