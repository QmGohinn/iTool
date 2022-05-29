import request from '@/utils/request'
export default {
    // 1、讲师上列表（分页条件查询）
    /*
     * current：当前页
     * limit：每页记录数
     * teacherQuery：条件对象
    */
    getTeachersList(current, limit, teacherQuery) {
        return request({
            // url: '/eduservice/teacher/pageTeacherCondition/' + current + '/' + limit,
            url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            // teacherQuery：条件对象，后端使用 RequestBody 获取数据
            // data 表示把对象转换为 json，进行传递给接口
            data: teacherQuery
        })
    },
      // 删除讲师
      deleteTeacherById(id){
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete'
        })
    },
     // 添加讲师
     addTeacher(teacher) {
        return request({
            url: `/eduservice/teacher/addTeacher`,
            method: 'post',
            data: teacher
        })
    },
     // 根据讲师 ID 查询讲师信息
    getTeacherInfoById(id) {
        return request({
            url: `/eduservice/teacher/getTeacher/${id}`,
            method: 'get',
        })
    },
    // 讲师修改
    updateTeacher(teacher) {
        return request({
            url: `/eduservice/teacher/updateTeacher`,
            method: 'post',
            data: teacher
        })
    }
}