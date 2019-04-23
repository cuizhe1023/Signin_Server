package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Course;
import com.nuc.signin_server.entity.SelectCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT StudentId,StudentName,Gender FROM select_course WHERE CourseId=#{courseId} ORDER BY StudentId")
    List<SelectCourse> getCourseListByTeacherId(String courseId);

    @Select("SELECT COUNT(StudentId) FROM select_course WHERE CourseId=#{courseId}")
    int getStudentSum(String courseId);
}
