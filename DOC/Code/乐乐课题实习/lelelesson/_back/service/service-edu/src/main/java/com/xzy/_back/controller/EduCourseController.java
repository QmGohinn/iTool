package com.xzy._back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy._back.model.EduCourse;
import com.xzy._back.model.vo.CoursePublish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
import com.xzy._back.commonutils2.RestResult;
import com.xzy._back.model.vo.CourseInfoForm;
import com.xzy._back.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "添加课程")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public RestResult addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return RestResult.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据课程 ID 查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public RestResult getCourseInfo(
            @ApiParam(name = "courseId", value = "课程 ID", required = true)
            @PathVariable String courseId) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfo(courseId);
        return RestResult.ok().data("courseInfoForm", courseInfoForm);
    }
    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public RestResult updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        courseService.updateCourseInfo(courseInfoForm);
        return RestResult.ok().data("courseInfoForm", courseInfoForm);
    }
    @ApiOperation(value = "课程确认信息显示")
    @GetMapping("getPublishCourseInfo/{courseId}")
    public RestResult getPublishCourseInfo(
            @ApiParam(name = "courseId", value = "课程 ID", required = true)
            @PathVariable String courseId)
    {
        CoursePublish coursePublish = courseService.publishCourseInfo(courseId);
        return RestResult.ok().data("publishCourse", coursePublish);
    }
    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{courseId}")
    public RestResult publishCourse(
            @ApiParam(name = "courseId", value = "课程 ID", required = true)
            @PathVariable String courseId)
    {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return RestResult.ok();
    }

    // TODO 完善条件查询带分页
    @ApiOperation(value = "查询课程信息带分页")
    @GetMapping("pageListCourse/{current}/{limit}")
    public RestResult pageListCourse(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_modified");
        Page<EduCourse> pageCourse = new Page<EduCourse>(current, limit);
        courseService.page(pageCourse, wrapper);
        long total = pageCourse.getTotal();  // 获取总记录数
        List<EduCourse> list = pageCourse.getRecords();  // 数据 list 集合
        return RestResult.ok().data("total", total).data("rows", list);
    }
    @ApiOperation(value = "删除课程")
    @DeleteMapping("{courseId}")
    public RestResult deleteCourse(
            @ApiParam(name = "courseId", value = "课程 ID", required = true)
            @PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return RestResult.ok();
    }
}
