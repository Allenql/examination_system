package com.examination.dao;

import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Evadba;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/3 23:51
 */
public interface ExerciseMapper {
    List<Map> findEvadbaByType(String type);

    Evadba findEvadbaById_noAns(long id);

    Evadba findEvadbaById_Ans(long eid);

    Choicedba findChoicedbaById_Ans(@Param("id") Long id);

}
