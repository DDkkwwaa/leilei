/*
 Navicat Premium Data Transfer

 Source Server         : MySQL888
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : db_purchase

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 22/04/2026 06:20:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bjmd_note
-- ----------------------------
DROP TABLE IF EXISTS `bjmd_note`;
CREATE TABLE `bjmd_note`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '笔记内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updata_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bjmd_note
-- ----------------------------
INSERT INTO `bjmd_note` VALUES (1, 'admin', '测试', '<p>111222</p>', '2023-04-14 17:34:43', '2023-04-19 21:46:07', NULL);
INSERT INTO `bjmd_note` VALUES (2, 'admin', '制造业', '<p>我是第二个111</p>', '2012-03-03 07:10:29', '2023-04-14 17:34:08', NULL);
INSERT INTO `bjmd_note` VALUES (5, 'sale', '咨询业', 'With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. Navicat 15 has added support for the system-wide dark mode. I will greet this day with love in my heart. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Sometimes you win, sometimes you learn. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Champions keep playing until they get it right. The Synchronize to Database function will give you a full picture of all database differences. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane. If the plan doesn’t work, change the plan, but never the goal. Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another. The past has no power over the present moment. Success consists of going from failure to failure without loss of enthusiasm. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Optimism is the one quality more associated with success and happiness than any other. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Sometimes you win, sometimes you learn. Navicat 15 has added support for the system-wide dark mode. Difficult circumstances serve as a textbook of life for people. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window. The past has no power over the present moment. Anyone who has ever made anything of importance was disciplined. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. You will succeed because most people are lazy. The On Startup feature allows you to control what tabs appear when you launch Navicat. If you wait, all that happens is you get older. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. Flexible settings enable you to set up a custom key for comparison and synchronization. If the plan doesn’t work, change the plan, but never the goal. A query is used to extract data from the database in a readable format according to the user\'s request. If you wait, all that happens is you get older. A man is not old until regrets take the place of dreams. Champions keep playing until they get it right. In the middle of winter I at last discovered that there was in me an invincible summer. If it scares you, it might be a good thing to try. Flexible settings enable you to set up a custom key for comparison and synchronization. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. Flexible settings enable you to set up a custom key for comparison and synchronization. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. A query is used to extract data from the database in a readable format according to the user\'s request. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. I may not have gone where I intended to go, but I think I have ended up where I needed to be. With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. The Synchronize to Database function will give you a full picture of all database differences.', '2003-11-22 02:53:22', '2023-04-14 16:54:16', NULL);
INSERT INTO `bjmd_note` VALUES (31, 'admin', 'tes', '<p>我是测试系统</p>', '2023-04-30 21:38:10', '2023-05-12 14:48:49', NULL);

-- ----------------------------
-- Table structure for ckmd_depository
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository`;
CREATE TABLE `ckmd_depository`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库编号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `head` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库负责人',
  `store_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库地址',
  `stock_total` bigint NULL DEFAULT NULL COMMENT '库存总容量',
  `surplus` bigint NULL DEFAULT NULL COMMENT '剩余容量',
  `area` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '面积单位',
  `status` int NULL DEFAULT NULL COMMENT '0 正常 1 停用',
  `build_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository
-- ----------------------------
INSERT INTO `ckmd_depository` VALUES (1, '12sa', '虚拟仓库A', 'admin', '16423526791', '北京市/市辖区/东城区/', 2000, 2000, '立方米', 0, '2023-02-05 17:28:44', '');
INSERT INTO `ckmd_depository` VALUES (2, NULL, '虚拟仓库B', '老王', '15684457223', '天津市/市辖区/和平区/', 2000, 2000, '立方米', 0, '2023-03-22 22:44:35', '');
INSERT INTO `ckmd_depository` VALUES (4, NULL, '武汉仓1', '老孙', '1568546244', '湖北省/武汉市/市辖区/', 200, 200, '立方米', 0, '2023-04-28 20:25:43', '');
INSERT INTO `ckmd_depository` VALUES (5, NULL, '擎天', '老孙', '15685545221', '贵州省/黔东南苗族侗族自治州/天柱县/', 200, NULL, '', 0, '2023-05-12 10:53:14', '');

-- ----------------------------
-- Table structure for ckmd_depository_in
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository_in`;
CREATE TABLE `ckmd_depository_in`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `source_number` int NULL DEFAULT NULL COMMENT '来源 可有也可无',
  `in_id` bigint NOT NULL COMMENT '入库编号',
  `depository` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '入库仓库名',
  `shop_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '入库商名',
  `shop_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '入库商品单价',
  `shop_number` bigint NULL DEFAULT NULL COMMENT '入库商品数量',
  `price_total` decimal(10, 2) NULL DEFAULT NULL COMMENT '入库商品总价',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位规格 [个斤盒]',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '入库日期',
  `in_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '入库人',
  `shop_supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商名',
  `is_inspection` int NULL DEFAULT 1 COMMENT '0已质检 1 未质检',
  `status` int NULL DEFAULT 1 COMMENT '0 已入库 1 未入库',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository_in
-- ----------------------------
INSERT INTO `ckmd_depository_in` VALUES (1, NULL, 122322587, '北京海淀仓', '苹果', 7.00, 50, 325.00, '斤', '2023-05-12 11:37:46', 'admin', '北极星', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (2, NULL, 123456789, '北京', '雪碧', 11.00, 122, 1342.00, '件', '2023-03-23 21:22:20', 'admin', NULL, 1, 1, NULL);
INSERT INTO `ckmd_depository_in` VALUES (3, NULL, 170498910, '北京', '阿莫西林胶囊', 8.20, 20, NULL, '个', '2023-05-12 11:37:12', 'admin', NULL, 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (4, NULL, 811498807, '北京海淀仓', '华硕笔记本', 10000.00, 50, 120.00, '件', '2023-04-23 20:12:27', 'admin', '华硕厂商', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (5, NULL, 504912341, '武汉仓1', '鱼', 15.60, 230, 3588.00, '斤', NULL, NULL, NULL, 1, 1, '2023-05-13 16:13:50');
INSERT INTO `ckmd_depository_in` VALUES (10, NULL, 718006985, '虚拟仓库B', '华硕笔记本', 8865.00, 120, 120.00, '个', '2023-05-15 23:35:00', 'admin', '璐有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (11, NULL, 143649068, '虚拟仓库A', '苹果', 6.50, 300, 120.00, '斤', '2023-05-18 18:12:36', 'admin', '胡記发展贸易有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (12, NULL, 718006981, '虚拟仓库A', '鱼', 15.60, 230, 120.00, '斤', '2023-06-01 21:55:02', 'admin', '贾有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (13, NULL, 210663607, '虚拟仓库B', '苹果', 21.00, 50, 120.00, '件', '2023-06-03 14:29:22', 'admin', '嘉伦工程有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` VALUES (14, NULL, 128980332, '虚拟仓库A', '鱼', 100.00, 3, 300.00, '件', '2026-04-22 00:47:58', 'admin', NULL, 0, 0, '2026-04-22 00:47:47');

-- ----------------------------
-- Table structure for ckmd_depository_out
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository_out`;
CREATE TABLE `ckmd_depository_out`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '出库id',
  `source_number` bigint NULL DEFAULT NULL COMMENT '来源 id',
  `out_id` bigint NULL DEFAULT NULL COMMENT '出库编号',
  `depository` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出库仓库',
  `shop_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shop_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '出库价格',
  `shop_number` bigint NULL DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(60, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '规格',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '出库时间',
  `out_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出库用户',
  `shop_supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `status` int NULL DEFAULT NULL COMMENT '是否出库 0 出库 1不出库',
  `out_inspection` int NULL DEFAULT 1 COMMENT '0 审核 1表示未审核',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository_out
-- ----------------------------
INSERT INTO `ckmd_depository_out` VALUES (1, NULL, 123323, '虚拟仓库A', '苹果', 8.54, 30, 256.20, '个', '2023-04-27 20:19:55', '老王', '华宇', 0, 0, '2023-04-27 20:16:36');
INSERT INTO `ckmd_depository_out` VALUES (2, NULL, 123, '虚拟仓库B', '雪碧', 200.00, 30, 6000.00, '箱', '2023-04-27 20:14:09', NULL, NULL, 1, 0, NULL);
INSERT INTO `ckmd_depository_out` VALUES (3, 123456, 161997858, '虚拟仓库A', '苹果', 1.00, 5, 5.00, '箱', NULL, 'admin', '老杨', 1, 1, '2023-06-03 11:07:25');
INSERT INTO `ckmd_depository_out` VALUES (4, 900000002, 800000017, '虚拟仓库A', '雪碧', 11.35, 7, 79.45, '斤', '2026-04-22 03:12:46', 'admin', 'Customer-002', 0, 0, '2026-04-21 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (5, 900000004, 800000021, '虚拟仓库A', '雪碧', 13.35, 9, 120.15, '斤', '2026-04-21 11:17:29', 'admin', 'Customer-004', 0, 0, '2026-04-21 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (6, 900000006, 800000025, '虚拟仓库A', '雪碧', 15.35, 11, 168.85, '斤', '2026-04-22 03:26:42', 'manager', 'Customer-006', 0, 0, '2026-04-21 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (7, 900000008, 800000029, '虚拟仓库A', '雪碧', 17.35, 13, 225.55, '斤', '2026-04-20 23:17:29', 'admin', 'Customer-008', 0, 0, '2026-04-21 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (8, 900000010, 800000033, '虚拟仓库A', '雪碧', 19.35, 15, 290.25, '斤', '2026-04-22 03:32:37', 'admin', 'Customer-010', 0, 0, '2026-04-21 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (9, 900000012, 800000037, '虚拟仓库A', '雪碧', 9.35, 17, 158.95, '斤', '2026-04-20 11:17:29', 'manager', 'Customer-012', 0, 0, '2026-04-21 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (10, 900000014, 800000041, '虚拟仓库A', '雪碧', 11.35, 19, 215.65, '斤', '2026-04-22 03:27:48', 'admin', 'Customer-014', 0, 0, '2026-04-21 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (11, 900000016, 800000045, '虚拟仓库A', '雪碧', 13.35, 21, 280.35, '斤', '2026-04-19 23:17:29', 'admin', 'Customer-016', 0, 0, '2026-04-21 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (13, 900000020, 800000053, '虚拟仓库A', '雪碧', 17.35, 5, 86.75, '斤', '2026-04-19 11:17:29', 'admin', 'Customer-020', 0, 0, '2026-04-21 04:17:29');
INSERT INTO `ckmd_depository_out` VALUES (14, 900000022, 800000057, '虚拟仓库A', '雪碧', 19.35, 7, 135.45, '斤', NULL, 'admin', 'Customer-022', 1, 1, '2026-04-21 02:17:29');
INSERT INTO `ckmd_depository_out` VALUES (15, 900000024, 800000061, '虚拟仓库A', '雪碧', 9.35, 9, 84.15, '斤', '2026-04-18 23:17:29', 'manager', 'Customer-024', 0, 0, '2026-04-21 00:17:29');
INSERT INTO `ckmd_depository_out` VALUES (16, 900000026, 800000065, '虚拟仓库A', '雪碧', 11.35, 11, 124.85, '斤', NULL, 'admin', 'Customer-026', 1, 1, '2026-04-20 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (17, 900000028, 800000069, '虚拟仓库A', '雪碧', 13.35, 13, 173.55, '斤', '2026-04-18 11:17:29', 'admin', 'Customer-028', 0, 0, '2026-04-20 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (18, 900000030, 800000073, '虚拟仓库A', '雪碧', 15.35, 15, 230.25, '斤', NULL, 'manager', 'Customer-030', 1, 1, '2026-04-20 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (19, 900000032, 800000077, '虚拟仓库A', '雪碧', 17.35, 17, 294.95, '斤', '2026-04-17 23:17:29', 'admin', 'Customer-032', 0, 0, '2026-04-20 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (20, 900000034, 800000081, '虚拟仓库A', '雪碧', 19.35, 19, 367.65, '斤', '2026-04-22 04:39:13', 'admin', 'Customer-034', 0, 0, '2026-04-20 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (21, 900000036, 800000085, '虚拟仓库A', '雪碧', 9.35, 21, 196.35, '斤', '2026-04-17 11:17:29', 'manager', 'Customer-036', 0, 0, '2026-04-20 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (22, 900000038, 800000089, '虚拟仓库A', '雪碧', 11.35, 23, 261.05, '斤', NULL, 'admin', 'Customer-038', 1, 1, '2026-04-20 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (23, 900000040, 800000093, '虚拟仓库A', '雪碧', 13.35, 5, 66.75, '斤', '2026-04-16 23:17:29', 'admin', 'Customer-040', 0, 0, '2026-04-20 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (24, 900000002, 800000057, '虚拟仓库A', '华硕笔记本', 12.70, 9, 114.30, '个', '2026-04-22 03:17:41', 'admin', 'Customer-002', 0, 0, '2026-04-21 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (25, 900000004, 800000061, '虚拟仓库A', '华硕笔记本', 14.70, 13, 191.10, '个', '2026-04-21 10:17:29', 'admin', 'Customer-004', 0, 0, '2026-04-21 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (26, 900000006, 800000065, '虚拟仓库A', '华硕笔记本', 16.70, 17, 283.90, '个', '2026-04-22 03:26:42', 'manager', 'Customer-006', 0, 0, '2026-04-21 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (27, 900000008, 800000069, '虚拟仓库A', '华硕笔记本', 18.70, 21, 392.70, '个', '2026-04-20 22:17:29', 'admin', 'Customer-008', 0, 0, '2026-04-21 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (28, 900000010, 800000073, '虚拟仓库A', '华硕笔记本', 8.70, 5, 43.50, '个', '2026-04-22 03:32:37', 'admin', 'Customer-010', 0, 0, '2026-04-21 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (29, 900000012, 800000077, '虚拟仓库A', '华硕笔记本', 10.70, 9, 96.30, '个', '2026-04-20 10:17:29', 'manager', 'Customer-012', 0, 0, '2026-04-21 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (30, 900000014, 800000081, '虚拟仓库A', '华硕笔记本', 12.70, 13, 165.10, '个', '2026-04-22 03:27:48', 'admin', 'Customer-014', 0, 0, '2026-04-21 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (31, 900000016, 800000085, '虚拟仓库A', '华硕笔记本', 14.70, 17, 249.90, '个', '2026-04-19 22:17:29', 'admin', 'Customer-016', 0, 0, '2026-04-21 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (33, 900000020, 800000093, '虚拟仓库A', '华硕笔记本', 18.70, 5, 93.50, '个', '2026-04-19 10:17:29', 'admin', 'Customer-020', 0, 0, '2026-04-21 04:17:29');
INSERT INTO `ckmd_depository_out` VALUES (34, 900000022, 800000097, '虚拟仓库A', '华硕笔记本', 8.70, 9, 78.30, '个', NULL, 'admin', 'Customer-022', 1, 1, '2026-04-21 02:17:29');
INSERT INTO `ckmd_depository_out` VALUES (35, 900000024, 800000101, '虚拟仓库A', '华硕笔记本', 10.70, 13, 139.10, '个', '2026-04-18 22:17:29', 'manager', 'Customer-024', 0, 0, '2026-04-21 00:17:29');
INSERT INTO `ckmd_depository_out` VALUES (36, 900000026, 800000105, '虚拟仓库A', '华硕笔记本', 12.70, 17, 215.90, '个', NULL, 'admin', 'Customer-026', 1, 1, '2026-04-20 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (37, 900000028, 800000109, '虚拟仓库A', '华硕笔记本', 14.70, 21, 308.70, '个', '2026-04-18 10:17:29', 'admin', 'Customer-028', 0, 0, '2026-04-20 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (38, 900000030, 800000113, '虚拟仓库A', '华硕笔记本', 16.70, 5, 83.50, '个', NULL, 'manager', 'Customer-030', 1, 1, '2026-04-20 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (39, 900000032, 800000117, '虚拟仓库A', '华硕笔记本', 18.70, 9, 168.30, '个', '2026-04-17 22:17:29', 'admin', 'Customer-032', 0, 0, '2026-04-20 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (40, 900000034, 800000121, '虚拟仓库A', '华硕笔记本', 8.70, 13, 113.10, '个', '2026-04-22 04:39:13', 'admin', 'Customer-034', 0, 0, '2026-04-20 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (41, 900000036, 800000125, '虚拟仓库A', '华硕笔记本', 10.70, 17, 181.90, '个', '2026-04-17 10:17:29', 'manager', 'Customer-036', 0, 0, '2026-04-20 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (42, 900000038, 800000129, '虚拟仓库A', '华硕笔记本', 12.70, 21, 266.70, '个', NULL, 'admin', 'Customer-038', 1, 1, '2026-04-20 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (43, 900000040, 800000133, '虚拟仓库A', '华硕笔记本', 14.70, 5, 73.50, '个', '2026-04-16 22:17:29', 'admin', 'Customer-040', 0, 0, '2026-04-20 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (44, 900000002, 800000097, '虚拟仓库A', '苹果', 14.05, 11, 154.55, '个', '2026-04-22 03:23:40', 'admin', 'Customer-002', 0, 0, '2026-04-21 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (45, 900000004, 800000101, '虚拟仓库A', '苹果', 16.05, 17, 272.85, '个', '2026-04-21 09:17:29', 'admin', 'Customer-004', 0, 0, '2026-04-21 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (46, 900000006, 800000105, '虚拟仓库A', '苹果', 18.05, 23, 415.15, '个', '2026-04-22 03:26:42', 'manager', 'Customer-006', 0, 0, '2026-04-21 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (47, 900000008, 800000109, '虚拟仓库A', '苹果', 20.05, 9, 180.45, '个', '2026-04-20 21:17:29', 'admin', 'Customer-008', 0, 0, '2026-04-21 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (48, 900000010, 800000113, '虚拟仓库A', '苹果', 10.05, 15, 150.75, '个', '2026-04-22 03:32:37', 'admin', 'Customer-010', 0, 0, '2026-04-21 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (49, 900000012, 800000117, '虚拟仓库A', '苹果', 12.05, 21, 253.05, '个', '2026-04-20 09:17:29', 'manager', 'Customer-012', 0, 0, '2026-04-21 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (50, 900000014, 800000121, '虚拟仓库A', '苹果', 14.05, 7, 98.35, '个', '2026-04-22 03:27:48', 'admin', 'Customer-014', 0, 0, '2026-04-21 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (51, 900000016, 800000125, '虚拟仓库A', '苹果', 16.05, 13, 208.65, '个', '2026-04-19 21:17:29', 'admin', 'Customer-016', 0, 0, '2026-04-21 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (53, 900000020, 800000133, '虚拟仓库A', '苹果', 20.05, 5, 100.25, '个', '2026-04-19 09:17:29', 'admin', 'Customer-020', 0, 0, '2026-04-21 04:17:29');
INSERT INTO `ckmd_depository_out` VALUES (54, 900000022, 800000137, '虚拟仓库A', '苹果', 10.05, 11, 110.55, '个', NULL, 'admin', 'Customer-022', 1, 1, '2026-04-21 02:17:29');
INSERT INTO `ckmd_depository_out` VALUES (55, 900000024, 800000141, '虚拟仓库A', '苹果', 12.05, 17, 204.85, '个', '2026-04-18 21:17:29', 'manager', 'Customer-024', 0, 0, '2026-04-21 00:17:29');
INSERT INTO `ckmd_depository_out` VALUES (56, 900000026, 800000145, '虚拟仓库A', '苹果', 14.05, 23, 323.15, '个', NULL, 'admin', 'Customer-026', 1, 1, '2026-04-20 22:17:29');
INSERT INTO `ckmd_depository_out` VALUES (57, 900000028, 800000149, '虚拟仓库A', '苹果', 16.05, 9, 144.45, '个', '2026-04-18 09:17:29', 'admin', 'Customer-028', 0, 0, '2026-04-20 20:17:29');
INSERT INTO `ckmd_depository_out` VALUES (58, 900000030, 800000153, '虚拟仓库A', '苹果', 18.05, 15, 270.75, '个', NULL, 'manager', 'Customer-030', 1, 1, '2026-04-20 18:17:29');
INSERT INTO `ckmd_depository_out` VALUES (59, 900000032, 800000157, '虚拟仓库A', '苹果', 20.05, 21, 421.05, '个', '2026-04-17 21:17:29', 'admin', 'Customer-032', 0, 0, '2026-04-20 16:17:29');
INSERT INTO `ckmd_depository_out` VALUES (60, 900000034, 800000161, '虚拟仓库A', '苹果', 10.05, 7, 70.35, '个', '2026-04-22 04:39:13', 'admin', 'Customer-034', 0, 0, '2026-04-20 14:17:29');
INSERT INTO `ckmd_depository_out` VALUES (61, 900000036, 800000165, '虚拟仓库A', '苹果', 12.05, 13, 156.65, '个', '2026-04-17 09:17:29', 'manager', 'Customer-036', 0, 0, '2026-04-20 12:17:29');
INSERT INTO `ckmd_depository_out` VALUES (62, 900000038, 800000169, '虚拟仓库A', '苹果', 14.05, 19, 266.95, '个', NULL, 'admin', 'Customer-038', 1, 1, '2026-04-20 10:17:29');
INSERT INTO `ckmd_depository_out` VALUES (63, 900000040, 800000173, '虚拟仓库A', '苹果', 16.05, 5, 80.25, '个', '2026-04-16 21:17:29', 'admin', 'Customer-040', 0, 0, '2026-04-20 08:17:29');
INSERT INTO `ckmd_depository_out` VALUES (67, 900000001, 160141881, '虚拟仓库A', '雪碧', 10.35, 6, 62.10, '斤', '2026-04-22 02:54:36', '', 'Customer-001', 0, 0, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (68, 900000003, 194877413, '虚拟仓库A', '雪碧', 12.35, 8, 98.80, '斤', '2026-04-22 02:57:07', '', 'Customer-003', 0, 0, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (69, 900000005, 191342888, '虚拟仓库A', '雪碧', 14.35, 10, 143.50, '斤', NULL, '', 'Customer-005', 1, 1, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (70, 900000007, 488257215, '虚拟仓库A', '雪碧', 16.35, 12, 196.20, '斤', '2026-04-22 03:29:38', '', 'Customer-007', 0, 0, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (71, 900000009, 273425734, '虚拟仓库A', '雪碧', 18.35, 14, 256.90, '斤', '2026-04-22 03:32:14', '', 'Customer-009', 0, 0, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (72, 900000011, 187627105, '虚拟仓库A', '雪碧', 8.35, 16, 133.60, '斤', '2026-04-22 03:36:29', '', 'Customer-011', 0, 0, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (73, 900000013, 934965707, '虚拟仓库A', '雪碧', 10.35, 18, 186.30, '斤', NULL, '', 'Customer-013', 1, 1, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (74, 900000015, 128565196, '虚拟仓库A', '雪碧', 12.35, 20, 247.00, '斤', NULL, '', 'Customer-015', 1, 1, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (75, 900000017, 121397145, '虚拟仓库A', '雪碧', 14.35, 22, 315.70, '斤', NULL, '', 'Customer-017', 1, 1, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (76, 900000019, 213645420, '虚拟仓库A', '雪碧', 16.35, 24, 392.40, '斤', NULL, '', 'Customer-019', 1, 1, '2026-04-22 00:30:00');
INSERT INTO `ckmd_depository_out` VALUES (78, 900000023, 566232269, '虚拟仓库A', '雪碧', 8.35, 8, 66.80, '斤', NULL, '', 'Customer-023', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (79, 900000025, 903029153, '虚拟仓库A', '雪碧', 10.35, 10, 103.50, '斤', NULL, '', 'Customer-025', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (80, 900000027, 897405228, '虚拟仓库A', '雪碧', 12.35, 12, 148.20, '斤', NULL, '', 'Customer-027', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (81, 900000029, 666295890, '虚拟仓库A', '雪碧', 14.35, 14, 200.90, '斤', '2026-04-22 03:39:14', '', 'Customer-029', 0, 0, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (82, 900000031, 208809936, '虚拟仓库A', '雪碧', 16.35, 16, 261.60, '斤', NULL, '', 'Customer-031', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (83, 900000033, 798978392, '虚拟仓库A', '雪碧', 18.35, 18, 330.30, '斤', NULL, '', 'Customer-033', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (84, 900000035, 186515128, '虚拟仓库A', '雪碧', 8.35, 20, 167.00, '斤', NULL, '', 'Customer-035', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (85, 900000037, 151462076, '虚拟仓库A', '雪碧', 10.35, 22, 227.70, '斤', NULL, '', 'Customer-037', 1, 1, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (86, 900000039, 160320959, '虚拟仓库A', '雪碧', 12.35, 24, 296.40, '斤', '2026-04-22 03:24:25', '', 'Customer-039', 0, 0, '2026-04-22 00:30:01');
INSERT INTO `ckmd_depository_out` VALUES (87, 900000018, 155782490, '虚拟仓库A', '雪碧', 15.35, 23, 353.05, '斤', '2026-04-22 04:29:09', 'admin', 'Customer-018', 0, 0, '2026-04-22 04:02:51');
INSERT INTO `ckmd_depository_out` VALUES (88, 900000021, 411362668, '虚拟仓库A', '雪碧', 18.35, 6, 110.10, '斤', '2026-04-22 04:16:43', 'admin', 'Customer-021', 0, 0, '2026-04-22 04:16:26');
INSERT INTO `ckmd_depository_out` VALUES (89, 900000021, 203509438, '虚拟仓库A', '华硕笔记本', 19.70, 7, 137.90, '个', '2026-04-22 04:16:43', 'admin', 'Customer-021', 0, 0, '2026-04-22 04:16:26');
INSERT INTO `ckmd_depository_out` VALUES (90, 900000021, 169680734, '虚拟仓库A', '苹果', 9.05, 8, 72.40, '个', '2026-04-22 04:16:43', 'admin', 'Customer-021', 0, 0, '2026-04-22 04:16:26');

-- ----------------------------
-- Table structure for ckmd_stock
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_stock`;
CREATE TABLE `ckmd_stock`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '序号 商品库存id',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  `quantity` bigint NULL DEFAULT NULL COMMENT '库存量',
  `speces` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '规格 斤 、千克、个',
  `depository` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '仓库名',
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `safety_stock` bigint NULL DEFAULT 10 COMMENT 'safe stock',
  `expire_date` date NULL DEFAULT NULL COMMENT 'expire date',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '库存清单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ckmd_stock
-- ----------------------------
INSERT INTO `ckmd_stock` VALUES (4, '雪碧', '饮品类', 22, '斤', '虚拟仓库A', 'C-02-02', '2023-03-23 21:23:33', '2026-04-22 00:17:29', '无', 125, NULL);
INSERT INTO `ckmd_stock` VALUES (5, '华硕笔记本', '电器类', 0, '个', '虚拟仓库A', 'A-01-01', '2023-05-07 17:36:31', '2026-04-22 00:17:29', '', 30, NULL);
INSERT INTO `ckmd_stock` VALUES (6, '苹果', '水果类', 47, '个', '虚拟仓库A', 'A-02-03', '2023-05-07 17:37:30', '2026-04-22 00:17:29', '', 59, NULL);
INSERT INTO `ckmd_stock` VALUES (7, '鱼', '水产类', 50, '千克', '虚拟仓库A', 'B-01-02', '2023-05-07 17:45:02', '2026-04-22 00:17:29', '', 75, NULL);
INSERT INTO `ckmd_stock` VALUES (8, '香蕉', '水果类', 23, '斤', '擎天', 'B-03-01', '2023-05-12 16:47:59', '2026-04-22 00:17:29', '', 33, NULL);
INSERT INTO `ckmd_stock` VALUES (9, '演示商品-纯牛奶', '乳制品类', 180, '箱', '虚拟仓库A', 'A-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 60, '2026-07-21');
INSERT INTO `ckmd_stock` VALUES (10, '演示商品-酸奶', '乳制品类', 220, '件', '虚拟仓库B', 'A-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 80, '2026-06-06');
INSERT INTO `ckmd_stock` VALUES (11, '演示商品-奶酪棒', '乳制品类', 160, '包', '武汉仓1', 'A-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 50, '2026-08-20');
INSERT INTO `ckmd_stock` VALUES (12, '演示商品-速冻水饺', '速冻食品类', 140, '袋', '虚拟仓库A', 'B-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 40, '2026-10-19');
INSERT INTO `ckmd_stock` VALUES (13, '演示商品-速冻汤圆', '速冻食品类', 130, '袋', '虚拟仓库B', 'B-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 35, '2026-10-19');
INSERT INTO `ckmd_stock` VALUES (14, '演示商品-鸡米花', '速冻食品类', 120, '袋', '武汉仓1', 'B-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 30, '2026-10-19');
INSERT INTO `ckmd_stock` VALUES (15, '演示商品-大米5kg', '粮油调味类', 90, '袋', '虚拟仓库A', 'C-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 25, '2027-04-22');
INSERT INTO `ckmd_stock` VALUES (16, '演示商品-菜籽油', '粮油调味类', 110, '桶', '虚拟仓库B', 'C-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 30, '2027-04-22');
INSERT INTO `ckmd_stock` VALUES (17, '演示商品-生抽', '粮油调味类', 260, '瓶', '武汉仓1', 'C-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 70, '2027-04-22');
INSERT INTO `ckmd_stock` VALUES (18, '演示商品-洗衣液', '日化清洁类', 140, '瓶', '虚拟仓库A', 'D-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 35, NULL);
INSERT INTO `ckmd_stock` VALUES (19, '演示商品-抽纸', '日化清洁类', 260, '提', '虚拟仓库B', 'D-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 90, NULL);
INSERT INTO `ckmd_stock` VALUES (20, '演示商品-洗洁精', '日化清洁类', 180, '瓶', '武汉仓1', 'D-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 45, NULL);
INSERT INTO `ckmd_stock` VALUES (21, '演示商品-薯片', '休闲零食类', 320, '包', '擎天', 'E-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 100, '2026-12-18');
INSERT INTO `ckmd_stock` VALUES (22, '演示商品-坚果礼盒', '休闲零食类', 100, '盒', '虚拟仓库A', 'E-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 20, '2026-12-18');
INSERT INTO `ckmd_stock` VALUES (23, '演示商品-苏打饼干', '休闲零食类', 300, '包', '虚拟仓库B', 'E-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 80, '2026-12-18');
INSERT INTO `ckmd_stock` VALUES (24, '演示商品-矿泉水550ml', '饮用水类', 800, '瓶', '武汉仓1', 'F-01-01', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 220, '2027-04-22');
INSERT INTO `ckmd_stock` VALUES (25, '演示商品-纯净水5L', '饮用水类', 260, '桶', '擎天', 'F-01-02', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 80, '2027-04-22');
INSERT INTO `ckmd_stock` VALUES (26, '演示商品-苏打水', '饮用水类', 360, '瓶', '虚拟仓库A', 'F-01-03', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示库存', 120, '2027-04-22');

-- ----------------------------
-- Table structure for jcmd_customer
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_customer`;
CREATE TABLE `jcmd_customer`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '客户姓名',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户电话',
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户地址',
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户邮箱',
  `fax` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '传真',
  `branch` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '银行名',
  `branch_no` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '卡号',
  `build_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jcmd_customer
-- ----------------------------
INSERT INTO `jcmd_customer` VALUES (1, '张三', '15685565446', '北京海淀区', '1070348698@qq.com', '556688', '中信银行', '25468446x458455', '2023-01-19 19:30:02', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (3, '孙安琪', '2015024130', '天河区天河路278号', 'ansun218@mail.com', '17165678577', 'Visa', '3572647513660753', '2019-05-24 10:50:44', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (4, '邱安琪', '16879419430', '罗湖区蔡屋围深南东路541号', 'anqiqiu105@outlook.com', '18035438189', 'UnionPay', '4817496935500194', '2004-11-08 01:23:28', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (5, '史晓明', '101724612', '西城区复兴门内大街741号', 'xiaomingshi@gmail.com', '1043573841', 'MasterCard', NULL, '2021-07-13 14:11:56', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (6, '任安琪', '1016481874', '东城区东单王府井东街902号', 'anqiren@gmail.com', '16385561570', 'American Express', '6218706318336242', '2018-06-22 10:05:13', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (7, '方安琪', '2038004755', '天河区天河路90号', 'fananqi@outlook.com', '15988881133', 'JCB', '4477357252652334', '2020-06-26 07:40:04', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (8, '田杰宏', '14314567223', '西城区复兴门内大街665号', 'jiehongtian@icloud.com', '1084121515', 'Visa', '379751807668208', '2020-07-21 23:57:19', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (9, '毛子韬', '2054161989', '白云区小坪东路207号', 'maozitao2019@icloud.com', '16425193461', 'American Express', '377830824899848', '2017-01-24 06:00:38', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (10, '武子韬', '106434415', '东城区东单王府井东街486号', 'wuz@gmail.com', NULL, 'MasterCard', '377417760293101', '2016-11-01 05:16:19', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (11, '邓震南', '76920499870', '东泰五街582号', 'dengz@hotmail.com', '18851937724', 'JCB', '346908265108123', '2015-04-29 12:05:04', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (12, '于子异', '14744589036', '朝阳区三里屯路512号', 'yuziyi4@icloud.com', '1096684286', 'American Express', '4633899537086193', '2013-11-29 11:10:05', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (13, '王子韬', '18677999969', '罗湖区清水河一路546号', NULL, '7555958001', 'MasterCard', '3586381212480214', '2022-02-01 05:12:12', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (14, '朱璐', '19148019897', '浦东新区健祥路202号', 'lzhu@yahoo.com', '216802380', 'Visa', '6215275615420946', '2017-03-16 23:12:02', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (15, '何杰宏', '2073404104', '白云区小坪东路963号', 'jiehonghe@outlook.com', '205749027', 'Visa', '3579930526023026', '2007-11-12 18:23:40', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (16, '梁云熙', '18692261005', '白云区机场路棠苑街五巷68号', 'lyunxi@mail.com', '2087426432', 'JCB', '6270926496612306', '2000-06-22 04:27:06', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (17, '傅晓明', '1054003710', '東城区東直門內大街16号', 'fuxiaoming8@hotmail.com', '1046483288', 'American Express', '3547343902699741', '2003-01-03 11:55:39', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (18, '贺璐', '7552744825', '罗湖区清水河一路705号', 'helu708@hotmail.com', '75562904320', 'American Express', '371508392403302', '2007-07-19 14:21:16', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (19, '姜云熙', '109648448', '西城区复兴门内大街903号', 'jiangyu@gmail.com', '16623826253', 'American Express', '375263940537526', '2003-03-31 21:33:38', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (20, '黄璐', '7698507685', '珊瑚路755号', 'lu92@outlook.com', '14091539883', 'MasterCard', '373073332962121', '2004-09-24 17:20:05', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (21, '曹晓明', '14724978125', '浦东新区健祥路284号', 'caoxiaom10@gmail.com', '2117678293', 'JCB', '5487681515258745', '2014-07-31 01:38:51', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (22, '吴睿', '18290154381', '东泰五街574号', 'rui1967@outlook.com', '19000340143', NULL, '374535202351105', '2006-11-27 18:27:46', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (23, '任子韬', '14562899476', '黄浦区淮海中路392号', 'zitaoren@mail.com', '211747654', 'American Express', NULL, '2013-08-30 22:32:10', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (24, '常宇宁', '18397703030', '坑美十五巷819号', 'yuningchang@hotmail.com', '19136140958', 'MasterCard', NULL, '2019-06-13 12:57:11', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (25, '邵致远', '19907753960', '天河区大信商圈大信南路465号', 'zhishao1018@outlook.com', '18097268286', 'Visa', '3548530169911210', '2015-04-17 20:28:54', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (26, '罗震南', '13199810691', '罗湖区田贝一路587号', 'zluo@yahoo.com', '19138187726', 'MasterCard', '5393652953393321', '2007-11-16 13:50:44', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (27, '杨詩涵', '15104719089', '紫马岭商圈中山五路706号', 'shiyang1005@icloud.com', NULL, 'JCB', '5239639966225812', '2005-11-07 18:06:51', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (28, '曾云熙', '1097872249', '海淀区清河中街68号431号', 'zeyunxi218@outlook.com', '104332437', 'JCB', '373120506532618', '2013-01-11 15:36:41', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (29, '毛睿', '76086143282', '紫马岭商圈中山五路323号', 'ruimao01@outlook.com', '7604866570', 'UnionPay', '377559218783132', '2012-08-31 06:03:08', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (30, '顾晓明', '76003838859', '京华商圈华夏街437号', 'xiaomingg@hotmail.com', '16529166037', 'MasterCard', '5272890019286354', '2014-06-06 08:58:25', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (31, '罗晓明', '7698730679', '东泰五街962号', 'xiaoming331@hotmail.com', '17212567543', 'Visa', '6276765968485518', '2022-07-11 23:25:54', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (32, '程安琪', '2142032862', '浦东新区健祥路305号', 'chanq@yahoo.com', '218795510', 'Visa', '4361135947126156', '2009-10-12 11:52:30', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (34, '罗晓明', '7698730679', '东泰五街962号', 'xiaoming331@hotmail.com', '17212567543', 'Visa', '6276765968485518', '2023-03-19 14:08:16', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (35, '测试', '15685565446', '北京海淀区', '1070348698@qq.com', '556688', '中信银行', '25468446x458455', '2026-04-12 16:48:00', NULL, NULL);
INSERT INTO `jcmd_customer` VALUES (36, '1', '1', '', '', '', '', '', '2026-04-12 17:01:20', NULL, '');
INSERT INTO `jcmd_customer` VALUES (37, '测试', '1', '', '1', '', '', '', '2026-04-12 17:23:26', NULL, '');
INSERT INTO `jcmd_customer` VALUES (38, '演示客户-华中001', '13970010001', '武汉市洪山区光谷一路1号', 'demo001@demo.com', '027-86000001', '中国银行', '621700010001', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (39, '演示客户-华中002', '13970010002', '武汉市洪山区光谷一路2号', 'demo002@demo.com', '027-86000002', '建设银行', '621700010002', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (40, '演示客户-华中003', '13970010003', '武汉市洪山区光谷一路3号', 'demo003@demo.com', '027-86000003', '工商银行', '621700010003', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (41, '演示客户-华中004', '13970010004', '武汉市洪山区光谷一路4号', 'demo004@demo.com', '027-86000004', '农业银行', '621700010004', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (42, '演示客户-华中005', '13970010005', '武汉市洪山区光谷一路5号', 'demo005@demo.com', '027-86000005', '中国银行', '621700010005', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (43, '演示客户-华中006', '13970010006', '武汉市洪山区光谷一路6号', 'demo006@demo.com', '027-86000006', '建设银行', '621700010006', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (44, '演示客户-华中007', '13970010007', '武汉市洪山区光谷一路7号', 'demo007@demo.com', '027-86000007', '工商银行', '621700010007', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (45, '演示客户-华中008', '13970010008', '武汉市洪山区光谷一路8号', 'demo008@demo.com', '027-86000008', '农业银行', '621700010008', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (46, '演示客户-华中009', '13970010009', '武汉市洪山区光谷一路9号', 'demo009@demo.com', '027-86000009', '中国银行', '621700010009', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (47, '演示客户-华中010', '13970010010', '武汉市洪山区光谷一路10号', 'demo010@demo.com', '027-86000010', '建设银行', '621700010010', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (48, '演示客户-华中011', '13970010011', '武汉市洪山区光谷一路11号', 'demo011@demo.com', '027-86000011', '工商银行', '621700010011', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (49, '演示客户-华中012', '13970010012', '武汉市洪山区光谷一路12号', 'demo012@demo.com', '027-86000012', '农业银行', '621700010012', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (50, '演示客户-华中013', '13970010013', '武汉市洪山区光谷一路13号', 'demo013@demo.com', '027-86000013', '中国银行', '621700010013', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (51, '演示客户-华中014', '13970010014', '武汉市洪山区光谷一路14号', 'demo014@demo.com', '027-86000014', '建设银行', '621700010014', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (52, '演示客户-华中015', '13970010015', '武汉市洪山区光谷一路15号', 'demo015@demo.com', '027-86000015', '工商银行', '621700010015', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (53, '演示客户-华中016', '13970010016', '武汉市洪山区光谷一路16号', 'demo016@demo.com', '027-86000016', '农业银行', '621700010016', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (54, '演示客户-华中017', '13970010017', '武汉市洪山区光谷一路17号', 'demo017@demo.com', '027-86000017', '中国银行', '621700010017', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (55, '演示客户-华中018', '13970010018', '武汉市洪山区光谷一路18号', 'demo018@demo.com', '027-86000018', '建设银行', '621700010018', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (56, '演示客户-华中019', '13970010019', '武汉市洪山区光谷一路19号', 'demo019@demo.com', '027-86000019', '工商银行', '621700010019', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_customer` VALUES (57, '演示客户-华中020', '13970010020', '武汉市洪山区光谷一路20号', 'demo020@demo.com', '027-86000020', '农业银行', '621700010020', '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');

-- ----------------------------
-- Table structure for jcmd_shop
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_shop`;
CREATE TABLE `jcmd_shop`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '商品科类',
  `shop_number` bigint NULL DEFAULT NULL COMMENT '商品数量',
  `specs` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '规格',
  `market_price` decimal(50, 2) NULL DEFAULT NULL COMMENT '市场价格',
  `build_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jcmd_shop
-- ----------------------------
INSERT INTO `jcmd_shop` VALUES (1, '苹果1', 1, 123, '斤', 13.50, '2023-02-05 16:54:11', '2024-05-01 09:28:37', NULL);
INSERT INTO `jcmd_shop` VALUES (2, '鱼', 2, 277, '斤', 10.60, '2023-02-05 17:07:05', '2023-03-20 23:09:24', NULL);
INSERT INTO `jcmd_shop` VALUES (3, '华硕笔记本', 2, 125, '台', 8.50, '2023-02-05 17:07:05', '2023-05-05 14:54:23', NULL);
INSERT INTO `jcmd_shop` VALUES (4, '阿莫西林胶囊', 4, 687, '盒', 85.00, '2023-02-05 17:07:05', '2023-03-20 23:43:37', NULL);
INSERT INTO `jcmd_shop` VALUES (5, '雪碧', 5, 88, '瓶', 5.80, '2023-02-05 17:07:05', '2023-04-04 20:51:24', NULL);
INSERT INTO `jcmd_shop` VALUES (6, '香蕉', 5, NULL, NULL, 12.00, '2023-03-19 15:38:13', '2023-04-04 20:55:25', '');
INSERT INTO `jcmd_shop` VALUES (7, '演示商品-纯牛奶', 7, 120, '箱', 68.00, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (8, '演示商品-酸奶', 7, 180, '件', 12.50, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (9, '演示商品-奶酪棒', 7, 160, '包', 18.90, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (10, '演示商品-速冻水饺', 8, 240, '袋', 23.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (11, '演示商品-速冻汤圆', 8, 200, '袋', 19.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (12, '演示商品-鸡米花', 8, 150, '袋', 21.60, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (13, '演示商品-大米5kg', 9, 80, '袋', 56.00, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (14, '演示商品-菜籽油', 9, 95, '桶', 79.90, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (15, '演示商品-生抽', 9, 300, '瓶', 9.90, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (16, '演示商品-洗衣液', 10, 130, '瓶', 35.90, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (17, '演示商品-抽纸', 10, 260, '提', 24.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (18, '演示商品-洗洁精', 10, 210, '瓶', 12.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (19, '演示商品-薯片', 11, 400, '包', 8.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (20, '演示商品-坚果礼盒', 11, 90, '盒', 59.00, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (21, '演示商品-苏打饼干', 11, 320, '包', 6.50, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (22, '演示商品-矿泉水550ml', 12, 600, '瓶', 2.00, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (23, '演示商品-纯净水5L', 12, 220, '桶', 11.80, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');
INSERT INTO `jcmd_shop` VALUES (24, '演示商品-苏打水', 12, 280, '瓶', 4.50, '2026-04-22 05:28:01', '2026-04-22 05:28:01', '批量演示数据');

-- ----------------------------
-- Table structure for jcmd_shop_type
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_shop_type`;
CREATE TABLE `jcmd_shop_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_id` bigint NULL DEFAULT NULL COMMENT '商品种类编号',
  `shop_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  `info` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jcmd_shop_type
-- ----------------------------
INSERT INTO `jcmd_shop_type` VALUES (1, 1, '水果类', '一级');
INSERT INTO `jcmd_shop_type` VALUES (2, 2, '水产类', '一级');
INSERT INTO `jcmd_shop_type` VALUES (3, 3, '电器类', '一级');
INSERT INTO `jcmd_shop_type` VALUES (4, 3, '医药类', '一级');
INSERT INTO `jcmd_shop_type` VALUES (5, 4, '饮品类', '一级');
INSERT INTO `jcmd_shop_type` VALUES (7, 20260422001, '乳制品类', '演示数据-乳制品');
INSERT INTO `jcmd_shop_type` VALUES (8, 20260422002, '速冻食品类', '演示数据-速冻食品');
INSERT INTO `jcmd_shop_type` VALUES (9, 20260422003, '粮油调味类', '演示数据-粮油调味');
INSERT INTO `jcmd_shop_type` VALUES (10, 20260422004, '日化清洁类', '演示数据-日化清洁');
INSERT INTO `jcmd_shop_type` VALUES (11, 20260422005, '休闲零食类', '演示数据-休闲零食');
INSERT INTO `jcmd_shop_type` VALUES (12, 20260422006, '饮用水类', '演示数据-饮用水');

-- ----------------------------
-- Table structure for jcmd_supplier
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_supplier`;
CREATE TABLE `jcmd_supplier`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `supplier_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商名',
  `head` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `shop_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `branch` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '银行',
  `branch_account` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `supplier_proxy` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商法人代表',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `other` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jcmd_supplier
-- ----------------------------
INSERT INTO `jcmd_supplier` VALUES (1, '北京鱼业', '杨自行', '1256841265', '北京昌平区', '鱼', '招商银行', '558462364655315451', '杨', '2023-02-05 17:59:24', '2023-05-12 12:11:59', NULL);
INSERT INTO `jcmd_supplier` VALUES (2, '嘉伦工程有限责任公司', '彭杰宏', '76914906845', '坑美十五巷586号', 'Strawbekry', '中信银行', '6290307664140642', '彭杰宏', '2021-05-07 06:48:49', '2023-05-12 12:12:37', NULL);
INSERT INTO `jcmd_supplier` VALUES (3, '潘記有限责任公司', '陶致远', '15291801410', '成华区二仙桥东三路546号', 'Mango', ' 邮政银行', '6220943385717238', '陶致远', '2020-10-10 23:30:46', '2023-05-12 12:12:55', NULL);
INSERT INTO `jcmd_supplier` VALUES (4, '子异有限责任公司', '于宇宁', '205015838', '海珠区江南西路536号', 'hrange', '浦发银行', '6261597080930940', '于宇宁', '2020-12-22 06:02:01', '2023-05-12 12:13:06', NULL);
INSERT INTO `jcmd_supplier` VALUES (5, '贾有限责任公司', '梁子异', '13351713862', '浦东新区健祥路975号', 'ambi-Strawberry', 'MasterCard', '6200179181979748', '梁子异', '2020-02-03 18:24:01', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (6, '睿物业代理有限责任公司', '尹子异', '18477850084', '罗湖区蔡屋围深南东路496号', 'fambutan', 'UnionPay', '6225194331513314', '尹子异', '2020-11-19 05:32:27', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (7, '陆記发展贸易有限责任公司', '吕宇宁', '13493985903', '延庆区028县道865号', 'iMango', 'MasterCard', '6263980689074641', '吕宇宁', '2022-12-15 22:43:05', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (8, '胡記发展贸易有限责任公司', '徐致远', '14723384265', '天河区天河路82号', 'Kiwi', 'UnionPay', '6273617269766562', '徐致远', '2023-02-02 01:29:25', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (9, '子异有限责任公司', '贾安琪', '2084712952', '海珠区江南西路454号', 'Grape air', 'UnionPay', '6254519854021754', '贾安琪', '2022-05-19 18:53:54', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (10, '璐有限责任公司', '苏子韬', '7551571770', '龙岗区布吉镇西环路411号', 'Gwape', 'Visa', '6206476415440933', '苏子韬', '2022-11-21 03:39:19', NULL, NULL);
INSERT INTO `jcmd_supplier` VALUES (11, '邹記顾问有限责任公司', '徐震南', '207964990', '海珠区江南西路118号', 'Ramlutan air', 'UnionPay', '6267092850431869', '徐震南', '2020-09-09 23:37:24', NULL, NULL);

-- ----------------------------
-- Table structure for jxmd_purchase
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_purchase`;
CREATE TABLE `jxmd_purchase`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购编号',
  `purchase_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购人',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品所属类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '采购供应商',
  `quantity` bigint NULL DEFAULT NULL COMMENT '采购数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价格',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '采购时间',
  `status` int NULL DEFAULT NULL COMMENT '状态 0 完成 1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位规格  个斤盒',
  `is_destroy` int NULL DEFAULT NULL COMMENT '0存在 1退货，退货后不显示，可增加查询按钮',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jxmd_purchase
-- ----------------------------
INSERT INTO `jxmd_purchase` VALUES (7, '718006985', '辰东', '华硕笔记本', '电器类', '璐有限责任公司', 120, 8865.00, 120.00, '2023-05-12 16:59:47', 1, '', '个', 1);
INSERT INTO `jxmd_purchase` VALUES (8, '718006981', '辰东', '鱼', '水产类', '贾有限责任公司', 230, 15.60, 120.00, '2023-05-13 16:11:58', 0, '', '斤', 1);
INSERT INTO `jxmd_purchase` VALUES (9, '210663607', 'test', '苹果', '水产类', '嘉伦工程有限责任公司', 50, 21.00, 120.00, '2023-06-01 21:57:09', 1, '', '件', 1);
INSERT INTO `jxmd_purchase` VALUES (10, '209088791', 'test', '苹果', '水果类', '北京鱼业', 12, 22.00, 120.00, '2023-06-01 21:57:31', 1, '', '斤', 0);
INSERT INTO `jxmd_purchase` VALUES (11, '182185694', '马化龙', '', '', '', NULL, NULL, 120.00, '2023-06-01 21:57:56', 1, '', '', 0);
INSERT INTO `jxmd_purchase` VALUES (12, '915089940', 'test', '雪碧', '饮品类', '子异有限责任公司', 25, 2.00, 120.00, '2023-06-01 21:59:43', 1, '', '个', 0);
INSERT INTO `jxmd_purchase` VALUES (13, '210753324', '张三', '', '', '', NULL, NULL, 120.00, '2023-06-01 22:00:04', 1, '', '', 0);
INSERT INTO `jxmd_purchase` VALUES (14, '20260422001', 'admin', '演示商品-纯牛奶', '乳制品类', '北京鱼业', 120, 58.00, 6960.00, '2026-04-22 05:33:55', 1, '演示采购单-乳制品补货', '箱', 0);
INSERT INTO `jxmd_purchase` VALUES (15, '20260422001', 'admin', '演示商品-酸奶', '乳制品类', '北京鱼业', 180, 10.50, 1890.00, '2026-04-22 05:33:55', 1, '演示采购单-乳制品补货', '件', 0);
INSERT INTO `jxmd_purchase` VALUES (16, '20260422001', 'admin', '演示商品-奶酪棒', '乳制品类', '北京鱼业', 140, 16.20, 2268.00, '2026-04-22 05:33:55', 1, '演示采购单-乳制品补货', '包', 0);
INSERT INTO `jxmd_purchase` VALUES (17, '20260422001', 'admin', '雪碧', '饮品类', '北京鱼业', 200, 2.30, 460.00, '2026-04-22 05:33:55', 1, '演示采购单-乳制品补货', '瓶', 0);
INSERT INTO `jxmd_purchase` VALUES (18, '20260422001', 'admin', '苹果', '水果类', '北京鱼业', 260, 9.00, 2340.00, '2026-04-22 05:33:55', 1, '演示采购单-乳制品补货', '斤', 0);
INSERT INTO `jxmd_purchase` VALUES (19, '20260422002', 'admin', '演示商品-速冻水饺', '速冻食品类', '嘉伦工程有限责任公司', 220, 19.80, 4356.00, '2026-04-21 05:33:55', 1, '演示采购单-速冻专区', '袋', 0);
INSERT INTO `jxmd_purchase` VALUES (20, '20260422002', 'admin', '演示商品-速冻汤圆', '速冻食品类', '嘉伦工程有限责任公司', 210, 17.20, 3612.00, '2026-04-21 05:33:55', 1, '演示采购单-速冻专区', '袋', 0);
INSERT INTO `jxmd_purchase` VALUES (21, '20260422002', 'admin', '演示商品-鸡米花', '速冻食品类', '嘉伦工程有限责任公司', 170, 18.50, 3145.00, '2026-04-21 05:33:55', 1, '演示采购单-速冻专区', '袋', 0);
INSERT INTO `jxmd_purchase` VALUES (22, '20260422002', 'admin', '鱼', '水产类', '嘉伦工程有限责任公司', 300, 13.50, 4050.00, '2026-04-21 05:33:55', 1, '演示采购单-速冻专区', '斤', 0);
INSERT INTO `jxmd_purchase` VALUES (23, '20260422002', 'admin', '香蕉', '水果类', '嘉伦工程有限责任公司', 180, 5.60, 1008.00, '2026-04-21 05:33:55', 1, '演示采购单-速冻专区', '斤', 0);
INSERT INTO `jxmd_purchase` VALUES (24, '20260422003', 'admin', '演示商品-大米5kg', '粮油调味类', '潘記有限责任公司', 90, 48.00, 4320.00, '2026-04-20 05:33:55', 0, '演示采购单-粮油专区', '袋', 0);
INSERT INTO `jxmd_purchase` VALUES (25, '20260422003', 'admin', '演示商品-菜籽油', '粮油调味类', '潘記有限责任公司', 100, 68.00, 6800.00, '2026-04-20 05:33:55', 0, '演示采购单-粮油专区', '桶', 0);
INSERT INTO `jxmd_purchase` VALUES (26, '20260422003', 'admin', '演示商品-生抽', '粮油调味类', '潘記有限责任公司', 260, 7.20, 1872.00, '2026-04-20 05:33:55', 0, '演示采购单-粮油专区', '瓶', 0);
INSERT INTO `jxmd_purchase` VALUES (27, '20260422003', 'admin', '演示商品-洗衣液', '日化清洁类', '潘記有限责任公司', 130, 29.00, 3770.00, '2026-04-20 05:33:55', 0, '演示采购单-粮油专区', '瓶', 0);
INSERT INTO `jxmd_purchase` VALUES (28, '20260422003', 'admin', '演示商品-抽纸', '日化清洁类', '潘記有限责任公司', 250, 19.80, 4950.00, '2026-04-20 05:33:55', 0, '演示采购单-粮油专区', '提', 0);
INSERT INTO `jxmd_purchase` VALUES (29, '20260422004', 'admin', '演示商品-薯片', '休闲零食类', '子异有限责任公司', 320, 6.20, 1984.00, '2026-04-19 05:33:55', 1, '演示采购单-零食饮水', '包', 0);
INSERT INTO `jxmd_purchase` VALUES (30, '20260422004', 'admin', '演示商品-坚果礼盒', '休闲零食类', '子异有限责任公司', 110, 45.00, 4950.00, '2026-04-19 05:33:55', 1, '演示采购单-零食饮水', '盒', 0);
INSERT INTO `jxmd_purchase` VALUES (31, '20260422004', 'admin', '演示商品-苏打饼干', '休闲零食类', '子异有限责任公司', 280, 5.10, 1428.00, '2026-04-19 05:33:55', 1, '演示采购单-零食饮水', '包', 0);
INSERT INTO `jxmd_purchase` VALUES (32, '20260422004', 'admin', '演示商品-矿泉水550ml', '饮用水类', '子异有限责任公司', 900, 1.50, 1350.00, '2026-04-19 05:33:55', 1, '演示采购单-零食饮水', '瓶', 0);
INSERT INTO `jxmd_purchase` VALUES (33, '20260422004', 'admin', '演示商品-纯净水5L', '饮用水类', '子异有限责任公司', 260, 8.80, 2288.00, '2026-04-19 05:33:55', 1, '演示采购单-零食饮水', '桶', 0);

-- ----------------------------
-- Table structure for jxmd_purchase_exit
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_purchase_exit`;
CREATE TABLE `jxmd_purchase_exit`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '采购编号',
  `exit_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '退采编号',
  `num` int NULL DEFAULT NULL COMMENT '退采数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日期',
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '原因',
  `status` int NULL DEFAULT NULL COMMENT '状态 0 完成 1进行中  ',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位规格 个斤盒',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jxmd_purchase_exit
-- ----------------------------
INSERT INTO `jxmd_purchase_exit` VALUES (1, ' 213341123', '12588', 30, 8.50, 25.50, '2023-04-30 23:41:31', '买多了', 1, '', NULL);
INSERT INTO `jxmd_purchase_exit` VALUES (2, '123456789', '12588', 30, 8.50, 25.50, '2023-04-30 23:41:38', '买多了', 1, NULL, NULL);
INSERT INTO `jxmd_purchase_exit` VALUES (3, ' 562231358', '123456', 20, 10.90, 120.00, '2023-04-30 23:41:43', '', 1, '', NULL);
INSERT INTO `jxmd_purchase_exit` VALUES (4, '179910234', '789581103', 230, 7.40, 120.00, '2023-05-18 18:11:25', 'aaaaa', 0, NULL, '个');
INSERT INTO `jxmd_purchase_exit` VALUES (5, '143649068', '436056446', 300, 6.50, 120.00, '2023-05-18 18:13:23', 'jxc1111111111111111111111111', 0, NULL, '斤');
INSERT INTO `jxmd_purchase_exit` VALUES (6, '718006985', '204514450', 120, 8865.00, 120.00, '2023-05-23 19:09:32', 'chusd1111111', 0, NULL, '个');
INSERT INTO `jxmd_purchase_exit` VALUES (7, '718006981', '612573700', 230, 15.60, 120.00, '2023-06-01 21:55:59', '买多了', 1, NULL, '斤');
INSERT INTO `jxmd_purchase_exit` VALUES (8, '210663607', '979599307', 50, 21.00, 120.00, '2023-06-03 14:31:21', '测试', 0, NULL, '件');

-- ----------------------------
-- Table structure for jxmd_sale
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_sale`;
CREATE TABLE `jxmd_sale`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sale_Number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '销售编号',
  `sale_user` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '销售人',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '销售商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品所属类',
  `supplier` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '销售客户，需要改成客户英文名',
  `num` bigint NULL DEFAULT NULL COMMENT '数量',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '单位规格  个斤盒',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '销售时间',
  `status` int NULL DEFAULT NULL COMMENT '状态 0 完成 1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '销售表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jxmd_sale
-- ----------------------------
INSERT INTO `jxmd_sale` VALUES (1, '123456', '王阳明', '苹果', '水果类', '老杨', 5, '箱', 1.00, 5.00, '2023-02-13 23:03:43', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (6, '123456', 'yang', '鱼', '水产类', '孙安琪', 60, NULL, 3.50, 120.00, '2023-03-24 00:03:32', 1, '');
INSERT INTO `jxmd_sale` VALUES (8, '873479332', '老王', '鱼', '水产类', '方安琪', 52, NULL, 3.20, 166.40, '2023-05-04 22:09:20', 1, '');
INSERT INTO `jxmd_sale` VALUES (9, '106103464', '挖', '苹果', '水果类', '张三', 100, NULL, 3.50, 350.00, '2023-05-06 20:33:48', 1, '');
INSERT INTO `jxmd_sale` VALUES (11, '343538883', '老三', '华硕笔记本', '电器类', '孙安琪', 56, '个', 20.00, 1120.00, '2023-05-12 00:44:58', 1, '');
INSERT INTO `jxmd_sale` VALUES (12, '276558819', '马化腾', '香蕉', '水果类', '毛子韬', 12, '斤', 12.50, 150.00, '2023-05-12 16:39:47', 1, '');
INSERT INTO `jxmd_sale` VALUES (13, '137694665', '辰东', '雪碧', '饮品类', '罗晓明', 110, '个', 7.80, 858.00, '2023-05-12 16:50:09', 1, '');
INSERT INTO `jxmd_sale` VALUES (14, '900000001', 'admin', '雪碧', '饮品类', 'Customer-001', 6, '斤', 10.35, 62.10, '2026-04-21 20:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (15, '900000002', 'admin', '雪碧', '饮品类', 'Customer-002', 7, '斤', 11.35, 79.45, '2026-04-21 17:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (16, '900000003', 'manager', '雪碧', '饮品类', 'Customer-003', 8, '斤', 12.35, 98.80, '2026-04-21 14:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (17, '900000004', 'admin', '雪碧', '饮品类', 'Customer-004', 9, '斤', 13.35, 120.15, '2026-04-21 11:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (18, '900000005', 'admin', '雪碧', '饮品类', 'Customer-005', 10, '斤', 14.35, 143.50, '2026-04-21 08:17:29', 1, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (19, '900000006', 'manager', '雪碧', '饮品类', 'Customer-006', 11, '斤', 15.35, 168.85, '2026-04-21 05:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (20, '900000007', 'admin', '雪碧', '饮品类', 'Customer-007', 12, '斤', 16.35, 196.20, '2026-04-21 02:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (21, '900000008', 'admin', '雪碧', '饮品类', 'Customer-008', 13, '斤', 17.35, 225.55, '2026-04-20 23:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (22, '900000009', 'manager', '雪碧', '饮品类', 'Customer-009', 14, '斤', 18.35, 256.90, '2026-04-20 20:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (23, '900000010', 'admin', '雪碧', '饮品类', 'Customer-010', 15, '斤', 19.35, 290.25, '2026-04-20 17:17:29', 0, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (24, '900000011', 'admin', '雪碧', '饮品类', 'Customer-011', 16, '斤', 8.35, 133.60, '2026-04-20 14:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (25, '900000012', 'manager', '雪碧', '饮品类', 'Customer-012', 17, '斤', 9.35, 158.95, '2026-04-20 11:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (26, '900000013', 'admin', '雪碧', '饮品类', 'Customer-013', 18, '斤', 10.35, 186.30, '2026-04-20 08:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (27, '900000014', 'admin', '雪碧', '饮品类', 'Customer-014', 19, '斤', 11.35, 215.65, '2026-04-20 05:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (28, '900000015', 'manager', '雪碧', '饮品类', 'Customer-015', 20, '斤', 12.35, 247.00, '2026-04-20 02:17:29', 1, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (29, '900000016', 'admin', '雪碧', '饮品类', 'Customer-016', 21, '斤', 13.35, 280.35, '2026-04-19 23:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (30, '900000017', 'admin', '雪碧', '饮品类', 'Customer-017', 22, '斤', 14.35, 315.70, '2026-04-19 20:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (31, '900000018', 'manager', '雪碧', '饮品类', 'Customer-018', 23, '斤', 15.35, 353.05, '2026-04-19 17:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (32, '900000019', 'admin', '雪碧', '饮品类', 'Customer-019', 24, '斤', 16.35, 392.40, '2026-04-19 14:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (33, '900000020', 'admin', '雪碧', '饮品类', 'Customer-020', 5, '斤', 17.35, 86.75, '2026-04-19 11:17:29', 0, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (34, '900000021', 'manager', '雪碧', '饮品类', 'Customer-021', 6, '斤', 18.35, 110.10, '2026-04-19 08:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (35, '900000022', 'admin', '雪碧', '饮品类', 'Customer-022', 7, '斤', 19.35, 135.45, '2026-04-19 05:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (36, '900000023', 'admin', '雪碧', '饮品类', 'Customer-023', 8, '斤', 8.35, 66.80, '2026-04-19 02:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (37, '900000024', 'manager', '雪碧', '饮品类', 'Customer-024', 9, '斤', 9.35, 84.15, '2026-04-18 23:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (38, '900000025', 'admin', '雪碧', '饮品类', 'Customer-025', 10, '斤', 10.35, 103.50, '2026-04-18 20:17:29', 1, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (39, '900000026', 'admin', '雪碧', '饮品类', 'Customer-026', 11, '斤', 11.35, 124.85, '2026-04-18 17:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (40, '900000027', 'manager', '雪碧', '饮品类', 'Customer-027', 12, '斤', 12.35, 148.20, '2026-04-18 14:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (41, '900000028', 'admin', '雪碧', '饮品类', 'Customer-028', 13, '斤', 13.35, 173.55, '2026-04-18 11:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (42, '900000029', 'admin', '雪碧', '饮品类', 'Customer-029', 14, '斤', 14.35, 200.90, '2026-04-18 08:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (43, '900000030', 'manager', '雪碧', '饮品类', 'Customer-030', 15, '斤', 15.35, 230.25, '2026-04-18 05:17:29', 1, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (44, '900000031', 'admin', '雪碧', '饮品类', 'Customer-031', 16, '斤', 16.35, 261.60, '2026-04-18 02:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (45, '900000032', 'admin', '雪碧', '饮品类', 'Customer-032', 17, '斤', 17.35, 294.95, '2026-04-17 23:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (46, '900000033', 'manager', '雪碧', '饮品类', 'Customer-033', 18, '斤', 18.35, 330.30, '2026-04-17 20:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (47, '900000034', 'admin', '雪碧', '饮品类', 'Customer-034', 19, '斤', 19.35, 367.65, '2026-04-17 17:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (48, '900000035', 'admin', '雪碧', '饮品类', 'Customer-035', 20, '斤', 8.35, 167.00, '2026-04-17 14:17:29', 1, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (49, '900000036', 'manager', '雪碧', '饮品类', 'Customer-036', 21, '斤', 9.35, 196.35, '2026-04-17 11:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (50, '900000037', 'admin', '雪碧', '饮品类', 'Customer-037', 22, '斤', 10.35, 227.70, '2026-04-17 08:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (51, '900000038', 'admin', '雪碧', '饮品类', 'Customer-038', 23, '斤', 11.35, 261.05, '2026-04-17 05:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (52, '900000039', 'manager', '雪碧', '饮品类', 'Customer-039', 24, '斤', 12.35, 296.40, '2026-04-17 02:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (53, '900000040', 'admin', '雪碧', '饮品类', 'Customer-040', 5, '斤', 13.35, 66.75, '2026-04-16 23:17:29', 0, 'PARTIAL_SHORTAGE');
INSERT INTO `jxmd_sale` VALUES (54, '900000001', 'admin', '华硕笔记本', '电器类', 'Customer-001', 7, '个', 11.70, 81.90, '2026-04-21 19:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (55, '900000002', 'admin', '华硕笔记本', '电器类', 'Customer-002', 9, '个', 12.70, 114.30, '2026-04-21 16:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (56, '900000003', 'manager', '华硕笔记本', '电器类', 'Customer-003', 11, '个', 13.70, 150.70, '2026-04-21 13:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (57, '900000004', 'admin', '华硕笔记本', '电器类', 'Customer-004', 13, '个', 14.70, 191.10, '2026-04-21 10:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (58, '900000005', 'admin', '华硕笔记本', '电器类', 'Customer-005', 15, '个', 15.70, 235.50, '2026-04-21 07:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (59, '900000006', 'manager', '华硕笔记本', '电器类', 'Customer-006', 17, '个', 16.70, 283.90, '2026-04-21 04:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (60, '900000007', 'admin', '华硕笔记本', '电器类', 'Customer-007', 19, '个', 17.70, 336.30, '2026-04-21 01:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (61, '900000008', 'admin', '华硕笔记本', '电器类', 'Customer-008', 21, '个', 18.70, 392.70, '2026-04-20 22:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (62, '900000009', 'manager', '华硕笔记本', '电器类', 'Customer-009', 23, '个', 19.70, 453.10, '2026-04-20 19:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (63, '900000010', 'admin', '华硕笔记本', '电器类', 'Customer-010', 5, '个', 8.70, 43.50, '2026-04-20 16:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (64, '900000011', 'admin', '华硕笔记本', '电器类', 'Customer-011', 7, '个', 9.70, 67.90, '2026-04-20 13:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (65, '900000012', 'manager', '华硕笔记本', '电器类', 'Customer-012', 9, '个', 10.70, 96.30, '2026-04-20 10:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (66, '900000013', 'admin', '华硕笔记本', '电器类', 'Customer-013', 11, '个', 11.70, 128.70, '2026-04-20 07:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (67, '900000014', 'admin', '华硕笔记本', '电器类', 'Customer-014', 13, '个', 12.70, 165.10, '2026-04-20 04:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (68, '900000015', 'manager', '华硕笔记本', '电器类', 'Customer-015', 15, '个', 13.70, 205.50, '2026-04-20 01:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (69, '900000016', 'admin', '华硕笔记本', '电器类', 'Customer-016', 17, '个', 14.70, 249.90, '2026-04-19 22:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (70, '900000017', 'admin', '华硕笔记本', '电器类', 'Customer-017', 19, '个', 15.70, 298.30, '2026-04-19 19:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (71, '900000018', 'manager', '华硕笔记本', '电器类', 'Customer-018', 21, '个', 16.70, 350.70, '2026-04-19 16:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (72, '900000019', 'admin', '华硕笔记本', '电器类', 'Customer-019', 23, '个', 17.70, 407.10, '2026-04-19 13:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (73, '900000020', 'admin', '华硕笔记本', '电器类', 'Customer-020', 5, '个', 18.70, 93.50, '2026-04-19 10:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (74, '900000021', 'manager', '华硕笔记本', '电器类', 'Customer-021', 7, '个', 19.70, 137.90, '2026-04-19 07:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (75, '900000022', 'admin', '华硕笔记本', '电器类', 'Customer-022', 9, '个', 8.70, 78.30, '2026-04-19 04:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (76, '900000023', 'admin', '华硕笔记本', '电器类', 'Customer-023', 11, '个', 9.70, 106.70, '2026-04-19 01:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (77, '900000024', 'manager', '华硕笔记本', '电器类', 'Customer-024', 13, '个', 10.70, 139.10, '2026-04-18 22:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (78, '900000025', 'admin', '华硕笔记本', '电器类', 'Customer-025', 15, '个', 11.70, 175.50, '2026-04-18 19:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (79, '900000026', 'admin', '华硕笔记本', '电器类', 'Customer-026', 17, '个', 12.70, 215.90, '2026-04-18 16:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (80, '900000027', 'manager', '华硕笔记本', '电器类', 'Customer-027', 19, '个', 13.70, 260.30, '2026-04-18 13:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (81, '900000028', 'admin', '华硕笔记本', '电器类', 'Customer-028', 21, '个', 14.70, 308.70, '2026-04-18 10:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (82, '900000029', 'admin', '华硕笔记本', '电器类', 'Customer-029', 23, '个', 15.70, 361.10, '2026-04-18 07:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (83, '900000030', 'manager', '华硕笔记本', '电器类', 'Customer-030', 5, '个', 16.70, 83.50, '2026-04-18 04:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (84, '900000031', 'admin', '华硕笔记本', '电器类', 'Customer-031', 7, '个', 17.70, 123.90, '2026-04-18 01:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (85, '900000032', 'admin', '华硕笔记本', '电器类', 'Customer-032', 9, '个', 18.70, 168.30, '2026-04-17 22:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (86, '900000033', 'manager', '华硕笔记本', '电器类', 'Customer-033', 11, '个', 19.70, 216.70, '2026-04-17 19:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (87, '900000034', 'admin', '华硕笔记本', '电器类', 'Customer-034', 13, '个', 8.70, 113.10, '2026-04-17 16:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (88, '900000035', 'admin', '华硕笔记本', '电器类', 'Customer-035', 15, '个', 9.70, 145.50, '2026-04-17 13:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (89, '900000036', 'manager', '华硕笔记本', '电器类', 'Customer-036', 17, '个', 10.70, 181.90, '2026-04-17 10:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (90, '900000037', 'admin', '华硕笔记本', '电器类', 'Customer-037', 19, '个', 11.70, 222.30, '2026-04-17 07:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (91, '900000038', 'admin', '华硕笔记本', '电器类', 'Customer-038', 21, '个', 12.70, 266.70, '2026-04-17 04:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (92, '900000039', 'manager', '华硕笔记本', '电器类', 'Customer-039', 23, '个', 13.70, 315.10, '2026-04-17 01:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (93, '900000040', 'admin', '华硕笔记本', '电器类', 'Customer-040', 5, '个', 14.70, 73.50, '2026-04-16 22:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (94, '900000001', 'admin', '苹果', '水果类', 'Customer-001', 8, '个', 13.05, 104.40, '2026-04-21 18:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (95, '900000002', 'admin', '苹果', '水果类', 'Customer-002', 11, '个', 14.05, 154.55, '2026-04-21 15:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (96, '900000003', 'manager', '苹果', '水果类', 'Customer-003', 14, '个', 15.05, 210.70, '2026-04-21 12:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (97, '900000004', 'admin', '苹果', '水果类', 'Customer-004', 17, '个', 16.05, 272.85, '2026-04-21 09:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (98, '900000005', 'admin', '苹果', '水果类', 'Customer-005', 20, '个', 17.05, 341.00, '2026-04-21 06:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (99, '900000006', 'manager', '苹果', '水果类', 'Customer-006', 23, '个', 18.05, 415.15, '2026-04-21 03:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (100, '900000007', 'admin', '苹果', '水果类', 'Customer-007', 6, '个', 19.05, 114.30, '2026-04-21 00:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (101, '900000008', 'admin', '苹果', '水果类', 'Customer-008', 9, '个', 20.05, 180.45, '2026-04-20 21:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (102, '900000009', 'manager', '苹果', '水果类', 'Customer-009', 12, '个', 9.05, 108.60, '2026-04-20 18:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (103, '900000010', 'admin', '苹果', '水果类', 'Customer-010', 15, '个', 10.05, 150.75, '2026-04-20 15:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (104, '900000011', 'admin', '苹果', '水果类', 'Customer-011', 18, '个', 11.05, 198.90, '2026-04-20 12:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (105, '900000012', 'manager', '苹果', '水果类', 'Customer-012', 21, '个', 12.05, 253.05, '2026-04-20 09:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (106, '900000013', 'admin', '苹果', '水果类', 'Customer-013', 24, '个', 13.05, 313.20, '2026-04-20 06:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (107, '900000014', 'admin', '苹果', '水果类', 'Customer-014', 7, '个', 14.05, 98.35, '2026-04-20 03:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (108, '900000015', 'manager', '苹果', '水果类', 'Customer-015', 10, '个', 15.05, 150.50, '2026-04-20 00:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (109, '900000016', 'admin', '苹果', '水果类', 'Customer-016', 13, '个', 16.05, 208.65, '2026-04-19 21:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (110, '900000017', 'admin', '苹果', '水果类', 'Customer-017', 16, '个', 17.05, 272.80, '2026-04-19 18:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (111, '900000018', 'manager', '苹果', '水果类', 'Customer-018', 19, '个', 18.05, 342.95, '2026-04-19 15:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (112, '900000019', 'admin', '苹果', '水果类', 'Customer-019', 22, '个', 19.05, 419.10, '2026-04-19 12:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (113, '900000020', 'admin', '苹果', '水果类', 'Customer-020', 5, '个', 20.05, 100.25, '2026-04-19 09:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (114, '900000021', 'manager', '苹果', '水果类', 'Customer-021', 8, '个', 9.05, 72.40, '2026-04-19 06:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (115, '900000022', 'admin', '苹果', '水果类', 'Customer-022', 11, '个', 10.05, 110.55, '2026-04-19 03:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (116, '900000023', 'admin', '苹果', '水果类', 'Customer-023', 14, '个', 11.05, 154.70, '2026-04-19 00:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (117, '900000024', 'manager', '苹果', '水果类', 'Customer-024', 17, '个', 12.05, 204.85, '2026-04-18 21:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (118, '900000025', 'admin', '苹果', '水果类', 'Customer-025', 20, '个', 13.05, 261.00, '2026-04-18 18:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (119, '900000026', 'admin', '苹果', '水果类', 'Customer-026', 23, '个', 14.05, 323.15, '2026-04-18 15:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (120, '900000027', 'manager', '苹果', '水果类', 'Customer-027', 6, '个', 15.05, 90.30, '2026-04-18 12:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (121, '900000028', 'admin', '苹果', '水果类', 'Customer-028', 9, '个', 16.05, 144.45, '2026-04-18 09:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (122, '900000029', 'admin', '苹果', '水果类', 'Customer-029', 12, '个', 17.05, 204.60, '2026-04-18 06:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (123, '900000030', 'manager', '苹果', '水果类', 'Customer-030', 15, '个', 18.05, 270.75, '2026-04-18 03:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (124, '900000031', 'admin', '苹果', '水果类', 'Customer-031', 18, '个', 19.05, 342.90, '2026-04-18 00:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (125, '900000032', 'admin', '苹果', '水果类', 'Customer-032', 21, '个', 20.05, 421.05, '2026-04-17 21:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (126, '900000033', 'manager', '苹果', '水果类', 'Customer-033', 24, '个', 9.05, 217.20, '2026-04-17 18:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (127, '900000034', 'admin', '苹果', '水果类', 'Customer-034', 7, '个', 10.05, 70.35, '2026-04-17 15:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (128, '900000035', 'admin', '苹果', '水果类', 'Customer-035', 10, '个', 11.05, 110.50, '2026-04-17 12:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (129, '900000036', 'manager', '苹果', '水果类', 'Customer-036', 13, '个', 12.05, 156.65, '2026-04-17 09:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (130, '900000037', 'admin', '苹果', '水果类', 'Customer-037', 16, '个', 13.05, 208.80, '2026-04-17 06:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (131, '900000038', 'admin', '苹果', '水果类', 'Customer-038', 19, '个', 14.05, 266.95, '2026-04-17 03:17:29', 1, NULL);
INSERT INTO `jxmd_sale` VALUES (132, '900000039', 'manager', '苹果', '水果类', 'Customer-039', 22, '个', 15.05, 331.10, '2026-04-17 00:17:29', 0, NULL);
INSERT INTO `jxmd_sale` VALUES (133, '900000040', 'admin', '苹果', '水果类', 'Customer-040', 5, '个', 16.05, 80.25, '2026-04-16 21:17:29', 0, NULL);

-- ----------------------------
-- Table structure for jxmd_sale_exit
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_sale_exit`;
CREATE TABLE `jxmd_sale_exit`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '销售编号',
  `exit_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '退购编号',
  `num` bigint NULL DEFAULT NULL COMMENT '退购数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '日期',
  `reason` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '退购原因',
  `status` int NULL DEFAULT NULL COMMENT '状态 0 完成  1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '销售退货表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jxmd_sale_exit
-- ----------------------------
INSERT INTO `jxmd_sale_exit` VALUES (1, '123456', '456', 202, 4.00, 7.00, '2023-02-13 23:19:36', '客户不想要了', 1, NULL);
INSERT INTO `jxmd_sale_exit` VALUES (2, '123', '456', 20, 4.00, NULL, '2023-03-24 00:15:40', '1', 1, NULL);
INSERT INTO `jxmd_sale_exit` VALUES (13, '', '267718632', 12, NULL, NULL, '2023-05-04 22:47:06', '', 1, '');
INSERT INTO `jxmd_sale_exit` VALUES (14, '123456', '373916142', 11, 2.00, 6.00, '2023-05-07 15:54:56', '', 1, '');
INSERT INTO `jxmd_sale_exit` VALUES (15, '123456', '198154791', 1, 1.00, 1.00, '2026-04-12 17:15:58', '', 0, '');
INSERT INTO `jxmd_sale_exit` VALUES (16, '123456', '762163078', 1, 12.00, 1.00, '2026-04-22 02:58:29', '', 0, '');

-- ----------------------------
-- Table structure for qjmd_table_list
-- ----------------------------
DROP TABLE IF EXISTS `qjmd_table_list`;
CREATE TABLE `qjmd_table_list`  (
  `id` bigint NOT NULL,
  `tableName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表名',
  `md` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '业务模块',
  `describe` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` bigint NULL DEFAULT NULL COMMENT '表的顺序即业务-菜单显示顺序',
  `children` bigint NULL DEFAULT NULL COMMENT '模块业务的子业务显示顺序',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qjmd_table_list
-- ----------------------------
INSERT INTO `qjmd_table_list` VALUES (1, 'jcmd_customer', '基础信息管理模块', '客户表', 1, 1, NULL);
INSERT INTO `qjmd_table_list` VALUES (2, 'jcmd_shop', '基础信息管理模块', '商品表', 1, 2, NULL);
INSERT INTO `qjmd_table_list` VALUES (3, 'jcmd_supplier', '基础信息管理模块', '供应商表', 1, 3, NULL);
INSERT INTO `qjmd_table_list` VALUES (4, 'bjmd_note', '备忘录', '笔记表', 2, 1, NULL);
INSERT INTO `qjmd_table_list` VALUES (5, 'jxmd_purchase', '进销管理模块', '采购表', 3, 1, NULL);
INSERT INTO `qjmd_table_list` VALUES (6, 'jxmd_purchase_exit', '进销管理模块', '采购退货表', 3, 2, NULL);
INSERT INTO `qjmd_table_list` VALUES (7, 'jxmd_sale', '进销管理模块', '销售表', 3, 3, NULL);
INSERT INTO `qjmd_table_list` VALUES (8, 'jxmd_sale_exit', '进销管理模块', '销售退货表', 3, 4, NULL);
INSERT INTO `qjmd_table_list` VALUES (9, 'ckmd_depository', '仓库管理模块', '仓库表', 4, 2, NULL);
INSERT INTO `qjmd_table_list` VALUES (11, 'ckmd_depository_in', '仓库管理模块', '入库清单表', 4, 3, NULL);
INSERT INTO `qjmd_table_list` VALUES (12, 'ckmd_depository_out', '仓库管理模块', '出库清单表', 4, 4, NULL);
INSERT INTO `qjmd_table_list` VALUES (13, 'xtmd_admin', '系统管理模块', '用户表', 5, 1, NULL);
INSERT INTO `qjmd_table_list` VALUES (14, 'xtmd_role', '系统管理模块', '角色表', 5, 2, NULL);
INSERT INTO `qjmd_table_list` VALUES (15, 'xtmd_dept', '系统管理模块', '部门表', 5, 3, NULL);
INSERT INTO `qjmd_table_list` VALUES (16, 'xtmd_menu', '系统管理模块', '菜单表', 5, 4, NULL);
INSERT INTO `qjmd_table_list` VALUES (17, 'ckmd_stock', '仓库管理模块', '库存清单列表', 4, 1, NULL);

-- ----------------------------
-- Table structure for xtmd_admin
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin`;
CREATE TABLE `xtmd_admin`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `dept` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门id或者名称',
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账户',
  `true_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `status` int NULL DEFAULT 0 COMMENT '0正常 1停用',
  `e_mail` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_ip` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `sex` int NULL DEFAULT 0 COMMENT '0男 1女 ',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin
-- ----------------------------
INSERT INTO `xtmd_admin` VALUES (1, '2', 'admin', '奥托·阿波卡利斯', '$2a$10$EUzRD0RKsdNRgnUVQb0snO3p06k5baCeZ/sbZdbIviYnqoX3GR4Wy', '', NULL, 0, '107348698@qq.com', '13521253828', '2023-01-08 15:05:52', NULL, '2023-01-08 15:05:00', 0, '温柔正确的人总是难以生存，因为这个世界既不温柔，也不正确。所以，奥托·阿波卡利斯并不需要温柔这种东西，奥托·阿波卡利斯只是单方面自私地攫取，直到把自己想要的那个结局紧紧攥在手里。');
INSERT INTO `xtmd_admin` VALUES (15, '2', 'saleMan', '辰东', '$2a$10$S/go13Lz2DFMsqlDiro0Y.cO4.vtRZ4PNubvuVBZCTvcRm4pvYRq.', NULL, NULL, 0, '', '15685545557', NULL, NULL, NULL, 0, '');
INSERT INTO `xtmd_admin` VALUES (16, '10', 'wareHouse', '马化龙', '$2a$10$upiKjmC3tNDe3XDZMqvSWek79ZtSEHIpsLSk/I9hAJCAWdiccLiTC', NULL, NULL, 0, '', '15954263248', NULL, NULL, NULL, 0, '');

-- ----------------------------
-- Table structure for xtmd_admin_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin_dept_relation`;
CREATE TABLE `xtmd_admin_dept_relation`  (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin_dept_relation
-- ----------------------------

-- ----------------------------
-- Table structure for xtmd_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin_role_relation`;
CREATE TABLE `xtmd_admin_role_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin_role_relation
-- ----------------------------
INSERT INTO `xtmd_admin_role_relation` VALUES (16, 1, 1);
INSERT INTO `xtmd_admin_role_relation` VALUES (18, 6, 1);
INSERT INTO `xtmd_admin_role_relation` VALUES (19, 5, 2);
INSERT INTO `xtmd_admin_role_relation` VALUES (21, 2, 3);
INSERT INTO `xtmd_admin_role_relation` VALUES (22, 15, 2);
INSERT INTO `xtmd_admin_role_relation` VALUES (23, 16, 4);

-- ----------------------------
-- Table structure for xtmd_dept
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_dept`;
CREATE TABLE `xtmd_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父部门id',
  `dept_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `order_num` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `status` int NULL DEFAULT NULL COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_dept
-- ----------------------------
INSERT INTO `xtmd_dept` VALUES (2, 100, '深圳总公司', '1', 'Otto Apocalypse', '16482618330', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2026-04-22 04:37:43');
INSERT INTO `xtmd_dept` VALUES (3, 100, '长沙分公司', '2', 'Kallen Kaslana', '13016238720', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2026-04-22 04:37:33');
INSERT INTO `xtmd_dept` VALUES (4, 101, '研发部门', '1', 'Theresa Apocalypse', '16567564426', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2026-04-22 04:37:20');
INSERT INTO `xtmd_dept` VALUES (5, 101, '销售部门', '2', 'Yae Sakura', '18489450107', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2026-04-22 04:38:17');
INSERT INTO `xtmd_dept` VALUES (6, 101, '测试部门', '3', 'Kiana Kaslana', '19479515718', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2026-04-22 04:38:04');
INSERT INTO `xtmd_dept` VALUES (7, 101, '财务部门', '4', '若依', '13121649314', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` VALUES (8, 101, '运维部门', '5', '若依', '19224141234', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` VALUES (9, 102, '市场二部', '1', '若依', '1033684022', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` VALUES (10, 102, '仓储部门', '2', '若依', '19690068075', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);

-- ----------------------------
-- Table structure for xtmd_menu
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_menu`;
CREATE TABLE `xtmd_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名称 label',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级ID',
  `level` int NULL DEFAULT NULL COMMENT '菜单级数',
  `sort` int NULL DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `hidden` int NULL DEFAULT NULL COMMENT '状态 0正常 1隐藏',
  `url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '访问地址',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'vue路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '后台菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_menu
-- ----------------------------
INSERT INTO `xtmd_menu` VALUES (1, '基础信息管理', 0, 0, 1, 'jcmd', 'user', '2020-02-02 14:50:36', 0, NULL, '/');
INSERT INTO `xtmd_menu` VALUES (2, '客户资料', 1, 1, 0, 'customer', 'folder', '2020-02-02 14:51:50', 0, 'jcmd/customer', '/customer');
INSERT INTO `xtmd_menu` VALUES (3, '供应商资料', 1, 1, 3, 'supplier', 'folder', '2020-02-02 14:52:44', 0, 'jcmd/supplier', '/supplier');
INSERT INTO `xtmd_menu` VALUES (4, '商品资料', 1, 1, 1, 'shop', 'folder', '2020-02-02 14:53:51', 0, 'jcmd/shop', '/shop');
INSERT INTO `xtmd_menu` VALUES (7, '进销管理', 0, 0, 3, 'jxmd', 'sell', '2020-02-04 16:18:00', 0, NULL, NULL);
INSERT INTO `xtmd_menu` VALUES (8, '采购', 7, 1, 1, 'purchase', 'jxmd-purchase', '2020-02-04 16:19:22', 0, 'jxmd/purchase', '/purchase');
INSERT INTO `xtmd_menu` VALUES (9, '采购退货', 7, 1, 2, 'purchaseExit', 'jxmd-purchaseExit', '2020-02-04 16:20:16', 0, 'jxmd/purchaseExit', '/purchaseExit');
INSERT INTO `xtmd_menu` VALUES (10, '销售', 7, 1, 3, 'sale', 'jxmd-sale', '2020-02-07 16:22:38', 0, 'jxmd/sale', '/sale');
INSERT INTO `xtmd_menu` VALUES (11, '销售退货', 7, 1, 4, 'saleExit', 'jxmd-saleExit', '2020-02-07 16:23:14', 0, 'jxmd/saleExit', '/saleExit');
INSERT INTO `xtmd_menu` VALUES (12, '仓库管理', 0, 0, 4, 'ckmd', 's-order', '2020-02-07 16:29:13', 0, NULL, '');
INSERT INTO `xtmd_menu` VALUES (13, '库存列表', 12, 1, 1, 'stockList', 'ckmd-stock', '2020-02-07 16:29:51', 0, 'ckmd/stockList', '/stockList');
INSERT INTO `xtmd_menu` VALUES (14, '入库清单', 12, 3, 2, 'depositoryIn', 'ckmd-depositoryIn', '2020-02-07 16:30:13', 0, 'ckmd/depositoryIn', '/depositoryIn');
INSERT INTO `xtmd_menu` VALUES (15, '出库清单', 12, 4, 3, 'depositoryOut', 'ckmd-depositoryOut', '2020-02-07 16:30:53', 0, 'ckmd/depositoryOut', '/depositoryOut');
INSERT INTO `xtmd_menu` VALUES (16, '系统管理', 0, 0, 5, 'xtmd', 's-platform', '2020-02-07 16:29:13', 0, NULL, NULL);
INSERT INTO `xtmd_menu` VALUES (17, '员工管理', 16, 1, 0, 'adminList', 'setting', '2020-02-07 16:30:53', 0, 'xtmd/adminList', '/adminList');
INSERT INTO `xtmd_menu` VALUES (18, '角色管理', 16, 1, 1, 'roleLlist', 'xtmd-rolelist', '2020-02-07 16:30:53', 0, 'xtmd/roleList', '/roleList');
INSERT INTO `xtmd_menu` VALUES (19, '部门管理', 16, 1, 2, 'deptList', 'xtmd-deptList', '2020-02-07 16:30:53', 0, 'xtmd/deptList', '/deptList');
INSERT INTO `xtmd_menu` VALUES (20, '系统配置', 16, 1, 3, 'syscfg', 'setting', '2020-02-07 16:30:53', 0, 'xtmd/syscfg', '/syscfg');
INSERT INTO `xtmd_menu` VALUES (21, '仓库列表', 12, 2, 0, 'depository', 'setting', '2023-03-29 13:44:34', 0, 'ckmd/depositoryList', '/depository');
INSERT INTO `xtmd_menu` VALUES (22, '首页', 0, 0, 0, 'home', 's-home', '2023-03-31 16:10:48', 0, NULL, '/');
INSERT INTO `xtmd_menu` VALUES (23, '部门资料', 1, 1, 2, 'dept', 'folder', '2023-03-31 16:14:13', 0, 'jcmd/dept', '/dept');
INSERT INTO `xtmd_menu` VALUES (24, '操作日志', 16, 1, 4, 'operationLog', 'setting', '2026-04-19 16:36:11', 0, 'xtmd/operationLog', '/operationLog');

-- ----------------------------
-- Table structure for xtmd_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_operation_log`;
CREATE TABLE `xtmd_operation_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人用户名',
  `action_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '动作类型:LOGIN/WAVE_CREATE/PICK_FINISH/INBOUND/OUTBOUND',
  `biz_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务类型:AUTH/SALE/INBOUND/OUTBOUND',
  `biz_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务单号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志内容',
  `request_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端IP',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_oplog_create_time`(`create_time`) USING BTREE,
  INDEX `idx_oplog_action_type`(`action_type`) USING BTREE,
  INDEX `idx_oplog_operator`(`operator`) USING BTREE,
  INDEX `idx_oplog_biz_no`(`biz_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 607 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xtmd_operation_log
-- ----------------------------
INSERT INTO `xtmd_operation_log` VALUES (2, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100001', 'Import sale orders batch #1', '/sale/importExcel', 'GET', '192.168.10.2', '2026-04-21 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (3, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100002', 'Create wave task #2', '/sale/createWave', 'POST', '192.168.10.3', '2026-04-21 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (4, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100003', 'Pick scan confirm #3', '/sale/pickScanConfirm', 'GET', '192.168.10.4', '2026-04-21 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (5, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100004', 'Finish picking #4', '/sale/finishPick', 'POST', '192.168.10.5', '2026-04-21 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (6, 'manager', 'INBOUND', 'STOCK', 'BIZ00100005', 'Inbound audit completed #5', '/depositoryIn/checkById', 'GET', '192.168.10.6', '2026-04-21 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (7, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100006', 'Outbound audit completed #6', '/depositoryOut/checkById', 'POST', '192.168.10.7', '2026-04-21 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (8, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100007', 'Manual order status update #7', '/sale/updateStatus', 'GET', '192.168.10.8', '2026-04-21 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (9, 'admin', 'LOGIN', 'AUTH', 'BIZ00100008', 'User login success #8', '/login', 'POST', '192.168.10.9', '2026-04-21 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (10, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'BIZ00100009', 'Import sale orders batch #9', '/sale/importExcel', 'GET', '192.168.10.10', '2026-04-21 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (11, 'manager', 'WAVE_CREATE', 'SALE', 'BIZ00100010', 'Create wave task #10', '/sale/createWave', 'POST', '192.168.10.11', '2026-04-21 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (12, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100011', 'Pick scan confirm #11', '/sale/pickScanConfirm', 'GET', '192.168.10.12', '2026-04-21 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (13, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100012', 'Finish picking #12', '/sale/finishPick', 'POST', '192.168.10.13', '2026-04-21 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (14, 'admin', 'INBOUND', 'STOCK', 'BIZ00100013', 'Inbound audit completed #13', '/depositoryIn/checkById', 'GET', '192.168.10.14', '2026-04-21 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (15, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100014', 'Outbound audit completed #14', '/depositoryOut/checkById', 'POST', '192.168.10.15', '2026-04-21 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (16, 'manager', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100015', 'Manual order status update #15', '/sale/updateStatus', 'GET', '192.168.10.16', '2026-04-21 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (17, 'admin', 'LOGIN', 'AUTH', 'BIZ00100016', 'User login success #16', '/login', 'POST', '192.168.10.17', '2026-04-21 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (18, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100017', 'Import sale orders batch #17', '/sale/importExcel', 'GET', '192.168.10.18', '2026-04-21 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (19, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'BIZ00100018', 'Create wave task #18', '/sale/createWave', 'POST', '192.168.10.19', '2026-04-21 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (20, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100019', 'Pick scan confirm #19', '/sale/pickScanConfirm', 'GET', '192.168.10.20', '2026-04-21 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (21, 'manager', 'PICK_FINISH', 'SALE', 'BIZ00100020', 'Finish picking #20', '/sale/finishPick', 'POST', '192.168.10.21', '2026-04-21 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (22, 'admin', 'INBOUND', 'STOCK', 'BIZ00100021', 'Inbound audit completed #21', '/depositoryIn/checkById', 'GET', '192.168.10.22', '2026-04-21 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (23, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100022', 'Outbound audit completed #22', '/depositoryOut/checkById', 'POST', '192.168.10.23', '2026-04-21 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (24, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100023', 'Manual order status update #23', '/sale/updateStatus', 'GET', '192.168.10.24', '2026-04-21 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (25, 'admin', 'LOGIN', 'AUTH', 'BIZ00100024', 'User login success #24', '/login', 'POST', '192.168.10.25', '2026-04-21 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (26, 'manager', 'ORDER_IMPORT', 'SALE', 'BIZ00100025', 'Import sale orders batch #25', '/sale/importExcel', 'GET', '192.168.10.26', '2026-04-20 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (27, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100026', 'Create wave task #26', '/sale/createWave', 'POST', '192.168.10.27', '2026-04-20 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (28, 'SYSTEM', 'PICK_SCAN', 'SALE', 'BIZ00100027', 'Pick scan confirm #27', '/sale/pickScanConfirm', 'GET', '192.168.10.28', '2026-04-20 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (29, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100028', 'Finish picking #28', '/sale/finishPick', 'POST', '192.168.10.29', '2026-04-20 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (30, 'admin', 'INBOUND', 'STOCK', 'BIZ00100029', 'Inbound audit completed #29', '/depositoryIn/checkById', 'GET', '192.168.10.30', '2026-04-20 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (31, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100030', 'Outbound audit completed #30', '/depositoryOut/checkById', 'POST', '192.168.10.31', '2026-04-20 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (32, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100031', 'Manual order status update #31', '/sale/updateStatus', 'GET', '192.168.10.32', '2026-04-20 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (33, 'admin', 'LOGIN', 'AUTH', 'BIZ00100032', 'User login success #32', '/login', 'POST', '192.168.10.33', '2026-04-20 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (34, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100033', 'Import sale orders batch #33', '/sale/importExcel', 'GET', '192.168.10.34', '2026-04-20 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (35, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100034', 'Create wave task #34', '/sale/createWave', 'POST', '192.168.10.35', '2026-04-20 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (36, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100035', 'Pick scan confirm #35', '/sale/pickScanConfirm', 'GET', '192.168.10.36', '2026-04-20 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (37, 'SYSTEM', 'PICK_FINISH', 'SALE', 'BIZ00100036', 'Finish picking #36', '/sale/finishPick', 'POST', '192.168.10.37', '2026-04-20 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (38, 'admin', 'INBOUND', 'STOCK', 'BIZ00100037', 'Inbound audit completed #37', '/depositoryIn/checkById', 'GET', '192.168.10.38', '2026-04-20 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (39, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100038', 'Outbound audit completed #38', '/depositoryOut/checkById', 'POST', '192.168.10.39', '2026-04-20 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (40, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100039', 'Manual order status update #39', '/sale/updateStatus', 'GET', '192.168.10.40', '2026-04-20 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (41, 'manager', 'LOGIN', 'AUTH', 'BIZ00100040', 'User login success #40', '/login', 'POST', '192.168.10.41', '2026-04-20 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (42, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100041', 'Import sale orders batch #41', '/sale/importExcel', 'GET', '192.168.10.42', '2026-04-20 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (43, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100042', 'Create wave task #42', '/sale/createWave', 'POST', '192.168.10.43', '2026-04-20 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (44, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100043', 'Pick scan confirm #43', '/sale/pickScanConfirm', 'GET', '192.168.10.44', '2026-04-20 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (45, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100044', 'Finish picking #44', '/sale/finishPick', 'POST', '192.168.10.45', '2026-04-20 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (46, 'SYSTEM', 'INBOUND', 'STOCK', 'BIZ00100045', 'Inbound audit completed #45', '/depositoryIn/checkById', 'GET', '192.168.10.46', '2026-04-20 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (47, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100046', 'Outbound audit completed #46', '/depositoryOut/checkById', 'POST', '192.168.10.47', '2026-04-20 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (48, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100047', 'Manual order status update #47', '/sale/updateStatus', 'GET', '192.168.10.48', '2026-04-20 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (49, 'admin', 'LOGIN', 'AUTH', 'BIZ00100048', 'User login success #48', '/login', 'POST', '192.168.10.49', '2026-04-20 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (50, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100049', 'Import sale orders batch #49', '/sale/importExcel', 'GET', '192.168.10.50', '2026-04-19 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (51, 'manager', 'WAVE_CREATE', 'SALE', 'BIZ00100050', 'Create wave task #50', '/sale/createWave', 'POST', '192.168.10.51', '2026-04-19 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (52, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100051', 'Pick scan confirm #51', '/sale/pickScanConfirm', 'GET', '192.168.10.52', '2026-04-19 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (53, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100052', 'Finish picking #52', '/sale/finishPick', 'POST', '192.168.10.53', '2026-04-19 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (54, 'admin', 'INBOUND', 'STOCK', 'BIZ00100053', 'Inbound audit completed #53', '/depositoryIn/checkById', 'GET', '192.168.10.54', '2026-04-19 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (55, 'SYSTEM', 'OUTBOUND', 'STOCK', 'BIZ00100054', 'Outbound audit completed #54', '/depositoryOut/checkById', 'POST', '192.168.10.55', '2026-04-19 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (56, 'manager', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100055', 'Manual order status update #55', '/sale/updateStatus', 'GET', '192.168.10.56', '2026-04-19 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (57, 'admin', 'LOGIN', 'AUTH', 'BIZ00100056', 'User login success #56', '/login', 'POST', '192.168.10.57', '2026-04-19 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (58, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100057', 'Import sale orders batch #57', '/sale/importExcel', 'GET', '192.168.10.58', '2026-04-19 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (59, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100058', 'Create wave task #58', '/sale/createWave', 'POST', '192.168.10.59', '2026-04-19 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (60, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100059', 'Pick scan confirm #59', '/sale/pickScanConfirm', 'GET', '192.168.10.60', '2026-04-19 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (61, 'manager', 'PICK_FINISH', 'SALE', 'BIZ00100060', 'Finish picking #60', '/sale/finishPick', 'POST', '192.168.10.61', '2026-04-19 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (62, 'admin', 'INBOUND', 'STOCK', 'BIZ00100061', 'Inbound audit completed #61', '/depositoryIn/checkById', 'GET', '192.168.10.62', '2026-04-19 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (63, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100062', 'Outbound audit completed #62', '/depositoryOut/checkById', 'POST', '192.168.10.63', '2026-04-19 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (64, 'SYSTEM', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100063', 'Manual order status update #63', '/sale/updateStatus', 'GET', '192.168.10.64', '2026-04-19 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (65, 'admin', 'LOGIN', 'AUTH', 'BIZ00100064', 'User login success #64', '/login', 'POST', '192.168.10.65', '2026-04-19 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (66, 'manager', 'ORDER_IMPORT', 'SALE', 'BIZ00100065', 'Import sale orders batch #65', '/sale/importExcel', 'GET', '192.168.10.66', '2026-04-19 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (67, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100066', 'Create wave task #66', '/sale/createWave', 'POST', '192.168.10.67', '2026-04-19 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (68, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100067', 'Pick scan confirm #67', '/sale/pickScanConfirm', 'GET', '192.168.10.68', '2026-04-19 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (69, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100068', 'Finish picking #68', '/sale/finishPick', 'POST', '192.168.10.69', '2026-04-19 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (70, 'admin', 'INBOUND', 'STOCK', 'BIZ00100069', 'Inbound audit completed #69', '/depositoryIn/checkById', 'GET', '192.168.10.70', '2026-04-19 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (71, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100070', 'Outbound audit completed #70', '/depositoryOut/checkById', 'POST', '192.168.10.71', '2026-04-19 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (72, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100071', 'Manual order status update #71', '/sale/updateStatus', 'GET', '192.168.10.72', '2026-04-19 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (73, 'SYSTEM', 'LOGIN', 'AUTH', 'BIZ00100072', 'User login success #72', '/login', 'POST', '192.168.10.73', '2026-04-19 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (74, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100073', 'Import sale orders batch #73', '/sale/importExcel', 'GET', '192.168.10.74', '2026-04-18 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (75, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100074', 'Create wave task #74', '/sale/createWave', 'POST', '192.168.10.75', '2026-04-18 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (76, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100075', 'Pick scan confirm #75', '/sale/pickScanConfirm', 'GET', '192.168.10.76', '2026-04-18 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (77, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100076', 'Finish picking #76', '/sale/finishPick', 'POST', '192.168.10.77', '2026-04-18 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (78, 'admin', 'INBOUND', 'STOCK', 'BIZ00100077', 'Inbound audit completed #77', '/depositoryIn/checkById', 'GET', '192.168.10.78', '2026-04-18 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (79, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100078', 'Outbound audit completed #78', '/depositoryOut/checkById', 'POST', '192.168.10.79', '2026-04-18 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (80, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100079', 'Manual order status update #79', '/sale/updateStatus', 'GET', '192.168.10.80', '2026-04-18 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (81, 'manager', 'LOGIN', 'AUTH', 'BIZ00100080', 'User login success #80', '/login', 'POST', '192.168.10.81', '2026-04-18 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (82, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'BIZ00100081', 'Import sale orders batch #81', '/sale/importExcel', 'GET', '192.168.10.82', '2026-04-18 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (83, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100082', 'Create wave task #82', '/sale/createWave', 'POST', '192.168.10.83', '2026-04-18 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (84, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100083', 'Pick scan confirm #83', '/sale/pickScanConfirm', 'GET', '192.168.10.84', '2026-04-18 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (85, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100084', 'Finish picking #84', '/sale/finishPick', 'POST', '192.168.10.85', '2026-04-18 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (86, 'manager', 'INBOUND', 'STOCK', 'BIZ00100085', 'Inbound audit completed #85', '/depositoryIn/checkById', 'GET', '192.168.10.86', '2026-04-18 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (87, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100086', 'Outbound audit completed #86', '/depositoryOut/checkById', 'POST', '192.168.10.87', '2026-04-18 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (88, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100087', 'Manual order status update #87', '/sale/updateStatus', 'GET', '192.168.10.88', '2026-04-18 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (89, 'admin', 'LOGIN', 'AUTH', 'BIZ00100088', 'User login success #88', '/login', 'POST', '192.168.10.89', '2026-04-18 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (90, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100089', 'Import sale orders batch #89', '/sale/importExcel', 'GET', '192.168.10.90', '2026-04-18 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (91, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'BIZ00100090', 'Create wave task #90', '/sale/createWave', 'POST', '192.168.10.91', '2026-04-18 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (92, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100091', 'Pick scan confirm #91', '/sale/pickScanConfirm', 'GET', '192.168.10.92', '2026-04-18 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (93, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100092', 'Finish picking #92', '/sale/finishPick', 'POST', '192.168.10.93', '2026-04-18 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (94, 'admin', 'INBOUND', 'STOCK', 'BIZ00100093', 'Inbound audit completed #93', '/depositoryIn/checkById', 'GET', '192.168.10.94', '2026-04-18 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (95, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100094', 'Outbound audit completed #94', '/depositoryOut/checkById', 'POST', '192.168.10.95', '2026-04-18 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (96, 'manager', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100095', 'Manual order status update #95', '/sale/updateStatus', 'GET', '192.168.10.96', '2026-04-18 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (97, 'admin', 'LOGIN', 'AUTH', 'BIZ00100096', 'User login success #96', '/login', 'POST', '192.168.10.97', '2026-04-18 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (98, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100097', 'Import sale orders batch #97', '/sale/importExcel', 'GET', '192.168.10.98', '2026-04-17 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (99, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100098', 'Create wave task #98', '/sale/createWave', 'POST', '192.168.10.99', '2026-04-17 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (100, 'SYSTEM', 'PICK_SCAN', 'SALE', 'BIZ00100099', 'Pick scan confirm #99', '/sale/pickScanConfirm', 'GET', '192.168.10.100', '2026-04-17 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (101, 'manager', 'PICK_FINISH', 'SALE', 'BIZ00100100', 'Finish picking #100', '/sale/finishPick', 'POST', '192.168.10.101', '2026-04-17 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (102, 'admin', 'INBOUND', 'STOCK', 'BIZ00100101', 'Inbound audit completed #101', '/depositoryIn/checkById', 'GET', '192.168.10.102', '2026-04-17 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (103, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100102', 'Outbound audit completed #102', '/depositoryOut/checkById', 'POST', '192.168.10.103', '2026-04-17 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (104, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100103', 'Manual order status update #103', '/sale/updateStatus', 'GET', '192.168.10.104', '2026-04-17 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (105, 'admin', 'LOGIN', 'AUTH', 'BIZ00100104', 'User login success #104', '/login', 'POST', '192.168.10.105', '2026-04-17 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (106, 'manager', 'ORDER_IMPORT', 'SALE', 'BIZ00100105', 'Import sale orders batch #105', '/sale/importExcel', 'GET', '192.168.10.106', '2026-04-17 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (107, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100106', 'Create wave task #106', '/sale/createWave', 'POST', '192.168.10.107', '2026-04-17 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (108, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100107', 'Pick scan confirm #107', '/sale/pickScanConfirm', 'GET', '192.168.10.108', '2026-04-17 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (109, 'SYSTEM', 'PICK_FINISH', 'SALE', 'BIZ00100108', 'Finish picking #108', '/sale/finishPick', 'POST', '192.168.10.109', '2026-04-17 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (110, 'admin', 'INBOUND', 'STOCK', 'BIZ00100109', 'Inbound audit completed #109', '/depositoryIn/checkById', 'GET', '192.168.10.110', '2026-04-17 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (111, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100110', 'Outbound audit completed #110', '/depositoryOut/checkById', 'POST', '192.168.10.111', '2026-04-17 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (112, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100111', 'Manual order status update #111', '/sale/updateStatus', 'GET', '192.168.10.112', '2026-04-17 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (113, 'admin', 'LOGIN', 'AUTH', 'BIZ00100112', 'User login success #112', '/login', 'POST', '192.168.10.113', '2026-04-17 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (114, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100113', 'Import sale orders batch #113', '/sale/importExcel', 'GET', '192.168.10.114', '2026-04-17 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (115, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100114', 'Create wave task #114', '/sale/createWave', 'POST', '192.168.10.115', '2026-04-17 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (116, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100115', 'Pick scan confirm #115', '/sale/pickScanConfirm', 'GET', '192.168.10.116', '2026-04-17 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (117, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100116', 'Finish picking #116', '/sale/finishPick', 'POST', '192.168.10.117', '2026-04-17 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (118, 'SYSTEM', 'INBOUND', 'STOCK', 'BIZ00100117', 'Inbound audit completed #117', '/depositoryIn/checkById', 'GET', '192.168.10.118', '2026-04-17 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (119, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100118', 'Outbound audit completed #118', '/depositoryOut/checkById', 'POST', '192.168.10.119', '2026-04-17 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (120, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100119', 'Manual order status update #119', '/sale/updateStatus', 'GET', '192.168.10.120', '2026-04-17 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (121, 'manager', 'LOGIN', 'AUTH', 'BIZ00100120', 'User login success #120', '/login', 'POST', '192.168.10.121', '2026-04-17 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (122, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100121', 'Import sale orders batch #121', '/sale/importExcel', 'GET', '192.168.10.122', '2026-04-16 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (123, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100122', 'Create wave task #122', '/sale/createWave', 'POST', '192.168.10.123', '2026-04-16 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (124, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100123', 'Pick scan confirm #123', '/sale/pickScanConfirm', 'GET', '192.168.10.124', '2026-04-16 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (125, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100124', 'Finish picking #124', '/sale/finishPick', 'POST', '192.168.10.125', '2026-04-16 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (126, 'manager', 'INBOUND', 'STOCK', 'BIZ00100125', 'Inbound audit completed #125', '/depositoryIn/checkById', 'GET', '192.168.10.126', '2026-04-16 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (127, 'SYSTEM', 'OUTBOUND', 'STOCK', 'BIZ00100126', 'Outbound audit completed #126', '/depositoryOut/checkById', 'POST', '192.168.10.127', '2026-04-16 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (128, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100127', 'Manual order status update #127', '/sale/updateStatus', 'GET', '192.168.10.128', '2026-04-16 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (129, 'admin', 'LOGIN', 'AUTH', 'BIZ00100128', 'User login success #128', '/login', 'POST', '192.168.10.129', '2026-04-16 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (130, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100129', 'Import sale orders batch #129', '/sale/importExcel', 'GET', '192.168.10.130', '2026-04-16 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (131, 'manager', 'WAVE_CREATE', 'SALE', 'BIZ00100130', 'Create wave task #130', '/sale/createWave', 'POST', '192.168.10.131', '2026-04-16 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (132, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100131', 'Pick scan confirm #131', '/sale/pickScanConfirm', 'GET', '192.168.10.132', '2026-04-16 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (133, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100132', 'Finish picking #132', '/sale/finishPick', 'POST', '192.168.10.133', '2026-04-16 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (134, 'admin', 'INBOUND', 'STOCK', 'BIZ00100133', 'Inbound audit completed #133', '/depositoryIn/checkById', 'GET', '192.168.10.134', '2026-04-16 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (135, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100134', 'Outbound audit completed #134', '/depositoryOut/checkById', 'POST', '192.168.10.135', '2026-04-16 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (136, 'SYSTEM', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100135', 'Manual order status update #135', '/sale/updateStatus', 'GET', '192.168.10.136', '2026-04-16 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (137, 'admin', 'LOGIN', 'AUTH', 'BIZ00100136', 'User login success #136', '/login', 'POST', '192.168.10.137', '2026-04-16 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (138, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100137', 'Import sale orders batch #137', '/sale/importExcel', 'GET', '192.168.10.138', '2026-04-16 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (139, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100138', 'Create wave task #138', '/sale/createWave', 'POST', '192.168.10.139', '2026-04-16 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (140, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100139', 'Pick scan confirm #139', '/sale/pickScanConfirm', 'GET', '192.168.10.140', '2026-04-16 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (141, 'manager', 'PICK_FINISH', 'SALE', 'BIZ00100140', 'Finish picking #140', '/sale/finishPick', 'POST', '192.168.10.141', '2026-04-16 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (142, 'admin', 'INBOUND', 'STOCK', 'BIZ00100141', 'Inbound audit completed #141', '/depositoryIn/checkById', 'GET', '192.168.10.142', '2026-04-16 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (143, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100142', 'Outbound audit completed #142', '/depositoryOut/checkById', 'POST', '192.168.10.143', '2026-04-16 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (144, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100143', 'Manual order status update #143', '/sale/updateStatus', 'GET', '192.168.10.144', '2026-04-16 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (145, 'SYSTEM', 'LOGIN', 'AUTH', 'BIZ00100144', 'User login success #144', '/login', 'POST', '192.168.10.145', '2026-04-16 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (146, 'manager', 'ORDER_IMPORT', 'SALE', 'BIZ00100145', 'Import sale orders batch #145', '/sale/importExcel', 'GET', '192.168.10.146', '2026-04-15 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (147, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100146', 'Create wave task #146', '/sale/createWave', 'POST', '192.168.10.147', '2026-04-15 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (148, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100147', 'Pick scan confirm #147', '/sale/pickScanConfirm', 'GET', '192.168.10.148', '2026-04-15 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (149, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100148', 'Finish picking #148', '/sale/finishPick', 'POST', '192.168.10.149', '2026-04-15 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (150, 'admin', 'INBOUND', 'STOCK', 'BIZ00100149', 'Inbound audit completed #149', '/depositoryIn/checkById', 'GET', '192.168.10.150', '2026-04-15 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (151, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100150', 'Outbound audit completed #150', '/depositoryOut/checkById', 'POST', '192.168.10.151', '2026-04-15 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (152, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100151', 'Manual order status update #151', '/sale/updateStatus', 'GET', '192.168.10.152', '2026-04-15 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (153, 'admin', 'LOGIN', 'AUTH', 'BIZ00100152', 'User login success #152', '/login', 'POST', '192.168.10.153', '2026-04-15 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (154, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'BIZ00100153', 'Import sale orders batch #153', '/sale/importExcel', 'GET', '192.168.10.154', '2026-04-15 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (155, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100154', 'Create wave task #154', '/sale/createWave', 'POST', '192.168.10.155', '2026-04-15 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (156, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100155', 'Pick scan confirm #155', '/sale/pickScanConfirm', 'GET', '192.168.10.156', '2026-04-15 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (157, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100156', 'Finish picking #156', '/sale/finishPick', 'POST', '192.168.10.157', '2026-04-15 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (158, 'admin', 'INBOUND', 'STOCK', 'BIZ00100157', 'Inbound audit completed #157', '/depositoryIn/checkById', 'GET', '192.168.10.158', '2026-04-15 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (159, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100158', 'Outbound audit completed #158', '/depositoryOut/checkById', 'POST', '192.168.10.159', '2026-04-15 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (160, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100159', 'Manual order status update #159', '/sale/updateStatus', 'GET', '192.168.10.160', '2026-04-15 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (161, 'manager', 'LOGIN', 'AUTH', 'BIZ00100160', 'User login success #160', '/login', 'POST', '192.168.10.161', '2026-04-15 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (162, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100161', 'Import sale orders batch #161', '/sale/importExcel', 'GET', '192.168.10.162', '2026-04-15 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (163, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'BIZ00100162', 'Create wave task #162', '/sale/createWave', 'POST', '192.168.10.163', '2026-04-15 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (164, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100163', 'Pick scan confirm #163', '/sale/pickScanConfirm', 'GET', '192.168.10.164', '2026-04-15 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (165, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100164', 'Finish picking #164', '/sale/finishPick', 'POST', '192.168.10.165', '2026-04-15 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (166, 'manager', 'INBOUND', 'STOCK', 'BIZ00100165', 'Inbound audit completed #165', '/depositoryIn/checkById', 'GET', '192.168.10.166', '2026-04-15 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (167, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100166', 'Outbound audit completed #166', '/depositoryOut/checkById', 'POST', '192.168.10.167', '2026-04-15 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (168, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100167', 'Manual order status update #167', '/sale/updateStatus', 'GET', '192.168.10.168', '2026-04-15 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (169, 'admin', 'LOGIN', 'AUTH', 'BIZ00100168', 'User login success #168', '/login', 'POST', '192.168.10.169', '2026-04-15 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (170, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100169', 'Import sale orders batch #169', '/sale/importExcel', 'GET', '192.168.10.170', '2026-04-14 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (171, 'manager', 'WAVE_CREATE', 'SALE', 'BIZ00100170', 'Create wave task #170', '/sale/createWave', 'POST', '192.168.10.171', '2026-04-14 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (172, 'SYSTEM', 'PICK_SCAN', 'SALE', 'BIZ00100171', 'Pick scan confirm #171', '/sale/pickScanConfirm', 'GET', '192.168.10.172', '2026-04-14 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (173, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100172', 'Finish picking #172', '/sale/finishPick', 'POST', '192.168.10.173', '2026-04-14 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (174, 'admin', 'INBOUND', 'STOCK', 'BIZ00100173', 'Inbound audit completed #173', '/depositoryIn/checkById', 'GET', '192.168.10.174', '2026-04-14 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (175, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100174', 'Outbound audit completed #174', '/depositoryOut/checkById', 'POST', '192.168.10.175', '2026-04-14 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (176, 'manager', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100175', 'Manual order status update #175', '/sale/updateStatus', 'GET', '192.168.10.176', '2026-04-14 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (177, 'admin', 'LOGIN', 'AUTH', 'BIZ00100176', 'User login success #176', '/login', 'POST', '192.168.10.177', '2026-04-14 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (178, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100177', 'Import sale orders batch #177', '/sale/importExcel', 'GET', '192.168.10.178', '2026-04-14 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (179, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100178', 'Create wave task #178', '/sale/createWave', 'POST', '192.168.10.179', '2026-04-14 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (180, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100179', 'Pick scan confirm #179', '/sale/pickScanConfirm', 'GET', '192.168.10.180', '2026-04-14 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (181, 'SYSTEM', 'PICK_FINISH', 'SALE', 'BIZ00100180', 'Finish picking #180', '/sale/finishPick', 'POST', '192.168.10.181', '2026-04-14 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (182, 'admin', 'INBOUND', 'STOCK', 'BIZ00100181', 'Inbound audit completed #181', '/depositoryIn/checkById', 'GET', '192.168.10.182', '2026-04-14 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (183, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100182', 'Outbound audit completed #182', '/depositoryOut/checkById', 'POST', '192.168.10.183', '2026-04-14 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (184, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100183', 'Manual order status update #183', '/sale/updateStatus', 'GET', '192.168.10.184', '2026-04-14 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (185, 'admin', 'LOGIN', 'AUTH', 'BIZ00100184', 'User login success #184', '/login', 'POST', '192.168.10.185', '2026-04-14 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (186, 'manager', 'ORDER_IMPORT', 'SALE', 'BIZ00100185', 'Import sale orders batch #185', '/sale/importExcel', 'GET', '192.168.10.186', '2026-04-14 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (187, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100186', 'Create wave task #186', '/sale/createWave', 'POST', '192.168.10.187', '2026-04-14 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (188, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100187', 'Pick scan confirm #187', '/sale/pickScanConfirm', 'GET', '192.168.10.188', '2026-04-14 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (189, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100188', 'Finish picking #188', '/sale/finishPick', 'POST', '192.168.10.189', '2026-04-14 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (190, 'SYSTEM', 'INBOUND', 'STOCK', 'BIZ00100189', 'Inbound audit completed #189', '/depositoryIn/checkById', 'GET', '192.168.10.190', '2026-04-14 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (191, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100190', 'Outbound audit completed #190', '/depositoryOut/checkById', 'POST', '192.168.10.191', '2026-04-14 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (192, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100191', 'Manual order status update #191', '/sale/updateStatus', 'GET', '192.168.10.192', '2026-04-14 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (193, 'admin', 'LOGIN', 'AUTH', 'BIZ00100192', 'User login success #192', '/login', 'POST', '192.168.10.193', '2026-04-14 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (194, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100193', 'Import sale orders batch #193', '/sale/importExcel', 'GET', '192.168.10.194', '2026-04-13 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (195, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100194', 'Create wave task #194', '/sale/createWave', 'POST', '192.168.10.195', '2026-04-13 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (196, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100195', 'Pick scan confirm #195', '/sale/pickScanConfirm', 'GET', '192.168.10.196', '2026-04-13 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (197, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100196', 'Finish picking #196', '/sale/finishPick', 'POST', '192.168.10.197', '2026-04-13 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (198, 'admin', 'INBOUND', 'STOCK', 'BIZ00100197', 'Inbound audit completed #197', '/depositoryIn/checkById', 'GET', '192.168.10.198', '2026-04-13 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (199, 'SYSTEM', 'OUTBOUND', 'STOCK', 'BIZ00100198', 'Outbound audit completed #198', '/depositoryOut/checkById', 'POST', '192.168.10.199', '2026-04-13 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (200, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100199', 'Manual order status update #199', '/sale/updateStatus', 'GET', '192.168.10.200', '2026-04-13 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (201, 'manager', 'LOGIN', 'AUTH', 'BIZ00100200', 'User login success #200', '/login', 'POST', '192.168.10.1', '2026-04-13 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (202, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100201', 'Import sale orders batch #201', '/sale/importExcel', 'GET', '192.168.10.2', '2026-04-13 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (203, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100202', 'Create wave task #202', '/sale/createWave', 'POST', '192.168.10.3', '2026-04-13 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (204, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100203', 'Pick scan confirm #203', '/sale/pickScanConfirm', 'GET', '192.168.10.4', '2026-04-13 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (205, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100204', 'Finish picking #204', '/sale/finishPick', 'POST', '192.168.10.5', '2026-04-13 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (206, 'manager', 'INBOUND', 'STOCK', 'BIZ00100205', 'Inbound audit completed #205', '/depositoryIn/checkById', 'GET', '192.168.10.6', '2026-04-13 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (207, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100206', 'Outbound audit completed #206', '/depositoryOut/checkById', 'POST', '192.168.10.7', '2026-04-13 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (208, 'SYSTEM', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100207', 'Manual order status update #207', '/sale/updateStatus', 'GET', '192.168.10.8', '2026-04-13 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (209, 'admin', 'LOGIN', 'AUTH', 'BIZ00100208', 'User login success #208', '/login', 'POST', '192.168.10.9', '2026-04-13 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (210, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100209', 'Import sale orders batch #209', '/sale/importExcel', 'GET', '192.168.10.10', '2026-04-13 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (211, 'manager', 'WAVE_CREATE', 'SALE', 'BIZ00100210', 'Create wave task #210', '/sale/createWave', 'POST', '192.168.10.11', '2026-04-13 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (212, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100211', 'Pick scan confirm #211', '/sale/pickScanConfirm', 'GET', '192.168.10.12', '2026-04-13 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (213, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100212', 'Finish picking #212', '/sale/finishPick', 'POST', '192.168.10.13', '2026-04-13 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (214, 'admin', 'INBOUND', 'STOCK', 'BIZ00100213', 'Inbound audit completed #213', '/depositoryIn/checkById', 'GET', '192.168.10.14', '2026-04-13 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (215, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100214', 'Outbound audit completed #214', '/depositoryOut/checkById', 'POST', '192.168.10.15', '2026-04-13 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (216, 'manager', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100215', 'Manual order status update #215', '/sale/updateStatus', 'GET', '192.168.10.16', '2026-04-13 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (217, 'SYSTEM', 'LOGIN', 'AUTH', 'BIZ00100216', 'User login success #216', '/login', 'POST', '192.168.10.17', '2026-04-13 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (218, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100217', 'Import sale orders batch #217', '/sale/importExcel', 'GET', '192.168.10.18', '2026-04-12 23:12:00');
INSERT INTO `xtmd_operation_log` VALUES (219, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100218', 'Create wave task #218', '/sale/createWave', 'POST', '192.168.10.19', '2026-04-12 22:12:00');
INSERT INTO `xtmd_operation_log` VALUES (220, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100219', 'Pick scan confirm #219', '/sale/pickScanConfirm', 'GET', '192.168.10.20', '2026-04-12 21:12:00');
INSERT INTO `xtmd_operation_log` VALUES (221, 'manager', 'PICK_FINISH', 'SALE', 'BIZ00100220', 'Finish picking #220', '/sale/finishPick', 'POST', '192.168.10.21', '2026-04-12 20:12:00');
INSERT INTO `xtmd_operation_log` VALUES (222, 'admin', 'INBOUND', 'STOCK', 'BIZ00100221', 'Inbound audit completed #221', '/depositoryIn/checkById', 'GET', '192.168.10.22', '2026-04-12 19:12:00');
INSERT INTO `xtmd_operation_log` VALUES (223, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100222', 'Outbound audit completed #222', '/depositoryOut/checkById', 'POST', '192.168.10.23', '2026-04-12 18:12:00');
INSERT INTO `xtmd_operation_log` VALUES (224, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100223', 'Manual order status update #223', '/sale/updateStatus', 'GET', '192.168.10.24', '2026-04-12 17:12:00');
INSERT INTO `xtmd_operation_log` VALUES (225, 'admin', 'LOGIN', 'AUTH', 'BIZ00100224', 'User login success #224', '/login', 'POST', '192.168.10.25', '2026-04-12 16:12:00');
INSERT INTO `xtmd_operation_log` VALUES (226, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'BIZ00100225', 'Import sale orders batch #225', '/sale/importExcel', 'GET', '192.168.10.26', '2026-04-12 15:12:00');
INSERT INTO `xtmd_operation_log` VALUES (227, 'admin', 'WAVE_CREATE', 'SALE', 'BIZ00100226', 'Create wave task #226', '/sale/createWave', 'POST', '192.168.10.27', '2026-04-12 14:12:00');
INSERT INTO `xtmd_operation_log` VALUES (228, 'admin', 'PICK_SCAN', 'SALE', 'BIZ00100227', 'Pick scan confirm #227', '/sale/pickScanConfirm', 'GET', '192.168.10.28', '2026-04-12 13:12:00');
INSERT INTO `xtmd_operation_log` VALUES (229, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100228', 'Finish picking #228', '/sale/finishPick', 'POST', '192.168.10.29', '2026-04-12 12:12:00');
INSERT INTO `xtmd_operation_log` VALUES (230, 'admin', 'INBOUND', 'STOCK', 'BIZ00100229', 'Inbound audit completed #229', '/depositoryIn/checkById', 'GET', '192.168.10.30', '2026-04-12 11:12:00');
INSERT INTO `xtmd_operation_log` VALUES (231, 'manager', 'OUTBOUND', 'STOCK', 'BIZ00100230', 'Outbound audit completed #230', '/depositoryOut/checkById', 'POST', '192.168.10.31', '2026-04-12 10:12:00');
INSERT INTO `xtmd_operation_log` VALUES (232, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100231', 'Manual order status update #231', '/sale/updateStatus', 'GET', '192.168.10.32', '2026-04-12 09:12:00');
INSERT INTO `xtmd_operation_log` VALUES (233, 'admin', 'LOGIN', 'AUTH', 'BIZ00100232', 'User login success #232', '/login', 'POST', '192.168.10.33', '2026-04-12 08:12:00');
INSERT INTO `xtmd_operation_log` VALUES (234, 'admin', 'ORDER_IMPORT', 'SALE', 'BIZ00100233', 'Import sale orders batch #233', '/sale/importExcel', 'GET', '192.168.10.34', '2026-04-12 07:12:00');
INSERT INTO `xtmd_operation_log` VALUES (235, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'BIZ00100234', 'Create wave task #234', '/sale/createWave', 'POST', '192.168.10.35', '2026-04-12 06:12:00');
INSERT INTO `xtmd_operation_log` VALUES (236, 'manager', 'PICK_SCAN', 'SALE', 'BIZ00100235', 'Pick scan confirm #235', '/sale/pickScanConfirm', 'GET', '192.168.10.36', '2026-04-12 05:12:00');
INSERT INTO `xtmd_operation_log` VALUES (237, 'admin', 'PICK_FINISH', 'SALE', 'BIZ00100236', 'Finish picking #236', '/sale/finishPick', 'POST', '192.168.10.37', '2026-04-12 04:12:00');
INSERT INTO `xtmd_operation_log` VALUES (238, 'admin', 'INBOUND', 'STOCK', 'BIZ00100237', 'Inbound audit completed #237', '/depositoryIn/checkById', 'GET', '192.168.10.38', '2026-04-12 03:12:00');
INSERT INTO `xtmd_operation_log` VALUES (239, 'admin', 'OUTBOUND', 'STOCK', 'BIZ00100238', 'Outbound audit completed #238', '/depositoryOut/checkById', 'POST', '192.168.10.39', '2026-04-12 02:12:00');
INSERT INTO `xtmd_operation_log` VALUES (240, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'BIZ00100239', 'Manual order status update #239', '/sale/updateStatus', 'GET', '192.168.10.40', '2026-04-12 01:12:00');
INSERT INTO `xtmd_operation_log` VALUES (241, 'manager', 'LOGIN', 'AUTH', 'BIZ00100240', 'User login success #240', '/login', 'POST', '192.168.10.41', '2026-04-12 00:12:00');
INSERT INTO `xtmd_operation_log` VALUES (257, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200001', 'Demo event #1', '/sale/importExcel', 'GET', '10.20.30.2', '2026-04-20 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (258, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200002', 'Demo event #2', '/sale/createWave', 'POST', '10.20.30.3', '2026-04-20 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (259, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200003', 'Demo event #3', '/sale/pickScanConfirm', 'GET', '10.20.30.4', '2026-04-20 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (260, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200004', 'Demo event #4', '/sale/finishPick', 'POST', '10.20.30.5', '2026-04-20 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (261, 'admin', 'INBOUND', 'STOCK', 'DEMO00200005', 'Demo event #5', '/depositoryIn/checkById', 'GET', '10.20.30.6', '2026-04-20 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (262, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200006', 'Demo event #6', '/depositoryOut/checkById', 'POST', '10.20.30.7', '2026-04-20 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (263, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200007', 'Demo event #7', '/sale/updateStatus', 'GET', '10.20.30.8', '2026-04-20 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (264, 'manager', 'LOGIN', 'AUTH', 'DEMO00200008', 'Demo event #8', '/login', 'POST', '10.20.30.9', '2026-04-20 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (265, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'DEMO00200009', 'Demo event #9', '/sale/importExcel', 'GET', '10.20.30.10', '2026-04-20 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (266, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200010', 'Demo event #10', '/sale/createWave', 'POST', '10.20.30.11', '2026-04-20 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (267, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200011', 'Demo event #11', '/sale/pickScanConfirm', 'GET', '10.20.30.12', '2026-04-20 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (268, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200012', 'Demo event #12', '/sale/finishPick', 'POST', '10.20.30.13', '2026-04-20 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (269, 'admin', 'INBOUND', 'STOCK', 'DEMO00200013', 'Demo event #13', '/depositoryIn/checkById', 'GET', '10.20.30.14', '2026-04-20 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (270, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200014', 'Demo event #14', '/depositoryOut/checkById', 'POST', '10.20.30.15', '2026-04-20 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (271, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200015', 'Demo event #15', '/sale/updateStatus', 'GET', '10.20.30.16', '2026-04-20 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (272, 'manager', 'LOGIN', 'AUTH', 'DEMO00200016', 'Demo event #16', '/login', 'POST', '10.20.30.17', '2026-04-20 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (273, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200017', 'Demo event #17', '/sale/importExcel', 'GET', '10.20.30.18', '2026-04-20 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (274, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'DEMO00200018', 'Demo event #18', '/sale/createWave', 'POST', '10.20.30.19', '2026-04-20 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (275, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200019', 'Demo event #19', '/sale/pickScanConfirm', 'GET', '10.20.30.20', '2026-04-20 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (276, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200020', 'Demo event #20', '/sale/finishPick', 'POST', '10.20.30.21', '2026-04-20 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (277, 'admin', 'INBOUND', 'STOCK', 'DEMO00200021', 'Demo event #21', '/depositoryIn/checkById', 'GET', '10.20.30.22', '2026-04-20 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (278, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200022', 'Demo event #22', '/depositoryOut/checkById', 'POST', '10.20.30.23', '2026-04-20 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (279, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200023', 'Demo event #23', '/sale/updateStatus', 'GET', '10.20.30.24', '2026-04-20 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (280, 'manager', 'LOGIN', 'AUTH', 'DEMO00200024', 'Demo event #24', '/login', 'POST', '10.20.30.25', '2026-04-20 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (281, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200025', 'Demo event #25', '/sale/importExcel', 'GET', '10.20.30.26', '2026-04-19 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (282, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200026', 'Demo event #26', '/sale/createWave', 'POST', '10.20.30.27', '2026-04-19 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (283, 'SYSTEM', 'PICK_SCAN', 'SALE', 'DEMO00200027', 'Demo event #27', '/sale/pickScanConfirm', 'GET', '10.20.30.28', '2026-04-19 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (284, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200028', 'Demo event #28', '/sale/finishPick', 'POST', '10.20.30.29', '2026-04-19 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (285, 'admin', 'INBOUND', 'STOCK', 'DEMO00200029', 'Demo event #29', '/depositoryIn/checkById', 'GET', '10.20.30.30', '2026-04-19 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (286, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200030', 'Demo event #30', '/depositoryOut/checkById', 'POST', '10.20.30.31', '2026-04-19 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (287, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200031', 'Demo event #31', '/sale/updateStatus', 'GET', '10.20.30.32', '2026-04-19 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (288, 'manager', 'LOGIN', 'AUTH', 'DEMO00200032', 'Demo event #32', '/login', 'POST', '10.20.30.33', '2026-04-19 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (289, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200033', 'Demo event #33', '/sale/importExcel', 'GET', '10.20.30.34', '2026-04-19 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (290, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200034', 'Demo event #34', '/sale/createWave', 'POST', '10.20.30.35', '2026-04-19 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (291, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200035', 'Demo event #35', '/sale/pickScanConfirm', 'GET', '10.20.30.36', '2026-04-19 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (292, 'SYSTEM', 'PICK_FINISH', 'SALE', 'DEMO00200036', 'Demo event #36', '/sale/finishPick', 'POST', '10.20.30.37', '2026-04-19 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (293, 'admin', 'INBOUND', 'STOCK', 'DEMO00200037', 'Demo event #37', '/depositoryIn/checkById', 'GET', '10.20.30.38', '2026-04-19 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (294, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200038', 'Demo event #38', '/depositoryOut/checkById', 'POST', '10.20.30.39', '2026-04-19 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (295, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200039', 'Demo event #39', '/sale/updateStatus', 'GET', '10.20.30.40', '2026-04-19 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (296, 'manager', 'LOGIN', 'AUTH', 'DEMO00200040', 'Demo event #40', '/login', 'POST', '10.20.30.41', '2026-04-19 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (297, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200041', 'Demo event #41', '/sale/importExcel', 'GET', '10.20.30.42', '2026-04-19 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (298, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200042', 'Demo event #42', '/sale/createWave', 'POST', '10.20.30.43', '2026-04-19 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (299, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200043', 'Demo event #43', '/sale/pickScanConfirm', 'GET', '10.20.30.44', '2026-04-19 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (300, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200044', 'Demo event #44', '/sale/finishPick', 'POST', '10.20.30.45', '2026-04-19 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (301, 'SYSTEM', 'INBOUND', 'STOCK', 'DEMO00200045', 'Demo event #45', '/depositoryIn/checkById', 'GET', '10.20.30.46', '2026-04-19 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (302, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200046', 'Demo event #46', '/depositoryOut/checkById', 'POST', '10.20.30.47', '2026-04-19 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (303, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200047', 'Demo event #47', '/sale/updateStatus', 'GET', '10.20.30.48', '2026-04-19 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (304, 'manager', 'LOGIN', 'AUTH', 'DEMO00200048', 'Demo event #48', '/login', 'POST', '10.20.30.49', '2026-04-19 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (305, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200049', 'Demo event #49', '/sale/importExcel', 'GET', '10.20.30.50', '2026-04-18 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (306, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200050', 'Demo event #50', '/sale/createWave', 'POST', '10.20.30.51', '2026-04-18 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (307, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200051', 'Demo event #51', '/sale/pickScanConfirm', 'GET', '10.20.30.52', '2026-04-18 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (308, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200052', 'Demo event #52', '/sale/finishPick', 'POST', '10.20.30.53', '2026-04-18 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (309, 'admin', 'INBOUND', 'STOCK', 'DEMO00200053', 'Demo event #53', '/depositoryIn/checkById', 'GET', '10.20.30.54', '2026-04-18 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (310, 'SYSTEM', 'OUTBOUND', 'STOCK', 'DEMO00200054', 'Demo event #54', '/depositoryOut/checkById', 'POST', '10.20.30.55', '2026-04-18 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (311, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200055', 'Demo event #55', '/sale/updateStatus', 'GET', '10.20.30.56', '2026-04-18 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (312, 'manager', 'LOGIN', 'AUTH', 'DEMO00200056', 'Demo event #56', '/login', 'POST', '10.20.30.57', '2026-04-18 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (313, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200057', 'Demo event #57', '/sale/importExcel', 'GET', '10.20.30.58', '2026-04-18 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (314, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200058', 'Demo event #58', '/sale/createWave', 'POST', '10.20.30.59', '2026-04-18 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (315, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200059', 'Demo event #59', '/sale/pickScanConfirm', 'GET', '10.20.30.60', '2026-04-18 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (316, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200060', 'Demo event #60', '/sale/finishPick', 'POST', '10.20.30.61', '2026-04-18 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (317, 'admin', 'INBOUND', 'STOCK', 'DEMO00200061', 'Demo event #61', '/depositoryIn/checkById', 'GET', '10.20.30.62', '2026-04-18 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (318, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200062', 'Demo event #62', '/depositoryOut/checkById', 'POST', '10.20.30.63', '2026-04-18 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (319, 'SYSTEM', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200063', 'Demo event #63', '/sale/updateStatus', 'GET', '10.20.30.64', '2026-04-18 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (320, 'manager', 'LOGIN', 'AUTH', 'DEMO00200064', 'Demo event #64', '/login', 'POST', '10.20.30.65', '2026-04-18 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (321, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200065', 'Demo event #65', '/sale/importExcel', 'GET', '10.20.30.66', '2026-04-18 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (322, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200066', 'Demo event #66', '/sale/createWave', 'POST', '10.20.30.67', '2026-04-18 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (323, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200067', 'Demo event #67', '/sale/pickScanConfirm', 'GET', '10.20.30.68', '2026-04-18 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (324, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200068', 'Demo event #68', '/sale/finishPick', 'POST', '10.20.30.69', '2026-04-18 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (325, 'admin', 'INBOUND', 'STOCK', 'DEMO00200069', 'Demo event #69', '/depositoryIn/checkById', 'GET', '10.20.30.70', '2026-04-18 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (326, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200070', 'Demo event #70', '/depositoryOut/checkById', 'POST', '10.20.30.71', '2026-04-18 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (327, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200071', 'Demo event #71', '/sale/updateStatus', 'GET', '10.20.30.72', '2026-04-18 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (328, 'SYSTEM', 'LOGIN', 'AUTH', 'DEMO00200072', 'Demo event #72', '/login', 'POST', '10.20.30.73', '2026-04-18 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (329, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200073', 'Demo event #73', '/sale/importExcel', 'GET', '10.20.30.74', '2026-04-17 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (330, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200074', 'Demo event #74', '/sale/createWave', 'POST', '10.20.30.75', '2026-04-17 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (331, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200075', 'Demo event #75', '/sale/pickScanConfirm', 'GET', '10.20.30.76', '2026-04-17 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (332, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200076', 'Demo event #76', '/sale/finishPick', 'POST', '10.20.30.77', '2026-04-17 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (333, 'admin', 'INBOUND', 'STOCK', 'DEMO00200077', 'Demo event #77', '/depositoryIn/checkById', 'GET', '10.20.30.78', '2026-04-17 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (334, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200078', 'Demo event #78', '/depositoryOut/checkById', 'POST', '10.20.30.79', '2026-04-17 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (335, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200079', 'Demo event #79', '/sale/updateStatus', 'GET', '10.20.30.80', '2026-04-17 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (336, 'manager', 'LOGIN', 'AUTH', 'DEMO00200080', 'Demo event #80', '/login', 'POST', '10.20.30.81', '2026-04-17 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (337, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'DEMO00200081', 'Demo event #81', '/sale/importExcel', 'GET', '10.20.30.82', '2026-04-17 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (338, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200082', 'Demo event #82', '/sale/createWave', 'POST', '10.20.30.83', '2026-04-17 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (339, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200083', 'Demo event #83', '/sale/pickScanConfirm', 'GET', '10.20.30.84', '2026-04-17 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (340, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200084', 'Demo event #84', '/sale/finishPick', 'POST', '10.20.30.85', '2026-04-17 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (341, 'admin', 'INBOUND', 'STOCK', 'DEMO00200085', 'Demo event #85', '/depositoryIn/checkById', 'GET', '10.20.30.86', '2026-04-17 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (342, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200086', 'Demo event #86', '/depositoryOut/checkById', 'POST', '10.20.30.87', '2026-04-17 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (343, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200087', 'Demo event #87', '/sale/updateStatus', 'GET', '10.20.30.88', '2026-04-17 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (344, 'manager', 'LOGIN', 'AUTH', 'DEMO00200088', 'Demo event #88', '/login', 'POST', '10.20.30.89', '2026-04-17 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (345, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200089', 'Demo event #89', '/sale/importExcel', 'GET', '10.20.30.90', '2026-04-17 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (346, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'DEMO00200090', 'Demo event #90', '/sale/createWave', 'POST', '10.20.30.91', '2026-04-17 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (347, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200091', 'Demo event #91', '/sale/pickScanConfirm', 'GET', '10.20.30.92', '2026-04-17 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (348, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200092', 'Demo event #92', '/sale/finishPick', 'POST', '10.20.30.93', '2026-04-17 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (349, 'admin', 'INBOUND', 'STOCK', 'DEMO00200093', 'Demo event #93', '/depositoryIn/checkById', 'GET', '10.20.30.94', '2026-04-17 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (350, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200094', 'Demo event #94', '/depositoryOut/checkById', 'POST', '10.20.30.95', '2026-04-17 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (351, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200095', 'Demo event #95', '/sale/updateStatus', 'GET', '10.20.30.96', '2026-04-17 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (352, 'manager', 'LOGIN', 'AUTH', 'DEMO00200096', 'Demo event #96', '/login', 'POST', '10.20.30.97', '2026-04-17 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (353, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200097', 'Demo event #97', '/sale/importExcel', 'GET', '10.20.30.98', '2026-04-16 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (354, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200098', 'Demo event #98', '/sale/createWave', 'POST', '10.20.30.99', '2026-04-16 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (355, 'SYSTEM', 'PICK_SCAN', 'SALE', 'DEMO00200099', 'Demo event #99', '/sale/pickScanConfirm', 'GET', '10.20.30.100', '2026-04-16 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (356, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200100', 'Demo event #100', '/sale/finishPick', 'POST', '10.20.30.101', '2026-04-16 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (357, 'admin', 'INBOUND', 'STOCK', 'DEMO00200101', 'Demo event #101', '/depositoryIn/checkById', 'GET', '10.20.30.102', '2026-04-16 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (358, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200102', 'Demo event #102', '/depositoryOut/checkById', 'POST', '10.20.30.103', '2026-04-16 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (359, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200103', 'Demo event #103', '/sale/updateStatus', 'GET', '10.20.30.104', '2026-04-16 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (360, 'manager', 'LOGIN', 'AUTH', 'DEMO00200104', 'Demo event #104', '/login', 'POST', '10.20.30.105', '2026-04-16 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (361, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200105', 'Demo event #105', '/sale/importExcel', 'GET', '10.20.30.106', '2026-04-16 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (362, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200106', 'Demo event #106', '/sale/createWave', 'POST', '10.20.30.107', '2026-04-16 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (363, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200107', 'Demo event #107', '/sale/pickScanConfirm', 'GET', '10.20.30.108', '2026-04-16 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (364, 'SYSTEM', 'PICK_FINISH', 'SALE', 'DEMO00200108', 'Demo event #108', '/sale/finishPick', 'POST', '10.20.30.109', '2026-04-16 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (365, 'admin', 'INBOUND', 'STOCK', 'DEMO00200109', 'Demo event #109', '/depositoryIn/checkById', 'GET', '10.20.30.110', '2026-04-16 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (366, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200110', 'Demo event #110', '/depositoryOut/checkById', 'POST', '10.20.30.111', '2026-04-16 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (367, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200111', 'Demo event #111', '/sale/updateStatus', 'GET', '10.20.30.112', '2026-04-16 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (368, 'manager', 'LOGIN', 'AUTH', 'DEMO00200112', 'Demo event #112', '/login', 'POST', '10.20.30.113', '2026-04-16 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (369, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200113', 'Demo event #113', '/sale/importExcel', 'GET', '10.20.30.114', '2026-04-16 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (370, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200114', 'Demo event #114', '/sale/createWave', 'POST', '10.20.30.115', '2026-04-16 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (371, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200115', 'Demo event #115', '/sale/pickScanConfirm', 'GET', '10.20.30.116', '2026-04-16 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (372, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200116', 'Demo event #116', '/sale/finishPick', 'POST', '10.20.30.117', '2026-04-16 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (373, 'SYSTEM', 'INBOUND', 'STOCK', 'DEMO00200117', 'Demo event #117', '/depositoryIn/checkById', 'GET', '10.20.30.118', '2026-04-16 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (374, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200118', 'Demo event #118', '/depositoryOut/checkById', 'POST', '10.20.30.119', '2026-04-16 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (375, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200119', 'Demo event #119', '/sale/updateStatus', 'GET', '10.20.30.120', '2026-04-16 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (376, 'manager', 'LOGIN', 'AUTH', 'DEMO00200120', 'Demo event #120', '/login', 'POST', '10.20.30.121', '2026-04-16 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (377, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200121', 'Demo event #121', '/sale/importExcel', 'GET', '10.20.30.122', '2026-04-15 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (378, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200122', 'Demo event #122', '/sale/createWave', 'POST', '10.20.30.123', '2026-04-15 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (379, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200123', 'Demo event #123', '/sale/pickScanConfirm', 'GET', '10.20.30.124', '2026-04-15 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (380, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200124', 'Demo event #124', '/sale/finishPick', 'POST', '10.20.30.125', '2026-04-15 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (381, 'admin', 'INBOUND', 'STOCK', 'DEMO00200125', 'Demo event #125', '/depositoryIn/checkById', 'GET', '10.20.30.126', '2026-04-15 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (382, 'SYSTEM', 'OUTBOUND', 'STOCK', 'DEMO00200126', 'Demo event #126', '/depositoryOut/checkById', 'POST', '10.20.30.127', '2026-04-15 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (383, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200127', 'Demo event #127', '/sale/updateStatus', 'GET', '10.20.30.128', '2026-04-15 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (384, 'manager', 'LOGIN', 'AUTH', 'DEMO00200128', 'Demo event #128', '/login', 'POST', '10.20.30.129', '2026-04-15 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (385, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200129', 'Demo event #129', '/sale/importExcel', 'GET', '10.20.30.130', '2026-04-15 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (386, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200130', 'Demo event #130', '/sale/createWave', 'POST', '10.20.30.131', '2026-04-15 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (387, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200131', 'Demo event #131', '/sale/pickScanConfirm', 'GET', '10.20.30.132', '2026-04-15 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (388, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200132', 'Demo event #132', '/sale/finishPick', 'POST', '10.20.30.133', '2026-04-15 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (389, 'admin', 'INBOUND', 'STOCK', 'DEMO00200133', 'Demo event #133', '/depositoryIn/checkById', 'GET', '10.20.30.134', '2026-04-15 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (390, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200134', 'Demo event #134', '/depositoryOut/checkById', 'POST', '10.20.30.135', '2026-04-15 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (391, 'SYSTEM', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200135', 'Demo event #135', '/sale/updateStatus', 'GET', '10.20.30.136', '2026-04-15 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (392, 'manager', 'LOGIN', 'AUTH', 'DEMO00200136', 'Demo event #136', '/login', 'POST', '10.20.30.137', '2026-04-15 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (393, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200137', 'Demo event #137', '/sale/importExcel', 'GET', '10.20.30.138', '2026-04-15 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (394, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200138', 'Demo event #138', '/sale/createWave', 'POST', '10.20.30.139', '2026-04-15 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (395, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200139', 'Demo event #139', '/sale/pickScanConfirm', 'GET', '10.20.30.140', '2026-04-15 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (396, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200140', 'Demo event #140', '/sale/finishPick', 'POST', '10.20.30.141', '2026-04-15 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (397, 'admin', 'INBOUND', 'STOCK', 'DEMO00200141', 'Demo event #141', '/depositoryIn/checkById', 'GET', '10.20.30.142', '2026-04-15 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (398, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200142', 'Demo event #142', '/depositoryOut/checkById', 'POST', '10.20.30.143', '2026-04-15 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (399, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200143', 'Demo event #143', '/sale/updateStatus', 'GET', '10.20.30.144', '2026-04-15 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (400, 'SYSTEM', 'LOGIN', 'AUTH', 'DEMO00200144', 'Demo event #144', '/login', 'POST', '10.20.30.145', '2026-04-15 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (401, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200145', 'Demo event #145', '/sale/importExcel', 'GET', '10.20.30.146', '2026-04-14 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (402, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200146', 'Demo event #146', '/sale/createWave', 'POST', '10.20.30.147', '2026-04-14 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (403, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200147', 'Demo event #147', '/sale/pickScanConfirm', 'GET', '10.20.30.148', '2026-04-14 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (404, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200148', 'Demo event #148', '/sale/finishPick', 'POST', '10.20.30.149', '2026-04-14 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (405, 'admin', 'INBOUND', 'STOCK', 'DEMO00200149', 'Demo event #149', '/depositoryIn/checkById', 'GET', '10.20.30.150', '2026-04-14 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (406, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200150', 'Demo event #150', '/depositoryOut/checkById', 'POST', '10.20.30.151', '2026-04-14 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (407, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200151', 'Demo event #151', '/sale/updateStatus', 'GET', '10.20.30.152', '2026-04-14 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (408, 'manager', 'LOGIN', 'AUTH', 'DEMO00200152', 'Demo event #152', '/login', 'POST', '10.20.30.153', '2026-04-14 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (409, 'SYSTEM', 'ORDER_IMPORT', 'SALE', 'DEMO00200153', 'Demo event #153', '/sale/importExcel', 'GET', '10.20.30.154', '2026-04-14 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (410, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200154', 'Demo event #154', '/sale/createWave', 'POST', '10.20.30.155', '2026-04-14 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (411, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200155', 'Demo event #155', '/sale/pickScanConfirm', 'GET', '10.20.30.156', '2026-04-14 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (412, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200156', 'Demo event #156', '/sale/finishPick', 'POST', '10.20.30.157', '2026-04-14 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (413, 'admin', 'INBOUND', 'STOCK', 'DEMO00200157', 'Demo event #157', '/depositoryIn/checkById', 'GET', '10.20.30.158', '2026-04-14 11:17:29');
INSERT INTO `xtmd_operation_log` VALUES (414, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200158', 'Demo event #158', '/depositoryOut/checkById', 'POST', '10.20.30.159', '2026-04-14 10:17:29');
INSERT INTO `xtmd_operation_log` VALUES (415, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200159', 'Demo event #159', '/sale/updateStatus', 'GET', '10.20.30.160', '2026-04-14 09:17:29');
INSERT INTO `xtmd_operation_log` VALUES (416, 'manager', 'LOGIN', 'AUTH', 'DEMO00200160', 'Demo event #160', '/login', 'POST', '10.20.30.161', '2026-04-14 08:17:29');
INSERT INTO `xtmd_operation_log` VALUES (417, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200161', 'Demo event #161', '/sale/importExcel', 'GET', '10.20.30.162', '2026-04-14 07:17:29');
INSERT INTO `xtmd_operation_log` VALUES (418, 'SYSTEM', 'WAVE_CREATE', 'SALE', 'DEMO00200162', 'Demo event #162', '/sale/createWave', 'POST', '10.20.30.163', '2026-04-14 06:17:29');
INSERT INTO `xtmd_operation_log` VALUES (419, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200163', 'Demo event #163', '/sale/pickScanConfirm', 'GET', '10.20.30.164', '2026-04-14 05:17:29');
INSERT INTO `xtmd_operation_log` VALUES (420, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200164', 'Demo event #164', '/sale/finishPick', 'POST', '10.20.30.165', '2026-04-14 04:17:29');
INSERT INTO `xtmd_operation_log` VALUES (421, 'admin', 'INBOUND', 'STOCK', 'DEMO00200165', 'Demo event #165', '/depositoryIn/checkById', 'GET', '10.20.30.166', '2026-04-14 03:17:29');
INSERT INTO `xtmd_operation_log` VALUES (422, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200166', 'Demo event #166', '/depositoryOut/checkById', 'POST', '10.20.30.167', '2026-04-14 02:17:29');
INSERT INTO `xtmd_operation_log` VALUES (423, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200167', 'Demo event #167', '/sale/updateStatus', 'GET', '10.20.30.168', '2026-04-14 01:17:29');
INSERT INTO `xtmd_operation_log` VALUES (424, 'manager', 'LOGIN', 'AUTH', 'DEMO00200168', 'Demo event #168', '/login', 'POST', '10.20.30.169', '2026-04-14 00:17:29');
INSERT INTO `xtmd_operation_log` VALUES (425, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200169', 'Demo event #169', '/sale/importExcel', 'GET', '10.20.30.170', '2026-04-13 23:17:29');
INSERT INTO `xtmd_operation_log` VALUES (426, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200170', 'Demo event #170', '/sale/createWave', 'POST', '10.20.30.171', '2026-04-13 22:17:29');
INSERT INTO `xtmd_operation_log` VALUES (427, 'SYSTEM', 'PICK_SCAN', 'SALE', 'DEMO00200171', 'Demo event #171', '/sale/pickScanConfirm', 'GET', '10.20.30.172', '2026-04-13 21:17:29');
INSERT INTO `xtmd_operation_log` VALUES (428, 'manager', 'PICK_FINISH', 'SALE', 'DEMO00200172', 'Demo event #172', '/sale/finishPick', 'POST', '10.20.30.173', '2026-04-13 20:17:29');
INSERT INTO `xtmd_operation_log` VALUES (429, 'admin', 'INBOUND', 'STOCK', 'DEMO00200173', 'Demo event #173', '/depositoryIn/checkById', 'GET', '10.20.30.174', '2026-04-13 19:17:29');
INSERT INTO `xtmd_operation_log` VALUES (430, 'admin', 'OUTBOUND', 'STOCK', 'DEMO00200174', 'Demo event #174', '/depositoryOut/checkById', 'POST', '10.20.30.175', '2026-04-13 18:17:29');
INSERT INTO `xtmd_operation_log` VALUES (431, 'admin', 'ORDER_STATUS_UPDATE', 'SALE', 'DEMO00200175', 'Demo event #175', '/sale/updateStatus', 'GET', '10.20.30.176', '2026-04-13 17:17:29');
INSERT INTO `xtmd_operation_log` VALUES (432, 'manager', 'LOGIN', 'AUTH', 'DEMO00200176', 'Demo event #176', '/login', 'POST', '10.20.30.177', '2026-04-13 16:17:29');
INSERT INTO `xtmd_operation_log` VALUES (433, 'admin', 'ORDER_IMPORT', 'SALE', 'DEMO00200177', 'Demo event #177', '/sale/importExcel', 'GET', '10.20.30.178', '2026-04-13 15:17:29');
INSERT INTO `xtmd_operation_log` VALUES (434, 'admin', 'WAVE_CREATE', 'SALE', 'DEMO00200178', 'Demo event #178', '/sale/createWave', 'POST', '10.20.30.179', '2026-04-13 14:17:29');
INSERT INTO `xtmd_operation_log` VALUES (435, 'admin', 'PICK_SCAN', 'SALE', 'DEMO00200179', 'Demo event #179', '/sale/pickScanConfirm', 'GET', '10.20.30.180', '2026-04-13 13:17:29');
INSERT INTO `xtmd_operation_log` VALUES (436, 'SYSTEM', 'PICK_FINISH', 'SALE', 'DEMO00200180', 'Demo event #180', '/sale/finishPick', 'POST', '10.20.30.181', '2026-04-13 12:17:29');
INSERT INTO `xtmd_operation_log` VALUES (512, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 00:30:00');
INSERT INTO `xtmd_operation_log` VALUES (513, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 00:30:00');
INSERT INTO `xtmd_operation_log` VALUES (514, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 00:30:00');
INSERT INTO `xtmd_operation_log` VALUES (515, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 00:30:00');
INSERT INTO `xtmd_operation_log` VALUES (516, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 00:30:00');
INSERT INTO `xtmd_operation_log` VALUES (517, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 00:42:54');
INSERT INTO `xtmd_operation_log` VALUES (518, 'admin', 'INBOUND', 'INBOUND', '14', 'Inbound audit completed', '/depositoryIn/checkById', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 00:47:58');
INSERT INTO `xtmd_operation_log` VALUES (519, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:00:00');
INSERT INTO `xtmd_operation_log` VALUES (520, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:00:00');
INSERT INTO `xtmd_operation_log` VALUES (521, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:00:00');
INSERT INTO `xtmd_operation_log` VALUES (522, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:00:00');
INSERT INTO `xtmd_operation_log` VALUES (523, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:00:00');
INSERT INTO `xtmd_operation_log` VALUES (524, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:30:00');
INSERT INTO `xtmd_operation_log` VALUES (525, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:30:00');
INSERT INTO `xtmd_operation_log` VALUES (526, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:30:00');
INSERT INTO `xtmd_operation_log` VALUES (527, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:30:00');
INSERT INTO `xtmd_operation_log` VALUES (528, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 01:30:00');
INSERT INTO `xtmd_operation_log` VALUES (529, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:00:00');
INSERT INTO `xtmd_operation_log` VALUES (530, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:00:00');
INSERT INTO `xtmd_operation_log` VALUES (531, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:00:00');
INSERT INTO `xtmd_operation_log` VALUES (532, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:00:00');
INSERT INTO `xtmd_operation_log` VALUES (533, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:00:00');
INSERT INTO `xtmd_operation_log` VALUES (534, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 02:03:54');
INSERT INTO `xtmd_operation_log` VALUES (535, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 02:08:35');
INSERT INTO `xtmd_operation_log` VALUES (536, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:30:00');
INSERT INTO `xtmd_operation_log` VALUES (537, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:30:00');
INSERT INTO `xtmd_operation_log` VALUES (538, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:30:00');
INSERT INTO `xtmd_operation_log` VALUES (539, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:30:00');
INSERT INTO `xtmd_operation_log` VALUES (540, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 02:30:00');
INSERT INTO `xtmd_operation_log` VALUES (541, 'admin', 'PICK_FINISH', 'SALE', '900000001', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 02:54:36');
INSERT INTO `xtmd_operation_log` VALUES (542, 'admin', 'PICK_FINISH', 'SALE', '900000003', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 02:57:07');
INSERT INTO `xtmd_operation_log` VALUES (543, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:00:00');
INSERT INTO `xtmd_operation_log` VALUES (544, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:00:00');
INSERT INTO `xtmd_operation_log` VALUES (545, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:00:00');
INSERT INTO `xtmd_operation_log` VALUES (546, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:00:00');
INSERT INTO `xtmd_operation_log` VALUES (547, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:00:00');
INSERT INTO `xtmd_operation_log` VALUES (548, 'admin', 'PICK_FINISH', 'SALE', '900000002', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:23:41');
INSERT INTO `xtmd_operation_log` VALUES (549, 'admin', 'WAVE_CREATE', 'SALE', '137694665', 'Create wave and dispatch picking, depository=虚拟仓库A', '/sale/createWave', 'POST', '127.0.0.1', '2026-04-22 03:24:11');
INSERT INTO `xtmd_operation_log` VALUES (550, 'admin', 'PICK_SCAN', 'SALE', '900000039', 'Scan confirm product=雪碧, quantity=1', '/sale/pickScanConfirm', 'POST', '127.0.0.1', '2026-04-22 03:24:11');
INSERT INTO `xtmd_operation_log` VALUES (551, 'admin', 'PICK_FINISH', 'SALE', '900000039', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:24:25');
INSERT INTO `xtmd_operation_log` VALUES (552, 'admin', 'PICK_FINISH', 'SALE', '900000006', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:26:42');
INSERT INTO `xtmd_operation_log` VALUES (553, 'admin', 'PICK_FINISH', 'SALE', '900000014', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:27:48');
INSERT INTO `xtmd_operation_log` VALUES (554, 'admin', 'PICK_FINISH', 'SALE', '900000007', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:29:39');
INSERT INTO `xtmd_operation_log` VALUES (555, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:30:00');
INSERT INTO `xtmd_operation_log` VALUES (556, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:30:00');
INSERT INTO `xtmd_operation_log` VALUES (557, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:30:00');
INSERT INTO `xtmd_operation_log` VALUES (558, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:30:00');
INSERT INTO `xtmd_operation_log` VALUES (559, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 03:30:00');
INSERT INTO `xtmd_operation_log` VALUES (560, 'admin', 'PICK_FINISH', 'SALE', '900000009', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:32:14');
INSERT INTO `xtmd_operation_log` VALUES (561, 'admin', 'PICK_FINISH', 'SALE', '900000010', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:32:37');
INSERT INTO `xtmd_operation_log` VALUES (562, 'admin', 'PICK_FINISH', 'SALE', '900000011', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 03:36:29');
INSERT INTO `xtmd_operation_log` VALUES (563, 'admin', 'PICK_FINISH', 'SALE', '900000029', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 03:39:14');
INSERT INTO `xtmd_operation_log` VALUES (564, 'SYSTEM', 'WAVE_CREATE', 'SALE', '873479332', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 04:00:00');
INSERT INTO `xtmd_operation_log` VALUES (565, 'SYSTEM', 'WAVE_CREATE', 'SALE', '106103464', '定时任务自动生成波次，区域=北京海淀区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 04:00:00');
INSERT INTO `xtmd_operation_log` VALUES (566, 'SYSTEM', 'WAVE_CREATE', 'SALE', '343538883', '定时任务自动生成波次，区域=天河区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 04:00:00');
INSERT INTO `xtmd_operation_log` VALUES (567, 'SYSTEM', 'WAVE_CREATE', 'SALE', '276558819', '定时任务自动生成波次，区域=白云区，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 04:00:00');
INSERT INTO `xtmd_operation_log` VALUES (568, 'SYSTEM', 'WAVE_CREATE', 'SALE', '137694665', '定时任务自动生成波次，区域=东泰五街96，仓库=虚拟仓库A', NULL, NULL, NULL, '2026-04-22 04:00:00');
INSERT INTO `xtmd_operation_log` VALUES (569, 'admin', 'WAVE_CREATE', 'SALE', '900000018', 'Force replan wave, depository=虚拟仓库A', '/sale/createWave', 'POST', '127.0.0.1', '2026-04-22 04:05:40');
INSERT INTO `xtmd_operation_log` VALUES (570, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:14:19');
INSERT INTO `xtmd_operation_log` VALUES (571, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:15:21');
INSERT INTO `xtmd_operation_log` VALUES (572, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:15:35');
INSERT INTO `xtmd_operation_log` VALUES (573, 'admin', 'WAVE_CREATE', 'SALE', '900000021', 'Force replan wave, depository=虚拟仓库A', '/sale/createWave', 'POST', '127.0.0.1', '2026-04-22 04:16:26');
INSERT INTO `xtmd_operation_log` VALUES (574, 'admin', 'PICK_FINISH', 'SALE', '900000021', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 04:16:43');
INSERT INTO `xtmd_operation_log` VALUES (575, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:27:38');
INSERT INTO `xtmd_operation_log` VALUES (576, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:27:57');
INSERT INTO `xtmd_operation_log` VALUES (577, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:29:09');
INSERT INTO `xtmd_operation_log` VALUES (578, 'admin', 'PICK_FINISH', 'SALE', '900000018', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '127.0.0.1', '2026-04-22 04:29:09');
INSERT INTO `xtmd_operation_log` VALUES (579, 'admin', 'PICK_FINISH', 'SALE', '900000034', 'Finish picking and update sale status', '/sale/finishPick', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:39:13');
INSERT INTO `xtmd_operation_log` VALUES (580, 'admin', 'PICK_SCAN', 'SALE', '900000019', 'Scan confirm product=雪碧, quantity=1', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:43:59');
INSERT INTO `xtmd_operation_log` VALUES (581, 'admin', 'PICK_SCAN', 'SALE', '900000019', 'Scan confirm product=雪碧, quantity=6', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:44:03');
INSERT INTO `xtmd_operation_log` VALUES (582, 'admin', 'PICK_SCAN', 'SALE', '900000019', 'Scan confirm product=雪碧, quantity=12', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:44:05');
INSERT INTO `xtmd_operation_log` VALUES (583, 'admin', 'PICK_SCAN', 'SALE', '900000019', 'Scan confirm product=雪碧, quantity=16', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:44:07');
INSERT INTO `xtmd_operation_log` VALUES (584, 'admin', 'PICK_SCAN', 'SALE', '900000019', 'Scan confirm product=雪碧, quantity=21', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:44:09');
INSERT INTO `xtmd_operation_log` VALUES (585, 'admin', 'PICK_SCAN', 'SALE', '900000013', 'Scan confirm product=雪碧, quantity=1', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:45:54');
INSERT INTO `xtmd_operation_log` VALUES (586, 'admin', 'PICK_SCAN', 'SALE', '900000013', 'Scan confirm product=雪碧, quantity=1', '/sale/pickScanConfirm', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 04:46:44');
INSERT INTO `xtmd_operation_log` VALUES (587, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 04:57:14');
INSERT INTO `xtmd_operation_log` VALUES (588, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:01:55');
INSERT INTO `xtmd_operation_log` VALUES (589, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:10:02');
INSERT INTO `xtmd_operation_log` VALUES (590, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:10:18');
INSERT INTO `xtmd_operation_log` VALUES (591, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:11:12');
INSERT INTO `xtmd_operation_log` VALUES (592, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:11:41');
INSERT INTO `xtmd_operation_log` VALUES (593, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:12:10');
INSERT INTO `xtmd_operation_log` VALUES (594, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '127.0.0.1', '2026-04-22 05:12:17');
INSERT INTO `xtmd_operation_log` VALUES (595, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 05:49:53');
INSERT INTO `xtmd_operation_log` VALUES (596, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:00:20');
INSERT INTO `xtmd_operation_log` VALUES (597, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:03:11');
INSERT INTO `xtmd_operation_log` VALUES (598, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:04:24');
INSERT INTO `xtmd_operation_log` VALUES (599, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:05:42');
INSERT INTO `xtmd_operation_log` VALUES (600, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:06:09');
INSERT INTO `xtmd_operation_log` VALUES (601, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:07:07');
INSERT INTO `xtmd_operation_log` VALUES (602, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:10:20');
INSERT INTO `xtmd_operation_log` VALUES (603, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:10:31');
INSERT INTO `xtmd_operation_log` VALUES (604, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:11:03');
INSERT INTO `xtmd_operation_log` VALUES (605, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:11:10');
INSERT INTO `xtmd_operation_log` VALUES (606, 'admin', 'LOGIN', 'AUTH', NULL, 'User login success', '/login', 'POST', '0:0:0:0:0:0:0:1', '2026-04-22 06:11:35');

-- ----------------------------
-- Table structure for xtmd_role
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_role`;
CREATE TABLE `xtmd_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int NULL DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `build_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_role
-- ----------------------------
INSERT INTO `xtmd_role` VALUES (1, 'admin', '拥有全部菜单', 0, '0', 'admin', '2023-04-25 22:17:15', 'admin', '2023-05-12 15:59:08', '超级管理员');
INSERT INTO `xtmd_role` VALUES (2, 'sale', '首页、基础信息、备忘录、进销模块', 0, '0', 'admin', '2023-05-06 13:44:04', 'admin', '2023-05-12 16:02:06', '销售管理员及其销售人员');
INSERT INTO `xtmd_role` VALUES (3, 'sys', '全部菜单', 0, '0', 'admin', '2023-05-12 15:58:57', 'admin', '2023-05-12 15:59:13', '系统管理员');
INSERT INTO `xtmd_role` VALUES (4, 'warehouse', '首页、基础信息、备忘录、仓库管理', 0, '0', 'admin', '2023-05-12 15:58:59', 'admin', '2023-05-12 16:02:57', '仓库管理员及其仓库人员');

-- ----------------------------
-- Table structure for xtmd_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_role_menu_relation`;
CREATE TABLE `xtmd_role_menu_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 242 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xtmd_role_menu_relation
-- ----------------------------
INSERT INTO `xtmd_role_menu_relation` VALUES (173, 2, 22);
INSERT INTO `xtmd_role_menu_relation` VALUES (174, 2, 1);
INSERT INTO `xtmd_role_menu_relation` VALUES (175, 2, 2);
INSERT INTO `xtmd_role_menu_relation` VALUES (176, 2, 4);
INSERT INTO `xtmd_role_menu_relation` VALUES (177, 2, 23);
INSERT INTO `xtmd_role_menu_relation` VALUES (178, 2, 3);
INSERT INTO `xtmd_role_menu_relation` VALUES (179, 2, 5);
INSERT INTO `xtmd_role_menu_relation` VALUES (180, 2, 6);
INSERT INTO `xtmd_role_menu_relation` VALUES (181, 2, 7);
INSERT INTO `xtmd_role_menu_relation` VALUES (182, 2, 8);
INSERT INTO `xtmd_role_menu_relation` VALUES (183, 2, 9);
INSERT INTO `xtmd_role_menu_relation` VALUES (184, 2, 10);
INSERT INTO `xtmd_role_menu_relation` VALUES (185, 2, 11);
INSERT INTO `xtmd_role_menu_relation` VALUES (186, 1, 22);
INSERT INTO `xtmd_role_menu_relation` VALUES (187, 1, 1);
INSERT INTO `xtmd_role_menu_relation` VALUES (188, 1, 2);
INSERT INTO `xtmd_role_menu_relation` VALUES (189, 1, 4);
INSERT INTO `xtmd_role_menu_relation` VALUES (190, 1, 23);
INSERT INTO `xtmd_role_menu_relation` VALUES (191, 1, 3);
INSERT INTO `xtmd_role_menu_relation` VALUES (192, 1, 5);
INSERT INTO `xtmd_role_menu_relation` VALUES (193, 1, 6);
INSERT INTO `xtmd_role_menu_relation` VALUES (194, 1, 7);
INSERT INTO `xtmd_role_menu_relation` VALUES (195, 1, 8);
INSERT INTO `xtmd_role_menu_relation` VALUES (196, 1, 9);
INSERT INTO `xtmd_role_menu_relation` VALUES (197, 1, 10);
INSERT INTO `xtmd_role_menu_relation` VALUES (198, 1, 11);
INSERT INTO `xtmd_role_menu_relation` VALUES (199, 1, 12);
INSERT INTO `xtmd_role_menu_relation` VALUES (200, 1, 21);
INSERT INTO `xtmd_role_menu_relation` VALUES (201, 1, 13);
INSERT INTO `xtmd_role_menu_relation` VALUES (202, 1, 14);
INSERT INTO `xtmd_role_menu_relation` VALUES (203, 1, 15);
INSERT INTO `xtmd_role_menu_relation` VALUES (204, 1, 16);
INSERT INTO `xtmd_role_menu_relation` VALUES (205, 1, 17);
INSERT INTO `xtmd_role_menu_relation` VALUES (206, 1, 18);
INSERT INTO `xtmd_role_menu_relation` VALUES (207, 1, 19);
INSERT INTO `xtmd_role_menu_relation` VALUES (208, 1, 20);
INSERT INTO `xtmd_role_menu_relation` VALUES (209, 3, 22);
INSERT INTO `xtmd_role_menu_relation` VALUES (210, 3, 2);
INSERT INTO `xtmd_role_menu_relation` VALUES (211, 3, 5);
INSERT INTO `xtmd_role_menu_relation` VALUES (212, 3, 6);
INSERT INTO `xtmd_role_menu_relation` VALUES (213, 3, 7);
INSERT INTO `xtmd_role_menu_relation` VALUES (214, 3, 8);
INSERT INTO `xtmd_role_menu_relation` VALUES (215, 3, 9);
INSERT INTO `xtmd_role_menu_relation` VALUES (216, 3, 10);
INSERT INTO `xtmd_role_menu_relation` VALUES (217, 3, 11);
INSERT INTO `xtmd_role_menu_relation` VALUES (218, 3, 12);
INSERT INTO `xtmd_role_menu_relation` VALUES (219, 3, 21);
INSERT INTO `xtmd_role_menu_relation` VALUES (220, 3, 13);
INSERT INTO `xtmd_role_menu_relation` VALUES (221, 3, 14);
INSERT INTO `xtmd_role_menu_relation` VALUES (222, 3, 15);
INSERT INTO `xtmd_role_menu_relation` VALUES (223, 3, 16);
INSERT INTO `xtmd_role_menu_relation` VALUES (224, 3, 17);
INSERT INTO `xtmd_role_menu_relation` VALUES (225, 3, 18);
INSERT INTO `xtmd_role_menu_relation` VALUES (226, 3, 19);
INSERT INTO `xtmd_role_menu_relation` VALUES (227, 3, 20);
INSERT INTO `xtmd_role_menu_relation` VALUES (228, 4, 22);
INSERT INTO `xtmd_role_menu_relation` VALUES (229, 4, 1);
INSERT INTO `xtmd_role_menu_relation` VALUES (230, 4, 2);
INSERT INTO `xtmd_role_menu_relation` VALUES (231, 4, 4);
INSERT INTO `xtmd_role_menu_relation` VALUES (232, 4, 23);
INSERT INTO `xtmd_role_menu_relation` VALUES (233, 4, 3);
INSERT INTO `xtmd_role_menu_relation` VALUES (234, 4, 5);
INSERT INTO `xtmd_role_menu_relation` VALUES (235, 4, 6);
INSERT INTO `xtmd_role_menu_relation` VALUES (236, 4, 12);
INSERT INTO `xtmd_role_menu_relation` VALUES (237, 4, 21);
INSERT INTO `xtmd_role_menu_relation` VALUES (238, 4, 13);
INSERT INTO `xtmd_role_menu_relation` VALUES (239, 4, 14);
INSERT INTO `xtmd_role_menu_relation` VALUES (240, 4, 15);
INSERT INTO `xtmd_role_menu_relation` VALUES (241, 1, 24);

SET FOREIGN_KEY_CHECKS = 1;
