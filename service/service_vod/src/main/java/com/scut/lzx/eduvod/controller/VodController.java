package com.scut.lzx.eduvod.controller;

import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduvod.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: lzx_parent
 * @description: Vod Controller
 * @author: Zi-Xing Li
 * @create: 2022-04-21 14:27
 **/

@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VideoService videoService;


    @ApiOperation("上传视频到阿里云")
    @PostMapping("uploadAlyiVideo")
    public R uploadAliyunVideo(MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @ApiOperation("删除已上传的视频")
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        boolean tag = videoService.removeByVideoId(id);
        return R.ok();
    }
}
