<template>
  <div class="detail-page">
    <section class="detail-card">
      <div class="detail-toolbar">
        <button type="button" class="plain-btn" @click="loadOrders">刷新列表</button>
        <el-select v-model="selectedNumber" placeholder="选择采购单" class="order-select">
          <el-option v-for="item in orders" :key="item.number" :label="item.number" :value="item.number"></el-option>
        </el-select>
      </div>

      <template v-if="currentOrder">
        <div class="detail-meta">
          <div class="detail-row">
            <span>订单号</span>
            <strong>{{ currentOrder.number }}</strong>
            <span>订单日期</span>
            <strong>{{ formatTime(currentOrder.time) }}</strong>
          </div>
          <div class="detail-row">
            <span>供应商</span>
            <strong>{{ currentOrder.supplier }}</strong>
            <span>采购员</span>
            <strong>{{ currentOrder.purchaseUser }}</strong>
          </div>
          <div class="detail-row">
            <span>状态</span>
            <strong>
              <em class="status-pill" :class="{ 'is-done': Number(currentOrder.status) === 0 }">
                {{ Number(currentOrder.status) === 0 ? "已完成" : "进行中" }}
              </em>
            </strong>
            <span>总金额</span>
            <strong>{{ currentOrder.totalAmount }}</strong>
          </div>
          <div class="detail-row">
            <span>备注</span>
            <strong>{{ currentOrder.remark || "首批账务已核签" }}</strong>
          </div>
        </div>
      </template>
      <div v-else class="empty-card">暂无采购单数据。</div>
    </section>

    <section class="detail-card">
      <h3>明细行</h3>
      <div v-if="currentOrder" class="detail-table">
        <div class="detail-table__head">
          <span>商品编码</span>
          <span>商品</span>
          <span>数量</span>
          <span>单价</span>
          <span>金额</span>
        </div>
        <div v-for="(item, index) in currentOrder.rows" :key="item.id || index" class="detail-table__row">
          <span>{{ formatSkuCode(item.id, index) }}</span>
          <span>{{ item.shop }}</span>
          <span>{{ item.quantity }}</span>
          <span>{{ item.price }}</span>
          <span>{{ item.totalPrice || item.quantity * item.price }}</span>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "purchase",
  data() {
    return {
      orders: [],
      selectedNumber: "",
    };
  },
  computed: {
    currentOrder() {
      return this.orders.find((item) => item.number === this.selectedNumber) || null;
    },
  },
  mounted() {
    this.loadOrders();
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
    loadOrders() {
      this.$http
        .get("/purchase/list", {
          params: {
            pageNum: 1,
            pageSize: 200,
            keyword: "",
          },
        })
        .then((res) => {
          const rows = (res.data.data && res.data.data.list) || [];
          const map = rows.reduce((groups, item) => {
            if (!groups[item.number]) {
              groups[item.number] = {
                number: item.number,
                supplier: item.supplier,
                purchaseUser: item.purchaseUser,
                status: item.status,
                time: item.time,
                remark: item.remark,
                rows: [],
                totalAmount: 0,
              };
            }
            groups[item.number].rows.push(item);
            groups[item.number].totalAmount += Number(item.totalPrice || item.quantity * item.price || 0);
            return groups;
          }, {});

          this.orders = Object.values(map).sort((a, b) => new Date(b.time || 0) - new Date(a.time || 0));
          this.selectedNumber = this.orders.length ? this.orders[0].number : "";
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
  margin-bottom: 16px;
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

.order-select {
  width: 260px;
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
  background: #fff1d9;
  color: #bb8532;
  font-size: 12px;
}

.status-pill.is-done {
  background: #def4e8;
  color: #398d67;
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
