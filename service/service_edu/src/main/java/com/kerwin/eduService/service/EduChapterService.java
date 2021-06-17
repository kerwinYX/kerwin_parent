package com.kerwin.eduService.service;

import com.kerwin.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kerwin.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-11
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
