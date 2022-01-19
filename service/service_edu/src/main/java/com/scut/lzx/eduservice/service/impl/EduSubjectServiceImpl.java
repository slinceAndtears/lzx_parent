package com.scut.lzx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduSubject;
import com.scut.lzx.eduservice.entity.excel.SubjectData;
import com.scut.lzx.eduservice.listener.SubjectExcelListener;
import com.scut.lzx.eduservice.mapper.EduSubjectMapper;
import com.scut.lzx.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zixing-li
 * @since 2022-01-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Override
    public boolean saveSubject(MultipartFile file) {
        try {
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
            logger.info("upload the excel file {} success", file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("upload the excel file {} failure", file);
            return false;
        }
    }

    //判断一级分类不能重复
    @Override
    public EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return eduSubjectService.getOne(wrapper);
    }

    @Override
    public EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return eduSubjectService.getOne(wrapper);
    }

}
