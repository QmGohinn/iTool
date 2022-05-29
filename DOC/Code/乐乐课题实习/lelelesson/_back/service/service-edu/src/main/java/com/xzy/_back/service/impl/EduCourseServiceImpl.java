package com.xzy._back.service.impl;

import com.xzy._back.model.EduCourse;
import com.xzy._back.mapper.EduCourseMapper;
import com.xzy._back.model.vo.CoursePublish;
import com.xzy._back.service.EduChapterService;
import com.xzy._back.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzy._back.service.EduVideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
import com.xzy._back.service.base.exceptionhandler.MyException;
import com.xzy._back.model.EduCourse;
import com.xzy._back.mapper.EduCourseMapper;
import com.xzy._back.model.EduCourseDescription;
import com.xzy._back.model.vo.CourseInfoForm;
import com.xzy._back.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    // 注入课程描述服务层对象
    @Autowired
    private EduCourseDescriptionServiceImpl courseDescriptionService;
    // 添加课程基本信息
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        // 1、向课程表 edu_course 中添加课程基本信息
        // 将 CourseInfoForm 对象转换为 EduCourse 对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0)   // 添加失败
            throw new MyException(20001, "添加课程失败！");
        // 获取添加成功后的课程 id
        String courseId = eduCourse.getId();
        // 2、向课程简介 edu_course_description 中添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        // 设置课程简介的 id，就是课程 id
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);
        return courseId;
    }

    // 根据课程 ID 查询课程基本信息
    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        // 1、根据课程 ID 查询课程表 edu_course
        EduCourse eduCourse = baseMapper.selectById(courseId);
        // 2、根据课程 ID 查询课程描述表 edu_course_description
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        // 3、将 eduCourse、courseDescription 对象信息封装到 CourseInfoForm 对象中
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }
    // 修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        // 1、将 courseInfoForm 对象拆分为 EduCourse、EduCourseDescription 对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        // 2、修改课程表 edu_course
        int update = baseMapper.updateById(eduCourse);
        if (update == 0)
            throw new MyException(20001, "修改课程信息失败");
        // 2、修改课程描述表 edu_course_description
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublish publishCourseInfo(String courseId)
    {
        CoursePublish res = baseMapper.getPublishCourseInfo(courseId);
        return res;
    }

    // 注入小节service
    @Autowired
    private EduVideoService eduVideoService;
    // 注入章节service
    @Autowired
    private EduChapterService chapterService;
    // 删除课程
    @Override
    public void deleteCourse(String courseId) {
        // 1、根据课程id删除小节
        eduVideoService.removeById(courseId);
        // 2、根据课程id删除章节
        chapterService.removeById(courseId);
        // 3、根据课程id删除描述
        courseDescriptionService.removeById(courseId);
        // 4、根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) { //失败返回
            throw new MyException(20001, "删除课程失败！");
        }
    }
}
