package com.kerwin.eduService.controller;


import com.kerwin.commonutils.R;
import com.kerwin.eduService.entity.EduCourse;
import com.kerwin.eduService.entity.vo.CourseInfoVo;
import com.kerwin.eduService.entity.vo.CoursePublishVo;
import com.kerwin.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kerwin
 * @since 2021-06-11
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    //课程列表
    @GetMapping("getCourseList")
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list", list);
    }

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }
    //根据id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){

        CourseInfoVo courseInfoVo =  courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishVoInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo =  courseService.PublishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
}

