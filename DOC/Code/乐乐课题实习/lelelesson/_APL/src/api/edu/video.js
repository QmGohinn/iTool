import request from '@/utils/request'
export default {
    // 1、添加课程小节
    addVideo(video) {
        return request({
            url: '/eduservice/video/addVideo',
            method: 'post',
            data: video
        })
    },
    // 2、删除课程小节
    deleteVideo(id) {
        return request({
            url: `/eduservice/video/${id}`,
            method: 'delete'
        })
    },
    // 3、根据课程小节 ID 查询课程小节信息
    getVideo(id) {
        return request({
            url: `/eduservice/video/getVideoInfo/${id}`,
            method: 'get'
        })
    },
    // 4、修改课程小节信息
    updateVideo(video) {
        return request({
            url: '/eduservice/video/updateVideo',
            method: 'post',
            data: video
        })
    },
    // 删除课程小节的视频
    deleteAliyunVideo(videoSourceId) {
        return request({
            url: `/eduvideo/video/deleteAliyunVideo/${videoSourceId}`,
            method: 'delete'
          })
    }
}