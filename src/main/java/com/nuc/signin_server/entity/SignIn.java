package com.nuc.signin_server.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: cuizhe
 * @Date: 2019/4/22 14:32
 * @Description:
 */
@Data
public class SignIn {
    Integer signInId;

    String teacherId;

    Integer courseId;

    Date signDate;

    Integer arriveNumber;

    Integer studentNumber;
}
