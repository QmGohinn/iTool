<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-bottom: 40px">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>
    <el-button class="elButton" type="success" @click="openChapterDialog()">添加课程章节</el-button>
    <!-- 章节 -->
    <ul class="chanpterList">
      <li v-for="chapter in chapterVideoList" :key="chapter.id">
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button class="elButton" type="success" @click="openVideo(chapter.id)">添加小节</el-button>
            <el-button class="elButton" type="primary" @click="openEditChapter(chapter.id)">编辑</el-button>
            <el-button class="elButton" type="danger" @click="deleteChapter(chapter.id)">删除</el-button>
          </span>
        </p>
        <!-- 视频 -->
        <ul class="chanpterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>
              {{ video.title }}
              <el-button type="primary" round @click="openEditVideo(video.id)">编辑</el-button>
              <el-button type="danger" @click="deleteVideo(video.id)">删除</el-button>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button class="elButton" :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>
    
    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number
            v-model="chapter.sort"
            :min="0"
            controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button class="elButton" @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button class="elButton" type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加和修改课程小节表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" controls-position="right" />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.free">
            <el-radio :label="true" >免费</el-radio>
            <el-radio :label="false" >默认</el-radio>
          </el-radio-group>
        </el-form-item>


      <el-form-item label="上传视频">
        <!-- TODO -->
        <el-upload
        :on-success="handleVideoUploadSuccess"
        :on-remove="handleVideoRemove"
        :before-remove="beforeVideoRemove"
        :on-exceed="handleUploadExceed"
        :file-list="fileList"
        :action="BASE_API + `/eduvideo/video/uploadAliyunVideo`"
        :limit="1"
        class="upload-demo">
        <el-button size="small" type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
        <el-tooltip placement="right-end">
          <div slot="content">
            最大支持1G，<br />
            支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br />
            GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br />
            MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br />
            SWF、TS、VOB、WMV、WEBM 等视频格式上传
          </div>
          <i class="el-icon-question" />
        </el-tooltip>
      </el-upload>
      </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <!-- :disabled="saveVideoBtnDisabled" -->
        <el-button type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import chapter from "@/api/edu/chapter";
import video from "@/api/edu/video";

