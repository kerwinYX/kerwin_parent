package com.kerwin.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kerwin.commonutils.R;
import com.kerwin.eduService.entity.EduTeacher;
import com.kerwin.eduService.entity.vo.TeacherQuery;
import com.kerwin.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kerwin
 * @since 2021-06-07
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
public class EduTeacherController {

    private final EduTeacherService teacherService;

    public EduTeacherController(EduTeacherService eduTeacherService) {
        this.teacherService = eduTeacherService;
    }

    //查询讲师表中所有数据
    // rest风格
    @ApiOperation("查找所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("allTeachers", list);
    }

    //讲师逻辑删除
    //PathVariable 得到路径中的id值
    @ApiOperation("逻辑删除当期id讲师")
    @DeleteMapping("{id}")
    public R deleteTeacherById(@PathVariable String id) {
        boolean deleted = teacherService.removeById(id);
        if (deleted) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //  分页查询讲师方法
    @GetMapping("pageTeacher/{currentPage}/{size}")
    public R pageListTeacher(@PathVariable("currentPage") long currentPage, @PathVariable long size) {
        Page<EduTeacher> pageTeacher = new Page<>(currentPage, size);
        //  不带条件


        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> list = pageTeacher.getRecords();
//        Map<String, Object> map = new HashMap<>();
//        map.put("total",total);
//        map.put("teacherList",list);
//        return R.ok().data(map);

        return R.ok().data("total", total).data("teacherList", list);
    }

    /**
     * 带条件的分页查询
     * */
    @PostMapping("pageListTeacherByCondition/{currentPage}/{size}")
    public R pageListTeacherByCondition(@PathVariable("currentPage") long currentPage, @PathVariable long size,@RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(currentPage, size);
        //添加条件   可多条件组合查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断传进来的条件是否为空
        if (!StringUtils.isEmpty(name)) {
            wrapper.likeLeft("name",name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level",name);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",name);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified",name);
        }

        long total = pageTeacher.getTotal();
        List<EduTeacher> list = pageTeacher.getRecords();
        teacherService.page(pageTeacher, wrapper);
        return R.ok().data("total", total).data("teacherList", list);
    }

    /**
     * 添加对象
     * */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);

        if (save) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改对象
     * */
    @ApiOperation(value = "根据id查询对象")
    @PostMapping("find/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);

        return R.ok().data("teacher",teacher);
    }
    /**
     * 修改对象
     * */
    @ApiOperation(value = "根据id修改对象")
    @PostMapping("updateTeacher")
    public R updateTeacherById(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        }
        return  R.error();
    }



}

