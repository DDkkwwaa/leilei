<template>
  <div class="dashboard">
    <section class="metric-grid">
      <article v-for="item in metrics" :key="item.label" class="metric-card">
        <span>{{ item.label }}</span>
        <strong>{{ item.value }}</strong>
      </article>
    </section>

    <section class="summary-grid">
      <article class="summary-card">
        <h3>待办业务</h3>
        <div class="summary-list">
          <div class="summary-row">
            <span>待收货采购单</span>
            <strong>{{ todo.purchasePending }}</strong>
          </div>
          <div class="summary-row">
            <span>待发货销售单</span>
            <strong>{{ todo.salePending }}</strong>
          </div>
          <div class="summary-row">
            <span>低库存商品</span>
            <strong>{{ todo.lowStock }}</strong>
          </div>
        </div>
      </article>

      <article class="summary-card">
        <h3>业务链路</h3>
        <div class="summary-list">
          <div class="summary-row summary-row--text">
            <span>主数据准备</span>
            <strong>供应商 / 客户 / 仓库 / 商品</strong>
          </div>
          <div class="summary-row summary-row--text">
            <span>采购环节</span>
            <strong>建单 -> 收货 -> 入库</strong>
          </div>
          <div class="summary-row summary-row--text">
            <span>销售环节</span>
            <strong>建单 -> 发货 -> 出库</strong>
          </div>
        </div>
      </article>
    </section>

    <section class="content-grid">
      <article class="panel-card">
        <h3>低库存预警</h3>
        <div v-if="lowStockList.length" class="mini-table">
          <div class="mini-table__head">
            <span>商品</span>
            <span>仓库</span>
            <span>库存</span>
          </div>
          <div v-for="item in lowStockList" :key="item.id || item.shop + item.depository" class="mini-table__row">
            <span>{{ item.shop }}</span>
            <span>{{ item.depository }}</span>
            <span>{{ item.quantity }}</span>
          </div>
        </div>
        <div v-else class="empty-card">当前没有低库存预警。</div>
      </article>

      <article class="panel-card">
        <h3>最近库存流水</h3>
        <div class="mini-table">
          <div class="mini-table__head">
            <span>流水号</span>
            <span>商品</span>
            <span>类型</span>
            <span>数量</span>
            <span>结余</span>
          </div>
          <div v-for="item in recentFlows" :key="item.flowId + item.type" class="mini-table__row">
            <span>{{ item.flowId }}</span>
            <span>{{ item.shopName }}</span>
            <span>{{ item.type }}</span>
            <span>{{ item.shopNumber }}</span>
            <span>{{ item.balance }}</span>
          </div>
        </div>
      </article>
    </section>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      metrics: [
        { label: "供应商数", value: 0 },
        { label: "客户数", value: 0 },
        { label: "仓库数", value: 0 },
        { label: "SKU数", value: 0 },
      ],
      todo: {
        purchasePending: 0,
        salePending: 0,
        lowStock: 0,
      },
      lowStockList: [],
      recentFlows: [],
    };
  },
  mounted() {
    this.loadDashboard();
  },
  methods: {
    listParams(keywordKey, keyword = "") {
      return {
        params: {
          pageNum: 1,
          pageSize: 50,
          [keywordKey]: keyword,
        },
      };
    },
    loadDashboard() {
      Promise.all([
        this.$http.get("/supplier/list", this.listParams("keyword")),
        this.$http.get("/customer/listByName", this.listParams("keyword")),
        this.$http.get("/depository/list", this.listParams("keyword")),
        this.$http.get("/shop/list", this.listParams("keyword")),
        this.$http.get("/purchase/list", this.listParams("keyword")),
        this.$http.get("/sale/list", this.listParams("keyword")),
        this.$http.get("/stock/list", this.listParams("keyword")),
        this.$http.get("/depositoryIn/list", this.listParams("keyword")),
        this.$http.get("/depositoryOut/list", this.listParams("keyword")),
      ]).then((responses) => {
        const [supplierRes, customerRes, depositoryRes, shopRes, purchaseRes, saleRes, stockRes, inRes, outRes] = responses;
        const suppliers = (supplierRes.data.data && supplierRes.data.data.total) || 0;
        const customers = (customerRes.data.data && customerRes.data.data.total) || 0;
        const depositories = (depositoryRes.data.data && depositoryRes.data.data.total) || 0;
        const shops = (shopRes.data.data && shopRes.data.data.total) || 0;
        const purchases = (purchaseRes.data.data && purchaseRes.data.data.list) || [];
        const sales = (saleRes.data.data && saleRes.data.data.list) || [];
        const stocks = (stockRes.data.data && stockRes.data.data.list) || [];
        const inbound = (inRes.data.data && inRes.data.data.list) || [];
        const outbound = (outRes.data.data && outRes.data.data.list) || [];

        this.metrics = [
          { label: "供应商数", value: suppliers },
          { label: "客户数", value: customers },
          { label: "仓库数", value: depositories },
          { label: "SKU数", value: shops },
        ];

        const lowStockList = stocks
          .slice()
          .sort((a, b) => Number(a.quantity || 0) - Number(b.quantity || 0))
          .filter((item) => Number(item.quantity || 0) <= 50)
          .slice(0, 5);

        this.lowStockList = lowStockList;
        this.todo = {
          purchasePending: purchases.filter((item) => Number(item.status) === 1).length,
          salePending: sales.filter((item) => Number(item.status) === 1).length,
          lowStock: lowStockList.length,
        };

        const stockMap = stocks.reduce((map, item) => {
          map[item.shop] = Number(item.quantity || 0);
          return map;
        }, {});

        this.recentFlows = inbound
          .map((item) => ({
            flowId: item.inId,
            shopName: item.shopName,
            type: "入库",
            shopNumber: item.shopNumber,
            balance: stockMap[item.shopName] || item.shopNumber || 0,
            rawDate: item.date || item.createDate,
          }))
          .concat(
            outbound.map((item) => ({
              flowId: item.outId,
              shopName: item.shopName,
              type: "出库",
              shopNumber: item.shopNumber,
              balance: stockMap[item.shopName] || 0,
              rawDate: item.date || item.createDate,
            }))
          )
          .sort((a, b) => new Date(b.rawDate || 0) - new Date(a.rawDate || 0))
          .slice(0, 6);
      });
    },
  },
};
</script>

