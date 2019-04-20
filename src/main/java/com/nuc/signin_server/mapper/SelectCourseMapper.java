package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.SelectCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: cuizhe
 * @Date: 2019/4/20 12:34
 * @Description:
 */
public interface SelectCourseMapper {

    @Insert("INSERT INTO select_course(CourseId, StudentId, StudentName, Gender) " +
            "VALUES (#{courseId}, #{studentId}, #{studentName}, #{gender})")
    @Options(useGeneratedKeys = true, keyProperty = "selectCourseId", keyColumn = "Select_Course_Id")
    int insertSelectCourse(SelectCourse selectCourse);
}
