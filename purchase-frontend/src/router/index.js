import Vue from "vue";
import Router from "vue-router";
import Home from "@/common/Home";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/login",
      component: () => import("@/common/Login.vue"),
      name: "登录",
      hidden: true,
    },
    {
      path: "/404",
      component: () => import("@/common/404.vue"),
      name: "页面不存在",
      hidden: true,
    },
    {
      path: "/",
      name: "首页",
      component: () => import("@/common/Home.vue"),
      redirect: "index",
      children: [
        {
          path: "/index",
          name: "仪表盘",
          component: () => import("@/views/Index.vue"),
        },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "基础资料",
      iconCls: "el-icon-menu",
      leaf: false,
      children: [
        { path: "/customer", name: "客户资料", component: () => import("@/views/jcmd/customer.vue") },
        { path: "/shop", name: "商品资料", component: () => import("@/views/jcmd/shop.vue") },
        { path: "/dept", name: "部门资料", component: () => import("@/views/jcmd/dept.vue") },
        { path: "/supplier", name: "供应商资料", component: () => import("@/views/jcmd/supplier.vue") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "采购销售",
      iconCls: "el-icon-menu",
      children: [
        { path: "/purchase", name: "采购", component: () => import("@/views/jxmd/purchase.vue") },
        { path: "/purchaseExit", name: "采购退货", component: () => import("@/views/jxmd/purchaseExit.vue") },
        { path: "/sale", name: "销售", component: () => import("@/views/jxmd/sale.vue") },
        { path: "/saleExit", name: "销售退货", component: () => import("@/views/jxmd/saleExit.vue") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "仓库管理",
      iconCls: "el-icon-menu",
      children: [
        { path: "/depository", name: "仓库列表", component: () => import("@/views/ckmd/depositoryList") },
        { path: "/stockList", name: "库存列表", component: () => import("@/views/ckmd/stockList") },
        { path: "/depositoryIn", name: "入库清单", component: () => import("@/views/ckmd/depositoryIn") },
        { path: "/depositoryOut", name: "出库清单", component: () => import("@/views/ckmd/depositoryOut") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "系统管理",
      iconCls: "el-icon-menu",
      children: [
        { path: "/adminList", name: "员工管理", component: () => import("@/views/xtmd/adminList.vue") },
        { path: "/roleList", name: "角色管理", component: () => import("@/views/xtmd/roleList.vue") },
        { path: "/deptList", name: "部门管理", component: () => import("@/views/xtmd/deptList.vue") },
        { path: "/syscfg", name: "系统配置", component: () => import("@/views/xtmd/syscfg.vue") },
        { path: "/operationLog", name: "操作日志", component: () => import("@/views/xtmd/operationLog.vue") },
      ],
    },
    {
      path: "/mobilePick",
      component: Home,
      name: "移动拣货",
      hidden: true,
      children: [
        { path: "", name: "移动拣货页", component: () => import("@/views/mobile/pick.vue") },
      ],
    },
    {
      path: "*",
      hidden: true,
      redirect: { path: "/404" },
    },
  ],
});
