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
      name: "Login",
      hidden: true,
    },
    {
      path: "/404",
      component: () => import("@/common/404.vue"),
      name: "Notfound",
      hidden: true,
    },
    {
      path: "/",
      name: "Home",
      component: () => import("@/common/Home.vue"),
      redirect: "index",
      children: [
        {
          path: "/index",
          name: "Index",
          component: () => import("@/views/Index.vue"),
        },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "Basic Info",
      iconCls: "el-icon-menu",
      leaf: false,
      children: [
        { path: "/customer", name: "Customers", component: () => import("@/views/jcmd/customer.vue") },
        { path: "/shop", name: "Products", component: () => import("@/views/jcmd/shop.vue") },
        { path: "/dept", name: "Depositories", component: () => import("@/views/jcmd/dept.vue") },
        { path: "/supplier", name: "Suppliers", component: () => import("@/views/jcmd/supplier.vue") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "Purchase & Sale",
      iconCls: "el-icon-menu",
      children: [
        { path: "/purchase", name: "Purchase", component: () => import("@/views/jxmd/purchase.vue") },
        { path: "/purchaseExit", name: "Purchase Return", component: () => import("@/views/jxmd/purchaseExit.vue") },
        { path: "/sale", name: "Sale", component: () => import("@/views/jxmd/sale.vue") },
        { path: "/saleExit", name: "Sale Return", component: () => import("@/views/jxmd/saleExit.vue") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "Warehouse",
      iconCls: "el-icon-menu",
      children: [
        { path: "/depository", name: "Depository List", component: () => import("@/views/ckmd/depositoryList") },
        { path: "/stockList", name: "Stock List", component: () => import("@/views/ckmd/stockList") },
        { path: "/depositoryIn", name: "Inbound List", component: () => import("@/views/ckmd/depositoryIn") },
        { path: "/depositoryOut", name: "Outbound List", component: () => import("@/views/ckmd/depositoryOut") },
      ],
    },
    {
      path: "/",
      component: Home,
      name: "System",
      iconCls: "el-icon-menu",
      children: [
        { path: "/adminList", name: "Admins", component: () => import("@/views/xtmd/adminList.vue") },
        { path: "/roleList", name: "Roles", component: () => import("@/views/xtmd/roleList.vue") },
        { path: "/deptList", name: "Departments", component: () => import("@/views/xtmd/deptList.vue") },
        { path: "/syscfg", name: "System Config", component: () => import("@/views/xtmd/syscfg.vue") },
        { path: "/operationLog", name: "Operation Log", component: () => import("@/views/xtmd/operationLog.vue") },
      ],
    },
    {
      path: "/mobilePick",
      component: Home,
      name: "Mobile Pick",
      hidden: true,
      children: [
        { path: "", name: "MobilePick", component: () => import("@/views/mobile/pick.vue") },
      ],
    },
    {
      path: "*",
      hidden: true,
      redirect: { path: "/404" },
    },
  ],
});
