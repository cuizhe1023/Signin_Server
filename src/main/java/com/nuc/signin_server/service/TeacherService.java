package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.Teacher;
import com.nuc.signin_server.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:03
 * @Description:
 */
@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public int insertTeacher(Teacher teacher){
        return teacherMapper.insertTeacher(teacher);
    }

    public Teacher getTeacherByIdAndPassword(String id,String password){
        return teacherMapper.getTeacherByTeacherIdAndTeacherPassword(id,password);
    }

    public Teacher getTeacherById(String id){
        return teacherMapper.getTeacherById(id);
    }
}