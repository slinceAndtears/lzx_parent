package com.scut.lzx.eduservice.controller;


import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.entity.EduVideo;
import com.scut.lzx.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    private static final Logger logger = LoggerFactory.getLogger(EduVideoController.class);
    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean tag = eduVideoService.addVideo(eduVideo);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        boolean tag = eduVideoService.deleteVideo(id);
        logger.info("delete video {}, tag is {}", id, tag);
        return R.ok();
    }
}

