package com.examination.controller;


import com.examination.entity.Paper;
import com.examination.entity.Question.Choicedba;
import com.examination.entity.Question.Evadba;
import com.examination.entity.Question.Judgedba;
import com.examination.entity.Question.Subdba;
import com.examination.service.ChartService;
import com.examination.service.EvaluatingService;
import com.examination.service.ExamService;
import com.examination.service.ExerciseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    private static final String path = "student/";

    @Autowired
    private ExamService examService;
    @Autowired
    private ChartService chartService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private EvaluatingService evaluatingService;

    @RequestMapping("")
    public String index(){
        return "redirect:" + path + "studentpage";
    }

    /**
     * 学生首页
     * @return
     * @author zql
     */
    @RequestMapping("studentpage")
    public String studentPage(){
        return path + "studentpage";
    }

    /**
     * 在线考试
     * @return
     * @author zql
     */
    @RequestMapping("student_test")
    public String studentTest(){
        return path + "student_test";
    }

    /**
     * 获取考试列表
     * @return
     * @author zql
     */
    @RequestMapping("student_test_get")
    @ResponseBody
    public List<Paper> getStudentTest(HttpSession session){
        //获取当前登录的学生信息
//        long uid = (long) session.getAttribute("userid");
        long uid = 5;
        //查询该学生所在班级的考试列表
        return examService.listPaperBySid(uid);
    }

    /**
     * 开始考试
     * @return
     * @author zql
     */
    @RequestMapping("student_test_ing")
    public String studentTestIng(){
        return path + "student_test_ing";
    }

    /**
     * 选择题查询
     * @return
     * @author zql
     */
    @RequestMapping("student_exercise_choice_get_question")
    @ResponseBody
    public Choicedba studentExerciseChoiceGetQuestion(long id){
        //查询选择题（不携带答案）
        return examService.getChoicedbaById_noAns(id);
    }

    /**
     * 判断题查询
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_exercise_truefalse_get_question")
    @ResponseBody
    Judgedba studentExerciseTrueFalseGetQuestion(Long id) {
        //查询判断题（不携带答案）
        return  examService.getJudgedbaById_noAns(id);
    }

    /**
     * 主观题查询
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_exercise_sub_get_question")
    @ResponseBody
    Subdba studentExerciseSubGetQuestion(Long id) {
        //查询主观题（不携带答案）
        return  examService.getSubdbaById_noAns(id);
    }

    /**
     * 判断试卷是否提交
     * @param httpSession
     * @param pid
     * @return
     * @author zql
     */
    @RequestMapping(value = "paper_tested")
    @ResponseBody
    Boolean paperTested(HttpSession httpSession,long pid){
//        long sid = (long) httpSession.getAttribute("userid");
        long sid = 5;
        return examService.paperTested(sid,pid);
    }

    /**
     * 判断考试时间
     * @param id
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_test_get_paper")
    @ResponseBody
    Paper studentTestGetPaper(long id) {
        return  examService.getPaperById(id);
    }

    /**
     * 试卷提交
     * @param ans
     * @param pid
     * @param httpSession
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_test_submit",method= RequestMethod.POST)
    @ResponseBody
    String studentTestSubmit(String ans,long pid,HttpSession httpSession ) {
        System.out.println(" ans:"+ans);
        System.out.println(" pid:"+pid);
//        long uid = (long) httpSession.getAttribute("userid");
        long uid = 5;
        return examService.submitPapre(uid,pid,ans);
    }

    /**
     * 成绩查询页面初始化
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_grade")
    String studentGrade() {
        return path + "student_grade";
    }

    /**
     * 成绩查询
     * @param httpSession
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_grade_get",method = RequestMethod.GET)
    @ResponseBody
    List<Map> studentGradeGet(HttpSession httpSession){
//        long uid = (long) httpSession.getAttribute("userid");
        long uid = 5;
        return examService.listGradeBySid(uid);
    }

    /**
     * 成绩图表分析页面初始化
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_chart")
    String studentChart(){
        return path + "student_chart";
    }

    /**
     * 成绩图表分析
     * @param session
     * @return
     * @author zql
     */
    @RequestMapping(value = "get/chart")
    @ResponseBody
    String getChart(HttpSession session){
//        long uid = (long)session.getAttribute("userid");
        long uid = 5;
        return chartService.studentGetChart(uid);
    }

    /**
     * 个人信息
     * @return
     * @author zql
     */
    @RequestMapping("student_info")
    public String stduentInfo(){
        return path + "student_info";
    }

    /**
     * 状态查询
     * @return
     * @author zql
     */
    @RequestMapping("student_status")
    public String studentStatus(){
        return path + "student_status";
    }

    /**
     * SQL练习页面初始化
     * @return
     * @author zql
     */
    @RequestMapping("student_evaluating_list_select")
    public String studentEvaluatingListSelect(){
        return path + "student_evaluating_list_select";
    }

    /**
     * 查找题列表
     * @return
     * @author zql
     */
    @RequestMapping("student_evaluating_list_select_get")
    @ResponseBody
    public List<Map> studentEvaluatingListSelectGet(){
        return exerciseService.getEvadbaByType("查找题");
    }

    /**
     * 根据id查询问题--初始化
     * @param
     * @return
     * @author zql
     */
    @RequestMapping("student_evaluating_question")
    public String studentEvaluatingQuestion(){
        return path + "student_evaluating_question";
    }

    /**
     * 根据id查询问题-
     * @param id
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_eva_get_question",method = RequestMethod.GET)
    @ResponseBody
    public Evadba studentEvaGetQuestion(long id){
        return exerciseService.getEvadbaById_noAns(id);
    }

    /**
     * SQL练习提交
     * @param eid
     * @param httpSession
     * @param sql2
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_evaluating_doPost",method = RequestMethod.POST)
    String studentEvaluatingDoPost(long eid, HttpSession httpSession,String sql2){
       // long uid = (long) httpSession.getAttribute("userid");
        long uid = 5;
        evaluatingService.EvaluateSQL(uid,eid,sql2);
        return "redirect:/" + path + "student_status";
    }


    /**
     * 状态查询
     * @param page
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_get_status",method = RequestMethod.GET)
    @ResponseBody
    List<Map> studentGetStatus(long page){
        final long n = 20;
        long off = (page-1) * n;
        return evaluatingService.get_Status(off,n);
    }

    /**
     * 随机练习
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_exercise")
    String student_exercise() {
        return path + "student_exercise";
    }

    /**
     * 随机选择题练习
     * @return
     * @author zql
     */
    @RequestMapping(value = "student_exercise_choice")
    String student_exercise_choice() {
        return path+"student_exercise_choice";
    }

    @RequestMapping(value = "student_exercise_choice_get_ans")
    @ResponseBody
    Choicedba student_exercise_choice_get_ans(Long id) {
        return  exerciseService.getChoicedbaById_Ans(id);
    }

}