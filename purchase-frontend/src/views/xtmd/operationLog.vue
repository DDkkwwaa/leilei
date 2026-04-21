<template>
  <div>
    <el-card class="box-card">
      <el-form :inline="true" :model="queryForm">
        <el-form-item label="Keyword">
          <el-input v-model="queryForm.keyword" placeholder="operator / bizNo / content" clearable />
        </el-form-item>
        <el-form-item label="Operator">
          <el-input v-model="queryForm.operator" placeholder="operator" clearable />
        </el-form-item>
        <el-form-item label="Action">
          <el-select v-model="queryForm.actionType" clearable placeholder="All">
            <el-option label="Login" value="LOGIN" />
            <el-option label="Wave Create" value="WAVE_CREATE" />
            <el-option label="Pick Finish" value="PICK_FINISH" />
            <el-option label="Inbound Audit" value="INBOUND" />
            <el-option label="Outbound Audit" value="OUTBOUND" />
            <el-option label="Order Status Update" value="ORDER_STATUS_UPDATE" />
            <el-option label="Order Import" value="ORDER_IMPORT" />
            <el-option label="Pick Scan" value="PICK_SCAN" />
          </el-select>
        </el-form-item>
        <el-form-item label="Date Range">
          <el-date-picker
            v-model="queryForm.dateRange"
            type="daterange"
            value-format="yyyy-MM-dd"
            range-separator="to"
            start-placeholder="start"
            end-placeholder="end"
            clearable
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">Search</el-button>
          <el-button @click="reset">Reset</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="dataListLoading" :data="dataList" style="width: 100%">
        <el-table-column prop="id" label="ID" width="90" />
        <el-table-column prop="operator" label="Operator" width="140" />
        <el-table-column label="Action" width="160">
          <template slot-scope="scope">
            {{ actionTypeText(scope.row.actionType) }}
          </template>
        </el-table-column>
        <el-table-column prop="bizType" label="Biz Type" width="120" />
        <el-table-column prop="bizNo" label="Biz No" width="180" show-overflow-tooltip />
        <el-table-column prop="content" label="Content" min-width="220" show-overflow-tooltip />
        <el-table-column prop="requestPath" label="Path" min-width="180" show-overflow-tooltip />
        <el-table-column prop="requestMethod" label="Method" width="100" />
        <el-table-column prop="ip" label="IP" width="140" />
        <el-table-column label="Time" width="180">
          <template slot-scope="scope">
            <span>{{ formatTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper"
        style="margin-top: 20px"
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
      />
    </el-card>
  </div>
</template>

<script>
const ACTION_TYPE_TEXT = {
  LOGIN: "Login",
  WAVE_CREATE: "Wave Create",
  PICK_FINISH: "Pick Finish",
  INBOUND: "Inbound Audit",
  OUTBOUND: "Outbound Audit",
  ORDER_STATUS_UPDATE: "Order Status Update",
  ORDER_IMPORT: "Order Import",
  PICK_SCAN: "Pick Scan",
};

export default {
  name: "OperationLog",
  data() {
    return {
      queryForm: {
        keyword: "",
        operator: "",
        actionType: "",
        dateRange: [],
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
    };
  },
  mounted() {
    this.getDataList();
  },
  methods: {
    actionTypeText(value) {
      return ACTION_TYPE_TEXT[value] || value || "";
    },
    formatTime(value) {
      if (!value) return "";
      return String(value).replace("T", " ");
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    reset() {
      this.queryForm = {
        keyword: "",
        operator: "",
        actionType: "",
        dateRange: [],
      };
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
    getDataList() {
      if (this.dataListLoading) return;
      this.dataListLoading = true;
      const [startDate, endDate] = this.queryForm.dateRange || [];
      this.$http
        .get("/operationLog/list", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.pageIndex,
            keyword: this.queryForm.keyword,
            operator: this.queryForm.operator,
            actionType: this.queryForm.actionType,
            startDate,
            endDate,
          },
        })
        .then((res) => {
          const pageData = (res.data && res.data.data) || {};
          this.dataList = pageData.list || [];
          this.totalPage = pageData.total || 0;
        })
        .finally(() => {
          this.dataListLoading = false;
        });
    },
  },
};
</script>

<style scoped>
</style>
