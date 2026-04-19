---
name: sql-init-data-pack
description: 规范建表和初始化数据输出，确保数据库结构、演示数据、预警场景和截图场景完整可用。
---

# Purpose
用于规范数据库建表和初始化数据交付，保证系统不仅“有表”，而且“有足够可演示的数据”。

## When To Use
适用于：
1. 新增业务表
2. 扩展已有商品表
3. 生成初始化 SQL
4. 生成演示数据 SQL
5. 为截图和答辩补充测试数据

## Schema Requirements
必须覆盖以下核心表：
- sys_user
- commodity
- inventory
- orders
- order_item
- wave_order
- operation_log

如已有同类表，优先补字段或兼容接入。

## Seed Data Requirements
初始化数据必须至少满足：

### 1. User
- admin 账号
- operator 账号

### 2. Commodity
- 不少于 5 条
- 覆盖生鲜、饮料、日用品
- 条码、库位、安全库存等字段完整

### 3. Inventory
- 与商品对应
- 部分低库存
- 部分 3 天内临期
- 状态可体现正常/预警/临期

### 4. Orders
- 不少于 10 条
- 覆盖待处理、拣货中、待配送、已完成、已取消或缺货场景

### 5. Wave Orders
- 不少于 5 条
- 能支撑波次列表与详情展示

### 6. Operation Logs
- 不少于 10 条
- 覆盖登录、入库、订单修改、拣货等类型

## Screenshot Support Rules
初始化数据必须能支撑：
- 登录页演示
- 商品管理列表
- 库存查询
- 预警列表
- 补货建议
- 订单管理
- 波次管理
- 波次详情
- 移动端拣货
- 日志页面
- 用户页面
- 首页看板

## Delivery Requirements
每次 SQL 交付都必须附带：

### 1. Task Goal
本次 SQL 目标。

### 2. Schema Changes
涉及哪些表、字段、索引、约束变化。

### 3. Seed Data Plan
本次准备了哪些业务场景数据。

### 4. Screenshot Mapping
这些数据分别支撑哪些页面或截图。

### 5. Validation
如何验证：
- 导入顺序
- 关联关系
- 数据量
- 状态覆盖
- 预警覆盖

### 6. Risks
说明与现有数据库可能的冲突点。

## Do Not
- 不要只给空表
- 不要只给极少数据
- 不要忽略状态覆盖
- 不要忽略预警和缺货场景
- 不要生成与页面展示不匹配的数据