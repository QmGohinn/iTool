package com.xzy._back.model.excel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    // 设置 Excel 表头名称
    @ExcelProperty(value = "课程一级分类名称", index = 0)
    private String firstTypeName;
    @ExcelProperty(value = "课程二级分类名称", index = 1)
    private String secondTypeName;
}