import request from '@/utils/request'
export default {
    // 1、添加课程信息
    addCourseInfo(courseInfo) {
        return request({
            url: '/eduservice/course/addCourseInfo',
            method: 'post',
            data: courseInfo
        })
    },
    // 2、查询所有讲师
    getAllTeachersList() {
        return request({
            url: '/eduservice/teacher/findAll',
            method: 'get'
        })
    },
    // 3、根据课程 ID 查询课程基本信息
    getCourseInfo(courseId) {
        return request({
            url: `/eduservice/course/getCourseInfo/${courseId}`,
            method: 'get'
        })
    },
    // 4、修改课程信息
    updateCourseInfo(courseInfoForm) {
        return request({
            url: '/eduservice/course/updateCourseInfo',
            method: 'post',
            data: courseInfoForm
        })
    },
    getPublishCourseInfo(courseId){
        return request({
            url:'/eduservice/course/getPublishCourseInfo/${courseId}',
            method: 'get'
        })
    },
    publishCourse(courseId){
        return request({
            url:`/eduservice/course/publishCourse/${courseId}`,
            method:'post'
        }) 
    },
    // 分页查询课程列表
    getListCourse(current, limit) {
        return request({
            url: `/eduservice/course/pageListCourse/${current}/${limit}`,
            method: 'get'
        })
    },
    // 删除课程
    deleteCourse(courseId) {
        return request({
            url: `/eduservice/course/${courseId}`,
            method: 'delete'
        })
    }
}