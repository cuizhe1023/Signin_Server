package com.nuc.signin_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuc.signin_server.entity.Course;
import com.nuc.signin_server.entity.SelectCourse;
import com.nuc.signin_server.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/20 12:34
 * @Description:
 */
@RestController
@RequestMapping("select_course")
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
            return null;
        }
        if (res != 0){
            System.out.println("添加学生信息成功：\n" + selectCourse.toString());
            return selectCourse;
        }
        return null;
    }

    @RequestMapping("studentList")
    public List<SelectCourse> getStudentListByCourseId(Integer courseId){
        List<SelectCourse> list = selectCourseService.getStudentList(courseId);
        System.out.println("选修了 " + courseId + " 的学生信息：");
        for (SelectCourse selectCourse :
                list) {
            System.out.println("学号：" + selectCourse.getStudentId() +
                    "，名字：" +selectCourse.getStudentName() + "，性别：" +selectCourse.getGender());
        }
        return list;
    }

    @RequestMapping("studentSum")
    public JSONObject getStudentSumByCourseId(Integer courseId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("sum", selectCourseService.getStudentSum(courseId));
        JSONObject json = new JSONObject(map);
        return json;
    }
}
