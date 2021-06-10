package com.kerwin.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kerwin.eduService.entity.EduSubject;
import com.kerwin.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
