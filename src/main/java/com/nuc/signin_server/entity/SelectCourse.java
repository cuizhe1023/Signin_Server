package com.nuc.signin_server.entity;

import lombok.Data;

/**
 * @Author: cuizhe
 * @Date: 2019/4/20 12:30
 * @Description:
 */
@Data
public class SelectCourse {
    Integer selectCourseId;

    Integer courseId;

    String studentId;

    String studentName;

    String gender;
}