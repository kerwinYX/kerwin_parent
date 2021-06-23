package com.kerwin.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kerwin.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
