package com.nuc.signin_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuc.signin_server.entity.Student_SignIn;
import com.nuc.signin_server.service.StudentSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("getNoSignInStudentList")
    public List<Student_SignIn> getNoSignInStudent(Integer signInId) {
        List<Student_SignIn> list = studentSignInService.getNoSignInStudent(signInId);
        System.out.println("没有签到的学生的信息");
        return list;
    }

    @RequestMapping("getSignInStudentList")
    public List<Student_SignIn> getSignInStudent(Integer signInId) {
        List<Student_SignIn> list = studentSignInService.getSignInStudent(signInId);
        System.out.println("没有签到的学生的信息");
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

    @RequestMapping("getNoSignInStudentNumber")
    public JSONObject getCountNoSignInStudentNumber(Integer signInId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("noSignIn",studentSignInService.getCountNoSignInStudentNumber(signInId));
        JSONObject json = new JSONObject(map);
        return json;
    }

    @RequestMapping("getSignInStudentNumber")
    public JSONObject getCountSignInStudentNumber(Integer signInId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("signIn",studentSignInService.getCountSignInStudentNumber(signInId));
        JSONObject json = new JSONObject(map);
        return json;
    }

}
