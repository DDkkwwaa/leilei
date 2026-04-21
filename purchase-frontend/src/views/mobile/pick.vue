<template>
  <div class="mobile-stage">
    <div class="phone-shell">
      <div class="phone-notch"></div>
      <div class="phone-screen">
        <div class="status-bar">
          <span class="status-time">9:41</span>
          <div class="status-icons">
            <span class="signal">
              <i></i><i></i><i></i><i></i>
            </span>
            <span class="wifi"></span>
            <span class="battery">
              <span class="battery-level"></span>
            </span>
          </div>
        </div>

        <div class="screen-header">
          <h2>移动拣货</h2>
          <p>按任务扫码并确认数量</p>
        </div>

        <div class="screen-body">
          <el-select v-model="saleNumber" placeholder="选择销售单" class="full" @change="loadPath">
            <el-option v-for="item in orders" :key="item.saleNumber" :label="item.saleNumber" :value="item.saleNumber" />
          </el-select>

          <div v-if="saleNumber" class="task-list">
            <div v-for="(item, idx) in tasks" :key="item.shopName + item.location + idx" class="task-item">
              <div class="line"><strong>库位</strong><span>{{ item.location || "-" }}</span></div>
              <div class="line"><strong>商品</strong><span>{{ item.shopName }}</span></div>
              <div class="line"><strong>待拣</strong><span>{{ item.needQuantity }}</span></div>
              <div class="line"><strong>已拣</strong><span>{{ item.pickedQuantity }}</span></div>
              <el-input v-model="item.scanCode" placeholder="扫码或输入条码" />
              <el-input-number v-model="item.scanQuantity" :min="1" :max="item.needQuantity" class="qty-input" />
              <el-button type="success" class="confirm-action" @click="confirmPick(item)">确认拣货</el-button>
            </div>
          </div>

          <div v-else class="empty-tip">请选择一个拣货中的销售单</div>
        </div>

        <div class="screen-footer">
          <button type="button" class="btn btn-secondary" :disabled="syncingQueue" @click="syncQueue">
            {{ syncingQueue ? "同步中..." : "同步离线缓存" }}
          </button>
          <button type="button" class="btn btn-primary" :disabled="finishingPick" @click="finishPick">
            {{ finishingPick ? "处理中..." : "完成拣货" }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const QUEUE_KEY = "mobile_pick_queue_v1";

export default {
  name: "MobilePick",
  data() {
    return {
      orders: [],
      saleNumber: "",
      tasks: [],
      syncingQueue: false,
      finishingPick: false,
    };
  },
  mounted() {
    this.loadOrders();
    window.addEventListener("online", this.syncQueue);
    this.syncQueue();
  },
  beforeDestroy() {
    window.removeEventListener("online", this.syncQueue);
  },
  methods: {
    loadOrders() {
      this.$http
        .get("/sale/waveList")
        .then((res) => {
          const rows = res.data.data || [];
          this.orders = rows.filter((x) => x.statusCode === "PICKING");
          if (this.orders.length && !this.saleNumber) {
            this.saleNumber = this.orders[0].saleNumber;
            this.loadPath();
          }
        })
        .catch(() => {
          this.$message.error("加载拣货订单失败");
        });
    },
    loadPath() {
      if (!this.saleNumber) return;
      this.$http
        .get("/sale/pickPath", { params: { saleNumber: this.saleNumber } })
        .then((res) => {
          this.tasks = (res.data.data || []).map((x) => ({
            ...x,
            pickedQuantity: 0,
            scanCode: "",
            scanQuantity: 1,
          }));
        })
        .catch(() => {
          this.$message.error("加载拣货路径失败");
        });
    },
    confirmPick(item) {
      const payload = {
        saleNumber: this.saleNumber,
        shopName: item.shopName,
        quantity: item.scanQuantity || 1,
        scanCode: item.scanCode || "",
      };
      if (!navigator.onLine) {
        this.pushQueue(payload);
        item.pickedQuantity = Math.min(item.needQuantity, item.pickedQuantity + payload.quantity);
        this.$message.warning("当前离线，已加入待同步队列");
        return;
      }
      this.$http
        .post("/sale/pickScanConfirm", null, { params: payload })
        .then((res) => {
          if (res.data.code === 200) {
            item.pickedQuantity = Math.min(item.needQuantity, item.pickedQuantity + payload.quantity);
            this.$message.success("确认成功");
          } else {
            this.$message.error(res.data.message || "确认失败");
          }
        })
        .catch(() => {
          this.$message.error("确认请求失败");
        });
    },
    pushQueue(payload) {
      const queue = this.getQueue();
      queue.push(payload);
      localStorage.setItem(QUEUE_KEY, JSON.stringify(queue));
    },
    getQueue() {
      try {
        return JSON.parse(localStorage.getItem(QUEUE_KEY) || "[]");
      } catch (e) {
        return [];
      }
    },
    syncQueue() {
      if (!navigator.onLine || this.syncingQueue) return;
      const queue = this.getQueue();
      if (!queue.length) return;
      this.syncingQueue = true;
      const runner = queue.reduce((p, item) => {
        return p.then(() => this.$http.post("/sale/pickScanConfirm", null, { params: item }));
      }, Promise.resolve());
      runner
        .then(() => {
          localStorage.removeItem(QUEUE_KEY);
          this.$message.success("离线数据同步完成");
        })
        .catch(() => {
          this.$message.error("离线数据同步失败");
        })
        .finally(() => {
          this.syncingQueue = false;
        });
    },
    finishPick() {
      if (!this.saleNumber || this.finishingPick) return;
      const allDone = this.tasks.length > 0 && this.tasks.every((x) => Number(x.pickedQuantity) >= Number(x.needQuantity));
      if (!allDone) {
        this.$message.warning("还有商品未完成拣货");
        return;
      }
      this.finishingPick = true;
      this.$http
        .post("/sale/finishPick", null, { params: { saleNumber: this.saleNumber } })
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.success("拣货完成");
            this.saleNumber = "";
            this.tasks = [];
            this.loadOrders();
          } else {
            this.$message.error(res.data.message || "完成失败");
          }
        })
        .catch(() => {
          this.$message.error("完成请求失败");
        })
        .finally(() => {
          this.finishingPick = false;
        });
    },
  },
};
</script>

