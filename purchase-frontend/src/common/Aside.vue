<template>
  <div class="aside-shell">
    <section class="brand-card">
      <span class="brand-badge">F088 SCM</span>
      <h2>供应链管理系统</h2>
      <p>Spring Boot 单体应用，覆盖主数据、采购、库存、销售和经营看板。</p>
    </section>

    <nav class="nav-list">
      <button
        v-for="(item, index) in flatMenuItems"
        :key="item.path"
        type="button"
        class="nav-item"
        :class="{ 'is-active': isActive(item) }"
        @click="clickMenu(item)"
      >
        <span>{{ item.label }}</span>
        <em>{{ sequence(index) }}</em>
      </button>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      menuData: [],
    };
  },
  computed: {
    flatMenuItems() {
      return this.menuData.reduce((list, item) => {
        if (item.children && item.children.length) {
          return list.concat(item.children);
        }
        list.push(item);
        return list;
      }, []);
    },
  },
  mounted() {
    this.initMenu();
  },
  methods: {
    initMenu() {
      this.menuData = [];
      this.$http.get("/menu/getShowMenu").then((res) => {
        this.menuData = res.data.data || [];
      });
    },
    sequence(index) {
      return String(index + 1).padStart(2, "0");
    },
    isActive(item) {
      return this.$route.path === item.path;
    },
    clickMenu(item) {
      if (this.$route.path !== item.path && !(this.$route.path === "/index" && item.path === "/")) {
        this.$router.push(item.path);
      }
      this.$store.commit("menuChange", item);
    },
  },
};
</script>

<style lang="less" scoped>
.aside-shell {
  display: flex;
  flex-direction: column;
  gap: 14px;
  min-height: 100%;
}

.brand-card {
  padding: 14px 12px;
  border: 1px solid rgba(228, 221, 205, 0.9);
  border-radius: 16px;
  background: #ffffff;
  box-shadow: 0 10px 24px rgba(53, 43, 23, 0.06);
}

.brand-badge {
  display: inline-flex;
  align-items: center;
  height: 22px;
  padding: 0 10px;
  border-radius: 999px;
  background: #dff2ee;
  color: #2d8b87;
  font-size: 10px;
  font-weight: 800;
  letter-spacing: 0.06em;
}

.brand-card h2 {
  margin: 12px 0 8px;
  color: #21303e;
  font-size: 15px;
  line-height: 1.3;
}

.brand-card p {
  color: #8a8f89;
  font-size: 12px;
  line-height: 1.65;
}

.nav-list {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.nav-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  min-height: 38px;
  padding: 0 10px;
  border: none;
  border-radius: 12px;
  background: transparent;
  color: #6f746d;
  text-align: left;
  cursor: pointer;

  span {
    font-size: 12px;
    font-weight: 600;
  }

  em {
    color: #a4a399;
    font-size: 11px;
    font-style: normal;
    font-weight: 700;
  }
}

.nav-item:hover {
  background: rgba(255, 255, 255, 0.62);
}

.nav-item.is-active {
  background: #e7f4ef;
  color: #2b8b86;
  box-shadow: inset 0 0 0 1px rgba(149, 205, 193, 0.55);
}

.nav-item.is-active em {
  color: #2b8b86;
}
</style>
