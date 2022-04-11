package com.scut.lzx.eduservice.service.impl;

import com.scut.lzx.eduservice.entity.EduCourse;
import com.scut.lzx.eduservice.entity.EduCourseDescription;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.mapper.EduCourseMapper;
import com.scut.lzx.eduservice.service.EduCourseDescriptionService;
import com.scut.lzx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Zi-Xing li
 * @since 2022-03-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            logger.error("添加课程信息失败, courseInfoVo is {}", courseInfoVo);
            throw new MyException(20001, "添加课程信息失败");
        }
        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(courseDescription);
    }
}
