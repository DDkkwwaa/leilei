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
  --theme-primary: #1d7c80;
  --theme-primary-dark: #18696d;
  --theme-accent: #8fd6c8;
  --theme-bg: #f5f1e8;
  --theme-surface: #ffffff;
  --theme-surface-soft: #fbf8f2;
  --theme-border: #e9e0d1;
  --theme-border-strong: #ddd3c4;
  --theme-text: #22313f;
  --theme-text-secondary: #7f847d;
  --theme-shadow: 0 14px 30px rgba(80, 62, 34, 0.08);
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
  background: linear-gradient(180deg, #f7f3ec 0%, #f4efe6 100%);
  color: var(--theme-text);
}

* {
  box-sizing: border-box;
}

.page-card,
.box-card.el-card {
  border: 1px solid var(--theme-border);
  border-radius: 18px;
  background: var(--theme-surface);
  box-shadow: var(--theme-shadow);
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
  border-color: var(--theme-border);
  border-radius: 10px;
  background: #fffdfa;
  color: var(--theme-text);
  transition: all 0.2s ease;
}

.el-input__inner {
  height: 40px;
}

.el-input__inner:focus,
.el-textarea__inner:focus {
  border-color: var(--theme-primary);
  background: #ffffff;
  box-shadow: 0 0 0 4px rgba(29, 124, 128, 0.08);
}

.el-button {
  border-radius: 999px;
  font-weight: 600;
}

.el-button--primary {
  border-color: transparent;
  background: linear-gradient(135deg, var(--theme-primary) 0%, var(--theme-primary-dark) 100%);
  box-shadow: 0 12px 22px rgba(29, 124, 128, 0.18);
}

.el-button--primary:hover,
.el-button--primary:focus {
  border-color: transparent;
  background: linear-gradient(135deg, #248b90 0%, #1a7174 100%);
}

.el-button--info,
.el-button--default {
  border-color: var(--theme-border);
  background: #ffffff;
  color: #56606e;
}

.el-button--info:hover,
.el-button--default:hover,
.el-button--info:focus,
.el-button--default:focus {
  border-color: rgba(29, 124, 128, 0.2);
  background: #faf7f1;
  color: var(--theme-primary);
}

.el-table {
  border-radius: 18px;
  overflow: hidden;
  color: var(--theme-text);
}

.el-table th.el-table__cell {
  background: #fbf8f2;
  color: #6b746f;
  font-weight: 700;
}

.el-table td.el-table__cell,
.el-table th.el-table__cell {
  border-bottom: 1px solid #efe7da;
}

.el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell {
  background: #fcfaf5;
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
</style>
