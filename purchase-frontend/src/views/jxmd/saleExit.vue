<template>
  <div class="entity-page">
    <section class="entity-card entity-card--form">
      <div class="section-head">
        <h3>{{ dataDialogForm.id ? "编辑销售退货" : "新增销售退货" }}</h3>
        <div class="head-actions">
          <button type="button" class="plain-btn" @click="startCreate">新增退货单</button>
          <button type="button" class="plain-btn" @click="resetForm">清空表单</button>
        </div>
      </div>

      <el-form ref="saleExitForm" :model="dataDialogForm" :rules="rules" label-position="top" class="entity-form">
        <div class="form-grid">
          <el-form-item label="销售单号" prop="number">
            <el-select v-model="dataDialogForm.number" filterable placeholder="请选择销售单">
              <el-option
                v-for="item in saleOptions"
                :key="item.value"
                :label="item.value + ' / ' + item.customer"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="退货编号">
            <el-input :value="dataDialogForm.exitNumber || '系统自动生成'" disabled></el-input>
          </el-form-item>

          <el-form-item label="退货数量" prop="num">
            <el-input v-model="dataDialogForm.num" placeholder="请输入退货数量"></el-input>
          </el-form-item>

          <el-form-item label="退货单价" prop="price">
            <el-input v-model="dataDialogForm.price" placeholder="请输入退货单价"></el-input>
          </el-form-item>

          <el-form-item label="状态" prop="status">
            <el-select v-model="dataDialogForm.status" placeholder="请选择状态">
              <el-option v-for="item in statusTwo" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </div>

        <el-form-item label="退货原因" prop="reason">
          <el-input v-model="dataDialogForm.reason" type="textarea" :rows="4" placeholder="请输入退货原因"></el-input>
        </el-form-item>

        <el-button type="primary" class="save-btn" @click="handleSubmitFormData('saleExitForm')">
          {{ dataDialogForm.id ? "保存修改" : "保存退货单" }}
        </el-button>
      </el-form>
    </section>

    <section class="entity-card entity-card--list">
      <div class="section-head">
        <h3>销售退货列表</h3>
        <div class="list-actions">
          <el-input v-model="dataform.select" clearable placeholder="搜索销售单号" @keyup.enter.native="getDataList"></el-input>
          <button type="button" class="plain-btn" @click="getDataList">查询</button>
        </div>
      </div>

      <div class="entity-table">
        <div class="entity-table__head">
          <span>销售单号</span>
          <span>退货编号</span>
          <span>数量</span>
          <span>金额</span>
          <span>状态</span>
          <span>操作</span>
        </div>
        <div v-for="item in dataList" :key="item.id" class="entity-table__row">
          <span>{{ item.number }}</span>
          <span>{{ item.exitNumber }}</span>
          <span>{{ item.num }}</span>
          <span>{{ item.totalPrice }}</span>
          <span class="status-pill" :class="{ 'is-done': Number(item.status) === 0 }">
            {{ Number(item.status) === 0 ? "完成" : "进行中" }}
          </span>
          <span class="row-actions">
            <button type="button" class="mini-btn" @click="handleEdit(0, item)">编辑</button>
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
  name: "saleExit",
  data() {
    return {
      statusTwo: [
        { id: 0, name: "完成" },
        { id: 1, name: "进行中" },
      ],
      saleOptions: [],
      dataform: {
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
        number: "",
        exitNumber: "",
        num: "",
        price: "",
        status: 1,
        time: "",
        reason: "",
        remark: "",
      },
      rules: {
        number: [{ required: true, message: "请选择销售单号", trigger: "change" }],
        num: [{ required: true, message: "请输入退货数量", trigger: "blur" }],
        price: [{ required: true, message: "请输入退货单价", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.getDataList();
    this.getSaleOrder();
  },
  methods: {
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
        number: item.number,
        exitNumber: item.exitNumber,
        num: item.num,
        price: item.price,
        status: item.status,
        time: item.time,
        reason: item.reason,
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
          totalPrice: Number(this.dataDialogForm.num || 0) * Number(this.dataDialogForm.price || 0),
        };
        this.$http
          .post("/saleExit/add", payload)
          .then(() => {
            this.$message.success(this.dataDialogForm.id ? "销售退货单已更新" : "销售退货单已创建");
            this.resetForm();
            this.getDataList();
          })
          .finally(() => {
            this.dialogFormSubmitVisible = false;
          });
      });
    },
    handleDelete(index, item) {
      this.$confirm("确认删除当前销售退货单吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => this.$http.post(`/saleExit/delete/?id=${item.id}`))
        .then(() => {
          this.$message.success("销售退货单已删除");
          this.resetForm();
          this.getDataList();
        })
        .catch(() => {});
    },
    resetForm() {
      this.dataDialogForm = {
        id: 0,
        number: "",
        exitNumber: "",
        num: "",
        price: "",
        status: 1,
        time: "",
        reason: "",
        remark: "",
      };
      if (this.$refs.saleExitForm) {
        this.$refs.saleExitForm.resetFields();
      }
    },
    getSaleOrder() {
      this.$http.get("/sale/getNumberAndCustomer").then((res) => {
        this.saleOptions = res.data.data || [];
      });
    },
    getDataList() {
      if (this.dataListLoading) {
        return;
      }
      this.dataListLoading = true;
      this.$http
        .get("/saleExit/list", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.dataform.select,
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
.entity-page { display: grid; grid-template-columns: minmax(0, 1fr) minmax(0, 1.12fr); gap: 12px; }
.entity-card { padding: 18px; border: 1px solid rgba(233,224,209,.92); border-radius: 18px; background: #fff; box-shadow: 0 12px 26px rgba(70,52,20,.06); }
.section-head { display: flex; align-items: center; justify-content: space-between; gap: 12px; margin-bottom: 14px; }
.section-head h3 { margin: 0; color: #1f2f3c; font-size: 20px; }
.head-actions, .list-actions, .row-actions { display: flex; align-items: center; gap: 8px; }
.plain-btn, .mini-btn { height: 34px; padding: 0 14px; border: 1px solid #e6ded0; border-radius: 999px; background: #fff; color: #66706b; cursor: pointer; }
.mini-btn { height: 30px; padding: 0 12px; }
.mini-btn--danger { color: #b55b52; }
.entity-form /deep/ .el-form-item { margin-bottom: 12px; }
.form-grid { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 0 12px; }
.save-btn { width: 100%; margin-top: 6px; }
.entity-table { display: grid; gap: 6px; }
.entity-table__head, .entity-table__row { display: grid; grid-template-columns: 1fr 1fr .55fr .7fr .7fr .95fr; gap: 10px; align-items: center; }
.entity-table__head { color: #9a9a93; font-size: 12px; font-weight: 700; padding: 0 8px 8px; }
.entity-table__row { padding: 12px 8px; border-radius: 12px; background: #fffdfa; color: #54605f; font-size: 13px; }
.status-pill { display: inline-flex; align-items: center; justify-content: center; min-width: 54px; height: 28px; padding: 0 10px; border-radius: 999px; background: #fff1d9; color: #bb8532; font-size: 12px; }
.status-pill.is-done { background: #def4e8; color: #398d67; }
.table-foot { margin-top: 14px; }
@media (max-width: 1080px) {
  .entity-page, .form-grid, .entity-table__head, .entity-table__row { grid-template-columns: 1fr; }
}
</style>
