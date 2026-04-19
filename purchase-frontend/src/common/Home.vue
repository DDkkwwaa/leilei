<template>
  <div class="layout-shell">
    <div class="layout-frame">
      <aside class="layout-aside">
        <Aside />
      </aside>

      <main class="layout-main">
        <section class="page-hero">
          <div class="page-hero__copy">
            <h1>{{ pageMeta.title }}</h1>
            <p>{{ pageMeta.description }}</p>
          </div>
          <Header />
        </section>

        <section class="layout-content">
          <router-view />
        </section>
      </main>
    </div>
  </div>
</template>

<script>
import Aside from "@/common/Aside.vue";
import Header from "@/common/Header.vue";

const metaMap = {
  "/index": {
    title: "运营驾驶舱",
    description: "围绕 \"人·货·场·单\" 构建采购、库存与销售的一体化管控视图。",
  },
  "/supplier": {
    title: "供应商管理",
    description: "维护供应商编码、联系人与地址，为采购流程提供基础主数据。",
  },
  "/customer": {
    title: "客户管理",
    description: "维护客户档案与往来信息，支撑销售链路和回款记录。",
  },
  "/depository": {
    title: "仓库管理",
    description: "查看仓库容量、库区结构与库存承载情况。",
  },
  "/shop": {
    title: "商品管理",
    description: "管理商品资料、规格属性与分类体系。",
  },
  "/purchase": {
    title: "采购单详情",
    description: "按单号查看采购摘要与明细，不再继续使用旧式列表弹窗。",
  },
  "/purchaseExit": {
    title: "采购退货",
    description: "维护采购退货单、退货原因和金额，职责上独立于采购主单。",
  },
  "/sale": {
    title: "销售单详情",
    description: "按单号查看销售摘要与明细，统一销售业务阅读路径。",
  },
  "/saleExit": {
    title: "销售退货",
    description: "集中处理销售退货、退款原因和处理状态，避免继续挤在销售正向流程里。",
  },
  "/stockList": {
    title: "库存台账",
    description: "展示实时结存、低库存预警与最近出入库流水。",
  },
  "/depositoryIn": {
    title: "入库清单",
    description: "记录待入库与已入库物料，执行质检与入库确认。",
  },
  "/depositoryOut": {
    title: "出库清单",
    description: "维护出库申请、审核与库存扣减流程，并与入库页保持同一套布局职责。",
  },
};

export default {
  name: "Home",
  components: {
    Aside,
    Header,
  },
  computed: {
    pageMeta() {
      return (
        metaMap[this.$route.path] || {
          title: this.$route.name || "供应链后台",
          description: "保持统一的业务管理视图与操作体验。",
        }
      );
    },
  },
};
</script>

<style lang="less" scoped>
.layout-shell {
  min-height: 100vh;
  padding: 0;
  background: linear-gradient(180deg, #f7f3ec 0%, #f4efe6 100%);
}

.layout-frame {
  display: grid;
  grid-template-columns: 180px minmax(0, 1fr);
  gap: 0;
  min-height: 100vh;
}

.layout-aside {
  min-height: 100%;
  background: #ffffff;
  border-right: 1px solid #e9e0d1;
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
}

.layout-main {
  min-width: 0;
  display: flex;
  flex-direction: column;
}

.page-hero {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 24px;
  padding: 16px 24px;
  background: #ffffff;
  border-bottom: 1px solid #e9e0d1;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  margin-bottom: 0;
}

.page-hero__copy h1 {
  margin: 0;
  color: #1d2d3a;
  font-size: 20px;
  line-height: 1.1;
  font-weight: 700;
}

.page-hero__copy p {
  margin-top: 4px;
  color: #7b867e;
  font-size: 12px;
}

.layout-content {
  min-width: 0;
  flex: 1;
  padding: 24px;
  overflow-y: auto;
}

@media (max-width: 1080px) {
  .layout-frame {
    grid-template-columns: 1fr;
  }

  .layout-aside {
    display: none;
  }

  .page-hero {
    flex-direction: column;
    align-items: stretch;
    gap: 12px;
  }

  .layout-content {
    padding: 16px;
  }
}
</style>
