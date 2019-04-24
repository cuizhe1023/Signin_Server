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
                                     @RequestParam(value = "courseId") String courseId){
        int res = 0;
        try {
            res = studentSignInService.initSignInData(signInId,signDate,courseId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化签到信息失败");
        }
        if (res != 0){
            System.out.println("初始化签到信息成功");
            HashMap<String,Object> map = new HashMap();
            map.put("signNumber",res);
            JSONObject json = new JSONObject(map);
            return json;
        }
        return null;
    }

    @RequestMapping("findByIdAndSignId")
    public Student_SignIn findByIdAndSignId(String studentId, Integer signInId){
        System.out.println("通过 id：" + studentId + " 和签到编号" + signInId + " 查找学生");
        return studentSignInService.findByIdAndSignId(studentId,signInId);
    }

    @RequestMapping("updateStatus")
    public Student_SignIn updateSignInStatus(String studentId, String signDate, Integer signInId){
        Student_SignIn sign = new Student_SignIn();
        int res = 0;
        try {
            res = studentSignInService.updateStatus(studentId,signDate,signInId);
            sign = findByIdAndSignId(studentId,signInId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res != 0){
            System.out.println("修改学生签到信息成功！");
            return sign;
        }
        System.out.println("修改学生签到信息失败！");
        return null;
    }

    @RequestMapping("getNoSignInStudent")
    public List<Student_SignIn> getNoSignInStudent(String signInId){
        List<Student_SignIn> list = studentSignInService.getNoSignInStudent(signInId);
        System.out.println("没有签到的学生的信息");
        return list;
    }

}
