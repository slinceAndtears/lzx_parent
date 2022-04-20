package com.scut.lzx.eduservice.controller;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.EduCourse;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.entity.vo.CoursePublishVo;
import com.scut.lzx.eduservice.service.EduCourseService;
import com.scut.lzx.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    private static final Logger logger = LoggerFactory.getLogger(EduCourseController.class);
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @ApiOperation("根据课程查询课程的基本信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfo = eduCourseService.getCourseInfoById(courseId);
        return R.ok().data("courseInfoVo", courseInfo);
    }

    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        boolean res = eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @ApiOperation("获取课程发布的所有信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.getPublishInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    @ApiOperation("课程信息的最终发布")
    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        boolean tag = eduCourseService.publishCourse(id);
        return R.ok();
    }

    @ApiOperation("获取所有已发布的课程列表")
    @GetMapping("")
    public R getCourseList() {
        List<EduCourse> courseList = eduCourseService.getCourseList();
        return R.ok().data("list", courseList);
    }

    @ApiOperation("根据课程id删除课程相关的所有信息")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        boolean tag=eduCourseService.deleteCourse(courseId);
        return R.ok();
    }
}

