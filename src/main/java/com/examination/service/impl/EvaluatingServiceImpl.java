package com.examination.service.impl;

import com.examination.dao.EvaluateMapper;
import com.examination.dao.ExerciseMapper;
import com.examination.entity.Question.Evadba;
import com.examination.entity.Status;
import com.examination.service.CompareSQLService;
import com.examination.service.EvaluatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/4 0:25
 */
@Service
public class EvaluatingServiceImpl implements EvaluatingService {
    @Autowired
    private EvaluateMapper evaluateMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Autowired
    private CompareSQLService compareSQLService;
    @Override
    public void EvaluateSQL(long uid, long eid, String sql2) {
        System.out.println("********执行评测服务*********");

        Status status = new Status();
        status.setUid(uid);
        status.setEid(eid);
        status.setSql2(sql2);
        evaluateMapper.submit(status);

        Evadba evadba = exerciseMapper.findEvadbaById_Ans(eid);
        String sql1 = evadba.getRightanswer();
        String type = evadba.getType();
        String[] tables = evadba.getIntable().split(",");
        String result ;

        System.out.println(type);
        try{
            Boolean bool;
            if (type.equals("查找题")){
                bool = compareSQLService.compareRS(sql1,sql2);
            }else{
                bool = compareSQLService.compareChange(tables,sql1,sql2);
            }
            result = bool ? "答案正确，你真棒！": "答案错误，你真笨!";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = "SQL语句出错："+e.getMessage();
        }
        status.setStatus(result);
        evaluateMapper.update(status);
    }

    @Override
    public List<Map> get_Status(long off, long n) {
        return evaluateMapper.getList(off, n);
    }
}
