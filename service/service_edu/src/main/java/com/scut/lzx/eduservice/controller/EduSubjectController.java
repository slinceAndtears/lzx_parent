package com.scut.lzx.eduservice.controller;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.subject.OneSubject;
import com.scut.lzx.eduservice.service.EduSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-19
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    private static final Logger logger = LoggerFactory.getLogger(EduSubjectController.class);
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        boolean res=eduSubjectService.saveSubject(file);
        return res?R.ok():R.error();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list=eduSubjectService.getAllSubject();
        logger.info("get all subject info");
        return R.ok().data("list",list);
    }
}

