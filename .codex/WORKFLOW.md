# Codex Workflow

本工作流用于指导 Codex 在当前仓库中完成“商品管理项目二次开发为前置仓信息管理系统”的全过程。

## Stage 0：仓库接管
负责人：repo-analyst

### 目标
- 识别当前仓库结构
- 判断是单仓库还是前后端分离
- 找出现有商品管理项目的核心模块
- 找出可复用基础设施

### 输出
- 仓库扫描报告
- 可复用模块清单
- 待改造模块清单
- 高风险点
- 推荐第一批改动文件

---

## Stage 1：总体规划
负责人：delivery-orchestrator

### 目标
- 根据需求文档和扫描结果制定二开路线
- 明确阶段边界
- 明确当前阶段目标

### 输出
- 阶段计划
- 功能优先级
- Agent 分工建议
- 当前阶段交付定义

---

## Stage 2：数据库与后端骨架
负责人：db-migration-seeder + backend-implementer

### 目标
- 补充商品字段
- 新增 inventory / orders / order_item / wave_order / operation_log
- 生成基础 CRUD 和查询接口

### 输出
- SQL 变更脚本
- 实体、Mapper、Service、Controller
- 基础分页与详情接口
- 字段对齐清单

---

## Stage 3：核心业务链路
负责人：backend-implementer + admin-frontend-builder + mobile-h5-builder

### 目标
打通核心链路：
创建订单 → 生成波次 → 推送拣货 → 扫码确认 → 库存扣减 → 状态更新 → 日志记录

### 输出
- 订单创建能力
- 波次生成能力
- 拣货路径能力
- 移动端拣货流程
- 日志记录能力

---

## Stage 4：自动化与分析
负责人：backend-implementer + admin-frontend-builder

### 目标
- 库存预警
- 补货建议
- 首页看板
- 导入导出
- 预警提醒展示

### 输出
- 定时任务
- 统计接口
- 看板页面
- 导入导出能力

---

## Stage 5：验收与交付
负责人：qa-reviewer + delivery-orchestrator

### 目标
- 构建通过
- 页面可演示
- 数据可截图
- 业务主链路可跑通

### 输出
- QA 检查报告
- 问题清单
- 修复建议
- 最终交付判断

---

## Global Delivery Rules

1. 每次只推进一个清晰的业务块。
2. 未完成扫描前，不允许直接大改代码。
3. 未完成后端骨架前，不优先做看板和截图优化。
4. 所有改动都要保证最小侵入式。
5. 所有页面都必须服务于最终论文截图和答辩演示。