package com.scut.lzx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.commonutils.R;
import com.scut.lzx.eduservice.client.VodClient;
import com.scut.lzx.eduservice.entity.EduVideo;
import com.scut.lzx.eduservice.mapper.EduVideoMapper;
import com.scut.lzx.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean addVideo(EduVideo eduVideo) {
        int insert = baseMapper.insert(eduVideo);
        logger.info("insert video {}", eduVideo);
        return insert == 1;
    }

    @Override
    public boolean deleteVideo(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        if (!StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            R r = vodClient.removeAlyVideo(eduVideo.getVideoSourceId());
            if (r.getCode() == 20001) {
                logger.error("delete video failure, the videos source id is {}", eduVideo.getVideoSourceId());
                throw new MyException(20001, "删除视频失败");
            }
            logger.info("remove video by id {}", eduVideo.getVideoSourceId());
        }
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
