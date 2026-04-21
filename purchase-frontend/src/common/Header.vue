<template>
  <div class="header-actions">
    <button type="button" class="bell-pill" @click="goWarningPage">
      <span class="bell-pill__icon">预警</span>
      <span class="bell-pill__text">预警</span>
      <em v-if="warningCount > 0" class="bell-pill__badge">{{ warningCount > 99 ? "99+" : warningCount }}</em>
    </button>

    <button type="button" class="admin-pill" @click="goProfilePage">
      <span class="admin-pill__icon">系统</span>
      <span class="admin-pill__copy">
        <small>系统管理员</small>
        <strong>{{ username }}</strong>
      </span>
    </button>

    <button type="button" class="logout-pill" @click="handleLogout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      warningCount: 0,
      warningTimer: null,
    };
  },
  computed: {
    username() {
      return sessionStorage.getItem("username") || "ADMIN";
    },
  },
  mounted() {
    this.loadWarningCount();
    this.warningTimer = setInterval(this.loadWarningCount, 60000);
  },
  beforeDestroy() {
    if (this.warningTimer) {
      clearInterval(this.warningTimer);
      this.warningTimer = null;
    }
  },
  methods: {
    loadWarningCount() {
      this.$http
        .get("/stock/warningCount")
        .then((res) => {
          this.warningCount = Number((res.data && res.data.data) || 0);
        })
        .catch(() => {
          this.warningCount = 0;
        });
    },
    goWarningPage() {
      if (this.$route.path !== "/stockList") {
        this.$router.push("/stockList");
      }
    },
    goProfilePage() {
      if (this.$route.path !== "/syscfg") {
        this.$router.push("/syscfg");
      }
    },
    handleLogout() {
      this.$confirm("确认退出当前账号吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$router.push("/login");
        })
        .catch(() => {
          this.$message.info("已取消退出");
        });
    },
  },
};
</script>

<style lang="less" scoped>
.header-actions {
  display: flex;
  align-items: center;
  gap: 10px;
}

.bell-pill,
.admin-pill,
.logout-pill {
  display: inline-flex;
  align-items: center;
  border: 1px solid rgba(255, 255, 255, 0.72);
  border-radius: 999px;
  background: linear-gradient(160deg, rgba(255, 255, 255, 0.76), rgba(246, 250, 255, 0.5));
  box-shadow:
    0 10px 24px rgba(33, 56, 92, 0.14),
    inset 0 1px 0 rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(10px) saturate(1.15);
}

.bell-pill {
  gap: 8px;
  height: 46px;
  padding: 0 14px;
  color: #3f4754;
  font-size: 12px;
  font-weight: 700;
  cursor: pointer;
}

.bell-pill__badge {
  min-width: 20px;
  height: 20px;
  padding: 0 6px;
  border-radius: 999px;
  background: #e64b3c;
  color: #fff;
  font-style: normal;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
}

.admin-pill {
  gap: 10px;
  padding: 6px 12px 6px 6px;
  cursor: pointer;
}

.admin-pill__icon {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border-radius: 999px;
  background: linear-gradient(135deg, #5c89fb 0%, #4a75e9 100%);
  color: #ffffff;
  font-size: 12px;
  font-weight: 800;
}

.admin-pill__copy {
  display: flex;
  flex-direction: column;
  align-items: flex-start;

  small {
    color: #8d9189;
    font-size: 10px;
    font-weight: 700;
  }

  strong {
    margin-top: 2px;
    color: #5c6171;
    font-size: 14px;
    line-height: 1;
  }
}

.logout-pill {
  height: 46px;
  padding: 0 16px;
  color: #3f4754;
  font-size: 12px;
  font-weight: 700;
  cursor: pointer;
}
</style>
