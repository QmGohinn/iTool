<template>
  <div class="app-container">
    <div id="top">
      <h2>课程列表</h2>
      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="courseQuery.title" placeholder="课程名称" />
        </el-form-item>
        <el-form-item>
          <el-select
            v-model="courseQuery.status"
            clearable
            placeholder="课程状态">
            <el-option value="Normal" label="已发布" />
            <el-option value="Draft" label="未发布" />
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getCourseList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
    </div>
    <!-- 表格 -->
    <el-table :data="courseList" border fit highlight-current-row>
      <el-table-column label="序号" width="50" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="课程名称" width="200" />
      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === "Normal" ? "已发布" : "未发布" }}
        </template>
      </el-table-column>
      <el-table-column prop="lessonNum" label="课时数" width="65" />
      <el-table-column prop="gmtModified" label="更新时间" width="160" />
      <el-table-column prop="viewCount" label="浏览量" width="65" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程基本信息</el-button>
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程大纲息</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteCourseByCourseId(scope.row.id)">删除课程信息</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getCourseList"/>
  </div>
</template>

<script>
//引入调用teacher.js文件
import course from "@/api/edu/course";
export default {
  data() {
    return {
      courseList: [], // 查询之后接口返回集合（课程列表）
      page: 1, // 当前页
      limit: 5, // 每页记录数
      total: 0, // 总记录数
      courseQuery: {}, // 条件封装对象
    };
  },
  created() {
    this.getCourseList();
  },
  methods: {
    // 删除课程
    deleteCourseByCourseId(courseId) {
      this.$confirm("此操作将永久删除课程记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 点击确认按钮，执行 then 方法
        course.deleteCourse(courseId).then((response) => {
          // 删除成功
          // 1、提示信息
          this.$message({
            type: "success",
            message: "删除课程成功!",
          });
          // 2、回到课程列表页面
          this.getCourseList();
        });
      }); // 点击取消按钮，执行 catch 方法
    },
    // 课程列表的方法
    // TODO 完善
    getCourseList(page=1) {
      this.page = page;
      course.getListCourse(this.page, this.limit).then((response) => {
        this.courseList = response.data.rows;
        this.total = response.data.total;
      });
    },
    // 情况查询条件的form表单
   // TODO 完善
    resetData() {
      // 表单输入项数据清空
      this.courseQuery = {};
      // 查询所有课程列表
      this.getCourseList();
    },
  },
};
</script>

<style scoped>
#top {
  text-align: center;
}
</style>
