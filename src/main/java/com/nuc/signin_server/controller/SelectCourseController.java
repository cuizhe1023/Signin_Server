package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.SelectCourse;
import com.nuc.signin_server.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/20 12:34
 * @Description:
 */
@RequestMapping("select_course")
@RestController
public class SelectCourseController {
    @Autowired
    SelectCourseService selectCourseService;

    @RequestMapping("insert")
    public SelectCourse insertSelectCourse(SelectCourse selectCourse){
        int res = 0;
        try {
            res = selectCourseService.insertSelectCourse(selectCourse);
        } catch (Exception e) {
            System.out.println("添加学生信息失败.");
            e.printStackTrace();
        }
        if (res != 0){
            System.out.println("添加课程成功,课程信息：\n" + selectCourse.toString());
            return selectCourse;
        }
        return null;
    }
}
