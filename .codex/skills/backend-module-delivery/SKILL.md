---
name: backend-module-delivery
description: 规范后端模块开发交付，要求每次改动都附带接口清单、数据库影响、自测步骤和业务规则覆盖说明。
---

# Purpose
用于规范后端模块的交付格式，避免只改代码、不说明接口、不说明数据库影响，也不说明怎么验证。

## When To Use
适用于：
1. 商品模块字段扩展
2. 库存模块开发
3. 订单与订单明细模块开发
4. 波次模块开发
5. 日志模块开发
6. 预警、补货、看板等后端能力开发
7. 任意 controller / service / mapper 变更后

## Delivery Requirements
每次后端模块交付都必须附带以下内容：

### 1. Task Goal
说明本次实现的后端目标。

### 2. Files To Modify
列出本次新增或修改的文件：
- entity
- mapper
- xml
- service
- serviceImpl
- controller
- dto / vo / query
- scheduler
- utils / enums

### 3. Database Impact
说明：
- 新增表
- 补充字段
- 新增索引
- 唯一约束
- 状态枚举变化
- 兼容性风险

### 4. API List
列出新增或变更接口：
- 请求方式
- 路径
- 主要入参
- 主要出参
- 权限要求

### 5. Business Rules Covered
明确本次实现覆盖了哪些业务规则。

### 6. Self Test
说明如何测试：
- 调用顺序
- 样例数据
- 预期结果
- 边界情况

### 7. Risks
说明当前仍依赖什么、缺什么、可能阻塞前端的点是什么。

## Recommended Quality Bar
一份合格的后端交付必须满足：
1. 代码结构与现有项目一致
2. 接口命名与风格统一
3. 业务规则清晰可追踪
4. 前端可以直接对接
5. 测试方式可执行

## Do Not
- 不要只给代码，不给接口说明
- 不要只给接口，不说数据库变化
- 不要不写测试路径
- 不要擅自引入复杂中间件
- 不要跳过权限说明和状态流转说明