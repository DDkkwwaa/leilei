<template>
  <div class="detail-page">
    <section class="detail-card">
      <div class="detail-toolbar">
        <el-input v-model="query.keyword" placeholder="订单号" clearable class="query-input" />
        <el-select v-model="query.statusCode" clearable placeholder="状态" class="query-select">
          <el-option label="待生成波次" value="WAITING_WAVE" />
          <el-option label="部分缺货" value="PARTIAL_SHORTAGE" />
          <el-option label="拣货中" value="PICKING" />
          <el-option label="已完成" value="COMPLETED" />
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
        <el-upload class="upload-wrap" action="" :http-request="uploadSaleExcel" :show-file-list="false" accept=".xls,.xlsx">
          <button type="button" class="plain-btn">导入表格订单</button>
        </el-upload>
        <button type="button" class="plain-btn" @click="$router.push('/mobilePick')">移动拣货页</button>
      </div>

      <div class="detail-toolbar">
        <el-select v-model="selectedNumber" placeholder="选择销售单" class="order-select">
          <el-option v-for="item in orders" :key="item.saleNumber" :label="item.saleNumber" :value="item.saleNumber" />
        </el-select>
        <el-select v-model="selectedDepository" placeholder="选择仓库" class="depository-select" clearable>
          <el-option v-for="item in depositoryOptions" :key="item.id" :label="item.name" :value="item.name" />
        </el-select>
        <button type="button" class="plain-btn plain-btn--primary" :disabled="!canCreateWave || creatingWave" @click="handleCreateWave(false)">
          {{ creatingWave ? "生成中..." : "生成波次" }}
        </button>
        <button type="button" class="plain-btn plain-btn--warning" :disabled="!canReplanWave || creatingWave" @click="handleCreateWave(true)">
          {{ creatingWave ? "重排中..." : "重排波次" }}
        </button>
        <button type="button" class="plain-btn plain-btn--success" :disabled="!canFinishPick || finishingPick" @click="handleFinishPick">
          {{ finishingPick ? "处理中..." : "完成拣货" }}
        </button>
      </div>

      <template v-if="currentOrder">
        <div class="detail-meta">
          <div class="detail-row">
            <span>订单号</span>
            <strong>{{ currentOrder.saleNumber }}</strong>
            <span>下单时间</span>
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
            <span>仓库</span>
            <strong>{{ currentOrder.depository || selectedDepository || "-" }}</strong>
          </div>
          <div class="detail-row">
            <span>状态</span>
            <strong>
              <em class="status-pill" :class="statusClass(currentOrder.statusCode)">
                {{ statusText(currentOrder.statusCode, currentOrder.statusLabel) }}
              </em>
            </strong>
            <span>总金额</span>
            <strong>{{ currentOrder.totalAmount || 0 }}</strong>
          </div>
        </div>
      </template>
      <div v-else class="empty-card">暂无销售单数据。</div>
    </section>

    <section class="detail-card">
      <h3>波次任务路径</h3>
      <div v-if="currentOrder && currentOrder.waveNo" class="wave-view">
        <div class="wave-summary">
          <span>波次号：{{ currentOrder.waveNo }}</span>
          <span>任务数：{{ currentWaveTasks.length }}</span>
          <span>路径节点：{{ routeSteps.length }}</span>
        </div>

        <div v-if="routeSteps.length" class="path-board">
          <div class="path-board__title">拣货路径看板</div>
          <div class="path-board__scroll">
            <div v-for="(node, index) in routeSteps" :key="`${node}-${index}`" class="path-node">
              <div class="path-node__index">{{ index + 1 }}</div>
              <div class="path-node__name">{{ node }}</div>
            </div>
          </div>
        </div>

        <div v-if="currentWaveTasks.length" class="detail-table">
          <div class="detail-table__head wave-head">
            <span>顺序</span>
            <span>库位</span>
            <span>商品</span>
            <span>待拣数量</span>
            <span>仓库</span>
          </div>
          <div v-for="(item, index) in currentWaveTasks" :key="`${item.shopName}-${index}`" class="detail-table__row wave-row">
            <span>{{ item.locationOrder || index + 1 }}</span>
            <span>{{ item.location || "-" }}</span>
            <span>{{ item.shopName || "-" }}</span>
            <span>{{ item.needQuantity || 0 }}</span>
            <span>{{ item.depository || "-" }}</span>
          </div>
        </div>
        <div v-else class="empty-card">该订单暂未生成拣货任务。</div>
      </div>
      <div v-else class="empty-card">订单未生成波次，暂无可查看路径。</div>
    </section>

    <section class="detail-card">
      <h3>订单明细</h3>
      <div v-if="currentRows.length" class="detail-table">
        <div class="detail-table__head">
          <span>商品编码</span>
          <span>商品</span>
          <span>数量</span>
          <span>单价</span>
          <span>金额</span>
          <span>备注</span>
        </div>
        <div v-for="(item, index) in currentRows" :key="item.id || index" class="detail-table__row">
          <span>{{ formatSkuCode(item.id, index) }}</span>
          <span>{{ item.shop }}</span>
          <span>{{ item.num }}</span>
          <span>{{ item.price }}</span>
          <span>{{ item.totalPrice || item.num * item.price }}</span>
          <span :class="{ shortage: isShortage(item.remark) }">{{ formatRemark(item.remark) }}</span>
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
        statusCode: "",
        dateRange: [],
      },
      orders: [],
      selectedNumber: "",
      selectedDepository: "",
      depositoryOptions: [],
      rowsByNumber: {},
      waveTasksByNumber: {},
      creatingWave: false,
      finishingPick: false,
    };
  },
  computed: {
    currentOrder() {
      return this.orders.find((item) => item.saleNumber === this.selectedNumber) || null;
    },
    currentRows() {
      return this.selectedNumber ? this.rowsByNumber[this.selectedNumber] || [] : [];
    },
    currentWaveTasks() {
      return this.selectedNumber ? this.waveTasksByNumber[this.selectedNumber] || [] : [];
    },
    routeSteps() {
      if (!this.currentWaveTasks.length) {
        return [];
      }
      const first = this.currentWaveTasks[0] || {};
      if (Array.isArray(first.routeLocations) && first.routeLocations.length) {
        return first.routeLocations;
      }
      const sequence = String(first.routeSequence || "");
      if (!sequence) {
        return [];
      }
      return sequence
        .split("->")
        .map((x) => x.trim())
        .filter((x) => !!x);
    },
    canCreateWave() {
      return !!this.currentOrder && ["WAITING_WAVE", "PARTIAL_SHORTAGE"].includes(this.currentOrder.statusCode);
    },
    canReplanWave() {
      return !!this.currentOrder && this.currentOrder.statusCode === "PICKING";
    },
    canFinishPick() {
      return !!this.currentOrder && this.currentOrder.statusCode === "PICKING";
    },
  },
  watch: {
    selectedNumber: {
      immediate: true,
      handler(val) {
        if (val) {
          this.loadPickPath(val);
        }
      },
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
      return `商品-${String(raw).padStart(4, "0")}`;
    },
    statusClass(statusCode) {
      if (statusCode === "COMPLETED") return "is-done";
      if (statusCode === "PICKING") return "is-picking";
      if (statusCode === "PARTIAL_SHORTAGE") return "is-warning";
      return "";
    },
    statusText(statusCode, fallback) {
      if (statusCode === "COMPLETED") return "已完成";
      if (statusCode === "PICKING") return "拣货中";
      if (statusCode === "PARTIAL_SHORTAGE") return "部分缺货";
      if (statusCode === "WAITING_WAVE") return "待生成波次";
      return fallback || "-";
    },
    isShortage(remark) {
      return !!remark && (String(remark).includes("PARTIAL_SHORTAGE") || String(remark).includes("Partial shortage"));
    },
    formatRemark(remark) {
      if (!remark) return "-";
      if (this.isShortage(remark)) return "部分缺货";
      return remark;
    },
    resetQuery() {
      this.query = { keyword: "", statusCode: "", dateRange: [] };
      this.refreshAll();
    },
    refreshAll() {
      Promise.all([this.loadWaveOrders(), this.loadOrderRows()]).then(() => {
        if (this.selectedNumber) {
          this.loadPickPath(this.selectedNumber);
        }
      });
    },
    loadWaveOrders() {
      return this.$http.get("/sale/waveList").then((res) => {
        let list = res.data.data || [];
        if (this.query.keyword) {
          list = list.filter((item) => String(item.saleNumber || "").includes(this.query.keyword.trim()));
        }
        if (this.query.statusCode) {
          list = list.filter((item) => item.statusCode === this.query.statusCode);
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
          params: { pageNum: 1, pageSize: 500, keyword: this.query.keyword, startDate, endDate },
        })
        .then((res) => {
          const rows = (res.data.data && res.data.data.list) || [];
          this.rowsByNumber = rows.reduce((groups, item) => {
            if (!groups[item.saleNumber]) groups[item.saleNumber] = [];
            groups[item.saleNumber].push(item);
            return groups;
          }, {});
        });
    },
    loadPickPath(saleNumber) {
      this.$http
        .get("/sale/pickPath", { params: { saleNumber } })
        .then((res) => {
          this.$set(this.waveTasksByNumber, saleNumber, (res.data && res.data.data) || []);
        })
        .catch(() => {
          this.$set(this.waveTasksByNumber, saleNumber, []);
        });
    },
    loadDepositories() {
      this.$http.get("/depository/listAll").then((res) => {
        this.depositoryOptions = res.data.data || [];
      });
    },
    uploadSaleExcel(option) {
      const formData = new FormData();
      formData.append("file", option.file);
      this.$http
        .post("/sale/importExcel", formData, { headers: { "Content-Type": "multipart/form-data" } })
        .then((res) => {
          if (res.data.code === 200) {
            const data = res.data.data || {};
            this.$message.success(`导入完成：成功${data.success || 0}，部分缺货${data.partialShortage || 0}`);
            this.refreshAll();
          } else {
            this.$message.error(res.data.message || "导入失败");
          }
          option.onSuccess(res.data);
        })
        .catch((err) => {
          option.onError(err);
          this.$message.error("导入失败");
        });
    },
    handleCreateWave(forceReplan) {
      if (!this.currentOrder || this.creatingWave) return;
      const depositoryName = this.selectedDepository || this.currentOrder.depository;
      if (!depositoryName) {
        this.$message.warning("请先选择仓库");
        return;
      }
      this.creatingWave = true;
      this.$http
        .post("/sale/createWave", null, {
          params: { saleNumber: this.currentOrder.saleNumber, depositoryName, forceReplan: !!forceReplan },
        })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success(forceReplan ? "波次已重排" : "波次已生成，进入拣货中");
            this.refreshAll();
          } else {
            this.$message.error(res.data.message || "处理失败");
          }
        })
        .catch(() => {
          this.$message.error(forceReplan ? "重排波次请求失败" : "生成波次请求失败");
        })
        .finally(() => {
          this.creatingWave = false;
        });
    },
    handleFinishPick() {
      if (!this.currentOrder || this.finishingPick) return;
      this.finishingPick = true;
      this.$http
        .post("/sale/finishPick", null, { params: { saleNumber: this.currentOrder.saleNumber } })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("拣货完成，订单已更新");
            this.refreshAll();
          } else {
            this.$message.error(res.data.message || "处理失败");
          }
        })
        .catch(() => {
          this.$message.error("完成拣货请求失败");
        })
        .finally(() => {
          this.finishingPick = false;
        });
    },
  },
};
</script>

