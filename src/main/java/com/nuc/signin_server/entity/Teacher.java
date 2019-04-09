package com.nuc.signin_server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:02
 */
@Data
public class Teacher implements Serializable {

    private String teacherId;

    private String teacherName;

    private String teacherPassword;
}
