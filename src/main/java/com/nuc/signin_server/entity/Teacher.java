package com.nuc.signin_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Teacher implements Serializable {

    private String teacherId;

    private String teacherName;

    private String teacherPassword;
}
