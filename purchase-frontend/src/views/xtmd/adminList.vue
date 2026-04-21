<template>
  <div>
    <el-card class="box-card">
      <div class="mod-admin">
        <el-form :inline="true" :model="dataForm">
          <el-form-item>
            <el-input v-model="dataForm.keyword" clearable placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item>
            <el-button type="info" icon="el-icon-search" @click="searchData">查询</el-button>
            <el-button type="primary" @click="openAddDialog">新增</el-button>
          </el-form-item>
        </el-form>

        <el-table :data="dataList" style="width: 100%">
          <el-table-column prop="id" label="ID" width="70" />
          <el-table-column prop="dept" label="部门" width="120">
            <template slot-scope="scope">
              <span>{{ deptNameText(scope.row.dept) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="userName" label="用户名" width="120" />
          <el-table-column prop="trueName" label="真实姓名" width="120" />
          <el-table-column prop="sex" label="性别" width="80">
            <template slot-scope="scope">
              <span>{{ Number(scope.row.sex) === 0 ? "男" : "女" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="eMail" label="邮箱" width="180" show-overflow-tooltip />
          <el-table-column prop="phone" label="电话" width="140" />
          <el-table-column prop="status" label="状态" width="80">
            <template slot-scope="scope">
              <span>{{ Number(scope.row.status) === 0 ? "正常" : "禁用" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template slot-scope="scope">
              <span>{{ formatDateTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="280">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              <el-button size="mini" @click="passwordInit(scope.row)">重置密码</el-button>
              <el-button size="mini" type="success" @click="handleAdminRole(scope.row)">分配角色</el-button>
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
          style="margin-top: 24px"
        />
      </div>

      <el-dialog :title="dataDialogForm.id ? '更新用户' : '新增用户'" width="36%" :visible.sync="dialogFormVisible" append-to-body @close="closeDialog">
        <el-form ref="adminForm" :model="dataDialogForm" :rules="rules">
          <el-form-item label="用户名" label-width="120px" prop="userName">
            <el-input v-model="dataDialogForm.userName" style="width: 300px" />
          </el-form-item>
          <el-form-item label="真实姓名" label-width="120px" prop="trueName">
            <el-input v-model="dataDialogForm.trueName" style="width: 300px" />
          </el-form-item>
          <el-form-item label="部门" label-width="120px" prop="dept">
            <el-select v-model="dataDialogForm.dept" style="width: 300px" placeholder="请选择部门">
              <el-option v-for="item in deptAll" :key="item.id" :label="item.deptName" :value="String(item.id)" />
            </el-select>
          </el-form-item>
          <el-form-item label="性别" label-width="120px" prop="sex">
            <el-select v-model="dataDialogForm.sex" style="width: 300px" placeholder="请选择性别">
              <el-option label="男" :value="0" />
              <el-option label="女" :value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱" label-width="120px" prop="eMail">
            <el-input v-model="dataDialogForm.eMail" style="width: 300px" />
          </el-form-item>
          <el-form-item label="电话" label-width="120px" prop="phone">
            <el-input v-model="dataDialogForm.phone" style="width: 300px" />
          </el-form-item>
          <el-form-item label="状态" label-width="120px" prop="status">
            <el-select v-model="dataDialogForm.status" style="width: 300px">
              <el-option label="正常" :value="0" />
              <el-option label="禁用" :value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="备注" label-width="120px">
            <el-input type="textarea" v-model="dataDialogForm.remark" style="width: 300px" />
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmitFormData('adminForm')">确定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="用户分配角色" width="36%" :visible.sync="dialogAdminToRoleFormVisible" append-to-body>
        <el-tree
          ref="treeRole"
          style="padding-left: 20px"
          :data="roleTree"
          show-checkbox
          node-key="id"
          :default-checked-keys="roleChecks"
          :props="{ children: 'children', label: 'label' }"
          @check="handleRoleChecked"
        />
        <div slot="footer">
          <el-button @click="dialogAdminToRoleFormVisible = false">取消</el-button>
          <el-button type="primary" @click="submitAdminRole">确定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "adminList",
  data() {
    const checkUserName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入用户名"));
        return;
      }
      if (this.checkUserId !== 0) {
        callback();
        return;
      }
      this.$http
        .get(`/admin/checkUserName?userName=${encodeURIComponent(value)}`)
        .then((res) => {
          if (res.data && res.data.data === "YES") {
            callback(new Error("用户名已存在"));
            return;
          }
          callback();
        })
        .catch(() => callback(new Error("用户名校验失败")));
    };

    return {
      checkUserId: 0,
      dataForm: { keyword: "" },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dialogFormVisible: false,
      dialogFormSubmitVisible: false,
      dialogAdminToRoleFormVisible: false,
      deptAll: [],
      dataDialogForm: {
        id: 0,
        userName: "",
        trueName: "",
        sex: 0,
        eMail: "",
        phone: "",
        dept: "",
        status: 0,
        remark: "",
      },
      adminId: 0,
      roleTree: [],
      roleChecks: [],
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { validator: checkUserName, trigger: "blur" },
        ],
        trueName: [{ required: true, message: "请输入真实姓名", trigger: "blur" }],
        dept: [{ required: true, message: "请选择部门", trigger: "change" }],
      },
    };
  },
  mounted() {
    this.getDeptAll();
    this.getDataList();
  },
  methods: {
    formatDateTime(value) {
      return value ? String(value).replace("T", " ") : "";
    },
    searchData() {
      this.pageIndex = 1;
      this.getDataList();
    },
    deptNameText(value) {
      if (value === undefined || value === null || value === "") return "-";
      const raw = String(value);
      const match = this.deptAll.find((x) => String(x.id) === raw || x.deptName === raw);
      return match ? match.deptName : raw;
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
    getDeptAll() {
      this.$http.get("/jcDept/listAll").then((res) => {
        this.deptAll = (res.data && res.data.data) || [];
      });
    },
    openAddDialog() {
      this.checkUserId = 0;
      this.dialogFormVisible = true;
      this.dataDialogForm = {
        id: 0,
        userName: "",
        trueName: "",
        sex: 0,
        eMail: "",
        phone: "",
        dept: "",
        status: 0,
        remark: "",
      };
    },
    closeDialog() {
      this.dialogFormVisible = false;
      this.dialogFormSubmitVisible = false;
      if (this.$refs.adminForm) {
        this.$refs.adminForm.clearValidate();
      }
    },
    handleEdit(item) {
      this.checkUserId = Number(item.id || 0);
      this.dialogFormVisible = true;
      this.dataDialogForm = {
        id: item.id,
        userName: item.userName || "",
        trueName: item.trueName || "",
        sex: item.sex === null || item.sex === undefined ? 0 : Number(item.sex),
        eMail: item.eMail || "",
        phone: item.phone || "",
        dept: item.dept === null || item.dept === undefined ? "" : String(item.dept),
        status: item.status === null || item.status === undefined ? 0 : Number(item.status),
        remark: item.remark || "",
      };
    },
    handleSubmitFormData(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.dialogFormSubmitVisible) return;
        this.dialogFormSubmitVisible = true;
        this.$http
          .post("/admin/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "更新成功" : "新增成功，初始密码为 123456");
            this.dialogFormVisible = false;
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
        .then(() => this.$http.post(`/admin/delete/?id=${item.id}`))
        .then(() => {
          this.$message.success("删除成功");
          this.getDataList();
        })
        .catch(() => {});
    },
    passwordInit(item) {
      this.$confirm("此操作将重置密码为 123456，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/admin/pwdInit?id=${item.id}`))
        .then((res) => {
          if (res.data && Number(res.data.code) === 200) {
            this.$message.success("密码已重置为 123456");
          } else {
            this.$message.error("密码重置失败");
          }
        })
        .catch(() => {});
    },
    handleAdminRole(item) {
      this.adminId = item.id;
      this.$http.get(`/admin/getRoleByAdminId?adminId=${item.id}`).then((res) => {
        const data = (res.data && res.data.data) || {};
        this.roleTree = data.treeData || [];
        this.roleChecks = data.checks || [];
        this.dialogAdminToRoleFormVisible = true;
      });
    },
    handleRoleChecked() {
      this.roleChecks = this.$refs.treeRole.getCheckedKeys();
    },
    submitAdminRole() {
      this.$http
        .post("/admin/adminToRole", {
          adminId: this.adminId,
          roleIds: this.roleChecks,
        })
        .then((res) => {
          if (res.data && res.data.data === "鎴愬姛") {
            this.$message.success("用户分配角色成功");
          } else {
            this.$message.warning("用户分配角色失败");
          }
          this.dialogAdminToRoleFormVisible = false;
        });
    },
    getDataList() {
      if (this.dataListLoading) return;
      this.dataListLoading = true;
      this.$http
        .get("/admin/List", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.dataForm.keyword,
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
