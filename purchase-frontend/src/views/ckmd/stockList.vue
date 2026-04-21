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
          <span>状态</span>
        </div>
        <div v-for="item in stockList" :key="item.id || item.shop + item.depository" class="ledger-table__row">
          <span>{{ item.depository }}</span>
          <span>{{ item.shop }}</span>
          <span>{{ item.quantity }}</span>
          <span>{{ getSafeStock(item) }}</span>
          <span>
            <em class="status-pill" :class="{ 'is-warning': isWarning(item) }">
              {{ isWarning(item) ? "预警" : "正常" }}
            </em>
          </span>
        </div>
      </div>
    </section>

    <section class="ledger-card">
      <h3>库存预警</h3>
      <div v-if="warningList.length" class="ledger-table">
        <div class="ledger-table__head warning-head">
          <span>商品</span>
          <span>仓库</span>
          <span>现存量</span>
          <span>预警类型</span>
          <span>临期日期</span>
        </div>
        <div v-for="item in warningList.slice(0, 10)" :key="'warning-' + item.stockId" class="ledger-table__row warning-row">
          <span>{{ item.shop }}</span>
          <span>{{ item.depository }}</span>
          <span>{{ item.quantity }}</span>
          <span class="warn-value">{{ warningTypeText(item.warningType) }}</span>
          <span>{{ item.expireDate || "-" }}</span>
        </div>
      </div>
      <div v-else class="empty-card">当前暂无预警。</div>
    </section>

    <section class="ledger-card">
      <h3>补货建议</h3>
      <div v-if="replenishList.length" class="ledger-table">
        <div class="ledger-table__head replenish-head">
          <span>商品</span>
          <span>仓库</span>
          <span>现存量</span>
          <span>目标库存</span>
          <span>建议补货</span>
        </div>
        <div
          v-for="item in replenishList.slice(0, 10)"
          :key="'replenish-' + item.stockId"
          class="ledger-table__row replenish-row"
        >
          <span>{{ item.shop }}</span>
          <span>{{ item.depository }}</span>
          <span>{{ item.quantity }}</span>
          <span>{{ item.targetStock }}</span>
          <span class="replenish-value">{{ item.suggestQuantity }}</span>
        </div>
      </div>
      <div v-else class="empty-card">当前无需补货。</div>
    </section>

    <section class="ledger-card">
      <h3>库存分析（按缺货频次）</h3>
      <div class="detail-toolbar">
        <el-input v-model="analysisShopType" clearable placeholder="按品类筛选" class="query-input" />
        <button type="button" class="plain-btn" @click="loadAnalysis">查询</button>
      </div>
      <div class="ledger-table">
        <div class="ledger-table__head analysis-head">
          <span>商品</span>
          <span>品类</span>
          <span>库存</span>
          <span>近7日销量</span>
          <span>缺货频次</span>
        </div>
        <div v-for="item in analysisList.slice(0, 20)" :key="'analysis-' + item.shop" class="ledger-table__row analysis-row">
          <span>{{ item.shop }}</span>
          <span>{{ item.shopType || "-" }}</span>
          <span>{{ item.stockQuantity }}</span>
          <span>{{ item.recentSevenDaySale }}</span>
          <span class="warn-value">{{ item.shortageFrequency }}</span>
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
      warningList: [],
      replenishList: [],
      analysisList: [],
      analysisShopType: "",
      warningMap: {},
    };
  },
  mounted() {
    this.loadData();
    this.loadAnalysis();
  },
  methods: {
    getSafeStock(item) {
      const key = item.id;
      if (this.warningMap[key] && this.warningMap[key].safeStock !== undefined) {
        return this.warningMap[key].safeStock;
      }
      return 10;
    },
    isWarning(item) {
      const key = item.id;
      if (this.warningMap[key]) {
        return !!this.warningMap[key].warning;
      }
      return Number(item.quantity || 0) <= 10;
    },
    warningTypeText(type) {
      if (type === "LOW_STOCK") return "低库存";
      if (type === "NEAR_EXPIRE") return "临期";
      if (type === "BOTH") return "低库存+临期";
      return "正常";
    },
    loadData() {
      Promise.all([
        this.$http.get("/stock/list", { params: { pageNum: 1, pageSize: 100, keyword: "" } }),
        this.$http.get("/stock/warningList"),
        this.$http.get("/stock/replenishList"),
      ]).then(([stockRes, warningRes, replenishRes]) => {
        const stocks = (stockRes.data.data && stockRes.data.data.list) || [];
        const warnings = warningRes.data.data || [];
        const replenish = replenishRes.data.data || [];

        this.warningList = warnings.filter((item) => !!item.warning);
        this.replenishList = replenish;
        this.warningMap = warnings.reduce((map, item) => {
          map[item.stockId] = item;
          return map;
        }, {});
        this.stockList = stocks.slice(0, 10);
      });
    },
    loadAnalysis() {
      this.$http
        .get("/stock/analysisList", {
          params: {
            shopType: this.analysisShopType,
          },
        })
        .then((res) => {
          this.analysisList = res.data.data || [];
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

.empty-card {
  color: #8b8f99;
  padding: 20px 0 8px;
}

.detail-toolbar {
  display: flex;
  gap: 10px;
  margin-bottom: 10px;
}

.query-input {
  width: 220px;
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

.ledger-table {
  display: grid;
  gap: 8px;
}

.ledger-table__head,
.ledger-table__row {
  display: grid;
  grid-template-columns: 1fr 1.2fr 0.7fr 0.8fr 0.7fr;
  gap: 10px;
  font-size: 13px;
}

.warning-head,
.warning-row,
.replenish-head,
.replenish-row,
.analysis-head,
.analysis-row {
  grid-template-columns: 1.1fr 1fr 0.7fr 0.8fr 0.8fr;
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

.warn-value {
  color: #c65d42;
  font-weight: 700;
}

.replenish-value {
  color: #2f8a72;
  font-weight: 700;
}

@media (max-width: 1080px) {
  .ledger-page {
    grid-template-columns: 1fr;
  }
}
</style>
