package com.xzy._back.controller;

import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.model.subject.FirstType;
import com.xzy._back.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;
    @ApiOperation(value = "添加课程分类")
    @PostMapping("addSubject")
    public RestResult addSubject(MultipartFile file) { // 获取上传过来的文件，将文件内容读取出来
        // 上传过来的 excel 文件
        subjectService.saveSubject(file, subjectService);
        return RestResult.ok();
    }

    @ApiOperation(value = "课程分类列表（树形结构展示）")
    @GetMapping("getAllSubjects")
    public RestResult getAllSubjects(){
        List<FirstType> list = subjectService.getAllSubjects();
        return RestResult.ok().data("list", list);
    }
}