package com.nuc.signin_server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:20
 * @Description:
 */
@Data
public class Student implements Serializable {

    private String studentId;

    private String studentName;

    private String studentPassword;

    private String classId;

    private String gender;

    private String macAddress;
}