<style lang="less" scoped>
.dashboard {
  display: grid;
  gap: 16px;
}

.metric-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 16px;
}

.metric-card,
.summary-card,
.panel-card {
  border: 1px solid rgba(233, 224, 209, 0.92);
  border-radius: 20px;
  background: #ffffff;
  box-shadow: 0 12px 26px rgba(70, 52, 20, 0.06);
}

.metric-card {
  padding: 20px 24px;

  span {
    color: #92938d;
    font-size: 13px;
    font-weight: 700;
  }

  strong {
    display: block;
    margin-top: 12px;
    color: #1f2f3c;
    font-size: 42px;
    line-height: 1;
    font-weight: 800;
  }
}

.summary-grid,
.content-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 16px;
}

.summary-card,
.panel-card {
  padding: 20px 24px;
}

.summary-card h3,
.panel-card h3 {
  margin: 0 0 16px;
  color: #1f2f3c;
  font-size: 19px;
  font-weight: 700;
}

.summary-list {
  display: grid;
  gap: 12px;
}

.summary-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 12px;
  border-bottom: 1px dashed #ece3d7;
  color: #67706c;
  font-size: 14px;
}

.summary-row:last-child {
  padding-bottom: 0;
  border-bottom: none;
}

.summary-row strong {
  color: #1f2f3c;
  font-size: 15px;
  font-weight: 600;
}

.summary-row--text {
  align-items: flex-start;
}

.mini-table {
  display: grid;
  gap: 10px;
}

.mini-table__head,
.mini-table__row {
  display: grid;
  grid-template-columns: 1.5fr 1.2fr 0.6fr 0.6fr 0.6fr;
  gap: 8px;
  color: #69726e;
  font-size: 13px;
}

.panel-card:first-child .mini-table__head,
.panel-card:first-child .mini-table__row {
  grid-template-columns: 1.4fr 1fr 0.5fr;
}

.mini-table__head {
  color: #9a9a93;
  font-weight: 700;
}

.mini-table__row {
  padding-top: 10px;
  border-top: 1px solid #f1eade;
}

.empty-card {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100px;
  border: 1px dashed #eadfce;
  border-radius: 16px;
  color: #9a9a93;
  font-size: 14px;
}

@media (max-width: 1080px) {
  .metric-grid,
  .summary-grid,
  .content-grid {
    grid-template-columns: 1fr;
  }
}
</style>
