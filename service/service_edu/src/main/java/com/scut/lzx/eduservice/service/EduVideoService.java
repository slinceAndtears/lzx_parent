package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean addVideo(EduVideo eduVideo);

    boolean deleteVideo(String id);

    void removeByCourseId(String id);
}
