package com.scut.lzx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduChapter;
import com.scut.lzx.eduservice.entity.EduCourse;
import com.scut.lzx.eduservice.entity.EduCourseDescription;
import com.scut.lzx.eduservice.entity.EduVideo;
import com.scut.lzx.eduservice.entity.chapter.ChapterVo;
import com.scut.lzx.eduservice.entity.chapter.VideoVo;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.mapper.EduChapterMapper;
import com.scut.lzx.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.eduservice.service.EduCourseDescriptionService;
import com.scut.lzx.eduservice.service.EduCourseService;
import com.scut.lzx.eduservice.service.EduVideoService;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    private static final Logger logger = LoggerFactory.getLogger(EduChapterServiceImpl.class);

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoList(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<EduVideo> list = eduVideoService.list(videoQueryWrapper);
        List<ChapterVo> result = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); ++i) {
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(eduChapters.get(i).getId());
            chapterVo.setTitle(eduChapters.get(i).getTitle());
            chapterVo.setChildren(new ArrayList<>());
            result.add(chapterVo);
        }
        for (int i = 0; i < list.size(); ++i) {
            String id = list.get(i).getChapterId();
            for (int j = 0; j < result.size(); ++j) {
                if (id.equals(result.get(j).getId())) {
                    VideoVo videoVo = new VideoVo();
                    videoVo.setId(list.get(i).getId());
                    videoVo.setTitle(list.get(i).getTitle());
                    result.get(j).getChildren().add(videoVo);
                }
            }
        }
        logger.info("get all chapter and video list {}", result);
        return result;
    }

    @Override
    public boolean addChapter(EduChapter eduChapter) {
        int insert = baseMapper.insert(eduChapter);
        logger.info("save Chapter {}", eduChapter);
        return true;
    }

    @Override
    public EduChapter getChapterById(String chapterId) {
        EduChapter chapter = baseMapper.selectById(chapterId);
        logger.info("get Chapter {}  by Id {}", chapter, chapterId);
        return chapter;
    }

    @Override
    public boolean updateChapter(EduChapter eduChapter) {
        int i = baseMapper.updateById(eduChapter);
        logger.info("update chapter {}", eduChapter);
        return i == 1;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        //List<EduVideo> list = eduVideoService.list(queryWrapper);
        int count = eduVideoService.count(queryWrapper);
        if (count == 0) {
            int i = baseMapper.deleteById(chapterId);
            return i == 1;
        }
        logger.error("章节 {} 包含小结，无法删除", chapterId);
        throw new MyException(20001, "该章节包含小结，目前无法删除");
    }

    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        int delete = baseMapper.delete(wrapper);
        logger.info("remove Chapter by course id {},  delete rows {}", id, delete);
    }
}