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
            <p v-if="!isIndexPage">{{ pageMeta.description }}</p>
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
    title: "数智情景下的前置仓库信息系统",
    description: "",
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
    description: "按单号查看采购摘要与明细。",
  },
  "/purchaseExit": {
    title: "采购退货",
    description: "维护采购退货单、退货原因和金额。",
  },
  "/sale": {
    title: "销售单详情",
    description: "按单号查看销售摘要与明细。",
  },
  "/saleExit": {
    title: "销售退货",
    description: "集中处理销售退货、退款原因和处理状态。",
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
    description: "维护出库申请、审核与库存扣减流程。",
  },
  "/syscfg": {
    title: "个人中心",
    description: "查看当前登录管理员资料，并完成账号密码维护。",
  },
};

export default {
  name: "Home",
  components: {
    Aside,
    Header,
  },
  computed: {
    isIndexPage() {
      return this.$route.path === "/index";
    },
    pageMeta() {
      return (
        metaMap[this.$route.path] || {
          title: this.$route.name || "前置仓管理后台",
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
  background:
    radial-gradient(180px 260px at 8% 10%, rgba(173, 216, 255, 0.24), transparent 70%),
    radial-gradient(220px 300px at 92% 16%, rgba(197, 255, 229, 0.2), transparent 70%),
    linear-gradient(180deg, #edf3fb 0%, #e8eff8 100%);
}

.layout-frame {
  display: grid;
  grid-template-columns: 180px minmax(0, 1fr);
  gap: 0;
  min-height: 100vh;
}

.layout-aside {
  min-height: 100%;
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.66), rgba(245, 250, 255, 0.36));
  border-right: 1px solid rgba(255, 255, 255, 0.68);
  box-shadow: 6px 0 20px rgba(36, 58, 94, 0.1);
  backdrop-filter: blur(14px) saturate(1.18);
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
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.72), rgba(244, 250, 255, 0.46));
  border-bottom: 1px solid rgba(255, 255, 255, 0.66);
  box-shadow: 0 8px 20px rgba(36, 58, 94, 0.08);
  backdrop-filter: blur(12px) saturate(1.15);
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
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.18), rgba(255, 255, 255, 0));
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
