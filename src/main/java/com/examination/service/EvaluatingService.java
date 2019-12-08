package com.examination.service;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/4 0:24
 */
public interface EvaluatingService {
    void EvaluateSQL(long uid, long eid, String sql2);

    public List<Map> get_Status(long off, long n);
}
