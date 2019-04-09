package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Teacher;
import com.nuc.signin_server.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:04
 * @Description:
 */
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("teacherLogin")
    @ResponseBody
    public Teacher teacherLogin(Teacher teacher){

        Teacher result = teacherService.getTeacherByIdAndPassword(teacher.getTeacherId(),teacher.getTeacherPassword());
        if (result != null){
            System.out.println("老师：" + result.toString() + "登录了！");
            return result;
        }else {
            return null;
        }
    }

    @RequestMapping("teacherRegister")
    @ResponseBody
    public String teacherRegister(Teacher teacher){
        String result = "该账号已注册，请使用此账号直接登录或使用其他账号注册";
        int res = 0;
        try{
            res = teacherService.insertTeacher(teacher);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(result);
        }
        if (res != 0){
            result = "注册成功";
        }
        return result;
    }
}