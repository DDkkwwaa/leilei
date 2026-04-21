<template>
  <div>
    <el-card class="box-card">
      <div class="mod-role">
        <el-form :inline="true" :model="dataForm">
          <el-form-item>
            <el-input v-model="dataForm.keyword" clearable placeholder="请输入角色名称" />
          </el-form-item>
          <el-form-item>
            <el-button type="info" @click="searchData">查询</el-button>
            <el-button type="primary" @click="openAddDialog">新增</el-button>
          </el-form-item>
        </el-form>

        <el-table :data="dataList" style="width: 100%">
          <el-table-column prop="roleId" label="ID" width="70" />
          <el-table-column prop="roleName" label="角色名称" width="160" />
          <el-table-column prop="description" label="描述" min-width="180" />
          <el-table-column prop="status" label="状态" width="90">
            <template slot-scope="scope">
              <span>{{ Number(scope.row.status) === 0 ? "正常" : "停用" }}</span>
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
          <el-table-column label="操作" min-width="220">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              <el-button size="mini" type="success" @click="handleRoleMenu(scope.row)">分配菜单</el-button>
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

      <el-dialog :title="dataDialogForm.roleId ? '更新角色' : '新增角色'" width="36%" :visible.sync="dialogFormVisible" append-to-body @close="closeDialog">
        <el-form ref="roleForm" :model="dataDialogForm" :rules="rules">
          <el-form-item label="角色名称" label-width="120px" prop="roleName">
            <el-input v-model="dataDialogForm.roleName" style="width: 300px" />
          </el-form-item>
          <el-form-item label="状态" label-width="120px" prop="status">
            <el-select v-model="dataDialogForm.status" style="width: 300px">
              <el-option label="正常" :value="0" />
              <el-option label="停用" :value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="描述" label-width="120px" prop="description">
            <el-input type="textarea" v-model="dataDialogForm.description" style="width: 300px" />
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="submitRole('roleForm')">确定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="角色分配菜单" width="36%" :visible.sync="dialogRoleMenuFormVisible" append-to-body>
        <el-tree
          ref="treeMenu"
          style="padding-left: 20px"
          :data="menuTree"
          show-checkbox
          node-key="id"
          :default-checked-keys="menuChecks"
          :props="{ children: 'children', label: 'label' }"
          @check="handleMenuChecked"
        />
        <div slot="footer">
          <el-button @click="dialogRoleMenuFormVisible = false">取消</el-button>
          <el-button type="primary" @click="submitRoleMenu">确定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "roleList",
  data() {
    const checkRoleName = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入角色名称"));
        return;
      }
      if (this.dataDialogForm.roleId) {
        callback();
        return;
      }
      this.$http
        .get(`/role/checkRoleName?roleName=${encodeURIComponent(value)}`)
        .then((res) => {
          if (res.data && res.data.data === "YES") {
            callback(new Error("角色已存在"));
            return;
          }
          callback();
        })
        .catch(() => callback(new Error("角色名称校验失败")));
    };

    return {
      dataForm: { keyword: "" },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dialogFormVisible: false,
      dialogRoleMenuFormVisible: false,
      submitting: false,
      roleId: 0,
      dataDialogForm: {
        roleId: 0,
        roleName: "",
        status: 0,
        description: "",
      },
      menuTree: [],
      menuChecks: [],
      rules: {
        roleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" },
          { validator: checkRoleName, trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
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
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    openAddDialog() {
      this.dialogFormVisible = true;
      this.dataDialogForm = {
        roleId: 0,
        roleName: "",
        status: 0,
        description: "",
      };
    },
    closeDialog() {
      this.dialogFormVisible = false;
      if (this.$refs.roleForm) {
        this.$refs.roleForm.clearValidate();
      }
    },
    handleEdit(item) {
      this.dialogFormVisible = true;
      this.dataDialogForm = {
        roleId: item.roleId,
        roleName: item.roleName || "",
        status: item.status === null || item.status === undefined ? 0 : Number(item.status),
        description: item.description || "",
      };
    },
    submitRole(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.submitting) return;
        this.submitting = true;
        this.$http
          .post("/role/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.dataDialogForm.roleId ? "更新成功" : "新增成功");
            this.dialogFormVisible = false;
            this.getDataList();
          })
          .catch(() => {
            this.$message.error("保存失败");
          })
          .finally(() => {
            this.submitting = false;
          });
      });
    },
    handleDelete(item) {
      this.$confirm("此操作将删除该角色，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/role/delete?roleId=${item.roleId}`))
        .then((res) => {
          if (res.data && res.data.data === "true") {
            this.$message.success("删除成功");
          } else {
            this.$message.warning("角色正在被使用，无法删除");
          }
          this.getDataList();
        })
        .catch(() => {});
    },
    handleRoleMenu(item) {
      this.roleId = item.roleId;
      this.$http.get(`/role/getRoleMenuById?roleId=${item.roleId}`).then((res) => {
        const data = (res.data && res.data.data) || {};
        this.menuTree = data.treeData || [];
        this.menuChecks = data.checks || [];
        this.dialogRoleMenuFormVisible = true;
      });
    },
    handleMenuChecked() {
      this.menuChecks = this.$refs.treeMenu.getCheckedKeys();
    },
    submitRoleMenu() {
      this.$http
        .post("/role/roleToAuth", {
          roleId: this.roleId,
          menuIds: this.menuChecks,
        })
        .then((res) => {
          if (res.data && res.data.data === "鎴愬姛") {
            this.$message.success("角色分配菜单成功");
          } else {
            this.$message.warning("角色分配菜单失败");
          }
          this.dialogRoleMenuFormVisible = false;
        });
    },
    getDataList() {
      if (this.dataListLoading) return;
      this.dataListLoading = true;
      this.$http
        .get("/role/list", {
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
