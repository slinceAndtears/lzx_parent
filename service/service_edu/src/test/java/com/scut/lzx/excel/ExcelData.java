package com.scut.lzx.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExcelData implements Serializable {
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}