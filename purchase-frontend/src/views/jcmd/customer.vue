<template>
  <div class="entity-page">
    <section class="entity-card entity-card--form">
      <div class="section-head">
        <h3>{{ dataDialogForm.id ? "编辑客户" : "新增客户" }}</h3>
        <div class="head-actions">
          <button type="button" class="plain-btn" @click="startCreate">新增客户</button>
          <button type="button" class="plain-btn" @click="resetForm">清空表单</button>
        </div>
      </div>

      <el-form ref="customerForm" :model="dataDialogForm" :rules="rules" label-position="top" class="entity-form">
        <div class="form-grid">
          <el-form-item label="客户编码">
            <el-input :value="displayCustomerCode" disabled></el-input>
            <p class="field-tip">
              {{ dataDialogForm.id ? "前端展示编码，后端仍使用实际ID。" : " " }}
            </p>
          </el-form-item>
          <el-form-item label="客户名称" prop="name">
            <el-input v-model="dataDialogForm.name" placeholder="请输入客户名称"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="dataDialogForm.phone" placeholder="请输入联系电话"></el-input>
          </el-form-item>
          <el-form-item label="联系邮箱">
            <el-input v-model="dataDialogForm.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item label="传真">
            <el-input v-model="dataDialogForm.fax" placeholder="请输入传真"></el-input>
          </el-form-item>
          <el-form-item label="开户行">
            <el-input v-model="dataDialogForm.branch" placeholder="请输入开户行"></el-input>
          </el-form-item>
          <el-form-item label="银行卡号" class="form-grid__full">
            <el-input v-model="dataDialogForm.branchNo" placeholder="请输入银行卡号"></el-input>
          </el-form-item>
          <el-form-item label="地址" class="form-grid__full">
            <el-input v-model="dataDialogForm.address" placeholder="请输入客户地址"></el-input>
          </el-form-item>
        </div>

        <el-form-item label="备注">
          <el-input v-model="dataDialogForm.remark" type="textarea" :rows="3" placeholder="填写补充说明"></el-input>
        </el-form-item>

        <el-button type="primary" class="save-btn" @click="handleSubmitFormData('customerForm')">
          {{ dataDialogForm.id ? "保存修改" : "保存客户" }}
        </el-button>
      </el-form>
    </section>

    <section class="entity-card entity-card--list">
      <div class="section-head">
        <h3>客户列表</h3>
        <div class="list-actions">
          <el-input v-model="dataForm.selectcustomer" clearable placeholder="搜索客户" @keyup.enter.native="searchData"></el-input>
          <button type="button" class="plain-btn" @click="searchData">查询</button>
        </div>
      </div>

      <div class="entity-table">
        <div class="entity-table__head">
          <span>编号</span>
          <span>名称</span>
          <span>电话</span>
          <span>邮箱</span>
          <span>状态</span>
        </div>
        <button
          v-for="item in dataList"
          :key="item.id"
          type="button"
          class="entity-table__row"
          @click="handleEdit(0, item)"
        >
          <span>{{ formatDisplayCode("CUS", item.id, 3) }}</span>
          <span>{{ item.name }}</span>
          <span>{{ item.phone }}</span>
          <span>{{ item.email || "-" }}</span>
          <span class="status-badge">启用</span>
        </button>
      </div>

      <div class="table-foot">
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[5, 7, 10]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next"
        ></el-pagination>
        <button v-if="dataDialogForm.id" type="button" class="danger-btn" @click="handleDelete(0, dataDialogForm)">
          删除当前客户
        </button>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "customer",
  data() {
    return {
      dataForm: {
        selectcustomer: "",
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dialogFormSubmitVisible: false,
      draftCodeSeed: "",
      dataDialogForm: {
        id: 0,
        name: "",
        phone: "",
        address: "",
        email: "",
        fax: "",
        branch: "",
        branchNo: "",
        remark: "",
      },
      rules: {
        name: [{ required: true, message: "请输入客户名称", trigger: "blur" }],
        phone: [{ required: true, message: "请输入联系电话", trigger: "blur" }],
      },
    };
  },
  computed: {
    displayCustomerCode() {
      if (this.dataDialogForm.id) {
        return this.formatDisplayCode("CUS", this.dataDialogForm.id, 3);
      }
      return `CUS-${this.draftCodeSeed || "NEW"}`;
    },
  },
  mounted() {
    this.refreshDraftCode();
    this.getDataList();
  },
  methods: {
    searchData() {
      this.pageIndex = 1;
      this.getDataList();
    },
    formatDisplayCode(prefix, value, pad = 3) {
      return `${prefix}-${String(value).padStart(pad, "0")}`;
    },
    refreshDraftCode() {
      const seed = String(Date.now() % 1000).padStart(3, "0");
      this.draftCodeSeed = seed;
    },
    startCreate() {
      this.resetForm();
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
    handleEdit(index, item) {
      this.dataDialogForm = {
        id: item.id,
        name: item.name,
        phone: item.phone,
        address: item.address,
        email: item.email,
        fax: item.fax,
        branch: item.branch,
        branchNo: item.branchNo,
        remark: item.remark,
      };
    },
    handleSubmitFormData(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.dialogFormSubmitVisible) {
          return false;
        }
        this.dialogFormSubmitVisible = true;
        this.$http
          .post("/customer/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "客户已更新" : "客户已创建");
            this.resetForm();
            this.getDataList();
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    handleDelete(index, item) {
      if (!item.id) {
        return;
      }
      this.$confirm("确认删除当前客户吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/customer/delete?id=${item.id}`))
        .then(() => {
          this.$message.success("客户已删除");
          this.resetForm();
          this.getDataList();
        })
        .catch(() => {});
    },
    resetForm() {
      this.refreshDraftCode();
      this.dataDialogForm = {
        id: 0,
        name: "",
        phone: "",
        address: "",
        email: "",
        fax: "",
        branch: "",
        branchNo: "",
        remark: "",
      };
      if (this.$refs.customerForm) {
        this.$refs.customerForm.resetFields();
      }
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      this.$http
        .get("/customer/listByName", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.dataForm.selectcustomer,
          },
        })
        .then((res) => {
          this.dataList = res.data.data.list || [];
          this.totalPage = res.data.data.total || 0;
        })
        .finally(() => {
          this.dataListLoading = false;
        });
    },
  },
};
</script>

<style lang="less" scoped>
.entity-page {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1.1fr);
  gap: 12px;
}

