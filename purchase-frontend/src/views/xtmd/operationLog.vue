<template>
  <div>
    <el-card class="box-card">
      <el-form :inline="true" :model="queryForm">
        <el-form-item label="关键字">
          <el-input v-model="queryForm.keyword" placeholder="操作人/业务单号/内容" clearable />
        </el-form-item>
        <el-form-item label="操作人">
          <el-input v-model="queryForm.operator" placeholder="请输入操作人" clearable />
        </el-form-item>
        <el-form-item label="动作类型">
          <el-select v-model="queryForm.actionType" clearable placeholder="全部">
            <el-option label="登录" value="LOGIN" />
            <el-option label="生成波次" value="WAVE_CREATE" />
            <el-option label="完成拣货" value="PICK_FINISH" />
            <el-option label="入库审核" value="INBOUND" />
            <el-option label="出库审核" value="OUTBOUND" />
          </el-select>
        </el-form-item>
        <el-form-item label="日期区间">
          <el-date-picker
            v-model="queryForm.dateRange"
            type="daterange"
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            clearable
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="dataListLoading" :data="dataList" style="width: 100%">
        <el-table-column prop="id" label="ID" width="90" />
        <el-table-column prop="operator" label="操作人" width="140" />
        <el-table-column label="动作类型" width="140">
          <template slot-scope="scope">
            {{ actionTypeText(scope.row.actionType) }}
          </template>
        </el-table-column>
        <el-table-column prop="bizType" label="业务类型" width="120" />
        <el-table-column prop="bizNo" label="业务单号" width="180" show-overflow-tooltip />
        <el-table-column prop="content" label="内容" min-width="220" show-overflow-tooltip />
        <el-table-column prop="requestPath" label="请求路径" min-width="180" show-overflow-tooltip />
        <el-table-column prop="requestMethod" label="方法" width="100" />
        <el-table-column prop="ip" label="IP" width="140" />
        <el-table-column label="时间" width="180">
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
  LOGIN: "登录",
  WAVE_CREATE: "生成波次",
  PICK_FINISH: "完成拣货",
  INBOUND: "入库审核",
  OUTBOUND: "出库审核",
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
