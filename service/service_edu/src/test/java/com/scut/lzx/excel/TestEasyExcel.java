package com.scut.lzx.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    private String fileName = "E:/write.xlsx";

    @Test
    public void testWrite() {
        List<ExcelData> list = getData();
        EasyExcel.write(fileName, ExcelData.class).sheet("学生列表").doWrite(list);
    }

    @Test
    public void testRead() {
        EasyExcel.read(fileName, ExcelData.class, new ExcelListener()).sheet().doRead();
    }

    public static List<ExcelData> getData() {
        List<ExcelData> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            ExcelData data = new ExcelData();
            data.setSno(i);
            data.setSname("lizixing" + i);
            list.add(data);
        }
        return list;
    }
}
