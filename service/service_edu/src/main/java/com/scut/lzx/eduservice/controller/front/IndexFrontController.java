package com.scut.lzx.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.EduCourse;
import com.scut.lzx.eduservice.entity.EduTeacher;
import com.scut.lzx.eduservice.service.EduCourseService;
import com.scut.lzx.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: lzx_parent
 * @description: front controller
 * @author: Zi-Xing Li
 * @create: 2022-04-26 15:14
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    private static final Logger logger = LoggerFactory.getLogger(IndexFrontController.class);

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("获取前端首界面的信息")
    @GetMapping("index")
    public R index() {
        List<EduCourse> eduCourseList = eduCourseService.topEightCourse();
        List<EduTeacher> list = eduTeacherService.topFourTeacher();
        return R.ok().data("eduList", eduCourseList).data("teacherList", list);
    }
}

