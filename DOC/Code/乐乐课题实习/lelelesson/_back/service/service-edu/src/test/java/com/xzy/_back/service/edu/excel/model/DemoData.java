package com.xzy._back.service.edu.excel.model;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    // 设置 Excel 表头名称
    @ExcelProperty(value = "学生 ID", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}