package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 15:34
 * @Description:
 */
public interface CourseMapper {

    @Insert("INSERT INTO course(ClassId,CourseName,CourseTime,TeacherId,TeacherName) VALUES(#{classId},#{courseName},#{courseTime},#{teacherId},#{teacherName})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId", keyColumn = "CourseId")
    int insertCourse(Course course);
}
