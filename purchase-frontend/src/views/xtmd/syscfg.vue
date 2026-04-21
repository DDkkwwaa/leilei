<template>
  <div class="profile-page">
    <section class="profile-card">
      <div class="section-head">
        <h3>个人中心</h3>
        <button type="button" class="plain-btn" @click="loadAdminInfo">刷新资料</button>
      </div>

      <el-form ref="profileForm" :model="profileForm" :rules="profileRules" label-position="top" class="profile-form">
        <div class="profile-grid">
          <el-form-item label="登录账号">
            <el-input :value="profileForm.userName || username" disabled></el-input>
          </el-form-item>
          <el-form-item label="所属部门">
            <el-input :value="resolvedDeptName" disabled></el-input>
          </el-form-item>
          <el-form-item label="账号状态">
            <el-input :value="profileForm.status === 0 ? '正常' : '停用'" disabled></el-input>
          </el-form-item>

          <el-form-item label="真实姓名" prop="trueName">
            <el-input v-model="profileForm.trueName" placeholder="请输入真实姓名"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="profileForm.phone" placeholder="请输入联系电话"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="eMail">
            <el-input v-model="profileForm.eMail" placeholder="请输入邮箱"></el-input>
          </el-form-item>

          <el-form-item label="性别" prop="sex">
            <el-select v-model="profileForm.sex" placeholder="请选择性别">
              <el-option label="男" :value="0"></el-option>
              <el-option label="女" :value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" class="form-span-2">
            <el-input v-model="profileForm.remark" type="textarea" :rows="3" placeholder="填写补充说明"></el-input>
          </el-form-item>
        </div>

        <div class="actions">
          <el-button @click="resetProfileForm">重置</el-button>
          <el-button type="primary" :loading="profileSubmitting" @click="submitProfileForm">保存资料</el-button>
        </div>
      </el-form>
    </section>

    <section class="profile-card">
      <div class="section-head">
        <h3>修改密码</h3>
      </div>
      <el-form ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="100px" class="password-form">
        <el-form-item label="旧密码" prop="oldPassword">
          <el-input v-model="passwordForm.oldPassword" type="password" show-password placeholder="请输入旧密码" clearable />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="passwordForm.newPassword" type="password" show-password placeholder="请输入新密码" clearable />
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="passwordForm.confirmPassword"
            type="password"
            show-password
            placeholder="请再次输入新密码"
            clearable
          />
        </el-form-item>
        <div class="actions">
          <el-button @click="resetPasswordForm">重置</el-button>
          <el-button type="primary" :loading="passwordSubmitting" @click="submitPasswordForm">确认修改</el-button>
        </div>
      </el-form>
    </section>
  </div>
</template>

