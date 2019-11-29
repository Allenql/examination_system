package com.examination.controller;


import com.examination.entity.JudgeQuestion;
import com.examination.entity.Page;
import com.examination.service.PageService;
import com.examination.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    final static String path = "teacher/";

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PageService pageService;

    @RequestMapping(value = "")
    String index(Model model, HttpSession httpSession) {
        return "redirect:" + path + "teacherpage";
    }

    @RequestMapping(value = "teacherpage")
    String teacherPage(Model model, HttpSession httpSession) {
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("permission", httpSession.getAttribute("permission"));
        return path + "teacherpage";
    }

    @RequestMapping("teacher_info.html")
    public String teacherInfo() {
        return path + "teacher_info";
    }


    @RequestMapping("question_list.html")
    public String questionList() {
        return path + "question_list";
    }

    /**
     * 获取判断题 -- 分页查询
     *
     * @param model
     * @param request
     * @return
     * @Author zql
     */
    @RequestMapping("truefalse_list.html")
    public String trueFalseList(Model model, HttpServletRequest request) {
        //获取分页对象 用于计算总页数
        Page page = pageService.getPage(request.getParameter("currentPage"), "judge");
        //存入model
        model.addAttribute("page", page);
        //根据当前页获取判断题列表
        List<JudgeQuestion> judgeQuestionList = teacherService.getJudgeQuestionList(page);
        //存入model
        model.addAttribute("judgeQuestuon", judgeQuestionList);
        return path + "truefalse_list";
    }

    /**
     * 选择题下载
     *
     * @param res
     */
    @RequestMapping("choice/download")
    public void choiceDownload(HttpServletResponse res) {
        res.setHeader("Content-Disposition", "attachment; filename=choice_template.xlsx");
        res.setContentType("application/octet-stream; charset=utf-8");
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("templates/excel/choice_template.xlsx");
            FileCopyUtils.copy(is, res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("choice/upload")
    @ResponseBody
    public boolean choiceUpload(@RequestParam(value = "file") MultipartFile file) {
        try {
            return teacherService.addChoiceQuestionByExcel(file.getInputStream()) != 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断题模板下载
     *
     * @param res
     * @Author zql
     */
    @RequestMapping("judge/download")
    public void judgeDownload(HttpServletResponse res) {
        res.setHeader("Content-Disposition", "attachment; filename=judge_template.xlsx");
        res.setContentType("application/octet-stream; charset=utf-8");
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("templates/excel/judge_template.xlsx");
            FileCopyUtils.copy(is, res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断题上传
     *
     * @param file
     * @Author zql
     */
    @RequestMapping("judge/upload")
    @ResponseBody
    public boolean judgeUpload(@RequestParam(value = "file") MultipartFile file) {
        try {
            return teacherService.addJudgeQuestionByExcel(file.getInputStream()) != 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据id删除判断题
     *
     * @param id
     * @return
     * @Author zql
     */
    @RequestMapping("judge/delete")
    @ResponseBody
    public boolean deleteJudgeQuestion(String id) {
        System.out.println("judge_id = " + id);
        long judgeid = 0;
        if (null != id) {
            //数据类型转换
            judgeid = Long.parseLong(id);
        }
        return teacherService.deleteJudgeQuestionById(judgeid);
    }

    /**
     * 批量删除判断题
     *
     * @param list
     * @return
     * @Author zql
     */
    @RequestMapping("judge/delete_batch")
    @ResponseBody
    public boolean deleteJudgeQuestionBatch(@RequestParam("list[]") List<Long> list) {
        System.out.println("judge_id = " + list);
        int count = 0;
        //循环删除
        for (int i = 0; i < list.size(); i++) {
            if (teacherService.deleteJudgeQuestionById(list.get(i))) {
                count++;
            }
        }
        System.out.println("count = " + count);
        //判断是否全部删除
        if (count == list.size()) {
            return true;
        } else {
            return false;
        }
    }


    @RequestMapping("judge/update")
    @ResponseBody
    public boolean updateJudgeQuestion(JudgeQuestion judgeQuestion) {
        System.out.println("judgeUqestion = " + judgeQuestion);
        return teacherService.updateJudgeQuestion(judgeQuestion);
    }
}
