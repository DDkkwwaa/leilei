<template>
  <div class="entity-page">
    <section class="entity-card entity-card--form">
      <div class="section-head">
        <h3>{{ isShopCreateMode ? "新增商品" : "编辑商品" }}</h3>
        <div class="head-actions">
          <button type="button" class="primary-btn" :class="{ active: isShopCreateMode }" @click="startCreate">新增商品</button>
          <button type="button" class="plain-btn" @click="resetForm">清空表单</button>
        </div>
      </div>
      <p class="mode-tip" :class="{ create: isShopCreateMode, edit: !isShopCreateMode }">
        {{ isShopCreateMode ? "当前是新增商品模式，请填写信息后保存。" : "当前是编辑商品模式，保存后将覆盖原有数据。" }}
      </p>

      <el-form ref="shopForm" :model="dataDialogForm" :rules="rules" label-position="top" class="entity-form">
        <div class="form-grid">
          <el-form-item label="商品编码">
            <el-input :value="displayShopCode" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="dataDialogForm.name" placeholder="请输入商品名称"></el-input>
          </el-form-item>
          <el-form-item label="商品分类" prop="parentId">
            <el-select v-model="dataDialogForm.parentId" placeholder="请选择分类">
              <el-option v-for="item in shopTypeList" :key="item.id" :label="item.shopType" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="规格" prop="specs">
            <el-select v-model="dataDialogForm.specs" placeholder="请选择规格">
              <el-option v-for="item in specsList" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="市场价格">
            <el-input v-model="dataDialogForm.marketPrice" placeholder="请输入市场价格"></el-input>
          </el-form-item>
        </div>

        <el-form-item label="备注">
          <el-input v-model="dataDialogForm.remark" type="textarea" :rows="3" placeholder="填写补充说明"></el-input>
        </el-form-item>

        <el-button type="primary" class="save-btn" @click="handleSubmitFormData('shopForm')">
          {{ isShopCreateMode ? "保存商品" : "保存修改" }}
        </el-button>
      </el-form>
    </section>

    <section class="entity-card entity-card--list">
      <div class="section-head">
        <h3>商品列表</h3>
        <div class="list-actions">
          <el-input v-model="dataForm.shop" clearable placeholder="搜索商品" @keyup.enter.native="searchData"></el-input>
          <button type="button" class="plain-btn" @click="searchData">查询</button>
        </div>
      </div>

      <div class="entity-table">
        <div class="entity-table__head">
          <span>编号</span>
          <span>名称</span>
          <span>分类</span>
          <span>规格</span>
          <span>价格</span>
        </div>
        <button v-for="item in dataList" :key="item.id" type="button" class="entity-table__row" @click="handleEdit(item)">
          <span>{{ formatDisplayCode("商品", item.id, 4) }}</span>
          <span>{{ item.name }}</span>
          <span>{{ shopTypeName(item.parentId) }}</span>
          <span>{{ item.specs }}</span>
          <span>{{ item.marketPrice }}</span>
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
        <button v-if="dataDialogForm.id" type="button" class="danger-btn" @click="handleDelete(dataDialogForm)">
          删除当前商品
        </button>
      </div>
    </section>

    <section class="entity-card entity-card--type">
      <div class="section-head">
        <h3>商品分类管理</h3>
        <div class="head-actions">
          <button type="button" class="primary-btn" :class="{ active: isTypeCreateMode }" @click="resetTypeForm">新增分类</button>
        </div>
      </div>
      <p class="mode-tip" :class="{ create: isTypeCreateMode, edit: !isTypeCreateMode }">
        {{ isTypeCreateMode ? "当前是新增分类模式。" : "当前是编辑分类模式，保存后将更新该分类。" }}
      </p>

      <div class="type-form">
        <el-input v-model="typeForm.shopType" placeholder="请输入分类名称"></el-input>
        <el-input v-model="typeForm.info" placeholder="请输入分类描述"></el-input>
        <button type="button" class="plain-btn" :disabled="typeSubmitting" @click="saveType">
          {{ typeSubmitting ? "保存中..." : isTypeCreateMode ? "保存分类" : "更新分类" }}
        </button>
      </div>

      <div class="type-table">
        <div class="type-head">
          <span>ID</span>
          <span>名称</span>
          <span>描述</span>
          <span>操作</span>
        </div>
        <div v-for="item in shopTypeList" :key="'type-' + item.id" class="type-row">
          <span>{{ item.id }}</span>
          <span>{{ item.shopType }}</span>
          <span>{{ item.info || "-" }}</span>
          <span class="type-actions">
            <button type="button" class="plain-btn" @click="editType(item)">编辑</button>
            <button type="button" class="danger-btn" @click="deleteType(item)">删除</button>
          </span>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "shop",
  data() {
    return {
      shopTypeList: [],
      dataForm: {
        shop: "",
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
        parentId: "",
        shopNumber: "",
        specs: "",
        marketPrice: "",
        remark: "",
      },
      rules: {
        name: [{ required: true, message: "请输入商品名称", trigger: "blur" }],
        parentId: [{ required: true, message: "请选择商品分类", trigger: "change" }],
      },
      typeForm: {
        id: 0,
        shopType: "",
        info: "",
      },
      typeSubmitting: false,
      specsList: [
        { id: 0, name: "箱" },
        { id: 1, name: "件" },
        { id: 2, name: "包" },
        { id: 3, name: "个" },
      ],
    };
  },
  computed: {
    displayShopCode() {
      if (this.dataDialogForm.id) {
        return this.formatDisplayCode("商品", this.dataDialogForm.id, 4);
      }
      return `商品-${this.draftCodeSeed || "草稿"}`;
    },
    isShopCreateMode() {
      return !this.dataDialogForm.id;
    },
    isTypeCreateMode() {
      return !this.typeForm.id;
    },
  },
  mounted() {
    this.refreshDraftCode();
    this.getShopTypeList();
    this.getDataList();
  },
  methods: {
    shopTypeName(parentId) {
      const hit = this.shopTypeList.find((x) => Number(x.id) === Number(parentId));
      return hit ? hit.shopType : String(parentId || "-");
    },
    searchData() {
      this.pageIndex = 1;
      this.getDataList();
    },
    formatDisplayCode(prefix, value, pad = 4) {
      return `${prefix}-${String(value).padStart(pad, "0")}`;
    },
    refreshDraftCode() {
      const seed = String(Date.now() % 10000).padStart(4, "0");
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
    handleEdit(item) {
      this.dataDialogForm = {
        id: item.id,
        name: item.name,
        parentId: item.parentId,
        shopNumber: item.shopNumber,
        specs: item.specs,
        marketPrice: item.marketPrice,
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
          .post("/shop/add", this.dataDialogForm)
          .then(() => {
            this.$message.success(this.isShopCreateMode ? "商品已创建" : "商品已更新");
            this.resetForm();
            this.getDataList();
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    handleDelete(item) {
      if (!item.id) {
        return;
      }
      this.$confirm("确认删除当前商品吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/shop/delete?id=${item.id}`))
        .then(() => {
          this.$message.success("商品已删除");
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
        parentId: "",
        shopNumber: "",
        specs: "",
        marketPrice: "",
        remark: "",
      };
      if (this.$refs.shopForm) {
        this.$refs.shopForm.resetFields();
      }
    },
    getShopTypeList() {
      this.$http.get("/shop/getShopTypeList").then((res) => {
        this.shopTypeList = res.data.data || [];
      });
    },
    editType(item) {
      this.typeForm = {
        id: item.id || 0,
        shopType: item.shopType || "",
        info: item.info || "",
      };
    },
    resetTypeForm() {
      this.typeForm = {
        id: 0,
        shopType: "",
        info: "",
      };
    },
    saveType() {
      if (!this.typeForm.shopType || !this.typeForm.shopType.trim()) {
        this.$message.warning("请输入分类名称");
        return;
      }
      if (this.typeSubmitting) {
        return;
      }
      this.typeSubmitting = true;
      this.$http
        .post("/shop/saveShopType", {
          id: this.typeForm.id || null,
          shopType: this.typeForm.shopType.trim(),
          info: this.typeForm.info || "",
        })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success(this.isTypeCreateMode ? "分类已新增" : "分类已更新");
            this.resetTypeForm();
            this.getShopTypeList();
          } else {
            this.$message.error(res.data.message || "保存失败");
          }
        })
        .finally(() => {
          this.typeSubmitting = false;
        });
    },
    deleteType(item) {
      this.$confirm("确认删除该分类吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/shop/deleteShopType?id=${item.id}`))
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("分类删除成功");
            this.getShopTypeList();
          } else {
            this.$message.error(res.data.message || "删除失败");
          }
        })
        .catch(() => {});
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      this.$http
        .get("/shop/list", {
          params: {
            pageNum: this.pageIndex,
            pageSize: this.pageSize,
            keyword: this.dataForm.shop,
          },
        })
        .then((res) => {
          this.dataList = (res.data.data && res.data.data.list) || [];
          this.totalPage = (res.data.data && res.data.data.total) || 0;
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
  margin-bottom: 10px;
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
.primary-btn,
.danger-btn {
  height: 34px;
  padding: 0 14px;
  border: 1px solid #e6ded0;
  border-radius: 999px;
  background: #ffffff;
  color: #66706b;
  cursor: pointer;
}

.primary-btn {
  border-color: #67c7b0;
  background: linear-gradient(135deg, #e8fbf4 0%, #d2f3e8 100%);
  color: #1d7d66;
  font-weight: 700;
}

.primary-btn.active {
  border-color: #2ca583;
  box-shadow: 0 0 0 3px rgba(44, 165, 131, 0.14);
}

.danger-btn {
  color: #b55b52;
}

.mode-tip {
  margin: 0 0 12px;
  padding: 9px 12px;
  border-radius: 10px;
  font-size: 12px;
  line-height: 1.4;
  border: 1px solid transparent;
}

.mode-tip.create {
  color: #176950;
  background: #ecfaf5;
  border-color: #c9ebdf;
}

.mode-tip.edit {
  color: #8a5a19;
  background: #fff8eb;
  border-color: #f0e0be;
}

.entity-form /deep/ .el-form-item {
  margin-bottom: 12px;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 0 12px;
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
  grid-template-columns: 0.95fr 1.2fr 0.7fr 0.6fr 0.7fr;
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

.table-foot {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin-top: 14px;
}

.entity-card--type {
  grid-column: 1 / -1;
}

.type-form {
  display: grid;
  grid-template-columns: 1fr 1.2fr auto;
  gap: 10px;
  margin-bottom: 12px;
}

.type-table {
  display: grid;
  gap: 6px;
}

.type-head,
.type-row {
  display: grid;
  grid-template-columns: 80px 1.1fr 1.5fr 220px;
  gap: 10px;
  align-items: center;
}

.type-head {
  color: #9a9a93;
  font-size: 12px;
  font-weight: 700;
  padding: 0 8px 8px;
}

.type-row {
  padding: 10px 8px;
  border: 1px solid #f0e9db;
  border-radius: 12px;
  background: #fffdfa;
  color: #54605f;
  font-size: 13px;
}

.type-actions {
  display: flex;
  gap: 8px;
}

@media (max-width: 1080px) {
  .entity-page {
    grid-template-columns: 1fr;
  }

  .form-grid,
  .entity-table__head,
  .entity-table__row,
  .table-foot,
  .type-form,
  .type-head,
  .type-row {
    grid-template-columns: 1fr;
  }
}
</style>