export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseId: "",
      chapterVideoList: [],
      chapter: {
        // 封装课程章节数据
        title: "",
        sort: 0,
      },
      video: {
        title: "",
        sort: 0,
        free: 0,
        videoSourceId: "",      // 视频在阿里云的 id
        videoOriginalName: "",  // 视频名称
      },
      dialogChapterFormVisible: false, // 添加、修改课程章节的弹出框
      dialogVideoFormVisible: false, // 添加、修改课程小节的弹出框

      fileList: [], //上传文件列表
      BASE_API: process.env.BASE_API, // 接口API地址

    };
  },
  created() {
    console.log("chapter created");
    if(this.$route.params && this.$route.params.id)
    {
      this.courseId = this.$route.params.id;
      this.getChapterVideos();
    }
  },
  methods: {
      // 删除课程小节对应的视频
      handleVideoRemove() {
      // 调用接口的删除视频的方法
      video.deleteAliyunVideo(this.video.videoSourceId).then((response) => {
        // 提示信息
        this.$message({
          type: "success",
          message: "删除视频成功!",
        });
        // 把文件列表清空
        this.fileList = [];
        // 把video视频id和视频名称值清空
        // 上传视频id赋值
        this.video.videoSourceId = "";
        // 上传视频名称赋值
        this.video.videoOriginalName = "";
      });
    },
    // 点击×调用这个方法
    beforeVideoRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //上传视频成功调用的方法
    handleVideoUploadSuccess(response, file, fileList) {
      // 上传视频id赋值
      this.video.videoSourceId = response.data.videoId;
      // 上传视频名称赋值
      this.video.videoOriginalName = file.name;
      console.log("列表：" + this.fileList);
    },
    handleUploadExceed() {
      this.$message.warning("想要重新上传视频，请先删除已上传的视频");
    },
    // 添加课程小节弹框
    openVideo(chapterId) {
      // 弹出框显示
      this.dialogVideoFormVisible = true;
      // 清空form表单
      this.video = {};
      // 设置课程小节对应的课程章节id
      this.video.chapterId = chapterId;
    },
    // 修改课程小节弹出框
    openEditVideo(id) {
      // 弹出框显示
      this.dialogVideoFormVisible = true;
      // 数据回显，查询课程小节
      video.getVideo(id).then((response) => {
        this.video = response.data.eduVideo;
      });
    },
    saveOrUpdateVideo() {
      if (!this.video.id) {
        // 调用添加课程小节的方法
        this.addVideo();
      } else {
        // 调用修改课程小节的方法
        this.updateVideo();
      }
    },
    // 添加课程小节
    addVideo() {
      // 设置课程小节对应的课程id
      this.video.courseId = this.courseId;
      video.addVideo(this.video).then((response) => {
        // 1、关闭弹出框
        this.dialogVideoFormVisible = false;
        // 2、提示信息
        this.$message({
          type: "success",
          message: "添加课程小节成功!",
        });
        // 2、刷新页面
        this.getChapterVideos();
      });
    },
    // 删除课程小节
    deleteVideo(id) {
      this.$confirm("此操作将永久删除课程小节记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 点击确认按钮，执行 then 方法
        video.deleteVideo(id).then((response) => {
          // 删除成功
          // 1、提示信息
          this.$message({
            type: "success",
            message: "删除课程小节成功!",
          });
          // 2、刷新页面
          this.getChapterVideos();
        });
      }); // 点击取消按钮，执行 catch 方法
    },
    // 修改课程小节
    updateVideo() {
      video.updateVideo(this.video).then((response) => {
        // 1、关闭弹出框
        this.dialogVideoFormVisible = false;
        // 2、提示信息
        this.$message({
          type: "success",
          message: "修改课程小节成功！",
        });
        // 3、刷新页面
        this.getChapterVideos();
      });
    },
    getChapterVideos(){
      chapter.getAllChapterVideo(this.courseId).then((response) =>{
        this.chapterVideoList = response.data.chapterVideoList;
      });
    },
    next() {
      console.log("next");
      this.$router.push({ path: "/course/publish/" + this.courseId });
    },
    previous() {
      this.$router.push({ path: "/course/info/" + this.courseId});
    },
    // 弹出添加课程章节页面    
    openChapterDialog() {
      this.dialogChapterFormVisible = true;
      this.chapter = {};
    },
    // 添加课程章节
    addChapter(){
      // 设置课程 id 到 chapter 对象中
      this.chapter.courseId = this.courseId;
      chapter.addChapter(this.chapter).then((response) => {
      // 1、关闭弹出框
      this.dialogChapterFormVisible = false;
      // 2、提示信息
      this.$message({
        type: "success",
        message: "添加课程章节成功！",
      });
        // 3、刷新页面
        this.getChapterVideos();
      });
    },
    // 修改课程章节弹出框数据回显
    openEditChapter(chapterId){
      this.dialogChapterFormVisible = true; // 弹框显示
      chapter.getChapter(chapterId)
        .then(response =>{
          this.chapter = response.data.chapter
      })
    },
    // 修改课程章节信息
    updateChapter() {
      chapter.updateChapter(this.chapter).then((response) => {
        // 1、关闭弹出框
        this.dialogChapterFormVisible = false;
        // 2、提示信息
        this.$message({
          type: "success",
          message: "修改课程章节成功！",
        });
        // 3、刷新页面
        this.getChapterVideos();
      });
    },
    saveOrUpdate() {
      if (!this.chapter.id) {  // 添加课程章节
        this.addChapter();
      } else {  // 修改课程章节
        this.updateChapter();
      }
    },
    // 删除课程章节
    deleteChapter(chapterId) {
      // 删除讲师的方法
      this.$confirm("此操作将永久删除课程章节记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 点击确认按钮，执行 then 方法
        chapter.deleteChapter(chapterId).then((response) => {
          // 删除成功
          // 1、提示信息
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          // 2、刷新页面
          this.getChapterVideos();
        });
      }); // 点击取消按钮，执行 catch 方法
    },
  },
};
</script>

<style scoped>
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li {
  position: relative;
}
.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}
.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
}
</style>