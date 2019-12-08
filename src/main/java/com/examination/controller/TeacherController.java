package com.examination.controller;

import com.examination.entity.ChoiceQuestion;
import com.examination.entity.JudgeQuestion;
import com.examination.entity.Page;
import com.examination.entity.Paper;
import com.examination.entity.Question.Judgedba;
import com.examination.entity.SubjectQuestion;
import com.examination.service.ExamService;
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
import java.util.Map;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    final static String path = "teacher/";

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PageService pageService;
    @Autowired
    private ExamService examService;

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


    /**
     * 选择题列表查询
     * @return
     */
    @RequestMapping("question_list.html")
    public String questionList(Model model,String currentPage) {
        Page page = new Page();
        //page.getCurrentPage(); //当前页
        //page.getPageNumber(); //页面最大容量
        if(currentPage!=null){
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        List<ChoiceQuestion> list = teacherService.choiceList( page.getCurrentPage(),page.getPageNumber());

        page.setCount(teacherService.getCount());
        if(teacherService.getCount() % page.getPageNumber() == 0){
            page.setTotalPage(teacherService.getCount()/page.getPageNumber());
        }else{
            page.setTotalPage(teacherService.getCount()/page.getPageNumber()+1);
        }

        model.addAttribute("page",page);
        model.addAttribute("choiceQuestuon",list);
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

    /**
     * 选择题修改
     * @param choiceQuestion
     * @return
     */
    @RequestMapping("/choice/update")
    @ResponseBody
    public boolean updateChoiceQuestion(ChoiceQuestion choiceQuestion){
        return teacherService.updateChoiceQuestion(choiceQuestion);
    }

    @RequestMapping("/choice/delete")
    @ResponseBody
    public boolean deleteChoiceQuestion(@RequestParam("ids[]")String[] ids){
        List<String> list = new ArrayList<>(Arrays.asList(ids));
        return teacherService.deleteChoiceQuestion(list);
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


    /**
     * 判断题修改
     *
     * @param judgeQuestion
     * @return
     * @Author zql
     */
    @RequestMapping("judge/update")
    @ResponseBody
    public boolean updateJudgeQuestion(JudgeQuestion judgeQuestion) {
        System.out.println("judgeUqestion = " + judgeQuestion);
        return teacherService.updateJudgeQuestion(judgeQuestion);
    }

    /**
     * 获取主观题 -- 分页查询
     *
     * @param model
     * @param request
     * @return
     * @Author zql
     */
    @RequestMapping("sub_list.html")
    public String SubList(Model model, HttpServletRequest request) {
        //获取分页对象 用于计算总页数
        Page page = pageService.getPage(request.getParameter("currentPage"), "subject");
        //存入model
        model.addAttribute("page", page);
        //根据当前页获取判断题列表
        List<SubjectQuestion> subjectQuestionList = teacherService.getSubjectQuestionList(page);
        //存入model
        model.addAttribute("subjectQuestion", subjectQuestionList);
        return path + "sub_list";
    }

    /**
     * 主观题模板下载
     *
     * @param res
     * @Author zql
     */
    @RequestMapping("sub/download")
    public void subDownload(HttpServletResponse res) {
        res.setHeader("Content-Disposition", "attachment; filename=sub_template.xlsx");
        res.setContentType("application/octet-stream; charset=utf-8");
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("templates/excel/sub_template.xlsx");
            FileCopyUtils.copy(is, res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主观题上传
     *
     * @param file
     * @Author zql
     */
    @RequestMapping("sub/upload")
    @ResponseBody
    public boolean subUpload(@RequestParam(value = "file") MultipartFile file) {
        try {
            return teacherService.addSubjectQuestionByExcel(file.getInputStream()) != 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据id删除主观题
     *
     * @param id
     * @return
     * @Author zql
     */
    @RequestMapping("sub/delete")
    @ResponseBody
    public boolean deleteSubjectQuestion(String id) {
        System.out.println("subject_id = " + id);
        long subjectid = 0;
        if (null != id) {
            //数据类型转换
            subjectid = Long.parseLong(id);
        }
        return teacherService.deleteSubjectQuestionById(subjectid);
    }

    /**
     * 批量删除主观题
     *
     * @param list
     * @return
     * @Author zql
     */
    @RequestMapping("sub/delete_batch")
    @ResponseBody
    public boolean deleteSubjectQuestionBatch(@RequestParam("list[]") List<Long> list) {
        System.out.println("subject_id = " + list);
        int count = 0;
        //循环删除
        for (int i = 0; i < list.size(); i++) {
            if (teacherService.deleteSubjectQuestionById(list.get(i))) {
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


    /**
     * 主观题修改
     *
     * @param subjectQuestion
     * @return
     * @Author zql
     */
    @RequestMapping("sub/update")
    @ResponseBody
    public boolean updateSubjectQuestion(SubjectQuestion subjectQuestion) {
        System.out.println("SubjectQuestion = " + subjectQuestion);
        return teacherService.updateSubjectQuestion(subjectQuestion);
    }

    /**
     * 试卷列表页面
     *
     * @return
     * @Author zql
     */
    @RequestMapping("teacher_test_list.html")
    public String teacherTestList() {
        return path + "teacher_test_list";
    }

    /**
     * 试卷列表
     *
     * @param httpSession
     * @return
     * @Author zql
     */
    @RequestMapping(value = "teacher_test_list_content")
    @ResponseBody
    public List<Map> teacherTestListContent(HttpSession httpSession) {
//        long tid = (long) httpSession.getAttribute("userid");
        //暂时没有写登录  暂且给定一个默认值
        long tid = 1;
        return teacherService.listPaper(tid);
    }

    /**
     * 查询学生成绩
     *
     * @return
     * @Author zql
     */
    @RequestMapping(value = "student_grade")
    public String studentGrade() {
        return path + "student_grade";
    }

    /**
     * 查询试卷初始化
     *
     * @param pid
     * @return
     * @Author zql
     */
    @RequestMapping("see_test_ing")
    public String seeTestIng(String pid) {
        System.out.println("pid = " + pid);

        return path + "see_test_ing";
    }


    /**
     * 查询试卷详细信息
     *
     * @param pid
     * @return
     * @Author zql
     */
    @RequestMapping("teacher_see_paper")
    @ResponseBody
    public Paper teacherSeePaper(long pid) {
        return teacherService.getPaper(pid);
    }


    /**
     * 根据id查询判断题
     *
     * @param id
     * @return
     */
    @RequestMapping("/truefalse_get_questionByAns")
    @ResponseBody
    public Judgedba truefalseGetQuestionByAns(long id) {
        System.out.println("id = " + id);
        System.out.println(examService.getJudgedbaById_Ans(id));
        return examService.getJudgedbaById_Ans(id);
    }
}
