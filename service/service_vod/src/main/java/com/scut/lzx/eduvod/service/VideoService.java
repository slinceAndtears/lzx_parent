package com.scut.lzx.eduvod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    String uploadVideo(MultipartFile file);

    boolean removeByVideoId(String id);
}
