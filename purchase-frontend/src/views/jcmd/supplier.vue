<template>
  <div class="supplier-page">
    <section class="supplier-card supplier-card--form">
      <div class="section-head">
        <h3>{{ dataDialogForm.id ? "编辑供应商" : "新增供应商" }}</h3>
        <div class="head-actions">
          <button type="button" class="plain-btn" @click="startCreate">新增供应商</button>
          <button type="button" class="plain-btn" @click="resetForm">清空表单</button>
        </div>
      </div>

      <el-form ref="supplierForm" :model="dataDialogForm" :rules="rules" label-position="top" class="supplier-form">
        <div class="form-grid">
          <el-form-item label="供应商编码">
            <el-input :value="displaySupplierCode" disabled></el-input>
            <p class="field-tip">
              {{ dataDialogForm.id ? "前端展示编码，后端仍使用实际ID。" : "当前为草稿编码，保存后生成正式展示编码。" }}
            </p>
          </el-form-item>

          <el-form-item label="供应商名称" prop="supplierName">
            <el-input v-model="dataDialogForm.supplierName" placeholder="请输入供应商名称"></el-input>
          </el-form-item>

          <el-form-item label="联系人" prop="head">
            <el-input v-model="dataDialogForm.head" placeholder="请输入联系人"></el-input>
          </el-form-item>

          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="dataDialogForm.phone" placeholder="请输入联系电话"></el-input>
          </el-form-item>

          <el-form-item label="开户地址">
            <el-input v-model="dataDialogForm.branch" placeholder="请输入开户行"></el-input>
          </el-form-item>

          <el-form-item label="地址" class="form-grid__full" prop="address">
            <el-input v-model="dataDialogForm.address" placeholder="请输入供应商地址"></el-input>
          </el-form-item>
        </div>

        <el-form-item label="备注" class="form-grid__full">
          <el-input v-model="dataDialogForm.remark" type="textarea" :rows="3" placeholder="填写补充说明"></el-input>
        </el-form-item>

        <el-button type="primary" class="save-btn" @click="handleSubmitFormData('supplierForm')">
          {{ dataDialogForm.id ? "保存修改" : "保存供应商" }}
        </el-button>
      </el-form>
    </section>

    <section class="supplier-card supplier-card--list">
      <div class="section-head">
        <h3>供应商列表</h3>
        <div class="list-actions">
          <el-input v-model="dataForm.supplier" clearable placeholder="搜索供应商" @keyup.enter.native="getDataList"></el-input>
          <button type="button" class="plain-btn" @click="getDataList">查询</button>
        </div>
      </div>

      <div class="supplier-table">
        <div class="supplier-table__head">
          <span>编号</span>
          <span>名称</span>
          <span>联系人</span>
          <span>电话</span>
          <span>状态</span>
        </div>

        <button
          v-for="item in dataList"
          :key="item.id"
          type="button"
          class="supplier-table__row"
          @click="handleEdit(0, item)"
        >
          <span>{{ formatDisplayCode("SUP", item.id, 3) }}</span>
          <span>{{ item.supplierName }}</span>
          <span>{{ item.head }}</span>
          <span>{{ item.phone }}</span>
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
          删除当前供应商
        </button>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "supplier",
  data() {
    return {
      dataForm: {
        supplier: "",
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
        supplierName: "",
        head: "",
        phone: "",
        address: "",
        branch: "",
        remark: "",
      },
      rules: {
        supplierName: [{ required: true, message: "请输入供应商名称", trigger: "blur" }],
        head: [{ required: true, message: "请输入联系人", trigger: "blur" }],
        phone: [{ required: true, message: "请输入联系电话", trigger: "blur" }],
      },
    };
  },
  computed: {
    displaySupplierCode() {
      if (this.dataDialogForm.id) {
        return this.formatDisplayCode("SUP", this.dataDialogForm.id, 3);
      }
      return `SUP-${this.draftCodeSeed || "NEW"}`;
    },
  },
  mounted() {
    this.refreshDraftCode();
    this.getDataList();
  },
  methods: {
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
        supplierName: item.supplierName,
        head: item.head,
        phone: item.phone,
        address: item.address,
        branch: item.branch,
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
          .post("/supplier/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "供应商已更新" : "供应商已创建");
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
      this.$confirm("确认删除当前供应商吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/supplier/delete?id=${item.id}`))
        .then(() => {
          this.$message.success("供应商已删除");
          this.resetForm();
          this.getDataList();
        })
        .catch(() => {});
    },
    resetForm() {
      this.refreshDraftCode();
      this.dataDialogForm = {
        id: 0,
        supplierName: "",
        head: "",
        phone: "",
        address: "",
        branch: "",
        remark: "",
      };
      if (this.$refs.supplierForm) {
        this.$refs.supplierForm.resetFields();
      }
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      const params = {
        params: {
          pageNum: this.pageIndex,
          pageSize: this.pageSize,
          keyword: this.dataForm.supplier,
        },
      };
      this.$http
        .get("/supplier/list", params)
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
.supplier-page {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1.1fr);
  gap: 12px;
}

.supplier-card {
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

.supplier-form /deep/ .el-form-item {
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

.supplier-table {
  display: grid;
  gap: 6px;
}

.supplier-table__head,
.supplier-table__row {
  display: grid;
  grid-template-columns: 0.9fr 1.4fr 0.8fr 1fr 0.5fr;
  gap: 10px;
  align-items: center;
}

.supplier-table__head {
  color: #9a9a93;
  font-size: 12px;
  font-weight: 700;
  padding: 0 8px 8px;
}

.supplier-table__row {
  padding: 12px 8px;
  border: 1px solid transparent;
  border-radius: 12px;
  background: #fffdfa;
  color: #54605f;
  font-size: 13px;
  text-align: left;
  cursor: pointer;
}

.supplier-table__row:hover {
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
  .supplier-page {
    grid-template-columns: 1fr;
  }

  .form-grid,
  .supplier-table__head,
  .supplier-table__row,
  .table-foot {
    grid-template-columns: 1fr;
  }
}
</style>
