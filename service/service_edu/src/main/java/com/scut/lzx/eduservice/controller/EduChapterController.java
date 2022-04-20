package com.scut.lzx.eduservice.controller;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.EduChapter;
import com.scut.lzx.eduservice.entity.chapter.ChapterVo;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.service.EduChapterService;
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
@CrossOrigin
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    private static final Logger logger = LoggerFactory.getLogger(EduChapterController.class);
    @Autowired
    private EduChapterService eduChapterService;


    @ApiOperation("根据课程id获取章节列表")
    @GetMapping("/getChapterVideo/{courseId}")
    public R getAllChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoList(courseId);
        return R.ok().data("allChapterVideo", chapterVoList);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        boolean tag = eduChapterService.addChapter(eduChapter);
        return R.ok();
    }

    @ApiOperation("根据id获取章节")
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter chapter = eduChapterService.getChapterById(chapterId);
        return R.ok().data("chapter", chapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        boolean tag = eduChapterService.updateChapter(eduChapter);
        //TODO 各种异常判空
        return R.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean tag = eduChapterService.deleteChapterById(chapterId);
        if (tag){
            return R.ok();
        }
        return R.error();
    }
}

