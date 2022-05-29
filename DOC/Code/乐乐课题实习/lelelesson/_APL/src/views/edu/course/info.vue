<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：SrpingBoot + MybatisPlus + Vue：从零基础到搭建项目。专业名称注意大小写"/>
      </el-form-item>
      <!-- 所属分类 TODO -->
      <!-- 一级分类 -->
      <el-form-item label="课程分类">
        <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="firstSubjectChanged">
          <el-option
            v-for="subject in firstSubjectList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in secondSubjectList"
            :key="subject.value"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
      </el-form-item>
      <!-- 课程讲师 TODO -->
      <!-- 课程讲师 TODO -->
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"/>
      </el-form-item>
      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <tinymce :height="400" ref="content" v-model="courseInfo.description" />
      </el-form-item>
      <!-- 课程封面 TODO -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :auto-upload="true"
          :on-success="uploadSuccess"
          :before-upload="uploadBefore"
          :action="BASE_API + '/eduoss/file/upload'"
          class="avatar-uploader">
          <img :src="courseInfo.cover" />
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"/> 元
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import course from "@/api/edu/course";
import subject from "@/api/edu/subject";
import Tinymce from  "@/components/Tinymce"
export default {
  components:{Tinymce},
  data() {
    return {
      BASE_API: process.env.BASE_API, // 接口 API 地址
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfo: {
        title: "",
        subjectId: "",
        courseId: "", // 课程 ID
        teacherId: "",
        lessonNum: 0,
        subjectParentId: "",
        description: "",
        cover: "/static/default.jpg",
        price: 0,
      },
      teacherList: [],  // 封装所有讲师
      firstSubjectList: [],
      secondSubjectList: []
    };
  },
  created() {
    this.init();
  },
  watch: {
    // 监听路由
    $route(to, from) {
      // 路由变化的方式，路由发生变化，方法就会执行
      this.init();
    },
  },
  methods: {
    init() {
    // 获取路由中的课程 ID 值，做课程修改——数据回显
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id;
      this.getCourseInfo();
    } else {
      // 点击发布课程
      // 清空表单
      this.courseInfo = {
        title: "",
        subjectId: "", // 课程二级分类 ID
        subjectParentId: "", // 课程一级分类ID
        teacherId: "",
        lessonNum: 0,
        description: "",
        cover: "/static/default.jpg",
        price: 0,
      };
      this.getAllTeachers(); // 初始化所有讲师
      this.getFirstSubject(); // 初始化所有课程一级分类
      // 清空课程简介文本编辑器中的内容
      this.$refs.content = "";
      }
    },
    // 根据课程 ID 查询课程基本信息
        // 根据课程 ID 查询课程基本信息
    getCourseInfo(){
      course.getCourseInfo(this.courseId)
        .then(response=>{
          // 在 courseInfo 中含有：课程基本信息、课程一级分类 ID、课程二级分类 ID
          this.courseInfo = response.data.courseInfoForm
          // 1、查询所有课程一级分类、二级分类
          subject.getSubjectList()
            .then(response =>{
              // 2、获取所有课程一级分类
              this.firstSubjectList = response.data.list
              // 3、在所有课程一级分类中查找当前 courseInfo 中课程一级分类
              for(var i=0; i<this.firstSubjectList.length; i++){
                var firstSubject = this.firstSubjectList[i]
                if(firstSubject.id === this.courseInfo.subjectParentId){
                  this.secondSubjectList = firstSubject.children
                }
              }
          })
          this.getAllTeachers(); // 初始化所有讲师
        })
    },
    // 上传课程封面成功调用
    uploadSuccess(response, file) {
      this.courseInfo.cover = response.data.url
    },
    // 上传课程封面之前调用，规定文件格式和大小
    uploadBefore(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    firstSubjectChanged(value){
      for(var i = 0; i < this.firstSubjectList.length; i++)
      {
        var firstSubject = this.firstSubjectList[i];
        if(value === firstSubject.id)
        {
          this.secondSubjectList = firstSubject.children;
          this.courseInfo.courseId = "";
        }
      }
    },
    getFirstSubject(){
      subject.getSubjectList().then(response =>{
        this.firstSubjectList = response.data.list
      })
    },
    getAllTeachers(){  // 查询所有讲师
      course.getAllTeachersList()
        .then(response =>{
          this.teacherList = response.data.items
        })
    },
    addCourse() {
    course.addCourseInfo(this.courseInfo).then((response) => {
      // 提示信息
      this.$message({
        type: "success",
        message: "添加课程信息成功！",
      });
      // 跳转到第二步
      this.$router.push({
        path: "/course/chapter/" + response.data.courseId,
      });
    });
  },
    updateCourse() {
    course.updateCourseInfo(this.courseInfo).then((response) => {
      // 提示信息
      this.$message({
        type: "success",
        message: "修改课程信息成功！",
      });
      // 跳转到第二步
      this.$router.push({
        path: "/course/chapter/" + this.courseId,
      });
      });
    },
    saveOrUpdate() {
      // 判断进行的是课程添加还是课程修改操作，条件：courseInfo.id 是否存在
      if (!this.courseInfo.id) {
        // 添加课程
        this.addCourse();
      } else {
        // 修改课程
        this.updateCourse();
      }
    },
  },
};
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
