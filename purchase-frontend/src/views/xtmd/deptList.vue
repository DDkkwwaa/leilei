<template>
  <div>
    <el-card class="box-card">
      <div class="mod-role">
        <el-form :inline="true" :model="dataForm" class="demo-form-inline">
          <el-form-item>
            <el-input v-model="dataForm.select" clearable placeholder="请输入部门名称" />
          </el-form-item>
          <el-form-item>
            <el-button type="info" @click="searchData">查询</el-button>
            <el-button type="primary" @click="openDialog">新增</el-button>
          </el-form-item>
        </el-form>

        <el-table :data="dataList" style="width: 100%">
          <el-table-column type="selection" />
          <el-table-column prop="id" label="ID" width="70" />
          <el-table-column prop="deptName" label="部门名称" width="140" />
          <el-table-column prop="leader" label="负责人" width="120" />
          <el-table-column prop="phone" label="电话" width="150" />
          <el-table-column prop="createBy" label="创建者" width="120" />
          <el-table-column prop="status" label="状态" width="100">
            <template slot-scope="scope">
              <span>{{ Number(scope.row.status) === 0 ? "正常" : "禁用" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template slot-scope="scope">
              <span>{{ formatDateTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="updateTime" label="更新时间" width="180">
            <template slot-scope="scope">
              <span>{{ formatDateTime(scope.row.updateTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="160">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[5, 7, 10]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper"
          style="margin-top: 30px"
        />
      </div>

      <el-dialog
        :title="dataDialogForm.id ? '更新部门' : '新增部门'"
        width="35%"
        :visible.sync="dialogFormVisible"
        append-to-body
        @close="closeDialog"
      >
        <el-form :model="dataDialogForm" status-icon :rules="rules" ref="deptForm">
          <el-form-item label="部门名称" label-width="120px" prop="deptName">
            <el-input v-model="dataDialogForm.deptName" placeholder="部门名称" style="width: 300px" />
          </el-form-item>
          <el-form-item label="负责人" label-width="120px" prop="leader">
            <el-input v-model="dataDialogForm.leader" placeholder="负责人" style="width: 300px" />
          </el-form-item>
          <el-form-item label="电话" label-width="120px" prop="phone">
            <el-input v-model="dataDialogForm.phone" placeholder="电话" style="width: 300px" />
          </el-form-item>
          <el-form-item label="状态" label-width="120px" prop="status">
            <el-select v-model="dataDialogForm.status" placeholder="请选择" style="width: 300px">
              <el-option v-for="item in statusTwo" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="描述信息" label-width="120px" prop="remark">
            <el-input type="textarea" v-model="dataDialogForm.remark" style="width: 300px" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmitFormData('deptForm')">确定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "deptList",
  data() {
    const checkDeptName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入部门名称"));
        return;
      }
      if (this.checkDeptId !== 0) {
        callback();
        return;
      }
      this.$http
        .get(`/dept/checkdeptName?deptName=${encodeURIComponent(value)}`)
        .then((res) => {
          if (res.data && res.data.data === "YES") {
            callback(new Error("部门已存在"));
            return;
          }
          callback();
        })
        .catch(() => callback(new Error("部门名称校验失败")));
    };

    return {
      checkDeptId: 0,
      statusTwo: [
        { id: 0, name: "正常" },
        { id: 1, name: "禁用" },
      ],
      dataForm: { select: "" },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dialogFormVisible: false,
      dialogFormSubmitVisible: false,
      dataDialogForm: {
        id: 0,
        deptName: "",
        deptId: "",
        leader: "",
        phone: "",
        status: 0,
        remark: "",
      },
      rules: {
        deptName: [
          { required: true, message: "请输入部门名称", trigger: "blur" },
          { validator: checkDeptName, trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    this.getDataList();
  },
  methods: {
    formatDateTime(value) {
      if (!value) return "";
      return String(value).replace("T", " ");
    },
    searchData() {
      this.pageIndex = 1;
      this.getDataList();
    },
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    openDialog() {
      this.checkDeptId = 0;
      this.dialogFormVisible = true;
      this.dataDialogForm = {
        id: 0,
        deptName: "",
        deptId: "",
        leader: "",
        phone: "",
        status: 0,
        remark: "",
      };
    },
    closeDialog() {
      this.dialogFormVisible = false;
      this.dialogFormSubmitVisible = false;
      this.checkDeptId = 0;
      this.dataDialogForm = {
        id: 0,
        deptName: "",
        deptId: "",
        leader: "",
        phone: "",
        status: 0,
        remark: "",
      };
      if (this.$refs.deptForm) {
        this.$refs.deptForm.clearValidate();
      }
    },
    handleEdit(item) {
      this.dialogFormVisible = true;
      this.checkDeptId = Number(item.id || 0);
      this.dataDialogForm = {
        id: item.id,
        deptName: item.deptName || "",
        deptId: item.deptId || "",
        leader: item.leader || "",
        phone: item.phone || "",
        status: item.status === undefined || item.status === null ? 0 : Number(item.status),
        remark: item.remark || "",
      };
    },
    handleSubmitFormData(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.dialogFormSubmitVisible) {
          return;
        }
        this.dialogFormSubmitVisible = true;
        this.$http
          .post("/dept/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "更新成功" : "新增成功");
            this.closeDialog();
            this.getDataList();
          })
          .catch(() => {
            this.$message.error("保存失败");
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    handleDelete(item) {
      this.$confirm("此操作将永久删除该记录，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/dept/delete?id=${item.id}`))
        .then(() => {
          this.$message.success("删除成功");
          this.pageIndex = 1;
          this.getDataList();
        })
        .catch(() => {});
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      this.$http
        .get("/dept/list", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.dataForm.select,
          },
        })
        .then((res) => {
          const page = (res.data && res.data.data) || {};
          this.dataList = page.list || [];
          this.totalPage = page.total || 0;
        })
        .finally(() => {
          this.dataListLoading = false;
        });
    },
  },
};
</script>
