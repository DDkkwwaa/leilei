---
name: frontend-page-delivery
description: 规范后台前端页面交付，要求说明页面入口、菜单路由、接口绑定、字段展示和自测路径。
---

# Purpose

用于规范后台前端页面交付，确保每次页面开发都能说清楚“页面在哪、谁能看、调了什么接口、怎么验证”。

## Important Baseline

1. 当前前端技术栈是 `Vue2 + Element UI`。
2. 当前仓库已有后台管理页面与动态菜单基础。
3. 某些前置仓页面如订单、波次、日志、补货建议等属于目标态，不能默认当前已经存在。

## When To Use

适用于：

1. 商品管理页面改造
2. 库存查询与预警页面开发
3. 补货建议页面开发
4. 订单管理页面开发
5. 波次管理与详情页面开发
6. 用户管理、日志、看板开发
7. 菜单和路由改动

## Delivery Requirements

每次页面交付都必须附带以下内容：

### 1. Task Goal

本次页面目标是什么。

### 2. Files To Modify

列出本次新增或修改的：

- views
- components
- router
- store
- api
- constants / enums
- permission related files

### 3. Route and Menu Impact

说明：

- 页面路由
- 菜单入口
- 角色可见性
- 权限点

### 4. API Binding

说明页面调用哪些接口：

- 列表接口
- 详情接口
- 保存接口
- 删除接口
- 导入导出接口

### 5. UI Coverage

说明页面覆盖了哪些内容：

- 查询条件
- 表格列
- 状态标签
- 按钮
- 弹窗
- 详情区
- 高亮预警
- 图表展示

### 6. Self Test Path

说明如何验证：

- 从哪个菜单进入
- 用什么账号
- 操作哪些步骤
- 预期看到什么结果

### 7. Risks

说明：

- 依赖哪些后端字段
- 哪些接口还未就绪
- 哪些字典或枚举还需统一

## Screenshot-Oriented Rules

页面必须考虑：

1. 字段清楚
2. 布局清晰
3. 状态颜色可区分
4. 适合论文截图
5. 标题、筛选区、表格区层次清楚

## Do Not

- 不要只交页面代码，不说明入口
- 不要只说“已完成”，不说明字段和接口
- 不要做与现有后台风格明显冲突的页面
- 不要忽略 admin 和 operator 的菜单差异
