package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:03
 */
public interface TeacherMapper {
    @Select("SELECT * FROM teacher WHERE id=#{teacherid}")
    public Teacher getTeacherById(String teacherid);

    @Select("SELECT * FROM teacher WHERE teacherId=#{teacherId} and teacherPassword=#{teacherPassword}")
    public Teacher getByTeacherIdAndTeacherPassword(String teacherId,String teacherPassword);

    @Insert("INSERT INTO teacher VALUES(#{teacherId},#{teacherName},#{teacherPassword})")
    public int insertTeacher(Teacher teacher);
}