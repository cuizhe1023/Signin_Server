package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Student;
import com.nuc.signin_server.entity.Student_SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
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
            "WHERE SignInId=#{signInId} AND StudentId=#{studentId}")
    int updateStatus(String studentId, String signDate, Integer signInId);

    @Select("SELECT Id,SignInId,StudentId,StudentName,SignInDate,SignStatus " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND StudentId=#{studentId}")
    Student_SignIn findByIdAndSignId(String studentId, Integer signInId);

    @Select("SELECT Id,SignInId,StudentId,StudentName,SignInDate,SignStatus " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND SignStatus=0  ORDER BY StudentId")
    List<Student_SignIn> getNoSignInStudent(Integer signInId);

    @Select("SELECT Id,SignInId,StudentId,StudentName,SignInDate,SignStatus " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND SignStatus=1 ORDER BY StudentId")
    List<Student_SignIn> getSignInStudent(Integer signInId);

    @Select("SELECT COUNT(StudentId) " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND SignStatus=0")
    int getCountNoSignInStudentNumber(Integer signInId);

    @Select("SELECT COUNT(StudentId) " +
            "FROM student_signin " +
            "WHERE SignInId=#{signInId} AND SignStatus=1")
    int getCountSignInStudentNumber(Integer signInId);

    @Update("<script>" +
            "UPDATE student_signin " +
            "SET " +
            "<if test=\"leaveReason!=null\">" +
            "LeaveReason=#{leaveReason} " +
            "</if>" +
            "<if test=\"leaveReason==null\">" +
            "SignStatus = 1" +
            "</if>" +
            "WHERE SignInId=#{signInId} AND StudentId=#{studentId}" +
            "</script>")
    int updateReason(Integer signInId, String studentId, String leaveReason);

    @Select("SELECT LeaveReason FROM student_signin WHERE SignInId=#{signInId} AND StudentId=#{studentId}")
    String getLeaveReason(Integer signInId, String studentId);

    @Select("SELECT StudentId,SignStatus FROM student_signin WHERE SignInId=#{signInId} ORDER BY StudentId")
    List<Student_SignIn> getSignInResult(Integer signInId);
}
