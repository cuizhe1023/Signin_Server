package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.entity.Student_SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 23:20
 * @Description:
 */
public interface StudentSignInMapper {

    @Insert("INSERT INTO student_signin(SignInId,StudentId,StudentName,SignInDate) " +
            "SELECT #{signInId},StudentId,StudentName,#{signDate} " +
            "FROM select_course " +
            "WHERE CourseId=#{courseId}")
    int initSignIn(String signInId, String signDate, String courseId);

    @Update("UPDATE student_signin " +
            "SET SignStatus=1,SignInDate=#{signDate}" +
            "WHERE StudentId=#{studentId} AND SignInId=#{signInId}")
    int updateStatus(String studentId, String signDate, Integer signInId);

    @Select("SELECT Id,SignInId,StudentId,StudentName,SignInDate,SignStatus " +
            "FROM student_signin " +
            "WHERE StudentId=#{studentId} AND SignInId=#{signInId}")
    Student_SignIn findByIdAndSignId(String studentId, Integer signInId);

    @Select("SELECT Id,SignInId,StudentId,StudentName,SignInDate,SignStatus " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND SignStatus=0")
    List<Student_SignIn> getNoSignInStudent(String signInId);
}
