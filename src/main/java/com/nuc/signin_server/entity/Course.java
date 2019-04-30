package com.nuc.signin_server.entity;

import lombok.Data;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 15:00
 * @Description:
 */
@Data
public class Course {
    String courseId;

    String classId;

    String courseName;

    String courseTime;

    String teacherId;

    String teacherName;

    String TemplatePath;
}
