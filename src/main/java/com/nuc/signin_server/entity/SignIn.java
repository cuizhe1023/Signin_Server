package com.nuc.signin_server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuizhe
 * @Date: 2019/4/22 14:32
 * @Description:
 */
@Data
public class SignIn implements Serializable {
    Integer signInId;

    String teacherId;

    String teacherName;

    Integer courseId;

    String signDate;

    Integer arriveNumber;

    Integer studentNumber;
}
