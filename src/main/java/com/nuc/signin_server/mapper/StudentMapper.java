package com.nuc.signin_server.mapper;

import com.nuc.signin_server.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 11:23
 * @Description:
 */
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE studentId=#{studentId}")
    Student getStudentByStudentId(String studentId);

    @Select("SELECT * FROM student WHERE studentId=#{studentId} AND studentPassword=#{studentPassword}")
    Student getStudentByIdAndPassword(String studentId,String studentPassword);

    @Insert("INSERT INTO student VALUES(#{studentId},#{studentName},#{studentPassword},#{classId},#{gender},#{macAddress})")
    int insertStudent(Student student);

    @Update("UPDATE student SET macAddress=#{macAddress} WHERE studentId=#{studentId}")
    int updateMacAddress(String studentId,String macAddress);
}
