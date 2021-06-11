package com.kerwin.eduService.controller;


import com.kerwin.commonutils.R;
import com.kerwin.eduService.entity.vo.CourseInfoVo;
import com.kerwin.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }
}

