package com.examination.dao;

import java.util.List;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/12/3 0:35
 */
public interface ChartMapper {
    List<String> getPaperName(long studentId);

    List<Byte> getScore(long studentId);

    List<Byte> getScoreByPId(long pId);
}
