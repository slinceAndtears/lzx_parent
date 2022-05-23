package com.scut.lzx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduTeacher;
import com.scut.lzx.eduservice.mapper.EduTeacherMapper;
import com.scut.lzx.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-14
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    private static final Logger logger = LoggerFactory.getLogger(EduTeacherServiceImpl.class);

    @Cacheable(value = "teacher", key = "'selectTeacherList'")
    @Override
    public List<EduTeacher> topFourTeacher() {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(teacherQueryWrapper);
        logger.info("select teacher list {}", eduTeachers);
        return eduTeachers;
    }
}
