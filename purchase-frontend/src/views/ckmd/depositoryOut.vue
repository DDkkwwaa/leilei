<template>
  <div class="entity-page">
    <section class="entity-card entity-card--form">
      <div class="section-head">
        <h3>{{ dataDialogForm.id ? "编辑出库单" : "新增出库单" }}</h3>
        <div class="head-actions">
          <button type="button" class="plain-btn" @click="startCreate">新增出库</button>
          <button type="button" class="plain-btn" @click="resetForm">清空表单</button>
        </div>
      </div>

      <el-form ref="depositoryForm" :model="dataDialogForm" :rules="rules" label-position="top" class="entity-form">
        <div class="form-grid">
          <el-form-item label="出库仓库" prop="depository">
            <el-select v-model="dataDialogForm.depository" placeholder="请选择仓库">
              <el-option v-for="item in depositoryOptions" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="出库商品" prop="shopName">
            <el-select v-model="dataDialogForm.shopName" placeholder="请选择商品">
              <el-option v-for="item in shopOptions" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="出库数量" prop="shopNumber">
            <el-input v-model="dataDialogForm.shopNumber" placeholder="请输入出库数量"></el-input>
          </el-form-item>

          <el-form-item label="规格" prop="specs">
            <el-select v-model="dataDialogForm.specs" placeholder="请选择规格">
              <el-option v-for="item in specsList" :key="item.id" :label="item.name" :value="item.name"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="出库单价" prop="shopPrice">
            <el-input v-model="dataDialogForm.shopPrice" placeholder="请输入出库单价"></el-input>
          </el-form-item>
        </div>

        <el-form-item label="备注">
          <el-input v-model="dataDialogForm.remark" type="textarea" :rows="3" placeholder="填写补充说明"></el-input>
        </el-form-item>

        <el-button type="primary" class="save-btn" @click="handleSubmitFormData('depositoryForm')">
          {{ dataDialogForm.id ? "保存修改" : "保存出库单" }}
        </el-button>
      </el-form>
    </section>

    <section class="entity-card entity-card--list">
      <div class="section-head">
        <h3>出库记录</h3>
        <div class="list-actions">
          <el-input v-model="dataForm.select" clearable placeholder="搜索出库编号" @keyup.enter.native="searchData"></el-input>
          <button type="button" class="plain-btn" @click="searchData">查询</button>
        </div>
      </div>

      <div class="entity-table">
        <div class="entity-table__head">
          <span>出库号</span>
          <span>仓库</span>
          <span>商品</span>
          <span>数量</span>
          <span>状态</span>
          <span>操作</span>
        </div>
        <div v-for="item in dataList" :key="item.id" class="entity-table__row">
          <span>{{ item.outId }}</span>
          <span>{{ item.depository }}</span>
          <span>{{ item.shopName }}</span>
          <span>{{ item.shopNumber }}</span>
          <span class="status-pill" :class="{ 'is-done': Number(item.status) === 0 }">
            {{ Number(item.status) === 0 ? "已出库" : "待审核" }}
          </span>
          <span class="row-actions">
            <button type="button" class="mini-btn" @click="handleEdit(0, item)">编辑</button>
            <button type="button" class="mini-btn" :disabled="Number(item.status) === 0" @click="check(0, item)">审核</button>
            <button type="button" class="mini-btn mini-btn--danger" @click="handleDelete(0, item)">删除</button>
          </span>
        </div>
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
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "depositoryOut",
  data() {
    return {
      depositoryOptions: [],
      shopOptions: [],
      specsList: [
        { id: 0, name: "箱" },
        { id: 1, name: "件" },
        { id: 2, name: "台" },
        { id: 3, name: "套" },
      ],
      dataForm: {
        select: "",
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dialogFormSubmitVisible: false,
      dataDialogForm: {
        id: 0,
        outId: "",
        depository: "",
        shopName: "",
        shopNumber: "",
        specs: "",
        shopPrice: "",
        totalPrice: "",
        status: 1,
        outUser: "",
        remark: "",
      },
      rules: {
        depository: [{ required: true, message: "请选择仓库", trigger: "change" }],
        shopName: [{ required: true, message: "请选择商品", trigger: "change" }],
        shopNumber: [{ required: true, message: "请输入出库数量", trigger: "blur" }],
        shopPrice: [{ required: true, message: "请输入出库单价", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.getDataList();
    this.getDepositoryAll();
    this.getShopIdAndName();
  },
  methods: {
    searchData() {
      this.pageIndex = 1;
      this.getDataList();
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
        outId: item.outId,
        depository: item.depository,
        shopName: item.shopName,
        shopNumber: item.shopNumber,
        specs: item.specs,
        shopPrice: item.shopPrice,
        totalPrice: item.totalPrice,
        status: item.status,
        outUser: item.outUser || sessionStorage.getItem("username") || "",
        remark: item.remark,
      };
    },
    handleSubmitFormData(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid || this.dialogFormSubmitVisible) {
          return false;
        }
        this.dialogFormSubmitVisible = true;
        const payload = {
          ...this.dataDialogForm,
          outUser: sessionStorage.getItem("username") || "",
          totalPrice: Number(this.dataDialogForm.shopNumber || 0) * Number(this.dataDialogForm.shopPrice || 0),
        };
        this.$http
          .post("/depositoryOut/add", payload)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "出库单已更新" : "出库单已创建");
            this.resetForm();
            this.getDataList();
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    handleDelete(index, item) {
      this.$confirm("确认删除当前出库记录吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/depositoryOut/delete/?id=${item.id}`))
        .then((res) => {
          if (res.data.data === "0") {
            this.$message.warning("该记录存在业务关联，不能删除");
            return;
          }
          this.$message.success("出库记录已删除");
          this.resetForm();
          this.getDataList();
        })
        .catch(() => {});
    },
    resetForm() {
      this.dataDialogForm = {
        id: 0,
        outId: "",
        depository: "",
        shopName: "",
        shopNumber: "",
        specs: "",
        shopPrice: "",
        totalPrice: "",
        status: 1,
        outUser: "",
        remark: "",
      };
      if (this.$refs.depositoryForm) {
        this.$refs.depositoryForm.resetFields();
      }
    },
    getDepositoryAll() {
      this.$http.get("/depository/listAll").then((res) => {
        this.depositoryOptions = res.data.data || [];
      });
    },
    getShopIdAndName() {
      this.$http.get("/shop/getShopNameAll").then((res) => {
        this.shopOptions = res.data.data || [];
      });
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      this.$http
        .get("/depositoryOut/list", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.dataForm.select,
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
    check(index, item) {
      this.$confirm("确认审核后将正式出库，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/depositoryOut/checkById?id=${item.id}`))
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("出库审核完成");
            this.getDataList();
            return;
          }
          if (res.data.code === 500) {
            this.$message.error("库存不足，无法出库");
            return;
          }
          this.$message.error("操作失败，请稍后再试");
        })
        .catch(() => {});
    },
  },
};
</script>

<style lang="less" scoped>
.entity-page { display: grid; grid-template-columns: minmax(0, 1fr) minmax(0, 1.16fr); gap: 12px; }
.entity-card { padding: 18px; border: 1px solid rgba(233,224,209,.92); border-radius: 18px; background: #fff; box-shadow: 0 12px 26px rgba(70,52,20,.06); }
.section-head { display: flex; align-items: center; justify-content: space-between; gap: 12px; margin-bottom: 14px; }
.section-head h3 { margin: 0; color: #1f2f3c; font-size: 20px; }
.head-actions, .list-actions, .row-actions { display: flex; align-items: center; gap: 8px; }
.plain-btn, .mini-btn { height: 34px; padding: 0 14px; border: 1px solid #e6ded0; border-radius: 999px; background: #fff; color: #66706b; cursor: pointer; }
.mini-btn { height: 30px; padding: 0 12px; }
.mini-btn[disabled] { opacity: .55; cursor: not-allowed; }
.mini-btn--danger { color: #b55b52; }
.entity-form /deep/ .el-form-item { margin-bottom: 12px; }
.form-grid { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 0 12px; }
.save-btn { width: 100%; margin-top: 6px; }
.entity-table { display: grid; gap: 6px; }
.entity-table__head, .entity-table__row { display: grid; grid-template-columns: .8fr .9fr 1fr .5fr .65fr 1.2fr; gap: 10px; align-items: center; }
.entity-table__head { color: #9a9a93; font-size: 12px; font-weight: 700; padding: 0 8px 8px; }
.entity-table__row { padding: 12px 8px; border-radius: 12px; background: #fffdfa; color: #54605f; font-size: 13px; }
.status-pill { display: inline-flex; align-items: center; justify-content: center; min-width: 58px; height: 28px; padding: 0 10px; border-radius: 999px; background: #fff1d9; color: #bb8532; font-size: 12px; }
.status-pill.is-done { background: #def4e8; color: #398d67; }
.table-foot { margin-top: 14px; }
@media (max-width: 1080px) {
  .entity-page, .form-grid, .entity-table__head, .entity-table__row { grid-template-columns: 1fr; }
}
</style>
