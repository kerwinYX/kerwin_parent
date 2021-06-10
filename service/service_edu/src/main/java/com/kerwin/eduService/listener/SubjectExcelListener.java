package com.kerwin.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kerwin.eduService.entity.EduSubject;
import com.kerwin.eduService.entity.excel.SubjectData;
import com.kerwin.eduService.service.EduSubjectService;
import com.kerwin.servicebase.exceptionhandler.KerwinException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //SubjectExcelListener不能依靠spring注入对象  不能实现数据库操作

    private EduSubjectService subjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel的内容
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (data==null) {
            throw new KerwinException(20001,"文件数据为空");
        }
        EduSubject titleOne = existTitleOne(subjectService, data.getOneSubjectName());
        if (titleOne==null) {
            titleOne = new EduSubject();
            titleOne.setTitle(data.getOneSubjectName());
            titleOne.setParentId("0");
            subjectService.save(titleOne);
        }
        String pid = titleOne.getId();
        EduSubject titleTwo = existTitleTwo(subjectService, data.getTwoSubjectName(), pid);
        if (titleTwo==null) {
            titleTwo = new EduSubject();
            titleTwo.setTitle(data.getTwoSubjectName());
            titleTwo.setParentId(pid);
            subjectService.save(titleTwo);
        }

    }
    //判断导入之时  一级标题是否重复
    private EduSubject existTitleOne(EduSubjectService subjectService,String title){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);
    }

    //判断导入之时  二级标题是否重复
    private EduSubject existTitleTwo(EduSubjectService subjectService,String title,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
