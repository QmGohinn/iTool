package com.xzy._back.service.impl;

import com.xzy._back.model.EduChapter;
import com.xzy._back.mapper.EduChapterMapper;
import com.xzy._back.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzy._back.service.base.exceptionhandler.MyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 吴斌
 * @since 2022-01-06
 */
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzy._back.model.EduChapter;
import com.xzy._back.mapper.EduChapterMapper;
import com.xzy._back.model.EduVideo;
import com.xzy._back.model.chapter.Chapter;
import com.xzy._back.model.chapter.Video;
import com.xzy._back.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzy._back.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;
    // 课程大纲列表，根据课程 ID 进行查询
    @Override
    public List<Chapter> getChapterVideoByCourseId(String courseId) {
        // 1、根据课程 ID 查询课程中的所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<EduChapter>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        // 2、根据课程 ID 查询课程中的所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<EduVideo>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.orderByAsc("title");
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        ArrayList<Chapter> chapterList = new ArrayList<Chapter>(); // 创建 List 集合，用于最终封装数据
        // 3、遍历章节 List 集合，进行封装
        for (EduChapter eduChapter : eduChapterList) {  // eduChapter：每个章节
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(eduChapter, chapter); // 将 EduChapter 对象的值复制到 Chapter 对象中
            chapterList.add(chapter); // 将 chapter 对象存到 chapterList 最终的集合中
            ArrayList<Video> videoList = new ArrayList<Video>(); // 创建集合，用于封装章节中的小节
            // 4、遍历小节 List 集合，进行封装
            for (EduVideo eduVideo : eduVideoList) {  // eduVideo：每个小节
                // 判断条件：小节的 chapter_id 和 章节的 id 是否相同
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    Video video = new Video();
                    BeanUtils.copyProperties(eduVideo, video); // 进行封装
                    videoList.add(video);
                }
            }
            chapter.setChildren(videoList); // 将封装之后的小节集合，存到章节对象中
        }
        return chapterList;
    }
    // 删除课程章节
    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据 chapterId（课程章节 id）查询课程小节表（edu_video），若能查询出数据，不进行删除操作
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) { // 能查询出课程小节，不进行删除
            throw new MyException(20001, "不能直接删除，请先删除该课程章节下的所有课程小节！");
        } else {   // 不能查询出课程小节，进行删除课程章节
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }
}
