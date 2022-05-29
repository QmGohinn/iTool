import request from '@/utils/request'
export default {
    // 1、根据课程 ID 获取课程中的所有章节和小节数据列表
    getAllChapterVideo(courseId) {
        return request({
            url: '/eduservice/chapter/getChapterVideo/' + courseId,
            method: 'get'
        })
    },

    // 添加课程章节
    addChapter(chapter) {
        return request({
            url: '/eduservice/chapter/addChapter',
            method: 'post',
            data: chapter
        })
    },
    // 修改课程章节信息
    updateChapter(chapter) {
        return request({
            url: '/eduservice/chapter/updateChapter',
            method: 'post',
            data: chapter
        })
    },
    // 删除课程章节
    deleteChapter(chapterId) {
        return request({
            url: `/eduservice/chapter/${chapterId}`,
            method: 'delete'
        })
    },
    // 根据课程章节 ID 查询课程章节信息
    getChapter(chapterId) {
        return request({
            url: `/eduservice/chapter/getChapterInfo/${chapterId}`,
            method: 'get'
        })
    }
}