package com.xzy._back.service;

import com.xzy._back.model.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy._back.model.subject.FirstType;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface EduSubjectService extends IService<EduSubject> {
    // 添加课程分类
    public void saveSubject(MultipartFile file, EduSubjectService subjectService);
    // 课程分类列表（一级分类、一级分类对应的二级分类）
    public List<FirstType> getAllSubjects();
}