<template>
  <div class="app-container">
    <el-input
      v-model="filterText"
      placeholder="Filter keyword"
      style="margin-bottom: 30px"
    />
    <el-tree
      ref="tree2"
      :data="data2"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>
<script>
export default {
  data() {
    return {
      filterText: "",
      data2: [
        {
          id: 1,
          label: "前端开发技术",
          children: [
            {
              id: 1,
              label: "HTML",             
            },
            {
              id: 2,
              label: "CSS",             
            },
            {
              id: 3,
              label: "JavaScript",             
            },
            {
              id: 4,
              label: "JQuery",             
            },
            {
              id: 5,
              label: "Vue.js",             
            },
          ],
        },
        {
          id: 2,
          label: "后端开发",
          children: [
            {
              id: 1,
              label: "Java",
            },
            {
              id: 2,
              label: "C++",
            },
          ],
        },
        {
          id: 3,
          label: "数据库",
          children: [
            {
              id: 1,
              label: "MySQL",
            },
            {
              id: 2,
              label: "Redis",
            },
          ],
        },
      ],
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    },
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
  },
};
</script>

<script>
import subject from '@/api/edu/subject' 
export default {
  data() {
    return {
      filterText: "",
      data2: [],  // 返回所有课程分类的数据
      defaultProps: {
        children: "children",
        label: "title",
      },
    };
  },
  created() {
      this.getAllSubjectList()
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val);
    },
  },
  methods: {
    getAllSubjectList() {
        subject.getSubjectList()
            .then(response =>{
                this.data2 = response.data.list
            })
    },
    filterNode(value, data) {
      if (!value) return true;
      // 检索功能（忽略大小写比较）
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1;
    },
  },
};
</script>