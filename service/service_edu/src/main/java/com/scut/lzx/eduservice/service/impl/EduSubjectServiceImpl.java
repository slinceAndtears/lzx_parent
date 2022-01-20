package com.scut.lzx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduSubject;
import com.scut.lzx.eduservice.entity.excel.SubjectData;
import com.scut.lzx.eduservice.entity.subject.OneSubject;
import com.scut.lzx.eduservice.entity.subject.TwoSubject;
import com.scut.lzx.eduservice.listener.SubjectExcelListener;
import com.scut.lzx.eduservice.mapper.EduSubjectMapper;
import com.scut.lzx.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    @Override
    public List<OneSubject> getAllSubject() {
        List<OneSubject> result = new ArrayList<>();
        List<EduSubject> allSubjectData = list(null);
        if (allSubjectData == null || allSubjectData.size() == 0) {
            logger.error("subject data is null");
            throw new MyException(20001, "subject data is null");
        }
        Map<String, Integer> existOne = new HashMap<>();
        for (EduSubject subject : allSubjectData) {
            if (subject.getParentId().equals("0")) {//是一级分类
                existOne.put(subject.getId(), result.size());
                OneSubject oneSubject = new OneSubject();
                oneSubject.setChildren(new ArrayList<>());
                oneSubject.setId(subject.getId());
                oneSubject.setTitle(subject.getTitle());
                result.add(oneSubject);
            }
        }
        for (EduSubject subject : allSubjectData) {
            if (!subject.getParentId().equals("0")) {
                TwoSubject twoSubject = new TwoSubject();
                twoSubject.setId(subject.getId());
                twoSubject.setTitle(subject.getTitle());
                result.get(existOne.get(subject.getParentId())).getChildren().add(twoSubject);
            }
        }
        logger.info("get the subject info {}", result);
        return result;
    }

}
