package com.scut.lzx.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.lzx.eduservice.entity.EduSubject;
import com.scut.lzx.eduservice.entity.excel.SubjectData;
import com.scut.lzx.eduservice.service.EduSubjectService;
import com.scut.lzx.servicebase.exceptionhandler.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private static final Logger logger = LoggerFactory.getLogger(SubjectExcelListener.class);

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.eduSubjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            logger.error("subject data is null");
            throw new MyException(20001, "subject data is null");
        }
        EduSubject eduSubject = eduSubjectService.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
            logger.info("add the one subject {} success", eduSubject);
        }

        EduSubject twoSubject = eduSubjectService.existTwoSubject(eduSubjectService,
                subjectData.getTwoSubjectName(), eduSubject.getId());
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(eduSubject.getId());
            eduSubjectService.save(twoSubject);
            logger.info("add the two subject {} success", twoSubject);
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
