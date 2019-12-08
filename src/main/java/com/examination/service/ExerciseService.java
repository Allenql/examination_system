package com.examination.service;

import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Evadba;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/3 23:48
 */
public interface ExerciseService {
    List<Map> getEvadbaByType(String find);

    Evadba getEvadbaById_noAns(long id);

    public Choicedba getChoicedbaById_Ans (Long id);
}
