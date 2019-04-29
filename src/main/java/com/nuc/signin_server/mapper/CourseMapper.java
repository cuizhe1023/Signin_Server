package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 15:34
 * @Description:
 */
public interface CourseMapper {

    @Insert("INSERT INTO course(ClassId,CourseName,CourseTime,TeacherId,TeacherName) "+
            "VALUES(#{classId},#{courseName},#{courseTime},#{teacherId},#{teacherName})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId", keyColumn = "CourseId")
    int insertCourse(Course course);

    @Select("SELECT * FROM course WHERE TeacherId=#{teacherId}")
    List<Course> getCourseListByTeacherId(String teacherId);

    @Select("SELECT a.* " +
            "FROM course AS a,select_course AS b " +
            "WHERE a.CourseId=b.CourseId AND b.StudentId=#{studentId}")
    List<Course> getStudentCourseListByStudentId(String studentId);

    @Delete("DELETE FROM course WHERE CourseId=#{courseId}")
    int deleteCourse(Integer courseId);
}
