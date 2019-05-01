package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 18:46
 * @Description:
 */
public interface SignInMapper {

    @Insert("INSERT INTO signin(TeacherId,TeacherName,CourseId,SignDate) " +
            "VALUES (#{teacherId},#{teacherName},#{courseId},#{signDate})")
    @Options(useGeneratedKeys = true, keyProperty = "signInId", keyColumn = "SignInId")
    int createSignIn(SignIn signInId);

    @Update("UPDATE signin " +
            "SET ArriveNumber=#{arriveNumber},studentNumber=#{studentNumber} " +
            "WHERE SignInId=#{signInId}")
    int updateSignInData(Integer signInId, Integer arriveNumber, Integer studentNumber);

    @Select("SELECT * FROM signin WHERE CourseId=#{courseId}")
    List<SignIn> getSignInList(Integer courseId);

    @Select("SELECT SignInId " +
            "FROM signin " +
            "WHERE CourseId=#{courseId}")
    List<Integer> getSignInIdList(Integer courseId);
}
