<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style lang="less">
:root {
  --glass-bg: linear-gradient(155deg, rgba(255, 255, 255, 0.72), rgba(245, 250, 255, 0.42));
  --glass-bg-soft: linear-gradient(160deg, rgba(255, 255, 255, 0.52), rgba(245, 250, 255, 0.28));
  --glass-border: rgba(255, 255, 255, 0.72);
  --glass-shadow: 0 16px 34px rgba(28, 44, 72, 0.14);
  --glass-inset: inset 0 1px 0 rgba(255, 255, 255, 0.9);
  --theme-primary: #4c7ef8;
  --theme-primary-dark: #3f6fe4;
  --theme-text: #243448;
  --theme-text-secondary: #6d7783;
}

@supports (backdrop-filter: blur(1px)) {
  .glass-surface {
    backdrop-filter: blur(14px) saturate(1.2);
  }
}

html,
body,
#app {
  min-height: 100%;
}

html,
body,
p {
  margin: 0;
  padding: 0;
}

body {
  font-family: "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
  background:
    radial-gradient(160px 220px at 8% 8%, rgba(173, 216, 255, 0.26), transparent 68%),
    radial-gradient(220px 260px at 92% 18%, rgba(204, 255, 232, 0.18), transparent 70%),
    linear-gradient(180deg, #edf3fb 0%, #e8eff8 100%);
  color: var(--theme-text);
}

* {
  box-sizing: border-box;
}

.page-card,
.box-card.el-card {
  border: 1px solid var(--glass-border);
  border-radius: 18px;
  background: var(--glass-bg);
  box-shadow: var(--glass-shadow), var(--glass-inset);
  backdrop-filter: blur(14px) saturate(1.2);
  overflow: hidden;
}

.box-card.el-card .el-card__body,
.page-card .el-card__body {
  padding: 18px 18px 16px;
}

.demo-form-inline {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 6px 10px;
  margin-bottom: 16px;
}

.demo-form-inline .el-form-item {
  margin-bottom: 10px;
}

.el-input__inner,
.el-textarea__inner {
  border-color: var(--glass-border);
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.62);
  color: var(--theme-text);
  transition: all 0.2s ease;
  box-shadow: var(--glass-inset);
}

.el-input__inner {
  height: 40px;
}

.el-input__inner:focus,
.el-textarea__inner:focus {
  border-color: var(--theme-primary);
  background: rgba(255, 255, 255, 0.85);
  box-shadow: 0 0 0 4px rgba(76, 126, 248, 0.12);
}

.el-button {
  border-radius: 999px;
  font-weight: 600;
}

.el-button--primary {
  border-color: transparent;
  background: linear-gradient(135deg, var(--theme-primary) 0%, var(--theme-primary-dark) 100%);
  box-shadow: 0 12px 24px rgba(63, 111, 228, 0.24);
}

.el-button--primary:hover,
.el-button--primary:focus {
  border-color: transparent;
  background: linear-gradient(135deg, #5a89f8 0%, #4677ec 100%);
}

.el-button--info,
.el-button--default {
  border-color: var(--glass-border);
  background: rgba(255, 255, 255, 0.56);
  color: #56606e;
  box-shadow: var(--glass-inset);
}

.el-button--info:hover,
.el-button--default:hover,
.el-button--info:focus,
.el-button--default:focus {
  border-color: rgba(76, 126, 248, 0.22);
  background: rgba(255, 255, 255, 0.82);
  color: var(--theme-primary);
}

.el-table {
  border-radius: 18px;
  overflow: hidden;
  color: var(--theme-text);
  background: rgba(255, 255, 255, 0.46);
  backdrop-filter: blur(8px) saturate(1.15);
}

.el-table th.el-table__cell {
  background: rgba(244, 248, 255, 0.76);
  color: #6b746f;
  font-weight: 700;
}

.el-table td.el-table__cell,
.el-table th.el-table__cell {
  border-bottom: 1px solid rgba(255, 255, 255, 0.58);
}

.el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell {
  background: rgba(255, 255, 255, 0.64);
}

.el-table::before {
  height: 0;
}

.el-pagination {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
  padding-top: 12px;
  color: var(--theme-text-secondary);
}

.el-pagination button,
.el-pager li {
  border-radius: 10px;
}

.el-pagination .btn-next,
.el-pagination .btn-prev,
.el-pager li {
  min-width: 32px;
  height: 32px;
  line-height: 32px;
  background: rgba(255, 255, 255, 0.92);
}

.el-pager li.active {
  background: linear-gradient(135deg, var(--theme-primary) 0%, var(--theme-primary-dark) 100%);
  color: #ffffff;
}

.el-dialog {
  border-radius: 20px;
  border: 1px solid var(--glass-border);
  background: var(--glass-bg);
  box-shadow: var(--glass-shadow), var(--glass-inset);
  backdrop-filter: blur(14px) saturate(1.2);
  overflow: hidden;
}

.el-dialog__header {
  padding: 22px 24px 10px;
}

.el-dialog__title {
  color: var(--theme-text);
  font-weight: 700;
}

.el-dialog__body {
  padding: 18px 24px 12px;
}

.el-dialog__footer {
  padding: 12px 24px 24px;
}

.el-form-item__label {
  color: #5f6966;
  font-weight: 600;
}

.el-tag {
  border-radius: 999px;
}

/* Cross-page unification for custom page classes */
.detail-card,
.entity-card,
.box-card1,
.box-card2 {
  border: 1px solid var(--glass-border) !important;
  background: var(--glass-bg) !important;
  box-shadow: var(--glass-shadow), var(--glass-inset) !important;
  backdrop-filter: blur(12px) saturate(1.18) !important;
}

.detail-table__row,
.entity-table__row,
.empty-card {
  background: rgba(255, 255, 255, 0.46) !important;
  border-color: rgba(255, 255, 255, 0.56) !important;
}

.plain-btn,
.mini-btn,
.btn {
  border-color: var(--glass-border) !important;
  background: rgba(255, 255, 255, 0.58) !important;
  box-shadow: var(--glass-inset) !important;
}

.status-pill {
  border: 1px solid rgba(255, 255, 255, 0.68) !important;
  box-shadow: var(--glass-inset) !important;
}
</style>
