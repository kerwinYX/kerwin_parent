package com.kerwin.eduService.service.impl;

import com.kerwin.eduService.entity.EduCourse;
import com.kerwin.eduService.entity.EduCourseDescription;
import com.kerwin.eduService.entity.vo.CourseInfoVo;
import com.kerwin.eduService.mapper.EduCourseMapper;
import com.kerwin.eduService.service.EduCourseDescriptionService;
import com.kerwin.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kerwin.servicebase.exceptionhandler.KerwinException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-11
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //需要往两张表中添加信息  课程表  课程信息表

        //CourseInfoVo转化为EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        // 添加到课程表
        int insert = baseMapper.insert(eduCourse);
        if (insert<=0) {
            //添加课程失败，抛出异常
            throw new KerwinException(20001,"添加课程信息失败");
        }
        String pid = eduCourse.getId();

        // 添加到课程简介表
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setId(pid);
        courseDescriptionService.save(courseDescription);
        return pid;
    }
}