.entity-card {
  padding: 18px;
  border: 1px solid rgba(233, 224, 209, 0.92);
  border-radius: 18px;
  background: #ffffff;
  box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06);
}

.section-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-bottom: 14px;
}

.head-actions {
  display: flex;
  align-items: center;
  gap: 8px;
}

.section-head h3 {
  margin: 0;
  color: #1f2f3c;
  font-size: 20px;
}

.plain-btn,
.danger-btn {
  height: 34px;
  padding: 0 14px;
  border: 1px solid #e6ded0;
  border-radius: 999px;
  background: #ffffff;
  color: #66706b;
  cursor: pointer;
}

.danger-btn {
  color: #b55b52;
}

.entity-form /deep/ .el-form-item {
  margin-bottom: 12px;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 0 12px;
}

.form-grid__full {
  grid-column: 1 / -1;
}

.field-tip {
  margin: 6px 0 0;
  color: #9a9a93;
  font-size: 12px;
}

.save-btn {
  width: 100%;
  margin-top: 6px;
}

.list-actions {
  display: flex;
  align-items: center;
  gap: 10px;
}

.entity-table {
  display: grid;
  gap: 6px;
}

.entity-table__head,
.entity-table__row {
  display: grid;
  grid-template-columns: 0.9fr 1.2fr 1fr 1.2fr 0.5fr;
  gap: 10px;
  align-items: center;
}

.entity-table__head {
  color: #9a9a93;
  font-size: 12px;
  font-weight: 700;
  padding: 0 8px 8px;
}

.entity-table__row {
  padding: 12px 8px;
  border: 1px solid transparent;
  border-radius: 12px;
  background: #fffdfa;
  color: #54605f;
  font-size: 13px;
  text-align: left;
  cursor: pointer;
}

.entity-table__row:hover {
  border-color: #d6efe7;
  background: #f5fbf8;
}

.status-badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-radius: 999px;
  background: #ddf5e9;
  color: #39906d;
  font-size: 12px;
  font-weight: 700;
}

.table-foot {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-top: 14px;
}

@media (max-width: 1080px) {
  .entity-page {
    grid-template-columns: 1fr;
  }

  .form-grid,
  .entity-table__head,
  .entity-table__row,
  .table-foot {
    grid-template-columns: 1fr;
  }
}
</style>
