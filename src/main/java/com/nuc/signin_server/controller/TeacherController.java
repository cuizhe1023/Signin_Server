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
 */
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("doLogin")
    @ResponseBody
    public Teacher doLogin(Teacher teacher){

        Teacher result = teacherService.getByTeacherIdAndTeacherPassword(teacher.getTeacherId(),teacher.getTeacherPassword());
        if (result != null){
            System.out.println(result.toString() + "登录了！");
            return result;
        }else {
            return null;
        }
    }

    @RequestMapping("doRegist")
    @ResponseBody
    public String doRegist(Teacher teacher){
        String result = "该账号已注册，请使用此账号直接登录或使用其他账号注册";
        int res = 0;
        try{
            res = teacherService.add(teacher);
        }catch (Exception e){
            System.out.println(result);
        }
        if (res != 0){
            result = "注册成功";
        }
        return result;
    }
}