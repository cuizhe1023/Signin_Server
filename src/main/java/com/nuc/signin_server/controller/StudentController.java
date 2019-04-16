package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:43
 * @Description:
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("login")
    public Student studentLogin(Student student){
        Student result= studentService.getStudentByIdAndPassword(student.getStudentId(),student.getStudentPassword());
        if (result != null){
            System.out.println("学生：" + result.toString() + "登录了！");
            return result;
        }else {
            return null;
        }
    }

    @RequestMapping("register")
    public String studentRegister(Student student){
        String result = "该账号已注册，请使用此账号直接登录或使用其他账号注册";
        int res = 0;
        try{
            res = studentService.insertStudent(student);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(result);
        }
        if (res != 0){
            System.out.println(student.toString()+" 注册成功！");
            result = "注册成功";
        }
        return result;
    }

    @RequestMapping("findById")
    public Student getStudentById(String studentId){
        System.out.println("通过 id：" + studentId + "查找学生");
        return studentService.getStudentById(studentId);
    }

    @RequestMapping("updateSN")
    public Student updateMacAddress(Student student){
        String result = "请检查你的用户名是否正确，不要替签！";

        int res = 0;
        try{
            res = studentService.updateStudentMacAddress(student);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (res != 0){
            System.out.println("手机序列号修改成功！");
            return student;
        }
        System.out.println("请不要替签");
        return null;
    }
}
