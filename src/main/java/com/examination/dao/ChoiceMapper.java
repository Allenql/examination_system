package com.examination.dao;

import com.examination.entity.ChoiceQuestion;
import com.examination.entity.JudgeQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:54
 */
public interface ChoiceMapper {
    int addByList(List<ChoiceQuestion> choiceQuestions);

    List<ChoiceQuestion> choiceList(int currentPage, int pageNumber);

    int getCount();

    boolean updateChoiceQuestion(ChoiceQuestion choiceQuestion);

    boolean deleteChoiceQuestion(List<String> list);
}
