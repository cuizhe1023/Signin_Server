package com.nuc.signin_server.service;

import com.nuc.signin_server.entity.Course;
import com.nuc.signin_server.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 15:41
 * @Description:
 */
@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    public int insertCourse(Course course){
        return courseMapper.insertCourse(course);
    }
}
