<template>
  <div class="app-container">
      <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名" />
      </el-form-item>
      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00" />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00" />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 数据表格 -->
    <el-table
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80" />
      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资历" />
      <el-table-column label="照片"  width="104">
        <template slot-scope="scope">
          <img  :src="scope.row.avatar" alt="" style="width: 80px">
        </template>
      </el-table-column>
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeTeacherById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getList" />
  </div>
</template>
<script>
// 引入 teacher.js 文件
import teacher from "@/api/edu/teacher";
export default {
  // 写核心代码位置
  data() {
    // 定义变量和初始值
    return {
      list: null, // 查询成功后，接口返回集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
      teacherQuery: {}, // 查询条件封装对象
    };
  },
  created() {
    // 页面渲染之前执行，一般调用 methods 中定义的方法
    this.getList();
  },
  methods: {
      removeTeacherById(id){  // 删除讲师的方法
      this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {  // 点击确认按钮，执行 then 方法
          teacher.deleteTeacherById(id)
                .then(response =>{  // 删除成功
                  // 1、提示信息
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                  // 2、回到讲师列表页面
                  this.getList()
                })        
        })  // 点击取消按钮，执行 catch 方法
    },
    // 创建具体的方法，调用 teacher.js 中定义的方法
    getList() {
      this.listLoading = true;
      teacher
        .getTeachersList(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          // response 接口返回的数据
          this.list = response.data.rows;
          this.total = response.data.total;
        }) // 请求成功执行
        .catch((error) => {
          this.listLoading = false;
        }); // 请求失败执行
    },
  },
};
</script>