<template>
  <div class="header-actions">
    <button type="button" class="bell-pill" @click="goWarningPage">
      <span class="bell-pill__icon">🔔</span>
      <span class="bell-pill__text">预警</span>
      <em v-if="warningCount > 0" class="bell-pill__badge">{{ warningCount > 99 ? "99+" : warningCount }}</em>
    </button>

    <el-dropdown trigger="click" @command="handleCommand">
      <button type="button" class="admin-pill">
        <span class="admin-pill__icon">系</span>
        <span class="admin-pill__copy">
          <small>系统管理员</small>
          <strong>{{ username }}</strong>
        </span>
      </button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="password">修改密码</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <button type="button" class="logout-pill" @click="handleCommand('logout')">退出登录</button>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="420px" @close="closeDialog">
      <el-form ref="passwordForm" :model="updatePassword" :rules="passwordRules" label-width="90px">
        <el-form-item label="原密码" prop="oldPassword">
          <el-input v-model="updatePassword.oldPassword" type="password" placeholder="请输入原密码" clearable />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="updatePassword.newPassword" type="password" placeholder="请输入新密码" clearable />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmitFormData('passwordForm')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    const checkPassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入原密码"));
        return;
      }

      this.$http
        .post("/admin/checkPassword", this.updatePassword)
        .then((res) => {
          if (res.data.data === "false") {
            callback(new Error("原密码输入错误"));
            return;
          }
          callback();
        })
        .catch(() => callback(new Error("原密码校验失败")));
    };

    return {
      dialogFormVisible: false,
      dialogFormSubmitVisible: false,
      warningCount: 0,
      warningTimer: null,
      updatePassword: {
        username: sessionStorage.getItem("username"),
        oldPassword: "",
        newPassword: "",
      },
      passwordRules: {
        oldPassword: [{ validator: checkPassword, trigger: "blur" }],
        newPassword: [{ required: true, message: "请输入新密码", trigger: "blur" }],
      },
    };
  },
  computed: {
    username() {
      return sessionStorage.getItem("username") || "ADMIN";
    },
  },
  mounted() {
    this.loadWarningCount();
    this.warningTimer = setInterval(this.loadWarningCount, 60000);
  },
  beforeDestroy() {
    if (this.warningTimer) {
      clearInterval(this.warningTimer);
      this.warningTimer = null;
    }
  },
  methods: {
    loadWarningCount() {
      this.$http
        .get("/stock/warningCount")
        .then((res) => {
          this.warningCount = Number((res.data && res.data.data) || 0);
        })
        .catch(() => {
          this.warningCount = 0;
        });
    },
    goWarningPage() {
      if (this.$route.path !== "/stockList") {
        this.$router.push("/stockList");
      }
    },
    handleCommand(command) {
      if (command === "password") {
        this.dialogFormVisible = true;
        return;
      }
      if (command === "logout") {
        this.$confirm("确认退出当前账号吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.$router.push("/login");
          })
          .catch(() => {
            this.$message.info("已取消退出");
          });
      }
    },
    handleSubmitFormData(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.dialogFormSubmitVisible) {
          return false;
        }
        this.dialogFormSubmitVisible = true;
        this.$http
          .post("/admin/updatePassword", this.updatePassword)
          .then(() => {
            this.dialogFormVisible = false;
            this.closeDialog();
            this.$message.success("密码修改成功");
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    closeDialog() {
      this.updatePassword = {
        username: sessionStorage.getItem("username"),
        oldPassword: "",
        newPassword: "",
      };
      if (this.$refs.passwordForm) {
        this.$refs.passwordForm.resetFields();
      }
    },
  },
};
</script>

<style lang="less" scoped>
.header-actions {
  display: flex;
  align-items: center;
  gap: 10px;
}

.bell-pill,
.admin-pill,
.logout-pill {
  display: inline-flex;
  align-items: center;
  border: 1px solid rgba(228, 221, 205, 0.9);
  border-radius: 999px;
  background: #ffffff;
  box-shadow: 0 10px 20px rgba(53, 43, 23, 0.05);
}

.bell-pill {
  gap: 8px;
  height: 46px;
  padding: 0 14px;
  color: #3f4754;
  font-size: 12px;
  font-weight: 700;
  cursor: pointer;
}

.bell-pill__badge {
  min-width: 20px;
  height: 20px;
  padding: 0 6px;
  border-radius: 999px;
  background: #e64b3c;
  color: #fff;
  font-style: normal;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
}

.admin-pill {
  gap: 10px;
  padding: 6px 12px 6px 6px;
  cursor: pointer;
}

.admin-pill__icon {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-radius: 999px;
  background: #1d7c80;
  color: #ffffff;
  font-size: 12px;
  font-weight: 800;
}

.admin-pill__copy {
  display: flex;
  flex-direction: column;
  align-items: flex-start;

  small {
    color: #8d9189;
    font-size: 10px;
    font-weight: 700;
  }

  strong {
    margin-top: 2px;
    color: #5c6171;
    font-size: 14px;
    line-height: 1;
  }
}

.logout-pill {
  height: 46px;
  padding: 0 16px;
  color: #3f4754;
  font-size: 12px;
  font-weight: 700;
  cursor: pointer;
}
</style>
