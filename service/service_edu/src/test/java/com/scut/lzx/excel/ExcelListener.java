package com.scut.lzx.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelData> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        logger.info("read {} from excel", excelData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        logger.info("read the head {} data from excel", headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("read excel finish");
    }
}
