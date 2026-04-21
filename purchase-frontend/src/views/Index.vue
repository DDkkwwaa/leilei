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
          <div class="metric-value">￥{{ formatMoney(summary.todaySalesAmount) }}</div>
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

    <el-row :gutter="16" class="charts">
      <el-col :span="16">
        <el-card shadow="never">
          <div slot="header" class="panel-header">订单趋势图（近7天）</div>
          <div ref="orderTrendChart" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never">
          <div slot="header" class="panel-header">库存品类分布</div>
          <div ref="categoryPieChart" class="chart-box chart-box--small"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="charts">
      <el-col :span="24">
        <el-card shadow="never">
          <div slot="header" class="panel-header">库存品类排行图</div>
          <div ref="categoryBarChart" class="chart-box chart-box--medium"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row class="actions">
      <el-button type="primary" :loading="loading" @click="loadDashboard">刷新看板</el-button>
      <el-button @click="exportDashboard">导出分析报表</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      loading: false,
      orderTrendChartInstance: null,
      categoryPieChartInstance: null,
      categoryBarChartInstance: null,
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
    window.addEventListener("resize", this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
    this.disposeCharts();
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
          this.$nextTick(() => {
            this.renderCharts();
          });
        })
        .finally(() => {
          this.loading = false;
        });
    },
    disposeCharts() {
      if (this.orderTrendChartInstance) {
        this.orderTrendChartInstance.dispose();
        this.orderTrendChartInstance = null;
      }
      if (this.categoryPieChartInstance) {
        this.categoryPieChartInstance.dispose();
        this.categoryPieChartInstance = null;
      }
      if (this.categoryBarChartInstance) {
        this.categoryBarChartInstance.dispose();
        this.categoryBarChartInstance = null;
      }
    },
    renderCharts() {
      this.renderOrderTrendChart();
      this.renderCategoryPieChart();
      this.renderCategoryBarChart();
    },
    renderOrderTrendChart() {
      const chartDom = this.$refs.orderTrendChart;
      if (!chartDom) return;
      if (!this.orderTrendChartInstance) {
        this.orderTrendChartInstance = this.$echarts.init(chartDom);
      }
      const rows = this.summary.orderTrend7 || [];
      this.orderTrendChartInstance.setOption({
        tooltip: { trigger: "axis" },
        grid: { left: 42, right: 18, top: 24, bottom: 28 },
        xAxis: {
          type: "category",
          data: rows.map((x) => x.date),
          boundaryGap: false,
          axisLine: { lineStyle: { color: "#b8c6d8" } },
          axisLabel: { color: "#5e6b7a" },
        },
        yAxis: {
          type: "value",
          minInterval: 1,
          axisLine: { show: false },
          axisLabel: { color: "#5e6b7a" },
          splitLine: { lineStyle: { color: "rgba(133,153,186,.2)" } },
        },
        series: [
          {
            name: "订单数",
            type: "line",
            smooth: true,
            data: rows.map((x) => x.value || 0),
            symbol: "circle",
            symbolSize: 8,
            lineStyle: { width: 3, color: "#4e84ff" },
            itemStyle: { color: "#4e84ff" },
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  { offset: 0, color: "rgba(78,132,255,.34)" },
                  { offset: 1, color: "rgba(78,132,255,.04)" },
                ],
              },
            },
          },
        ],
      });
    },
    renderCategoryPieChart() {
      const chartDom = this.$refs.categoryPieChart;
      if (!chartDom) return;
      if (!this.categoryPieChartInstance) {
        this.categoryPieChartInstance = this.$echarts.init(chartDom);
      }
      const rows = this.summary.categoryStock || [];
      this.categoryPieChartInstance.setOption({
        tooltip: { trigger: "item" },
        legend: {
          bottom: 0,
          textStyle: { color: "#5e6b7a", fontSize: 11 },
        },
        series: [
          {
            name: "库存分布",
            type: "pie",
            radius: ["40%", "72%"],
            center: ["50%", "42%"],
            avoidLabelOverlap: true,
            label: { show: false },
            emphasis: { label: { show: true, fontSize: 12, fontWeight: "bold" } },
            labelLine: { show: false },
            data: rows.map((x) => ({ name: x.name, value: x.value || 0 })),
          },
        ],
      });
    },
    renderCategoryBarChart() {
      const chartDom = this.$refs.categoryBarChart;
      if (!chartDom) return;
      if (!this.categoryBarChartInstance) {
        this.categoryBarChartInstance = this.$echarts.init(chartDom);
      }
      const rows = [...(this.summary.categoryStock || [])].sort((a, b) => Number(b.value || 0) - Number(a.value || 0));
      this.categoryBarChartInstance.setOption({
        tooltip: { trigger: "axis", axisPointer: { type: "shadow" } },
        grid: { left: 46, right: 20, top: 20, bottom: 40 },
        xAxis: {
          type: "category",
          axisLabel: { color: "#5e6b7a", interval: 0, rotate: 20 },
          axisLine: { lineStyle: { color: "#b8c6d8" } },
          data: rows.map((x) => x.name),
        },
        yAxis: {
          type: "value",
          axisLabel: { color: "#5e6b7a" },
          splitLine: { lineStyle: { color: "rgba(133,153,186,.2)" } },
        },
        series: [
          {
            name: "库存数量",
            type: "bar",
            barWidth: 28,
            data: rows.map((x) => x.value || 0),
            itemStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  { offset: 0, color: "#64c8b0" },
                  { offset: 1, color: "#3b8dbe" },
                ],
              },
              borderRadius: [6, 6, 0, 0],
            },
          },
        ],
      });
    },
    handleResize() {
      if (this.orderTrendChartInstance) this.orderTrendChartInstance.resize();
      if (this.categoryPieChartInstance) this.categoryPieChartInstance.resize();
      if (this.categoryBarChartInstance) this.categoryBarChartInstance.resize();
    },
    exportDashboard() {
      this.$http
        .get("/dashboard/exportExcel", { responseType: "blob" })
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
      const num = Number(value || 0);
      return Number.isNaN(num) ? "0.00" : num.toFixed(2);
    },
    formatPercent(value) {
      const num = Number(value || 0);
      return Number.isNaN(num) ? "0.00%" : `${num.toFixed(2)}%`;
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

.chart-box {
  height: 300px;
  width: 100%;
}

.chart-box--small {
  height: 300px;
}

.chart-box--medium {
  height: 340px;
}
</style>
