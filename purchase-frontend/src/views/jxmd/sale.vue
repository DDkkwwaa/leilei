<template>
  <div class="detail-page">
    <section class="detail-card">
      <div class="detail-toolbar">
        <el-input v-model="query.keyword" placeholder="订单号" clearable class="query-input" />
        <el-select v-model="query.status" clearable placeholder="状态" class="query-select">
          <el-option label="待处理" :value="1" />
          <el-option label="已完成" :value="0" />
        </el-select>
        <el-date-picker
          v-model="query.dateRange"
          type="daterange"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          class="query-date"
          clearable
        />
        <button type="button" class="plain-btn" @click="refreshAll">查询</button>
        <button type="button" class="plain-btn" @click="resetQuery">重置</button>
      </div>

      <div class="detail-toolbar">
        <el-select v-model="selectedNumber" placeholder="选择销售单" class="order-select">
          <el-option v-for="item in orders" :key="item.saleNumber" :label="item.saleNumber" :value="item.saleNumber" />
        </el-select>
        <el-select v-model="selectedDepository" placeholder="选择发货仓库" class="depository-select" clearable>
          <el-option v-for="item in depositoryOptions" :key="item.id" :label="item.name" :value="item.name" />
        </el-select>
        <button
          type="button"
          class="plain-btn plain-btn--primary"
          :disabled="!currentOrder || currentOrder.statusCode !== 'WAITING_WAVE'"
          @click="handleCreateWave"
        >
          生成波次
        </button>
        <button
          type="button"
          class="plain-btn plain-btn--success"
          :disabled="!currentOrder || currentOrder.statusCode !== 'PICKING'"
          @click="handleFinishPick"
        >
          完成拣货
        </button>
        <el-select v-model="manualStatus" placeholder="手动改状态" class="status-select" clearable>
          <el-option label="待处理" :value="1" />
          <el-option label="已完成" :value="0" />
        </el-select>
        <button type="button" class="plain-btn" :disabled="!currentOrder || manualStatus === null" @click="handleManualStatus">
          保存状态
        </button>
      </div>

      <template v-if="currentOrder">
        <div class="detail-meta">
          <div class="detail-row">
            <span>订单号</span>
            <strong>{{ currentOrder.saleNumber }}</strong>
            <span>订单日期</span>
            <strong>{{ formatTime(currentOrder.time) }}</strong>
          </div>
          <div class="detail-row">
            <span>客户</span>
            <strong>{{ currentOrder.customer || "-" }}</strong>
            <span>销售员</span>
            <strong>{{ currentOrder.saleUser || "-" }}</strong>
          </div>
          <div class="detail-row">
            <span>波次号</span>
            <strong>{{ currentOrder.waveNo || "未生成" }}</strong>
            <span>发货仓库</span>
            <strong>{{ currentOrder.depository || selectedDepository || "-" }}</strong>
          </div>
          <div class="detail-row">
            <span>状态</span>
            <strong>
              <em class="status-pill" :class="statusClass(currentOrder.statusCode)">
                {{ currentOrder.statusLabel }}
              </em>
            </strong>
            <span>订单金额</span>
            <strong>{{ currentOrder.totalAmount || 0 }}</strong>
          </div>
        </div>
      </template>
      <div v-else class="empty-card">暂无销售单数据。</div>
    </section>

    <section class="detail-card">
      <h3>订单明细</h3>
      <div v-if="currentRows.length" class="detail-table">
        <div class="detail-table__head">
          <span>SKU</span>
          <span>商品</span>
          <span>数量</span>
          <span>单价</span>
          <span>金额</span>
        </div>
        <div v-for="(item, index) in currentRows" :key="item.id || index" class="detail-table__row">
          <span>{{ formatSkuCode(item.id, index) }}</span>
          <span>{{ item.shop }}</span>
          <span>{{ item.num }}</span>
          <span>{{ item.price }}</span>
          <span>{{ item.totalPrice || item.num * item.price }}</span>
        </div>
      </div>
      <div v-else class="empty-card">当前订单暂无明细。</div>
    </section>
  </div>
</template>

