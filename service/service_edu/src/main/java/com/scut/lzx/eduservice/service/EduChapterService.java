package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scut.lzx.eduservice.entity.chapter.ChapterVo;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoList(String courseId);

    boolean addChapter(EduChapter eduChapter);

    EduChapter getChapterById(String chapterId);

    boolean updateChapter(EduChapter eduChapter);

    boolean deleteChapterById(String chapterId);

    void removeByCourseId(String id);
}
