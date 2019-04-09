package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.Teacher;
import com.nuc.signin_server.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:03
 */
@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public int add(Teacher teacher){
        return teacherMapper.insertTeacher(teacher);
    }

    public Teacher getByTeacherIdAndTeacherPassword(String teacherId,String teacherPassword){
        return teacherMapper.getByTeacherIdAndTeacherPassword(teacherId,teacherPassword);
    }

    public Teacher getTeacherById(String teacherId){
        return teacherMapper.getTeacherById(teacherId);
    }
}