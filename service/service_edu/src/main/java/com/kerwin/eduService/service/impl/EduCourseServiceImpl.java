package com.kerwin.eduService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kerwin.eduService.entity.EduCourse;
import com.kerwin.eduService.entity.EduCourseDescription;
import com.kerwin.eduService.entity.vo.CourseInfoVo;
import com.kerwin.eduService.entity.vo.CoursePublishVo;
import com.kerwin.eduService.mapper.EduCourseMapper;
import com.kerwin.eduService.service.EduChapterService;
import com.kerwin.eduService.service.EduCourseDescriptionService;
import com.kerwin.eduService.service.EduCourseService;
import com.kerwin.eduService.service.EduVideoService;
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


    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    //注入小节和章节service
    @Autowired
    private EduVideoService eduVideoService;


    @Autowired
    private EduChapterService chapterService;
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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //2.查询描述表
        EduCourseDescription description = courseDescriptionService.getById(courseId);

        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update==0) {
            throw  new KerwinException(20001,"修改课程失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo PublishCourseInfo(String id) {
        return baseMapper.getPublishVoInfo(id);
    }


        //删除课程
    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0) { //失败返回
            throw new KerwinException(20001,"删除失败");
        }
    }
}
