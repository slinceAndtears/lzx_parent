package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
