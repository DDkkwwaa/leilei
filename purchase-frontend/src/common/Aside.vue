<template>
  <div class="aside-shell">
    <section class="brand-card">
      <span class="brand-badge">数智情景</span>
      <h2>前置仓库信息系统</h2>
    </section>

    <nav class="nav-groups">
      <section v-for="group in groupedMenus" :key="group.key" class="nav-group">
        <button type="button" class="nav-group__head" @click="toggleGroup(group.key)">
          <span class="nav-group__head-copy">
            <h3>{{ group.title }}</h3>
          </span>
          <em class="nav-group__caret" :class="{ 'is-open': isGroupOpen(group.key) }">⌄</em>
        </button>
        <transition name="drawer-slide">
          <div v-show="isGroupOpen(group.key)" class="nav-list">
            <button
              v-for="item in group.children"
              :key="item.path"
              type="button"
              class="nav-item"
              :class="{ 'is-active': isActive(item) }"
              @click="clickMenu(item)"
            >
              <span>{{ displayMenuName(item) }}</span>
              <em>{{ sequence(item._seq) }}</em>
            </button>
          </div>
        </transition>
      </section>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      menuData: [],
      collapsedGroups: {},
    };
  },
  computed: {
    groupedMenus() {
      let seq = 1;
      return (this.menuData || []).reduce((groups, parent) => {
        const children = Array.isArray(parent.children) ? [...parent.children] : [];
        if (!children.length && parent.path) {
          children.push(parent);
        }
        if (!children.length) {
          return groups;
        }
        groups.push({
          key: parent.path || parent.name || `group-${groups.length + 1}`,
          title: parent.label || parent.name || "未分类模块",
          children: children.map((item) => ({ ...item, _seq: seq++ })),
        });
        return groups;
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
        this.syncGroupState();
      });
    },
    sequence(index) {
      return String(index).padStart(2, "0");
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
    displayMenuName(item) {
      if (item.path === "/syscfg") {
        return "个人中心";
      }
      return item.label || item.name;
    },
    toggleGroup(groupKey) {
      this.$set(this.collapsedGroups, groupKey, !this.isGroupOpen(groupKey));
    },
    isGroupOpen(groupKey) {
      return this.collapsedGroups[groupKey] !== false;
    },
    syncGroupState() {
      const nextState = {};
      let hasActiveGroup = false;
      (this.groupedMenus || []).forEach((group, index) => {
        const active = group.children.some((item) => this.isActive(item));
        if (active) {
          hasActiveGroup = true;
        }
        nextState[group.key] = active || (!hasActiveGroup && index === 0);
      });
      this.collapsedGroups = nextState;
    },
  },
  watch: {
    $route() {
      this.syncGroupState();
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
  padding: 10px 8px;
  background:
    radial-gradient(120px 180px at 12% 10%, rgba(157, 202, 255, 0.2), transparent 65%),
    radial-gradient(180px 220px at 85% 24%, rgba(210, 255, 226, 0.16), transparent 70%),
    linear-gradient(180deg, rgba(255, 255, 255, 0.66), rgba(255, 255, 255, 0.38));
  backdrop-filter: blur(14px) saturate(1.2);
}

.brand-card {
  padding: 14px 12px;
  border: 1px solid rgba(255, 255, 255, 0.68);
  border-radius: 18px;
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.7), rgba(247, 252, 255, 0.46));
  box-shadow:
    0 16px 30px rgba(27, 45, 77, 0.13),
    inset 0 1px 0 rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(16px) saturate(1.25);
}

.brand-badge {
  display: inline-flex;
  align-items: center;
  height: 22px;
  padding: 0 10px;
  border-radius: 999px;
  background: linear-gradient(120deg, rgba(198, 246, 235, 0.84), rgba(196, 230, 255, 0.78));
  color: #2f6475;
  font-size: 10px;
  font-weight: 800;
  letter-spacing: 0.06em;
  border: 1px solid rgba(255, 255, 255, 0.7);
}

.brand-card h2 {
  margin: 12px 0 0;
  color: #21303e;
  font-size: 15px;
  line-height: 1.3;
}

.nav-groups {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.nav-group {
  border: 1px solid rgba(255, 255, 255, 0.62);
  border-radius: 16px;
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.66), rgba(246, 250, 255, 0.42));
  overflow: hidden;
  box-shadow:
    0 10px 22px rgba(31, 41, 55, 0.1),
    inset 0 1px 0 rgba(255, 255, 255, 0.82);
  backdrop-filter: blur(14px) saturate(1.2);
}

.nav-group__head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  border: none;
  padding: 8px 10px 7px;
  background: linear-gradient(160deg, rgba(252, 255, 255, 0.52), rgba(241, 248, 255, 0.36));
  border-bottom: 1px solid rgba(255, 255, 255, 0.48);
  text-align: left;
  cursor: pointer;
  transition: background 0.18s ease;
}

.nav-group__head:hover {
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.75), rgba(241, 250, 255, 0.5));
}

.nav-group__head-copy {
  min-width: 0;
}

.nav-group__head h3 {
  margin: 0;
  color: #314355;
  font-size: 12px;
  line-height: 1.2;
}

.nav-group__caret {
  margin-left: 10px;
  color: #67727f;
  font-size: 16px;
  font-style: normal;
  transition: transform 0.18s ease;
}

.nav-group__caret.is-open {
  transform: rotate(180deg);
}

.nav-list {
  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 6px;
  overflow: hidden;
}

.drawer-slide-enter-active,
.drawer-slide-leave-active {
  transition: all 0.2s ease;
}

.drawer-slide-enter,
.drawer-slide-leave-to {
  opacity: 0;
  transform: translateY(-4px);
}

.nav-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  min-height: 38px;
  padding: 0 10px;
  border: none;
  border-radius: 10px;
  border: 1px solid transparent;
  background: rgba(255, 255, 255, 0.24);
  color: #5c6875;
  text-align: left;
  cursor: pointer;
  transition: all 0.16s ease;

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
  border-color: rgba(255, 255, 255, 0.72);
  transform: translateY(-1px);
}

.nav-item.is-active {
  background: linear-gradient(120deg, rgba(199, 235, 255, 0.62), rgba(203, 247, 229, 0.58));
  color: #2f7f78;
  border-color: rgba(255, 255, 255, 0.78);
  box-shadow:
    0 6px 16px rgba(57, 111, 164, 0.16),
    inset 0 0 0 1px rgba(157, 208, 203, 0.45);
}

.nav-item.is-active em {
  color: #2b8b86;
}
</style>
