package com.scut.lzx.eduservice.controller;


import com.scut.lzx.eduservice.entity.EduTeacher;
import com.scut.lzx.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        return eduTeacherService.list(null);
    }

    @DeleteMapping("deleteById/{id}")
    public boolean deleteTeacher(@PathVariable String id){
        return eduTeacherService.removeById(id);
    }
}

