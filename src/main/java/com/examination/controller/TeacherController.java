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

    @RequestMapping("truefalse_list.html")
    public String trueFalseList(Model model, HttpServletRequest request) {
        Page page = pageService.getPage(request.getParameter("currentPage"), "judge");
        model.addAttribute("page", page);
        List<JudgeQuestion> judgeQuestionList = teacherService.getJudgeQuestionList(page);
        model.addAttribute("judgeQuestuon",judgeQuestionList);
        return path + "truefalse_list";
    }

    /**
     * 判断题模板下载
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
}
