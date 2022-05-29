package com.xzy._back.service;

import com.xzy._back.model.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy._back.model.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy._back.model.chapter.Chapter;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
public interface EduChapterService extends IService<EduChapter> {
    // 课程大纲列表，根据课程 ID 进行查询
    public List<Chapter> getChapterVideoByCourseId(String courseId);
    // 删除课程章节
    public boolean deleteChapter(String chapterId);
}

