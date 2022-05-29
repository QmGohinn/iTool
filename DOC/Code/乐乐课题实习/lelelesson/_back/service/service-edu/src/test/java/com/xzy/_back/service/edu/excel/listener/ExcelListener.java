package com.xzy._back.service.edu.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xzy._back.service.edu.excel.model.DemoData;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    // 一行一行读取 excel 表格中的数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("***** " + demoData);
    }
    // 读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }
    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}