<style lang="less" scoped>
.detail-page { display: grid; gap: 12px; }
.detail-card { padding: 16px 18px; border: 1px solid rgba(233, 224, 209, 0.92); border-radius: 18px; background: #fff; box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06); }
.detail-toolbar { display: flex; align-items: center; gap: 10px; margin-bottom: 12px; flex-wrap: wrap; }
.query-input { width: 220px; }
.query-select, .order-select, .depository-select { width: 180px; }
.query-date { width: 280px; }
.plain-btn { height: 34px; padding: 0 14px; border: 1px solid #e7ddce; border-radius: 999px; background: #fff; color: #69726d; cursor: pointer; }
.plain-btn--primary { border-color: #9dc8bc; color: #2f7d72; }
.plain-btn--warning { border-color: #e6b165; color: #8e5f1d; }
.plain-btn--success { border-color: #99c89f; color: #2a7b51; }
.plain-btn[disabled] { opacity: 0.5; cursor: not-allowed; }
.detail-meta { display: grid; gap: 12px; }
.detail-row { display: grid; grid-template-columns: 90px 1fr 90px 1fr; gap: 8px 14px; padding-top: 12px; border-top: 1px solid #f1eade; color: #86908b; font-size: 13px; }
.detail-row:first-child { padding-top: 0; border-top: none; }
.detail-row strong { color: #21303e; font-size: 14px; }
.status-pill { display: inline-flex; align-items: center; height: 24px; padding: 0 10px; border-radius: 999px; background: #eef1f5; color: #5e6672; font-size: 12px; }
.status-pill.is-done { background: #def4e8; color: #398d67; }
.status-pill.is-picking { background: #fff1d9; color: #bb8532; }
.status-pill.is-warning { background: #ffe2de; color: #c65d42; }
.detail-card h3 { margin: 0 0 12px; color: #21303e; font-size: 22px; }
.wave-view { display: grid; gap: 10px; }
.wave-summary { display: flex; flex-wrap: wrap; gap: 14px; color: #5f6866; font-size: 13px; }
.path-board { border: 1px solid #dbe9e4; border-radius: 14px; background: linear-gradient(135deg, #f6fffb 0%, #eef8f6 100%); padding: 12px; }
.path-board__title { font-size: 13px; font-weight: 700; color: #2f6f62; margin-bottom: 10px; }
.path-board__scroll { display: flex; gap: 10px; overflow-x: auto; padding-bottom: 4px; }
.path-node { min-width: 130px; border: 1px solid #cde5dd; border-radius: 12px; background: #fff; padding: 8px 10px; position: relative; }
.path-node::after { content: "→"; position: absolute; right: -10px; top: 50%; transform: translateY(-50%); color: #79a79a; font-weight: 700; }
.path-node:last-child::after { content: ""; }
.path-node__index { width: 22px; height: 22px; line-height: 22px; text-align: center; border-radius: 999px; background: #2fa487; color: #fff; font-size: 12px; margin-bottom: 6px; }
.path-node__name { font-size: 13px; color: #2f4340; font-weight: 700; }
.detail-table { display: grid; gap: 8px; }
.detail-table__head, .detail-table__row { display: grid; grid-template-columns: 0.9fr 1.2fr 0.6fr 0.7fr 0.8fr 0.8fr; gap: 10px; font-size: 13px; }
.detail-table__head { color: #9b9b94; font-weight: 700; }
.detail-table__row { padding-top: 10px; border-top: 1px solid #f1eade; color: #56605d; }
.wave-head, .wave-row { grid-template-columns: 0.4fr 1fr 1.2fr 0.7fr 0.9fr; }
.empty-card { color: #93948d; font-size: 13px; }
.shortage { color: #c65d42; font-weight: 700; }
</style>
