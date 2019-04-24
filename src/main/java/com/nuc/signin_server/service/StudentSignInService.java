package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.Student_SignIn;
import com.nuc.signin_server.mapper.StudentSignInMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 23:40
 * @Description:
 */
@Service
public class StudentSignInService {

    @Autowired
    StudentSignInMapper studentSignInMapper;

    public int initSignInData(String signInId, String signDate, String courseId){
        return studentSignInMapper.initSignIn(signInId,signDate,courseId);
    }

    public int updateStatus(String studentId, String signDate, Integer signInId){
        return studentSignInMapper.updateStatus(studentId,signDate,signInId);
    }

    public Student_SignIn findByIdAndSignId(String studentId, Integer signInId){
        return studentSignInMapper.findByIdAndSignId(studentId,signInId);
    }

    public List<Student_SignIn> getNoSignInStudent(String signInId){
        return studentSignInMapper.getNoSignInStudent(signInId);
    }
}
