package com.examination.service.impl;

import com.examination.dao.JudgeMapper;
import com.examination.dao.SubjectMapper;
import com.examination.entity.Page;
import com.examination.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :zql
 * @description :Allen自学
 * @date :2019/11/27 19:56
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private JudgeMapper judgeMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    Page page = new Page();

    @Override
    public Page getPage(String cp, String type) {
        page = new Page();
        int pageNumber = page.getPageNumber();
        int currentPage = cp != null ? Integer.parseInt(cp) : 1;
        int count = 0;
        if ("choice".equals(type)) {
            //count = choiceDao.getCount();
        } else if ("judge".equals(type)) {
            count = judgeMapper.getCount();
        } else if ("subject".equals(type)) {
            count = subjectMapper.getCount();
        }
        int totalPage = count % pageNumber == 0 ? count / pageNumber : count / pageNumber + 1;

        page.setCount(count);
        page.setCurrentPage(currentPage);
        page.setTotalPage(totalPage);
        return page;
    }
}
