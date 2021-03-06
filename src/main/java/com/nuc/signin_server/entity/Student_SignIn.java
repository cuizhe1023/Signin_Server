package com.nuc.signin_server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuizhe
 * @Date: 2019/4/23 23:13
 * @Description:
 */
@Data
public class Student_SignIn implements Serializable {
    private Integer id;

    private Integer signInId;

    private String studentId;

    private String studentName;

    private String signInDate;

    private Integer signStatus;

    private String leaveReason;
}
