package com.scut.lzx.eduvod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.scut.lzx.eduvod.service.VideoService;
import com.scut.lzx.eduvod.utils.AccessKey;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: lzx_parent
 * @description: VideoService
 * @author: Zi-Xing Li
 * @create: 2022-04-21 14:28
 **/
@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(AccessKey.ACCESS_KEY_ID,
                    AccessKey.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            logger.info("upload video to aliyun {}", response.getMessage());
            return response.getVideoId();
        } catch (IOException e) {
            logger.error("upload file failure ", e);
            throw new MyException(20001, "上传视频失败");
        }
    }

    @Override
    public boolean removeByVideoId(String id) {
        DefaultAcsClient client = AccessKey.initVodClient();
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(id);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            logger.info("delete video success  {}", response.getRequestId());
            return true;
        } catch (ClientException e) {
            logger.error("delete video failure ", e);
            throw new MyException(20001, "delete video failure ");
        }
    }
}
