package com.xzy._back.mapper;

import com.xzy._back.model.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzy._back.model.vo.CoursePublish;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublish getPublishCourseInfo(String courseId);
}
