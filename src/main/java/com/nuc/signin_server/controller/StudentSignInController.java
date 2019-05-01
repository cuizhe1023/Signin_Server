package com.nuc.signin_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuc.signin_server.entity.SelectCourse;
import com.nuc.signin_server.entity.Student_SignIn;
import com.nuc.signin_server.service.CourseService;
import com.nuc.signin_server.service.SelectCourseService;
import com.nuc.signin_server.service.SignInService;
import com.nuc.signin_server.service.StudentSignInService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 23:42
 * @Description:
 */
@RestController
@RequestMapping("studentSignIn")
public class StudentSignInController {
    @Autowired
    StudentSignInService studentSignInService;

    @Autowired
    SignInService signInService;

    @Autowired
    CourseService courseService;

    @Autowired
    SelectCourseService selectCourseService;

    @RequestMapping("initSignIn")
    public JSONObject initStudentSignInData(@RequestParam(value = "signInId") String signInId,
                                            @RequestParam(value = "signDate") String signDate,
                                            @RequestParam(value = "courseId") String courseId) {
        int res = 0;
        try {
            res = studentSignInService.initSignInData(signInId, signDate, courseId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化签到信息失败");
        }
        if (res != 0) {
            System.out.println("初始化签到信息成功");
            HashMap<String, Object> map = new HashMap();
            map.put("signNumber", res);
            JSONObject json = new JSONObject(map);
            return json;
        }
        return null;
    }

    @RequestMapping("findByIdAndSignId")
    public Student_SignIn findByIdAndSignId(String studentId, Integer signInId) {
        System.out.println("通过 id：" + studentId + " 和签到编号" + signInId + " 查找学生");
        return studentSignInService.findByIdAndSignId(studentId, signInId);
    }

    @RequestMapping("updateStatus")
    public Student_SignIn updateSignInStatus(String studentId, String signDate, Integer signInId) {
        Student_SignIn sign;
        int res = 0;
        try {
            res = studentSignInService.updateStatus(studentId, signDate, signInId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res != 0) {
            System.out.println("修改学生签到信息成功！");
            sign = findByIdAndSignId(studentId, signInId);
            return sign;
        }
        System.out.println("修改学生签到信息失败！");
        return null;
    }

    @RequestMapping("getNoSignInStudentList")// 显示没有签到的学生
    public List<Student_SignIn> getNoSignInStudent(Integer signInId) {
        List<Student_SignIn> list = studentSignInService.getNoSignInStudent(signInId);
        System.out.println("没有签到的学生的信息");
        return list;
    }

    @RequestMapping("getSignInStudentList")// 显示签到了的学生
    public List<Student_SignIn> getSignInStudent(Integer signInId) {
        List<Student_SignIn> list = studentSignInService.getSignInStudent(signInId);
        System.out.println("签到的学生的信息");
        return list;
    }

    @RequestMapping("updateReason")
    public Student_SignIn updateReason(Integer signInId, String studentId, String leaveReason) {
        int res = 0;
        Student_SignIn sign;
        try {
            res = studentSignInService.updateReason(signInId, studentId, leaveReason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res != 0) {
            System.out.println("修改学生请假理由成功");
            sign = findByIdAndSignId(studentId, signInId);
            return sign;
        }
        System.out.println("修改学生请假理由失败");
        return null;
    }

    @RequestMapping("getNoSignInStudentNumber")// 显示没有签到的学生的数目
    public JSONObject getCountNoSignInStudentNumber(Integer signInId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("noSignIn", studentSignInService.getCountNoSignInStudentNumber(signInId));
        JSONObject json = new JSONObject(map);
        return json;
    }

    @RequestMapping("getSignInStudentNumber")// 显示签到了的学生的数目
    public JSONObject getCountSignInStudentNumber(Integer signInId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("signIn", studentSignInService.getCountSignInStudentNumber(signInId));
        JSONObject json = new JSONObject(map);
        return json;
    }

    @RequestMapping("getLeaveReason")
    public JSONObject getLeaveReason(Integer signInId, String studentId) {
        String result = studentSignInService.getLeaveReason(signInId, studentId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveReason", result);
        JSONObject json = new JSONObject(map);
        return json;
    }

    @RequestMapping("getSignInResult")
    public HashMap<String, Object> getSignInResult(Integer signInId) {
        List<Student_SignIn> list = studentSignInService.getSignInResult(signInId);
        HashMap<String, Object> map = new HashMap<>();
        for (Student_SignIn s :
                list) {
            map.put(s.getStudentId(), s.getSignStatus());
        }
        return map;
    }

    @RequestMapping("downloadResult")
    public HashMap<String, Object> downloadResult(Integer courseId) {
        // 先初始化信息，对名单进行初始化
        String[] title = {"学号", "姓名", "性别"};
        // 初始化一些数据

        List<SelectCourse> selectCourses = selectCourseService.getStudentList(courseId);// 学生信息
        List<Integer> signInIdList = signInService.getSignInIdList(courseId); // 签到次数的信息
        // 获取文件名
        String namePath = courseService.getNameListPath(courseId);
        String fileName = namePath.substring(namePath.lastIndexOf("/") + 1);// linux
//        String fileName = namePath.substring(namePath.lastIndexOf("\\") + 1);
        System.out.println("file = " + fileName);
         String filePath = "/code/Signin_Server/ScoreList/"; // linux 上的文件
//        String filePath = "D:\\ScoreList\\" + fileName;
        try {
            File file = new File(filePath);
            file.createNewFile();
            // 创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            // 创建sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            Label label = null;
            for (int i = 0; i < title.length; i++) {
                // 第一行设置列名
                label = new Label(i, 0, title[i]);// 没有行的概念，只有坐标，第i列，第0行，名称
                sheet.addCell(label);
            }
            // 设置签到信息的列名
            if (signInIdList.size() > 0) {
                // i - 3 + 1 得到“第几次签到”这样的信息.
                for (int i = title.length; i < (title.length + signInIdList.size()); i++) {
                    String cont = "第 " + (i - title.length + 1) + " 次";
                    System.out.println("--" + cont);
                    label = new Label(i, 0, cont);
                    sheet.addCell(label);
                }
            }
            // 追加数据
            // 1.先追加学生信息
            for (int i = 0; i < selectCourses.size(); i++) {
                // 对列表的第 (i+1,0)这个单元格设置值，以此类推
                label = new Label(0, i + 1, selectCourses.get(i).getStudentId());
                sheet.addCell(label);
                label = new Label(1, i + 1, selectCourses.get(i).getStudentName());
                sheet.addCell(label);
                label = new Label(2, i + 1, selectCourses.get(i).getGender());
                sheet.addCell(label);
            }
            // 2.追加签到信息
            for (int i = 0; i < signInIdList.size(); i++) {
                List<Student_SignIn> list = studentSignInService.getSignInResult(signInIdList.get(i));
                // 因为是通过排序获取的，所以与列表中的 studentId 一一对应，
                for (int j = 0; j < list.size(); j++) {// 修改签到信息
                    // title.length 表示列，(j+1)表示行
                    label = new Label(title.length + i, j + 1, String.valueOf(list.get(j).getSignStatus()));
                    sheet.addCell(label);
                }
            }
            //写入数据
            workbook.write();
            //关闭连接
            workbook.close();
            //将写入数据库
            courseService.updateScoreList(courseId,filePath);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