<style scoped>
.mobile-stage {
  min-height: 100%;
  padding: 16px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  background: linear-gradient(180deg, #f2f4f9 0%, #e8ecf5 100%);
}

.phone-shell {
  width: 390px;
  max-width: 100%;
  padding: 10px;
  border-radius: 36px;
  background: linear-gradient(160deg, #262b33 0%, #0f1318 100%);
  box-shadow: 0 22px 40px rgba(10, 14, 20, 0.35);
}

.phone-notch {
  width: 140px;
  height: 26px;
  margin: 0 auto 8px;
  border-radius: 0 0 16px 16px;
  background: #0b0f14;
}

.phone-screen {
  border-radius: 28px;
  overflow: hidden;
  background: #f8fafc;
  min-height: 760px;
  display: flex;
  flex-direction: column;
}

.status-bar {
  height: 30px;
  padding: 6px 14px 4px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f8fafc;
  color: #0f172a;
  font-size: 12px;
  font-weight: 600;
}

.status-icons {
  display: flex;
  align-items: center;
  gap: 8px;
}

.signal {
  display: inline-flex;
  align-items: flex-end;
  gap: 2px;
  height: 12px;
}

.signal i {
  display: block;
  width: 2px;
  border-radius: 2px;
  background: #0f172a;
}

.signal i:nth-child(1) { height: 4px; opacity: 0.55; }
.signal i:nth-child(2) { height: 6px; opacity: 0.7; }
.signal i:nth-child(3) { height: 8px; opacity: 0.85; }
.signal i:nth-child(4) { height: 10px; }

.wifi {
  width: 12px;
  height: 8px;
  border: 2px solid #0f172a;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
  border-bottom: 0;
  opacity: 0.9;
}

.battery {
  position: relative;
  width: 20px;
  height: 10px;
  border: 1.5px solid #0f172a;
  border-radius: 2px;
  padding: 1px;
  box-sizing: border-box;
}

.battery::after {
  content: "";
  position: absolute;
  right: -3px;
  top: 2px;
  width: 2px;
  height: 4px;
  border-radius: 1px;
  background: #0f172a;
}

.battery-level {
  display: block;
  width: 75%;
  height: 100%;
  border-radius: 1px;
  background: linear-gradient(90deg, #49c36b 0%, #7adf64 100%);
}

.screen-header {
  padding: 18px 16px 14px;
  color: #0f172a;
  background: linear-gradient(135deg, #d7ecff 0%, #edf5ff 100%);
  border-bottom: 1px solid #dbe7f5;
}

.screen-header h2 {
  margin: 0;
  font-size: 20px;
}

.screen-header p {
  margin: 6px 0 0;
  color: #475569;
  font-size: 13px;
}

.screen-body {
  flex: 1;
  padding: 14px;
  overflow: auto;
}

.full {
  width: 100%;
  margin-bottom: 12px;
}

.task-list {
  display: grid;
  gap: 12px;
}

.task-item {
  padding: 12px;
  border: 1px solid #d9e3f0;
  border-radius: 14px;
  background: #ffffff;
  box-shadow: 0 4px 14px rgba(15, 23, 42, 0.05);
}

.line {
  margin-bottom: 6px;
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #334155;
}

.line strong {
  color: #0f172a;
}

.qty-input {
  margin-top: 10px;
}

.empty-tip {
  margin-top: 26px;
  text-align: center;
  color: #64748b;
  font-size: 14px;
}

.screen-footer {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  padding: 12px 14px calc(12px + env(safe-area-inset-bottom));
  background: #ffffff;
  border-top: 1px solid #e2e8f0;
}

.btn {
  height: 38px;
  border: 1px solid transparent;
  border-radius: 999px;
  background: linear-gradient(135deg, #f8fbff 0%, #eaf1fb 100%);
  color: #23384f;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
}

.btn:hover {
  filter: brightness(0.98);
  transform: translateY(-1px);
}

.btn-secondary {
  border-color: #c9d7ea;
  background: linear-gradient(135deg, #f5f8fc 0%, #e8eef7 100%);
  color: #35506d;
}

.btn-primary {
  border-color: #2d8fff;
  background: linear-gradient(135deg, #39a6ff 0%, #2d7dff 100%);
  color: #ffffff;
  box-shadow: 0 8px 18px rgba(45, 125, 255, 0.35);
}

.confirm-action {
  width: 100%;
  margin-top: 10px;
}

.screen-body /deep/ .confirm-action.el-button {
  border: none;
  border-radius: 10px;
  font-weight: 700;
  color: #ffffff;
  background: linear-gradient(135deg, #2ecc71 0%, #1faa59 100%);
  box-shadow: 0 8px 16px rgba(31, 170, 89, 0.35);
}

.screen-body /deep/ .confirm-action.el-button:hover {
  filter: brightness(0.97);
}

.btn[disabled] {
  opacity: 0.55;
  cursor: not-allowed;
  transform: none;
  box-shadow: none;
}

@media (max-width: 420px) {
  .mobile-stage {
    padding: 0;
  }

  .phone-shell {
    width: 100%;
    border-radius: 0;
    padding: 0;
  }

  .phone-notch {
    display: none;
  }

  .phone-screen {
    border-radius: 0;
    min-height: 100vh;
  }
}
</style>
