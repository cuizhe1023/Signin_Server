package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Course;
import com.nuc.signin_server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 19:23
 * @Description:
 */
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("insert")
    public Course insertCourse(Course course){
        int res = 0;
        try {
            res = courseService.insertCourse(course);
        } catch (Exception e) {
            System.out.println("添加课程失败.");
            e.printStackTrace();
        }
        if (res != 0){
            System.out.println("添加课程成功,课程信息：\n" + course.toString());
            return course;
        }
        return null;
    }

    @RequestMapping("createList")
    public List<Course> getAll(String teacherId){
        List<Course> list= courseService.getCourseList(teacherId);
        return list;
    }
}