<script>
export default {
  name: "sale",
  data() {
    return {
      query: {
        keyword: "",
        status: null,
        dateRange: [],
      },
      orders: [],
      selectedNumber: "",
      selectedDepository: "",
      manualStatus: null,
      depositoryOptions: [],
      rowsByNumber: {},
    };
  },
  computed: {
    currentOrder() {
      return this.orders.find((item) => item.saleNumber === this.selectedNumber) || null;
    },
    currentRows() {
      if (!this.selectedNumber) {
        return [];
      }
      return this.rowsByNumber[this.selectedNumber] || [];
    },
  },
  mounted() {
    this.refreshAll();
    this.loadDepositories();
  },
  methods: {
    formatTime(value) {
      return value ? String(value).replace("T", " ") : "-";
    },
    formatSkuCode(value, index) {
      const fallback = 1000 + Number(index || 0);
      const raw = value || fallback;
      return `SKU-${String(raw).padStart(4, "0")}`;
    },
    statusClass(statusCode) {
      if (statusCode === "COMPLETED") {
        return "is-done";
      }
      if (statusCode === "PICKING") {
        return "is-picking";
      }
      return "";
    },
    resetQuery() {
      this.query = {
        keyword: "",
        status: null,
        dateRange: [],
      };
      this.refreshAll();
    },
    refreshAll() {
      Promise.all([this.loadWaveOrders(), this.loadOrderRows()]);
    },
    loadWaveOrders() {
      return this.$http.get("/sale/waveList").then((res) => {
        let list = res.data.data || [];
        if (this.query.keyword) {
          list = list.filter((item) => String(item.saleNumber || "").includes(this.query.keyword));
        }
        if (this.query.status !== null && this.query.status !== undefined) {
          list = list.filter((item) => {
            const statusNum = item.statusCode === "COMPLETED" ? 0 : 1;
            return statusNum === this.query.status;
          });
        }
        if (this.query.dateRange && this.query.dateRange.length === 2) {
          const [startDate, endDate] = this.query.dateRange;
          const start = new Date(`${startDate} 00:00:00`).getTime();
          const end = new Date(`${endDate} 23:59:59`).getTime();
          list = list.filter((item) => {
            const t = new Date(item.time).getTime();
            return t >= start && t <= end;
          });
        }
        this.orders = list;
        if (!this.selectedNumber || !this.orders.find((item) => item.saleNumber === this.selectedNumber)) {
          this.selectedNumber = this.orders.length ? this.orders[0].saleNumber : "";
        }
      });
    },
    loadOrderRows() {
      const [startDate, endDate] = this.query.dateRange || [];
      return this.$http
        .get("/sale/list", {
          params: {
            pageNum: 1,
            pageSize: 500,
            keyword: this.query.keyword,
            status: this.query.status,
            startDate,
            endDate,
          },
        })
        .then((res) => {
          const rows = (res.data.data && res.data.data.list) || [];
          this.rowsByNumber = rows.reduce((groups, item) => {
            if (!groups[item.saleNumber]) {
              groups[item.saleNumber] = [];
            }
            groups[item.saleNumber].push(item);
            return groups;
          }, {});
        });
    },
    loadDepositories() {
      this.$http.get("/depository/listAll").then((res) => {
        this.depositoryOptions = res.data.data || [];
      });
    },
    handleCreateWave() {
      if (!this.currentOrder) {
        return;
      }
      const depositoryName = this.selectedDepository || this.currentOrder.depository;
      if (!depositoryName) {
        this.$message.warning("请先选择发货仓库");
        return;
      }
      this.$http
        .post("/sale/createWave", null, {
          params: {
            saleNumber: this.currentOrder.saleNumber,
            depositoryName,
          },
        })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("波次生成成功，已进入拣货中");
            this.refreshAll();
            return;
          }
          this.$message.error(res.data.message || "生成失败");
        });
    },
    handleFinishPick() {
      if (!this.currentOrder) {
        return;
      }
      this.$http
        .post("/sale/finishPick", null, {
          params: {
            saleNumber: this.currentOrder.saleNumber,
          },
        })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("拣货完成，订单已更新");
            this.refreshAll();
            return;
          }
          this.$message.error(res.data.message || "处理失败");
        });
    },
    handleManualStatus() {
      if (!this.currentOrder || this.manualStatus === null || this.manualStatus === undefined) {
        return;
      }
      this.$http
        .post("/sale/updateStatus", null, {
          params: {
            saleNumber: this.currentOrder.saleNumber,
            status: this.manualStatus,
          },
        })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("订单状态已更新");
            this.refreshAll();
            return;
          }
          this.$message.error(res.data.message || "更新失败");
        });
    },
  },
};
</script>

<style lang="less" scoped>
.detail-page {
  display: grid;
  gap: 12px;
}

.detail-card {
  padding: 16px 18px;
  border: 1px solid rgba(233, 224, 209, 0.92);
  border-radius: 18px;
  background: #ffffff;
  box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06);
}

.detail-toolbar {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 12px;
  flex-wrap: wrap;
}

.query-input {
  width: 220px;
}

.query-select,
.order-select,
.depository-select,
.status-select {
  width: 180px;
}

.query-date {
  width: 280px;
}

.plain-btn {
  height: 34px;
  padding: 0 14px;
  border: 1px solid #e7ddce;
  border-radius: 999px;
  background: #ffffff;
  color: #69726d;
  cursor: pointer;
}

.plain-btn--primary {
  border-color: #9dc8bc;
  color: #2f7d72;
}

.plain-btn--success {
  border-color: #99c89f;
  color: #2a7b51;
}

.plain-btn[disabled] {
  opacity: 0.5;
  cursor: not-allowed;
}

.detail-meta {
  display: grid;
  gap: 12px;
}

.detail-row {
  display: grid;
  grid-template-columns: 90px 1fr 90px 1fr;
  gap: 8px 14px;
  padding-top: 12px;
  border-top: 1px solid #f1eade;
  color: #86908b;
  font-size: 13px;
}

.detail-row:first-child {
  padding-top: 0;
  border-top: none;
}

.detail-row strong {
  color: #21303e;
  font-size: 14px;
}

.status-pill {
  display: inline-flex;
  align-items: center;
  height: 24px;
  padding: 0 10px;
  border-radius: 999px;
  background: #eef1f5;
  color: #5e6672;
  font-size: 12px;
}

.status-pill.is-done {
  background: #def4e8;
  color: #398d67;
}

.status-pill.is-picking {
  background: #fff1d9;
  color: #bb8532;
}

.detail-card h3 {
  margin: 0 0 12px;
  color: #21303e;
  font-size: 22px;
}

.detail-table {
  display: grid;
  gap: 8px;
}

.detail-table__head,
.detail-table__row {
  display: grid;
  grid-template-columns: 0.9fr 1.4fr 0.6fr 0.7fr 0.8fr;
  gap: 10px;
  font-size: 13px;
}

.detail-table__head {
  color: #9b9b94;
  font-weight: 700;
}

.detail-table__row {
  padding-top: 10px;
  border-top: 1px solid #f1eade;
  color: #56605d;
}

.empty-card {
  color: #93948d;
  font-size: 13px;
}
</style>
