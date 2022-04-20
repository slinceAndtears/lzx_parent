package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoById(String courseId);

    boolean updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishInfo(String id);

    boolean publishCourse(String id);

    List<EduCourse> getCourseList();

    boolean deleteCourse(String id);
}
