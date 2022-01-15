package com.scut.lzx.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.EduTeacher;
import com.scut.lzx.eduservice.entity.vo.TeacherQueryVo;
import com.scut.lzx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-14
 */
@RestController//用于返回json数据
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    private static final Logger logger = LoggerFactory.getLogger(EduTeacherController.class);
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "获取所有讲师信息")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据id删除讲师信息")
    @DeleteMapping("deleteById/{id}")
    public R deleteTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return b ? R.ok() : R.error();
    }

    @ApiOperation(value = "分页查询所有讲师信息")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@ApiParam(name = "current", value = "当前页数", required = true) @PathVariable long current,
                         @ApiParam(name = "limit", value = "每页多少数据", required = true) @PathVariable long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, null);
        return R.ok().data("items", page).data("total", page.getTotal());
    }

    @ApiOperation(value = "根据条件分页查询")
    @PostMapping("pageTeacherCondition")
    //RequestBody 帮我们把json数据封装到对象中 必须使用post提交
    public R pageTeacherCondition(
            @ApiParam(name = "条件查询的封装类") @RequestBody TeacherQueryVo teacherQueryVo) {
        Page<EduTeacher> page = new Page<>(teacherQueryVo.getCurrent(), teacherQueryVo.getLimit());

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        eduTeacherService.page(page, wrapper);
        return R.ok().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "讲师信息") @RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        return save ? R.ok() : R.error();
    }

    @ApiOperation(value = "根据id获取teacher")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("teacher", byId);
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        return b ? R.ok() : R.error();
    }
}