package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.SelectCourse;
import com.nuc.signin_server.mapper.SelectCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/20 12:34
 * @Description:
 */
@Service
public class SelectCourseService {

    @Autowired
    SelectCourseMapper selectCourseMapper;

    public int insertSelectCourse(SelectCourse selectCourse){
        return selectCourseMapper.insertSelectCourse(selectCourse);
    }
}
