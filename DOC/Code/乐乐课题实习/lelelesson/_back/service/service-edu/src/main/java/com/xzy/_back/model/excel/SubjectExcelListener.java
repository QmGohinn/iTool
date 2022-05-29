package com.xzy._back.model.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzy._back.service.base.exceptionhandler.MyException;
import com.xzy._back.model.EduSubject;
import com.xzy._back.service.EduSubjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    // 因为 SubjectExcelListener 不能交给 Spring 管理，需要自己 new，不能注入其他对象
    // 不能实现数据库操作
    public EduSubjectService subjectService;
    // 读取 excel 数据，一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new MyException(20001, "文件数据为空！");
        }
        // 一行一行进行读取，每次读取两个值，第一个是一级分类，第二个是二级分类
        // 添加一级分类
        // 判断一级分类是否重复
        EduSubject firstType = this.existFirstType(subjectService, subjectData.getFirstTypeName());
        if(firstType == null) { // 没有相同一级分类，进行添加
            firstType = new EduSubject();
            firstType.setParentId("0");
            firstType.setTitle(subjectData.getFirstTypeName());
            subjectService.save(firstType);
        }
        String pid = firstType.getId();  // 获取一级分类的 id 值
        // 添加二级分类
        // 判断二级分类是否重复
        EduSubject secondType = this.existSecondType(subjectService, subjectData.getSecondTypeName(), pid);
        if(secondType == null) { // 没有相同二级分类，进行添加
            secondType = new EduSubject();
            secondType.setParentId(pid);
            secondType.setTitle(subjectData.getSecondTypeName()); // 二级分类名称
            subjectService.save(secondType);
        }
    }
    // 判断一级分类不能重复添加
    private EduSubject existFirstType(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject firstType = subjectService.getOne(wrapper);
        return firstType;
    }
    // 判断二级分类不能重复添加
    private EduSubject existSecondType(EduSubjectService subjectService, String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject secondType = subjectService.getOne(wrapper);
        return secondType;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}