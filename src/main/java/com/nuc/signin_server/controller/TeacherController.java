package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.entity.Teacher;
import com.nuc.signin_server.service.TeacherService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:04
 * @Description:
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("login")
    public Teacher teacherLogin(Teacher teacher){

        Teacher result = teacherService.getTeacherByIdAndPassword(teacher.getTeacherId(),teacher.getTeacherPassword());
        if (result != null){
            System.out.println("老师：" + result.toString() + "登录了！");
            return result;
        }else {
            System.out.println("登录失败");
            return null;
        }
    }

    @RequestMapping("register")
    public Teacher teacherRegister(Teacher teacher){
        int res = 0;
        try{
            res = teacherService.insertTeacher(teacher);
            System.out.println("res0 = " + res);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("res1 = " + res);
            System.out.println("注册失败");
        }
        if (res != 0){
            System.out.println("res2 = " + res);
            System.out.println(teacher.toString()+" 注册成功！");
            return teacher;
        }
        return null;
    }

    @RequestMapping("findById")
    public Teacher getById(String teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    /**
     * 解析 excel 表
     *
     * @return 将 excel 表里的数据进行导入，帮助学生选课
     */
    @RequestMapping("readExcel")
    public List<Student> readExcel(File file){
        List<Student> result = new LinkedList<>();
        try {
            //创建 workbook
            Workbook workbook = Workbook.getWorkbook(file);
            // 获取 sheet 页
            Sheet sheet = workbook.getSheet(0);
            // 获取数据
            for (int i = 0; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j,i);// 获取单元格
                    System.out.println(cell.getContents() + " " );
                }
                System.out.println();
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return result;
    }
}