package com.xzy._back.controller;


import com.xzy._back.model.EduChapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.model.chapter.Chapter;
import com.xzy._back.model.vo.CourseInfoForm;
import com.xzy._back.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
@Api(description = "课程大纲管理")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;
    @ApiOperation(value = "课程大纲列表")
    @GetMapping("getChapterVideo/{courseId}")
    public RestResult getChapterVideo(
            @ApiParam(name = "courseId", value = "课程 ID", required = true)
            @PathVariable String courseId) {
        List<Chapter> list = chapterService.getChapterVideoByCourseId(courseId);
        return RestResult.ok().data("chapterVideoList", list);
    }

    @ApiOperation(value = "添加课程章节")
    @PostMapping("addChapter")
    public RestResult addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return RestResult.ok();
    }
    @ApiOperation(value = "根据课程章节 ID 查询课程章节信息")
    @GetMapping("getChapterInfo/{chapterId}")
    public RestResult getChapterInfo(
            @ApiParam(name = "chapterId", value = "课程章节 ID", required = true)
            @PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return RestResult.ok().data("chapter", eduChapter);
    }
    @ApiOperation(value = "修改课程章节信息")
    @PostMapping("updateChapter")
    public RestResult updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return RestResult.ok();
    }
    @ApiOperation(value = "根据课程章节 ID 删除课程章节信息")
    @DeleteMapping("{chapterId}")
    public RestResult deleteChapter(
            @ApiParam(name = "chapterId", value = "课程章节 ID", required = true)
            @PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag)
            return RestResult.ok();
        else
            return RestResult.error();
    }

}

