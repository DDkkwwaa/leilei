<template>
  <div class="mobile-page">
    <h2>Mobile Picking Tasks</h2>
    <el-select v-model="saleNumber" placeholder="Select order" class="full" @change="loadPath">
      <el-option v-for="item in orders" :key="item.saleNumber" :label="item.saleNumber" :value="item.saleNumber" />
    </el-select>

    <div v-if="saleNumber" class="task-list">
      <div v-for="(item, idx) in tasks" :key="item.shopName + item.location + idx" class="task-item">
        <div class="line"><strong>Location:</strong> {{ item.location || "-" }}</div>
        <div class="line"><strong>Product:</strong> {{ item.shopName }}</div>
        <div class="line"><strong>Need:</strong> {{ item.needQuantity }}</div>
        <div class="line"><strong>Picked:</strong> {{ item.pickedQuantity }}</div>
        <el-input v-model="item.scanCode" placeholder="Scan code / barcode" />
        <el-input-number v-model="item.scanQuantity" :min="1" :max="item.needQuantity" />
        <button type="button" class="btn" @click="confirmPick(item)">Confirm Pick</button>
      </div>
    </div>

    <div class="actions">
      <button type="button" class="btn" @click="syncQueue">Sync Offline Queue</button>
      <button type="button" class="btn btn-primary" @click="finishPick">Finish Picking</button>
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
      this.$http.get("/sale/waveList").then((res) => {
        const rows = res.data.data || [];
        this.orders = rows.filter((x) => x.statusCode === "PICKING");
        if (this.orders.length && !this.saleNumber) {
          this.saleNumber = this.orders[0].saleNumber;
          this.loadPath();
        }
      });
    },
    loadPath() {
      if (!this.saleNumber) return;
      this.$http.get("/sale/pickPath", { params: { saleNumber: this.saleNumber } }).then((res) => {
        this.tasks = (res.data.data || []).map((x) => ({
          ...x,
          pickedQuantity: 0,
          scanCode: "",
          scanQuantity: 1,
        }));
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
        this.$message.warning("Offline cached, will sync when online");
        return;
      }
      this.$http.post("/sale/pickScanConfirm", null, { params: payload }).then((res) => {
        if (res.data.code === 200) {
          item.pickedQuantity = Math.min(item.needQuantity, item.pickedQuantity + payload.quantity);
          this.$message.success("Confirmed");
        } else {
          this.$message.error(res.data.message || "Confirm failed");
        }
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
      if (!navigator.onLine) return;
      const queue = this.getQueue();
      if (!queue.length) return;
      const runner = queue.reduce((p, item) => {
        return p.then(() => this.$http.post("/sale/pickScanConfirm", null, { params: item }));
      }, Promise.resolve());
      runner
        .then(() => {
          localStorage.removeItem(QUEUE_KEY);
          this.$message.success("Offline queue synced");
        })
        .catch(() => {});
    },
    finishPick() {
      if (!this.saleNumber) return;
      const allDone = this.tasks.length > 0 && this.tasks.every((x) => Number(x.pickedQuantity) >= Number(x.needQuantity));
      if (!allDone) {
        this.$message.warning("There are unfinished items");
        return;
      }
      this.$http.post("/sale/finishPick", null, { params: { saleNumber: this.saleNumber } }).then((res) => {
        if (res.data.code === 200) {
          this.$message.success("Picking completed");
          this.saleNumber = "";
          this.tasks = [];
          this.loadOrders();
        } else {
          this.$message.error(res.data.message || "Complete failed");
        }
      });
    },
  },
};
</script>

<style scoped>
.mobile-page { max-width: 640px; margin: 0 auto; padding: 16px; }
.full { width: 100%; margin-bottom: 12px; }
.task-list { display: grid; gap: 12px; }
.task-item { padding: 12px; border: 1px solid #e6ded0; border-radius: 10px; background: #fff; }
.line { margin-bottom: 6px; font-size: 14px; }
.actions { margin-top: 16px; display: flex; gap: 10px; }
.btn { height: 34px; padding: 0 14px; border: 1px solid #e7ddce; border-radius: 999px; background: #fff; cursor: pointer; }
.btn-primary { border-color: #99c89f; color: #2a7b51; }
</style>
