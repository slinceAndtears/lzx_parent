package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-14
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> topFourTeacher();
}
