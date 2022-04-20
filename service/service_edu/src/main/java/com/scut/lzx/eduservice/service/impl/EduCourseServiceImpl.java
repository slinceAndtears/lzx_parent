package com.scut.lzx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduChapter;
import com.scut.lzx.eduservice.entity.EduCourse;
import com.scut.lzx.eduservice.entity.EduCourseDescription;
import com.scut.lzx.eduservice.entity.EduVideo;
import com.scut.lzx.eduservice.entity.vo.CourseInfoVo;
import com.scut.lzx.eduservice.entity.vo.CoursePublishVo;
import com.scut.lzx.eduservice.mapper.EduCourseMapper;
import com.scut.lzx.eduservice.service.EduChapterService;
import com.scut.lzx.eduservice.service.EduCourseDescriptionService;
import com.scut.lzx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.eduservice.service.EduVideoService;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            logger.error("添加课程信息失败, courseInfoVo is {}", courseInfoVo);
            throw new MyException(20001, "添加课程信息失败");
        }
        //获取添加之后  课程描述的id需要与课程id一一对应
        String cid = eduCourse.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        eduCourseDescriptionService.save(courseDescription);
        logger.info("save CourseDescription {}", courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfoById(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription byId = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo result = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, result);
        BeanUtils.copyProperties(byId, result);
        logger.info("get courseInfo {} by courseId {}", result, courseId);
        return result;
    }

    @Override
    public boolean updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        baseMapper.updateById(eduCourse);
        eduCourseDescriptionService.saveOrUpdate(eduCourseDescription);
        logger.info("update the courseInfo {}", courseInfoVo);
        return true;
    }

    @Override
    public CoursePublishVo getPublishInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        logger.info("get publish info {}  from courseId {}", publishCourseInfo, id);
        return publishCourseInfo;
    }

    @Override
    public boolean publishCourse(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        //TODO 后续Normal 需要改成枚举类型
        eduCourse.setStatus("Normal");
        eduCourse.setId(id);
        baseMapper.updateById(eduCourse);
        logger.info("publish course by id {}", id);
        return true;
    }

    @Override
    public List<EduCourse> getCourseList() {
        List<EduCourse> courseList = baseMapper.selectList(null);
        logger.info("select course list {}", courseList);
        return courseList;
    }

    @Override
    public boolean deleteCourse(String id) {
        //TODO 满足事务
        //删除小结
        eduVideoService.removeByCourseId(id);
        //删除章节);
        eduChapterService.removeByCourseId(id);
        //删除课程
        eduCourseDescriptionService.removeById(id);
        int i = baseMapper.deleteById(id);
        logger.info("delete course by id {}, delete rows  {}", id, i);
        return true;
    }
}
