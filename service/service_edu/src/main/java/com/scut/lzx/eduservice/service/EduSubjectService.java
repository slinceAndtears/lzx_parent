package com.scut.lzx.eduservice.service;

import com.scut.lzx.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    boolean saveSubject(MultipartFile file);
}
