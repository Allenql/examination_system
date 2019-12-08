package com.examination.service.impl;

import com.examination.dao.*;
import com.examination.entity.*;
import com.examination.service.TeacherService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private PaperMapper paperMapper;

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

    @Override
    public List<SubjectQuestion> getSubjectQuestionList(Page page) {
        int currentPage = page.getCurrentPage();
        int pageNumber = page.getPageNumber();
        return subjectMapper.getSubjectQuestionList((currentPage - 1) * pageNumber, pageNumber);
    }

    @Override
    public int addSubjectQuestionByExcel(InputStream inputStream) {
        List<SubjectQuestion> subjectQuestions = new ArrayList<SubjectQuestion>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String question = formatter.formatCellValue(row.getCell(0));
                String refanswer = formatter.formatCellValue(row.getCell(1));
                SubjectQuestion subjectQuestion = new SubjectQuestion(question,refanswer);
                subjectQuestions.add(subjectQuestion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return subjectMapper.addByList(subjectQuestions);
    }

    @Override
    public boolean deleteSubjectQuestionById(long id) {
        return subjectMapper.deleteSubjectQuestionById(id) > 0 ? true : false;
    }

    @Override
    public boolean updateSubjectQuestion(SubjectQuestion subjectQuestion) {
        return subjectMapper.updateSubjectQuestion(subjectQuestion) > 0 ? true : false;
    }

    @Override
    public List<Map> listPaper(long tid){
        return examMapper.listPaper(tid);
    }

    @Override
    public Paper getPaper(long pid) {
        return examMapper.getPaper(pid);
    }


    public List<ChoiceQuestion> choiceList(int currentPage, int pageNumber) {

        return choiceMapper.choiceList(currentPage,pageNumber);
    }

    @Override
    public int getCount() {
        return choiceMapper.getCount();
    }

    @Override
    public boolean updateChoiceQuestion(ChoiceQuestion choiceQuestion) {
        return choiceMapper.updateChoiceQuestion(choiceQuestion);
    }

    @Override
    public boolean deleteChoiceQuestion(List<String> list) {
        return choiceMapper.deleteChoiceQuestion(list);
    }

}
