package com.nuc.signin_server.entity;

import lombok.Data;

/**
 * @Author: cuizhe
 * @Date: 2019/4/22 14:32
 * @Description:
 */
@Data
public class SignIn {
    Integer signInId;

    String teacherId;

    String teacherName;

    Integer courseId;

    String signDate;

    Integer arriveNumber;

    Integer studentNumber;
}
