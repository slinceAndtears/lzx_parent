package com.scut.lzx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduVideo;
import com.scut.lzx.eduservice.mapper.EduVideoMapper;
import com.scut.lzx.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private static final Logger logger = LoggerFactory.getLogger(EduVideoServiceImpl.class);

    @Override
    public boolean addVideo(EduVideo eduVideo) {
        int insert = baseMapper.insert(eduVideo);
        logger.info("insert video {}", eduVideo);
        return insert == 1;
    }

    @Override
    public boolean deleteVideo(String id) {
        int i = baseMapper.deleteById(id);
        logger.info("delete video by id {}", id);
        return i == 1;
    }

    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        int delete = baseMapper.delete(wrapper);
        logger.info("remove video by course id {}, delete rows   {}", id, delete);
    }
}