<script>
export default {
  name: "syscfg",
  data() {
    const checkOldPassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入旧密码"));
        return;
      }
      this.$http
        .post("/admin/checkPassword", {
          username: this.username,
          oldPassword: this.passwordForm.oldPassword,
          newPassword: this.passwordForm.newPassword,
        })
        .then((res) => {
          if (res.data && res.data.data === "true") {
            callback();
            return;
          }
          callback(new Error("旧密码不正确"));
        })
        .catch(() => callback(new Error("旧密码校验失败")));
    };

    const checkConfirmPassword = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请再次输入新密码"));
        return;
      }
      if (value !== this.passwordForm.newPassword) {
        callback(new Error("两次输入的新密码不一致"));
        return;
      }
      callback();
    };

    return {
      originProfile: {},
      profileSubmitting: false,
      passwordSubmitting: false,
      deptOptions: [],
      profileForm: {
        userName: "",
        dept: "",
        status: 0,
        trueName: "",
        phone: "",
        eMail: "",
        sex: undefined,
        remark: "",
      },
      profileRules: {
        trueName: [{ required: true, message: "请输入真实姓名", trigger: "blur" }],
        phone: [{ required: true, message: "请输入联系电话", trigger: "blur" }],
      },
      passwordForm: {
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
      },
      passwordRules: {
        oldPassword: [{ validator: checkOldPassword, trigger: "blur" }],
        newPassword: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { min: 6, message: "新密码至少 6 位", trigger: "blur" },
        ],
        confirmPassword: [{ validator: checkConfirmPassword, trigger: "blur" }],
      },
    };
  },
  computed: {
    username() {
      return sessionStorage.getItem("username") || "";
    },
    resolvedDeptName() {
      const raw = this.profileForm.dept;
      if (raw === undefined || raw === null || raw === "") {
        return "-";
      }
      const rawText = String(raw);
      const match = this.deptOptions.find((x) => String(x.id) === rawText || String(x.deptName) === rawText);
      if (match) {
        return match.deptName;
      }
      return rawText;
    },
  },
  mounted() {
    this.loadDeptOptions();
    this.loadAdminInfo();
  },
  methods: {
    loadDeptOptions() {
      this.$http
        .get("/jcDept/listAll")
        .then((res) => {
          this.deptOptions = (res.data && res.data.data) || [];
        })
        .catch(() => {
          this.deptOptions = [];
        });
    },
    loadAdminInfo() {
      this.$http
        .get("/admin/getAdminInfo")
        .then((res) => {
          const list = (res.data && res.data.data) || [];
          const admin = Array.isArray(list) ? list[0] || {} : list || {};
          this.originProfile = { ...admin };
          this.profileForm = {
            userName: admin.userName || this.username,
            dept: admin.dept || "",
            status: Number(admin.status || 0),
            trueName: admin.trueName || "",
            phone: admin.phone || "",
            eMail: admin.eMail || "",
            sex: admin.sex === null || admin.sex === undefined ? undefined : Number(admin.sex),
            remark: admin.remark || "",
          };
        })
        .catch(() => {
          this.$message.error("加载个人资料失败");
        });
    },
    resetProfileForm() {
      const admin = this.originProfile || {};
      this.profileForm = {
        userName: admin.userName || this.username,
        dept: admin.dept || "",
        status: Number(admin.status || 0),
        trueName: admin.trueName || "",
        phone: admin.phone || "",
        eMail: admin.eMail || "",
        sex: admin.sex === null || admin.sex === undefined ? undefined : Number(admin.sex),
        remark: admin.remark || "",
      };
      if (this.$refs.profileForm) {
        this.$refs.profileForm.clearValidate();
      }
    },
    submitProfileForm() {
      this.$refs.profileForm.validate((valid) => {
        if (!valid || this.profileSubmitting) {
          return;
        }
        this.profileSubmitting = true;
        this.$http
          .post("/admin/updateProfile", {
            trueName: this.profileForm.trueName,
            phone: this.profileForm.phone,
            eMail: this.profileForm.eMail,
            sex: this.profileForm.sex,
            remark: this.profileForm.remark,
          })
          .then((res) => {
            if (res.data && Number(res.data.code) === 200) {
              this.$message.success("个人资料已保存");
              this.loadAdminInfo();
              return;
            }
            this.$message.error((res.data && (res.data.message || res.data.msg)) || "保存失败");
          })
          .catch(() => {
            this.$message.error("保存失败");
          })
          .finally(() => {
            this.profileSubmitting = false;
          });
      });
    },
    resetPasswordForm() {
      this.passwordForm = {
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
      };
      if (this.$refs.passwordForm) {
        this.$refs.passwordForm.resetFields();
      }
    },
    submitPasswordForm() {
      this.$refs.passwordForm.validate((valid) => {
        if (!valid || this.passwordSubmitting) {
          return;
        }
        this.passwordSubmitting = true;
        this.$http
          .post("/admin/updatePassword", {
            username: this.username,
            oldPassword: this.passwordForm.oldPassword,
            newPassword: this.passwordForm.newPassword,
          })
          .then((res) => {
            if (res.data && Number(res.data.code) === 200) {
              this.$message.success("密码修改成功");
              this.resetPasswordForm();
              return;
            }
            this.$message.error((res.data && (res.data.message || res.data.msg)) || "密码修改失败");
          })
          .catch(() => {
            this.$message.error("密码修改失败");
          })
          .finally(() => {
            this.passwordSubmitting = false;
          });
      });
    },
  },
};
</script>

<style lang="less" scoped>
.profile-page {
  display: grid;
  gap: 12px;
}

.profile-card {
  padding: 16px 18px;
  border: 1px solid rgba(233, 224, 209, 0.92);
  border-radius: 18px;
  background: #fff;
  box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06);
}

.section-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;
}

.section-head h3 {
  margin: 0;
  color: #21303e;
  font-size: 20px;
}

.plain-btn {
  height: 34px;
  padding: 0 14px;
  border: 1px solid #e7ddce;
  border-radius: 999px;
  background: #fff;
  color: #69726d;
  cursor: pointer;
}

.profile-form /deep/ .el-form-item {
  margin-bottom: 12px;
}

.profile-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 0 12px;
}

.form-span-2 {
  grid-column: span 2;
}

.password-form {
  max-width: 480px;
}

.actions {
  display: flex;
  gap: 10px;
}

@media (max-width: 1080px) {
  .profile-grid {
    grid-template-columns: 1fr;
  }

  .form-span-2 {
    grid-column: auto;
  }
}
</style>
