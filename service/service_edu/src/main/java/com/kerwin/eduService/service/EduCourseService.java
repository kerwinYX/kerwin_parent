package com.kerwin.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kerwin.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kerwin.eduService.entity.frontvo.CourseFrontVo;
import com.kerwin.eduService.entity.frontvo.CourseWebVo;
import com.kerwin.eduService.entity.vo.CourseInfoVo;
import com.kerwin.eduService.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-11
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo PublishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
