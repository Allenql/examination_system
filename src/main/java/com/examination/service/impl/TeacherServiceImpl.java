package com.examination.service.impl;

import com.examination.dao.ChoiceMapper;
import com.examination.dao.JudgeMapper;
import com.examination.entity.ChoiceQuestion;
import com.examination.entity.JudgeQuestion;
import com.examination.entity.Page;
import com.examination.service.TeacherService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/26 22:45
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private JudgeMapper judgeMapper;

    @Autowired
    private ChoiceMapper choiceMapper;

    @Override
    public int addJudgeQuestionByExcel(InputStream inputStream) {
        List<JudgeQuestion> judgeQuestions = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String question = formatter.formatCellValue(row.getCell(0));
                String rightAnswer = formatter.formatCellValue(row.getCell(1));
                JudgeQuestion judgeQuestion = new JudgeQuestion(question,rightAnswer);
                judgeQuestions.add(judgeQuestion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return judgeMapper.addByList(judgeQuestions);
    }

    @Override
    public int addChoiceQuestionByExcel(InputStream inputStream) {
        List<ChoiceQuestion> choiceQuestions = new ArrayList<ChoiceQuestion>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String question = formatter.formatCellValue(row.getCell(0));
                String option1 = formatter.formatCellValue(row.getCell(1));
                String option2 = formatter.formatCellValue(row.getCell(2));
                String option3 = formatter.formatCellValue(row.getCell(3));
                String option4 = formatter.formatCellValue(row.getCell(4));
                String rightAnswer = formatter.formatCellValue(row.getCell(5));
                ChoiceQuestion choiceQuestion = new ChoiceQuestion(question,option1,option2,option3,option4,rightAnswer);
                choiceQuestions.add(choiceQuestion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return choiceMapper.addByList(choiceQuestions);
    }

    @Override
    public List<JudgeQuestion> getJudgeQuestionList(Page page) {
        int currentPage = page.getCurrentPage();
        int pageNumber = page.getPageNumber();
        return judgeMapper.getJudgeQuestionList((currentPage - 1) * pageNumber, pageNumber);
    }

    @Override
    public boolean deleteJudgeQuestionById(long id) {
        return judgeMapper.deleteJudgeQuestionById(id) > 0 ? true : false;
    }

    @Override
    public boolean updateJudgeQuestion(JudgeQuestion judgeQuestion) {
        return judgeMapper.updateJudgeQuestion(judgeQuestion) > 0 ? true : false;
    }
}
