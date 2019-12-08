package com.examination.service.impl;

import com.examination.dao.ExerciseMapper;
import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Evadba;
import com.examination.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/3 23:49
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Override
    public List<Map> getEvadbaByType(String type) {
        return exerciseMapper.findEvadbaByType(type);
    }

    @Override
    public Evadba getEvadbaById_noAns(long id) {
        return exerciseMapper.findEvadbaById_noAns(id);
    }

    @Override
    public Choicedba getChoicedbaById_Ans(Long id) {
        return exerciseMapper.findChoicedbaById_Ans(id);
    }
}
