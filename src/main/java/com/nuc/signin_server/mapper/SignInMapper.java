package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 18:46
 * @Description:
 */
public interface SignInMapper {

    @Insert("INSERT INTO signin(TeacherId,TeacherName,CourseId,SignDate) " +
            "VALUES (#{teacherId},#{teacherName},#{courseId},#{signDate})")
    @Options(useGeneratedKeys = true, keyProperty = "signInId", keyColumn = "SignInId")
    int createSignIn(SignIn signIn);


}
