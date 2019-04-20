package com.nuc.signin_server.controller;

import com.nuc.signin_server.entity.Course;
import com.nuc.signin_server.entity.SelectCourse;
import com.nuc.signin_server.service.CourseService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: cuizhe
 * @Date: 2019/4/16 19:23
 * @Description:
 */
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("insert")
    public Course insertCourse(Course course){
        int res = 0;
        try {
            res = courseService.insertCourse(course);
        } catch (Exception e) {
            System.out.println("添加课程失败.");
            e.printStackTrace();
        }
        if (res != 0){
            System.out.println("添加课程成功,课程信息：\n" + course.toString());
            return course;
        }
        return null;
    }

    @RequestMapping("createList")
    public List<Course> getAll(String teacherId){
        List<Course> list= courseService.getCourseList(teacherId);
        return list;
    }

    @RequestMapping("/upload")
    public List<SelectCourse> upload(@RequestParam(value="file",required=false)MultipartFile file,
                                @RequestParam(value = "courseId")Integer courseId){

        System.out.println("courseId = " + courseId);
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件
        String path = "D:/excel/";
        //生成保存文件
        File uploadFile = new File(path+dFileName);
        System.out.println(uploadFile);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SelectCourse> selectCourseList = excel(uploadFile,courseId);
        for (SelectCourse selectCourse :
                selectCourseList) {
            System.out.println(selectCourse.toString());
        }
        return selectCourseList;
    }

    public List<SelectCourse> excel(File excelFile, Integer courseId){
        List<SelectCourse> selectCourseList = new LinkedList<SelectCourse>();
        try {
            //创建 workbook,并指定路径
            Workbook workbook = Workbook.getWorkbook(excelFile);
            //获取 sheet 页
            Sheet sheet = workbook.getSheet(0);
            // 获取数据
            for (int i = 1; i < sheet.getRows(); i++) {
                SelectCourse selectCourse = new SelectCourse();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j,i);//获取单元格
                    selectCourse.setCourseId(courseId);
                    if (j == 0){
                        selectCourse.setStudentId(cell.getContents());
                    }
                    if (j == 1){
                        selectCourse.setStudentName(cell.getContents());
                    }
                    if (j == 2){
                        selectCourse.setGender(cell.getContents());
                    }
                }
                selectCourseList.add(selectCourse);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return selectCourseList;
    }
}
