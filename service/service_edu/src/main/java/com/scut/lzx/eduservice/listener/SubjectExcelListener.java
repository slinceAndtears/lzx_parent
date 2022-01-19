package com.scut.lzx.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.scut.lzx.eduservice.entity.excel.SubjectData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private static final Logger logger = LoggerFactory.getLogger(SubjectExcelListener.class);

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
