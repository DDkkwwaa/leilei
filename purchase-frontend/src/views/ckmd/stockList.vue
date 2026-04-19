<template>
  <div class="ledger-page">
    <section class="ledger-card">
      <h3>库存结存</h3>
      <div class="ledger-table">
        <div class="ledger-table__head">
          <span>仓库</span>
          <span>商品</span>
          <span>现存量</span>
          <span>安全库存</span>
          <span>预警</span>
        </div>
        <div v-for="item in stockList" :key="item.id || item.shop + item.depository" class="ledger-table__row">
          <span>{{ item.depository }}</span>
          <span>{{ item.shop }}</span>
          <span>{{ item.quantity }}</span>
          <span>{{ safeLevel(item.quantity) }}</span>
          <span>
            <em class="status-pill" :class="{ 'is-warning': Number(item.quantity || 0) <= safeLevel(item.quantity) }">
              {{ Number(item.quantity || 0) <= safeLevel(item.quantity) ? "预警" : "正常" }}
            </em>
          </span>
        </div>
      </div>
    </section>

    <section class="ledger-card">
      <h3>最近流水</h3>
      <div class="ledger-table">
        <div class="ledger-table__head">
          <span>流水号</span>
          <span>业务类型</span>
          <span>商品</span>
          <span>数量</span>
          <span>结余</span>
        </div>
        <div v-for="item in flowList" :key="item.flowId + item.type" class="ledger-table__row">
          <span>{{ item.flowId }}</span>
          <span>{{ item.type }}</span>
          <span>{{ item.shopName }}</span>
          <span>{{ item.shopNumber }}</span>
          <span>{{ item.balance }}</span>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "stockList",
  data() {
    return {
      stockList: [],
      flowList: [],
    };
  },
  mounted() {
    this.loadData();
  },
  methods: {
    safeLevel(quantity) {
      const current = Number(quantity || 0);
      if (current <= 25) {
        return 15;
      }
      if (current <= 60) {
        return 20;
      }
      return 30;
    },
    loadData() {
      Promise.all([
        this.$http.get("/stock/list", { params: { pageNum: 1, pageSize: 100, keyword: "" } }),
        this.$http.get("/depositoryIn/list", { params: { pageNum: 1, pageSize: 50, keyword: "" } }),
        this.$http.get("/depositoryOut/list", { params: { pageNum: 1, pageSize: 50, keyword: "" } }),
      ]).then(([stockRes, inRes, outRes]) => {
        const stocks = (stockRes.data.data && stockRes.data.data.list) || [];
        const inbound = (inRes.data.data && inRes.data.data.list) || [];
        const outbound = (outRes.data.data && outRes.data.data.list) || [];
        const stockMap = stocks.reduce((map, item) => {
          map[item.shop] = Number(item.quantity || 0);
          return map;
        }, {});

        this.stockList = stocks.slice(0, 8);
        this.flowList = inbound
          .map((item) => ({
            flowId: item.inId,
            type: "采购收货",
            shopName: item.shopName,
            shopNumber: item.shopNumber,
            balance: stockMap[item.shopName] || item.shopNumber || 0,
            rawDate: item.date || item.createDate,
          }))
          .concat(
            outbound.map((item) => ({
              flowId: item.outId,
              type: "销售发货",
              shopName: item.shopName,
              shopNumber: item.shopNumber,
              balance: stockMap[item.shopName] || 0,
              rawDate: item.date || item.createDate,
            }))
          )
          .sort((a, b) => new Date(b.rawDate || 0) - new Date(a.rawDate || 0))
          .slice(0, 8);
      });
    },
  },
};
</script>

<style lang="less" scoped>
.ledger-page {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 12px;
}

.ledger-card {
  padding: 16px 18px;
  border: 1px solid rgba(233, 224, 209, 0.92);
  border-radius: 18px;
  background: #ffffff;
  box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06);
}

.ledger-card h3 {
  margin: 0 0 12px;
  color: #1f2f3c;
  font-size: 22px;
}

.ledger-table {
  display: grid;
  gap: 8px;
}

.ledger-table__head,
.ledger-table__row {
  display: grid;
  grid-template-columns: 1fr 1.2fr 0.6fr 0.7fr 0.6fr;
  gap: 10px;
  font-size: 13px;
}

.ledger-table__head {
  color: #9a9a93;
  font-weight: 700;
}

.ledger-table__row {
  padding-top: 10px;
  border-top: 1px solid #f1eade;
  color: #596260;
}

.status-pill {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 42px;
  height: 24px;
  padding: 0 10px;
  border-radius: 999px;
  background: #def4e8;
  color: #398d67;
  font-size: 12px;
}

.status-pill.is-warning {
  background: #fff1d9;
  color: #bb8532;
}

@media (max-width: 1080px) {
  .ledger-page {
    grid-template-columns: 1fr;
  }
}
</style>
