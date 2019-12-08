package com.examination.dao;

import com.examination.entity.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/4 0:27
 */
public interface EvaluateMapper {
    void submit(Status status);

    void update(Status status);

    List<Map> getList(@Param("off") long off, @Param("n") long n);

}
