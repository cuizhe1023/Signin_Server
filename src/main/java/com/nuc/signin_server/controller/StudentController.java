package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:43
 * @Description:
 */
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("studentLogin")
    @ResponseBody
    public Student studentLogin(Student student){
        Student result= studentService.getStudentByIdAndPassword(student.getStudentId(),student.getStudentPassword());
        if (result != null){
            System.out.println("学生：" + result.toString() + "登录了！");
            return result;
        }else {
            return null;
        }
    }

    @RequestMapping("studentRegister")
    @ResponseBody
    public String studentRegister(Student student){
        String result = "该账号已注册，请使用此账号直接登录或使用其他账号注册";
        int res = 0;
        try{
            res = studentService.insertStudent(student);
            System.out.println("res:" + res);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(result);
        }
        if (res != 0){
            result = "注册成功";
        }
        return result;
    }

    @RequestMapping("updateMac")
    @ResponseBody
    public String updateMacAddress(String studentId,String macAddress){
        String result = "请检查你的用户名是否正确，不要替签！";

        int res = 0;
        try{
            res = studentService.updateStudentMacAddress(studentId,macAddress);
        }catch (Exception e){
            System.out.println("MAC地址已经存在，有人替 " + studentId + " 签到");
            e.printStackTrace();
        }
        if (res != 0){
            result = "修改成功";
        }
        return result;
    }
}
