package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:43
 * @Description:
 */
@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public int insertStudent(Student student){
        return studentMapper.insertStudent(student);
    }

    public Student getStudentByIdAndPassword(String id, String password){
       return studentMapper.getStudentByIdAndPassword(id,password);
    }

    public Student getStudentById(String id){
        return studentMapper.getStudentByStudentId(id);
    }

    public int updateStudentMacAddress(Student student){
        return studentMapper.updateMacAddress(student);
    }

}
