package com.xzy._back.service;

import com.xzy._back.model.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
import com.xzy._back.model.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy._back.model.vo.CourseInfoForm;
import com.xzy._back.model.vo.CoursePublish;

public interface EduCourseService extends IService<EduCourse> {
    // 添加课程基本信息
    public String saveCourseInfo(CourseInfoForm courseInfoForm);
    // 根据课程 ID 查询课程基本信息
    public CourseInfoForm getCourseInfo(String courseId);
    // 修改课程信息
    public void updateCourseInfo(CourseInfoForm courseInfoForm);
    // 根据Id查找信息
    public CoursePublish publishCourseInfo(String courseId);
    // 删除课程
    public void deleteCourse(String courseId);
}