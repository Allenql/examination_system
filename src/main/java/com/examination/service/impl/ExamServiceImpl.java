package com.examination.service.impl;

import com.examination.dao.ExamMapper;
import com.examination.dao.StudentMapper;
import com.examination.entity.Paper;
import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Judgedba;
import com.examination.entity.Question.Subdba;
import com.examination.entity.Record;
import com.examination.entity.Student;
import com.examination.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/1 23:25
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Judgedba getJudgedbaById_Ans(long id) {
        return examMapper.getJudgedbaById_Ans(id);
    }

    @Override
    public List<Paper> listPaperBySid(long sid) {
        Student student = studentMapper.getStudentById(sid);
        String classid = student.getClassid();
        return examMapper.listPaperByClass(classid);
    }

    @Override
    public Choicedba getChoicedbaById_noAns(long id) {
        return examMapper.findChoicedbaById_noAns(id);
    }

    @Override
    public Judgedba getJudgedbaById_noAns(Long id) {
        return examMapper.findJudgedbaById_noAns(id);
    }

    @Override
    public Subdba getSubdbaById_noAns(Long id) {
        return examMapper.findSubdbaById_noAns(id);
    }

    @Override
    public Boolean paperTested(long sid, long pid) {
        return examMapper.paperTested(sid,pid) == 0 ? false : true;
    }

    @Override
    public Paper getPaperById(long id) {
        return examMapper.findPaperById(id);
    }

    @Override
    public String submitPapre(long sid, long pid, String record) {
        System.out.println("-------执行交卷服务--------------");
        Record r = new Record();
        r.setSid(sid);
        r.setPid(pid);
        r.setRecord(record);
        try{
            examMapper.submit(r);
            return "提交成功。\n请携带随身物品离开考场。";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "请勿重复提交试卷。\n以第一次提交为准。";
        }
    }

    @Override
    public  List<Map> listGradeBySid(long sid){
        return examMapper.listGradeBySid(sid);
    }

}
