<template>
  <div class="dashboard">
    <el-row :gutter="16" class="metrics">
      <el-col :span="6">
        <el-card shadow="never">
          <div class="metric-title">今日订单数</div>
          <div class="metric-value">{{ summary.todayOrderCount }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div class="metric-title">今日销售额</div>
          <div class="metric-value">¥{{ formatMoney(summary.todaySalesAmount) }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div class="metric-title">库存预警数</div>
          <div class="metric-value">{{ summary.warningCount }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div class="metric-title">退货损耗率</div>
          <div class="metric-value">{{ formatPercent(summary.lossRate) }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="panels">
      <el-col :span="12">
        <el-card shadow="never">
          <div slot="header" class="panel-header">近 7 天订单趋势</div>
          <el-table :data="summary.orderTrend7" size="mini" style="width: 100%">
            <el-table-column prop="date" label="日期" width="140" />
            <el-table-column prop="value" label="订单数" />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never">
          <div slot="header" class="panel-header">按品类库存汇总</div>
          <el-table :data="summary.categoryStock" size="mini" style="width: 100%">
            <el-table-column prop="name" label="品类" />
            <el-table-column prop="value" label="库存数量" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-row class="actions">
      <el-button type="primary" :loading="loading" @click="loadDashboard">刷新看板</el-button>
      <el-button @click="exportDashboard">导出分析 Excel</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      loading: false,
      summary: {
        todayOrderCount: 0,
        todaySalesAmount: 0,
        warningCount: 0,
        lossRate: 0,
        orderTrend7: [],
        categoryStock: [],
      },
    };
  },
  mounted() {
    this.loadDashboard();
  },
  methods: {
    loadDashboard() {
      if (this.loading) return;
      this.loading = true;
      this.$http
        .get("/dashboard/summary")
        .then((res) => {
          const data = (res.data && res.data.data) || {};
          this.summary = {
            ...this.summary,
            ...data,
          };
        })
        .finally(() => {
          this.loading = false;
        });
    },
    exportDashboard() {
      this.$http
        .get("/dashboard/exportExcel", {
          responseType: "blob",
        })
        .then((res) => {
          const blob = new Blob([res.data], { type: "application/vnd.ms-excel" });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement("a");
          link.href = url;
          link.download = "dashboard_analysis.xls";
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(url);
        });
    },
    formatMoney(value) {
      if (value === null || value === undefined || value === "") return "0.00";
      const num = Number(value);
      if (Number.isNaN(num)) return "0.00";
      return num.toFixed(2);
    },
    formatPercent(value) {
      if (value === null || value === undefined || value === "") return "0.00%";
      const num = Number(value);
      if (Number.isNaN(num)) return "0.00%";
      return `${num.toFixed(2)}%`;
    },
  },
};
</script>

<style scoped>
.dashboard {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.metric-title {
  font-size: 14px;
  color: #909399;
}

.metric-value {
  margin-top: 10px;
  font-size: 28px;
  font-weight: 700;
  color: #303133;
}

.panel-header {
  font-weight: 600;
}

.actions {
  display: flex;
  gap: 12px;
}
</style>
