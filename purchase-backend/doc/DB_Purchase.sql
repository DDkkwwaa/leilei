/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : DB_Purchase

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 01/05/2024 15:58:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bjmd_note
-- ----------------------------
DROP TABLE IF EXISTS `bjmd_note`;
CREATE TABLE `bjmd_note` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8_general_ci COMMENT '笔记内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updata_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bjmd_note
-- ----------------------------
BEGIN;
INSERT INTO `bjmd_note` (`id`, `user_name`, `title`, `content`, `create_time`, `updata_time`, `remark`) VALUES (1, 'admin', '测试', '<p>111222</p>', '2023-04-14 17:34:43', '2023-04-19 21:46:07', NULL);
INSERT INTO `bjmd_note` (`id`, `user_name`, `title`, `content`, `create_time`, `updata_time`, `remark`) VALUES (2, 'admin', '制造业', '<p>我是第二个111</p>', '2012-03-03 07:10:29', '2023-04-14 17:34:08', NULL);
INSERT INTO `bjmd_note` (`id`, `user_name`, `title`, `content`, `create_time`, `updata_time`, `remark`) VALUES (5, 'sale', '咨询业', 'With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. Navicat 15 has added support for the system-wide dark mode. I will greet this day with love in my heart. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more. Sometimes you win, sometimes you learn. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Champions keep playing until they get it right. The Synchronize to Database function will give you a full picture of all database differences. If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane. If the plan doesn’t work, change the plan, but never the goal. Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another. The past has no power over the present moment. Success consists of going from failure to failure without loss of enthusiasm. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Optimism is the one quality more associated with success and happiness than any other. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Sometimes you win, sometimes you learn. Navicat 15 has added support for the system-wide dark mode. Difficult circumstances serve as a textbook of life for people. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window. The past has no power over the present moment. Anyone who has ever made anything of importance was disciplined. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution. You will succeed because most people are lazy. The On Startup feature allows you to control what tabs appear when you launch Navicat. If you wait, all that happens is you get older. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. Flexible settings enable you to set up a custom key for comparison and synchronization. If the plan doesn’t work, change the plan, but never the goal. A query is used to extract data from the database in a readable format according to the user\'s request. If you wait, all that happens is you get older. A man is not old until regrets take the place of dreams. Champions keep playing until they get it right. In the middle of winter I at last discovered that there was in me an invincible summer. If it scares you, it might be a good thing to try. Flexible settings enable you to set up a custom key for comparison and synchronization. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. Flexible settings enable you to set up a custom key for comparison and synchronization. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder for building queries visually. A query is used to extract data from the database in a readable format according to the user\'s request. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. I may not have gone where I intended to go, but I think I have ended up where I needed to be. With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create, organize, access and share information in a secure and easy way. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration to multiple kinds of database so easy. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload privilege to use this feature. The Synchronize to Database function will give you a full picture of all database differences.', '2003-11-22 02:53:22', '2023-04-14 16:54:16', NULL);
INSERT INTO `bjmd_note` (`id`, `user_name`, `title`, `content`, `create_time`, `updata_time`, `remark`) VALUES (31, 'admin', 'tes', '<p>我是测试系统</p>', '2023-04-30 21:38:10', '2023-05-12 14:48:49', NULL);
COMMIT;

-- ----------------------------
-- Table structure for ckmd_depository
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository`;
CREATE TABLE `ckmd_depository` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库编号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库名称',
  `head` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库负责人',
  `store_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库地址',
  `stock_total` bigint DEFAULT NULL COMMENT '库存总容量',
  `surplus` bigint DEFAULT NULL COMMENT '剩余容量',
  `area` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '面积单位',
  `status` int DEFAULT NULL COMMENT '0 正常 1 停用',
  `build_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository
-- ----------------------------
BEGIN;
INSERT INTO `ckmd_depository` (`id`, `number`, `name`, `head`, `store_phone`, `address`, `stock_total`, `surplus`, `area`, `status`, `build_date`, `remark`) VALUES (1, '12sa', '虚拟仓库A', 'admin', '16423526791', '北京市/市辖区/东城区/', 2000, 2000, '立方米', 0, '2023-02-05 17:28:44', '');
INSERT INTO `ckmd_depository` (`id`, `number`, `name`, `head`, `store_phone`, `address`, `stock_total`, `surplus`, `area`, `status`, `build_date`, `remark`) VALUES (2, NULL, '虚拟仓库B', '老王', '15684457223', '天津市/市辖区/和平区/', 2000, 2000, '立方米', 0, '2023-03-22 22:44:35', '');
INSERT INTO `ckmd_depository` (`id`, `number`, `name`, `head`, `store_phone`, `address`, `stock_total`, `surplus`, `area`, `status`, `build_date`, `remark`) VALUES (4, NULL, '武汉仓1', '老孙', '1568546244', '湖北省/武汉市/市辖区/', 200, 200, '立方米', 0, '2023-04-28 20:25:43', '');
INSERT INTO `ckmd_depository` (`id`, `number`, `name`, `head`, `store_phone`, `address`, `stock_total`, `surplus`, `area`, `status`, `build_date`, `remark`) VALUES (5, NULL, '擎天', '老孙', '15685545221', '贵州省/黔东南苗族侗族自治州/天柱县/', 200, NULL, '', 0, '2023-05-12 10:53:14', '');
COMMIT;

-- ----------------------------
-- Table structure for ckmd_depository_in
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository_in`;
CREATE TABLE `ckmd_depository_in` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `source_number` int DEFAULT NULL COMMENT '来源 可有也可无',
  `in_id` bigint NOT NULL COMMENT '入库编号',
  `depository` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入库仓库名',
  `shop_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入库商名',
  `shop_price` decimal(10,2) DEFAULT NULL COMMENT '入库商品单价',
  `shop_number` bigint DEFAULT NULL COMMENT '入库商品数量',
  `price_total` decimal(10,2) DEFAULT NULL COMMENT '入库商品总价',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位规格 [个斤盒]',
  `date` datetime DEFAULT NULL COMMENT '入库日期',
  `in_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入库人',
  `shop_supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商名',
  `is_inspection` int DEFAULT '1' COMMENT '0已质检 1 未质检',
  `status` int DEFAULT '1' COMMENT '0 已入库 1 未入库',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository_in
-- ----------------------------
BEGIN;
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (1, NULL, 122322587, '北京海淀仓', '苹果', 7.00, 50, 325.00, '斤', '2023-05-12 11:37:46', 'admin', '北极星', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (2, NULL, 123456789, '北京', '雪碧', 11.00, 122, 1342.00, '件', '2023-03-23 21:22:20', 'admin', NULL, 1, 1, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (3, NULL, 170498910, '北京', '阿莫西林胶囊', 8.20, 20, NULL, '个', '2023-05-12 11:37:12', 'admin', NULL, 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (4, NULL, 811498807, '北京海淀仓', '华硕笔记本', 10000.00, 50, 120.00, '件', '2023-04-23 20:12:27', 'admin', '华硕厂商', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (5, NULL, 504912341, '武汉仓1', '鱼', 15.60, 230, 3588.00, '斤', NULL, NULL, NULL, 1, 1, '2023-05-13 16:13:50');
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (10, NULL, 718006985, '虚拟仓库B', '华硕笔记本', 8865.00, 120, 120.00, '个', '2023-05-15 23:35:00', 'admin', '璐有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (11, NULL, 143649068, '虚拟仓库A', '苹果', 6.50, 300, 120.00, '斤', '2023-05-18 18:12:36', 'admin', '胡記发展贸易有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (12, NULL, 718006981, '虚拟仓库A', '鱼', 15.60, 230, 120.00, '斤', '2023-06-01 21:55:02', 'admin', '贾有限责任公司', 0, 0, NULL);
INSERT INTO `ckmd_depository_in` (`id`, `source_number`, `in_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `price_total`, `specs`, `date`, `in_user`, `shop_supplier`, `is_inspection`, `status`, `create_date`) VALUES (13, NULL, 210663607, '虚拟仓库B', '苹果', 21.00, 50, 120.00, '件', '2023-06-03 14:29:22', 'admin', '嘉伦工程有限责任公司', 0, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for ckmd_depository_out
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_depository_out`;
CREATE TABLE `ckmd_depository_out` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '出库id',
  `source_number` bigint DEFAULT NULL COMMENT '来源 id',
  `out_id` bigint DEFAULT NULL COMMENT '出库编号',
  `depository` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出库仓库',
  `shop_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `shop_price` decimal(10,2) DEFAULT NULL COMMENT '出库价格',
  `shop_number` bigint DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(60,2) DEFAULT NULL COMMENT '商品总价',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规格',
  `date` datetime DEFAULT NULL COMMENT '出库时间',
  `out_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出库用户',
  `shop_supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `status` int DEFAULT NULL COMMENT '是否出库 0 出库 1不出库',
  `out_inspection` int DEFAULT '1' COMMENT '0 审核 1表示未审核',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ckmd_depository_out
-- ----------------------------
BEGIN;
INSERT INTO `ckmd_depository_out` (`id`, `source_number`, `out_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `total_price`, `specs`, `date`, `out_user`, `shop_supplier`, `status`, `out_inspection`, `create_date`) VALUES (1, NULL, 123323, '虚拟仓库A', '苹果', 8.54, 30, 256.20, '个', '2023-04-27 20:19:55', '老王', '华宇', 0, 0, '2023-04-27 20:16:36');
INSERT INTO `ckmd_depository_out` (`id`, `source_number`, `out_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `total_price`, `specs`, `date`, `out_user`, `shop_supplier`, `status`, `out_inspection`, `create_date`) VALUES (2, NULL, 123, '虚拟仓库B', '雪碧', 200.00, 30, 6000.00, '箱', '2023-04-27 20:14:09', NULL, NULL, 1, 0, NULL);
INSERT INTO `ckmd_depository_out` (`id`, `source_number`, `out_id`, `depository`, `shop_name`, `shop_price`, `shop_number`, `total_price`, `specs`, `date`, `out_user`, `shop_supplier`, `status`, `out_inspection`, `create_date`) VALUES (3, 123456, 161997858, '虚拟仓库A', '苹果', 1.00, 5, 5.00, '箱', NULL, 'admin', '老杨', 1, 1, '2023-06-03 11:07:25');
COMMIT;

-- ----------------------------
-- Table structure for ckmd_stock
-- ----------------------------
DROP TABLE IF EXISTS `ckmd_stock`;
CREATE TABLE `ckmd_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '序号 商品库存id',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品类型',
  `quantity` bigint DEFAULT NULL COMMENT '库存量',
  `speces` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规格 斤 、千克、个',
  `depository` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '仓库名',
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='库存清单';

-- ----------------------------
-- Records of ckmd_stock
-- ----------------------------
BEGIN;
INSERT INTO `ckmd_stock` (`id`, `shop`, `shop_type`, `quantity`, `speces`, `depository`, `address`, `create_time`, `update_time`, `remark`) VALUES (4, '雪碧', '饮品类', 100, '斤', '虚拟仓库A', '', '2023-03-23 21:23:33', '2023-04-28 20:26:45', '无');
INSERT INTO `ckmd_stock` (`id`, `shop`, `shop_type`, `quantity`, `speces`, `depository`, `address`, `create_time`, `update_time`, `remark`) VALUES (5, '华硕笔记本', '电器类', 20, '个', '虚拟仓库A', '', '2023-05-07 17:36:31', NULL, '');
INSERT INTO `ckmd_stock` (`id`, `shop`, `shop_type`, `quantity`, `speces`, `depository`, `address`, `create_time`, `update_time`, `remark`) VALUES (6, '苹果', '水果类', 62, '个', '虚拟仓库A', '', '2023-05-07 17:37:30', NULL, '');
INSERT INTO `ckmd_stock` (`id`, `shop`, `shop_type`, `quantity`, `speces`, `depository`, `address`, `create_time`, `update_time`, `remark`) VALUES (7, '鱼', '水产类', 50, '千克', '虚拟仓库A', '', '2023-05-07 17:45:02', NULL, '');
INSERT INTO `ckmd_stock` (`id`, `shop`, `shop_type`, `quantity`, `speces`, `depository`, `address`, `create_time`, `update_time`, `remark`) VALUES (8, '香蕉', '水果类', 23, '斤', '擎天', '', '2023-05-12 16:47:59', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for jcmd_customer
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_customer`;
CREATE TABLE `jcmd_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '客户姓名',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户电话',
  `address` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户地址',
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户邮箱',
  `fax` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传真',
  `branch` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行名',
  `branch_no` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卡号',
  `build_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jcmd_customer
-- ----------------------------
BEGIN;
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (1, '张三', '15685565446', '北京海淀区', '1070348698@qq.com', '556688', '中信银行', '25468446x458455', '2023-01-19 19:30:02', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (3, '孙安琪', '2015024130', '天河区天河路278号', 'ansun218@mail.com', '17165678577', 'Visa', '3572647513660753', '2019-05-24 10:50:44', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (4, '邱安琪', '16879419430', '罗湖区蔡屋围深南东路541号', 'anqiqiu105@outlook.com', '18035438189', 'UnionPay', '4817496935500194', '2004-11-08 01:23:28', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (5, '史晓明', '101724612', '西城区复兴门内大街741号', 'xiaomingshi@gmail.com', '1043573841', 'MasterCard', NULL, '2021-07-13 14:11:56', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (6, '任安琪', '1016481874', '东城区东单王府井东街902号', 'anqiren@gmail.com', '16385561570', 'American Express', '6218706318336242', '2018-06-22 10:05:13', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (7, '方安琪', '2038004755', '天河区天河路90号', 'fananqi@outlook.com', '15988881133', 'JCB', '4477357252652334', '2020-06-26 07:40:04', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (8, '田杰宏', '14314567223', '西城区复兴门内大街665号', 'jiehongtian@icloud.com', '1084121515', 'Visa', '379751807668208', '2020-07-21 23:57:19', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (9, '毛子韬', '2054161989', '白云区小坪东路207号', 'maozitao2019@icloud.com', '16425193461', 'American Express', '377830824899848', '2017-01-24 06:00:38', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (10, '武子韬', '106434415', '东城区东单王府井东街486号', 'wuz@gmail.com', NULL, 'MasterCard', '377417760293101', '2016-11-01 05:16:19', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (11, '邓震南', '76920499870', '东泰五街582号', 'dengz@hotmail.com', '18851937724', 'JCB', '346908265108123', '2015-04-29 12:05:04', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (12, '于子异', '14744589036', '朝阳区三里屯路512号', 'yuziyi4@icloud.com', '1096684286', 'American Express', '4633899537086193', '2013-11-29 11:10:05', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (13, '王子韬', '18677999969', '罗湖区清水河一路546号', NULL, '7555958001', 'MasterCard', '3586381212480214', '2022-02-01 05:12:12', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (14, '朱璐', '19148019897', '浦东新区健祥路202号', 'lzhu@yahoo.com', '216802380', 'Visa', '6215275615420946', '2017-03-16 23:12:02', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (15, '何杰宏', '2073404104', '白云区小坪东路963号', 'jiehonghe@outlook.com', '205749027', 'Visa', '3579930526023026', '2007-11-12 18:23:40', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (16, '梁云熙', '18692261005', '白云区机场路棠苑街五巷68号', 'lyunxi@mail.com', '2087426432', 'JCB', '6270926496612306', '2000-06-22 04:27:06', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (17, '傅晓明', '1054003710', '東城区東直門內大街16号', 'fuxiaoming8@hotmail.com', '1046483288', 'American Express', '3547343902699741', '2003-01-03 11:55:39', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (18, '贺璐', '7552744825', '罗湖区清水河一路705号', 'helu708@hotmail.com', '75562904320', 'American Express', '371508392403302', '2007-07-19 14:21:16', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (19, '姜云熙', '109648448', '西城区复兴门内大街903号', 'jiangyu@gmail.com', '16623826253', 'American Express', '375263940537526', '2003-03-31 21:33:38', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (20, '黄璐', '7698507685', '珊瑚路755号', 'lu92@outlook.com', '14091539883', 'MasterCard', '373073332962121', '2004-09-24 17:20:05', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (21, '曹晓明', '14724978125', '浦东新区健祥路284号', 'caoxiaom10@gmail.com', '2117678293', 'JCB', '5487681515258745', '2014-07-31 01:38:51', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (22, '吴睿', '18290154381', '东泰五街574号', 'rui1967@outlook.com', '19000340143', NULL, '374535202351105', '2006-11-27 18:27:46', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (23, '任子韬', '14562899476', '黄浦区淮海中路392号', 'zitaoren@mail.com', '211747654', 'American Express', NULL, '2013-08-30 22:32:10', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (24, '常宇宁', '18397703030', '坑美十五巷819号', 'yuningchang@hotmail.com', '19136140958', 'MasterCard', NULL, '2019-06-13 12:57:11', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (25, '邵致远', '19907753960', '天河区大信商圈大信南路465号', 'zhishao1018@outlook.com', '18097268286', 'Visa', '3548530169911210', '2015-04-17 20:28:54', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (26, '罗震南', '13199810691', '罗湖区田贝一路587号', 'zluo@yahoo.com', '19138187726', 'MasterCard', '5393652953393321', '2007-11-16 13:50:44', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (27, '杨詩涵', '15104719089', '紫马岭商圈中山五路706号', 'shiyang1005@icloud.com', NULL, 'JCB', '5239639966225812', '2005-11-07 18:06:51', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (28, '曾云熙', '1097872249', '海淀区清河中街68号431号', 'zeyunxi218@outlook.com', '104332437', 'JCB', '373120506532618', '2013-01-11 15:36:41', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (29, '毛睿', '76086143282', '紫马岭商圈中山五路323号', 'ruimao01@outlook.com', '7604866570', 'UnionPay', '377559218783132', '2012-08-31 06:03:08', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (30, '顾晓明', '76003838859', '京华商圈华夏街437号', 'xiaomingg@hotmail.com', '16529166037', 'MasterCard', '5272890019286354', '2014-06-06 08:58:25', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (31, '罗晓明', '7698730679', '东泰五街962号', 'xiaoming331@hotmail.com', '17212567543', 'Visa', '6276765968485518', '2022-07-11 23:25:54', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (32, '程安琪', '2142032862', '浦东新区健祥路305号', 'chanq@yahoo.com', '218795510', 'Visa', '4361135947126156', '2009-10-12 11:52:30', NULL, NULL);
INSERT INTO `jcmd_customer` (`id`, `name`, `phone`, `address`, `email`, `fax`, `branch`, `branch_no`, `build_date`, `update_time`, `remark`) VALUES (34, '罗晓明', '7698730679', '东泰五街962号', 'xiaoming331@hotmail.com', '17212567543', 'Visa', '6276765968485518', '2023-03-19 14:08:16', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for jcmd_shop
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_shop`;
CREATE TABLE `jcmd_shop` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名',
  `parent_id` bigint DEFAULT NULL COMMENT '商品科类',
  `shop_number` bigint DEFAULT NULL COMMENT '商品数量',
  `specs` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规格',
  `market_price` decimal(50,2) DEFAULT NULL COMMENT '市场价格',
  `build_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jcmd_shop
-- ----------------------------
BEGIN;
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (1, '苹果1', 1, 123, '斤', 13.50, '2023-02-05 16:54:11', '2024-05-01 09:28:37', NULL);
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (2, '鱼', 2, 277, '斤', 10.60, '2023-02-05 17:07:05', '2023-03-20 23:09:24', NULL);
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (3, '华硕笔记本', 2, 125, '台', 8.50, '2023-02-05 17:07:05', '2023-05-05 14:54:23', NULL);
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (4, '阿莫西林胶囊', 4, 687, '盒', 85.00, '2023-02-05 17:07:05', '2023-03-20 23:43:37', NULL);
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (5, '雪碧', 5, 88, '瓶', 5.80, '2023-02-05 17:07:05', '2023-04-04 20:51:24', NULL);
INSERT INTO `jcmd_shop` (`id`, `name`, `parent_id`, `shop_number`, `specs`, `market_price`, `build_date`, `update_date`, `remark`) VALUES (6, '香蕉', 5, NULL, NULL, 12.00, '2023-03-19 15:38:13', '2023-04-04 20:55:25', '');
COMMIT;

-- ----------------------------
-- Table structure for jcmd_shop_type
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_shop_type`;
CREATE TABLE `jcmd_shop_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_id` bigint DEFAULT NULL COMMENT '商品种类编号',
  `shop_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品类型',
  `info` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jcmd_shop_type
-- ----------------------------
BEGIN;
INSERT INTO `jcmd_shop_type` (`id`, `class_id`, `shop_type`, `info`) VALUES (1, 1, '水果类', '一级');
INSERT INTO `jcmd_shop_type` (`id`, `class_id`, `shop_type`, `info`) VALUES (2, 2, '水产类', '一级');
INSERT INTO `jcmd_shop_type` (`id`, `class_id`, `shop_type`, `info`) VALUES (3, 3, '电器类', '一级');
INSERT INTO `jcmd_shop_type` (`id`, `class_id`, `shop_type`, `info`) VALUES (4, 3, '医药类', '一级');
INSERT INTO `jcmd_shop_type` (`id`, `class_id`, `shop_type`, `info`) VALUES (5, 4, '饮品类', '一级');
COMMIT;

-- ----------------------------
-- Table structure for jcmd_supplier
-- ----------------------------
DROP TABLE IF EXISTS `jcmd_supplier`;
CREATE TABLE `jcmd_supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `supplier_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商名',
  `head` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `shop_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `branch` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行',
  `branch_account` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行账号',
  `supplier_proxy` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商法人代表',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `other` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jcmd_supplier
-- ----------------------------
BEGIN;
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (1, '北京鱼业', '杨自行', '1256841265', '北京昌平区', '鱼', '招商银行', '558462364655315451', '杨', '2023-02-05 17:59:24', '2023-05-12 12:11:59', NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (2, '嘉伦工程有限责任公司', '彭杰宏', '76914906845', '坑美十五巷586号', 'Strawbekry', '中信银行', '6290307664140642', '彭杰宏', '2021-05-07 06:48:49', '2023-05-12 12:12:37', NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (3, '潘記有限责任公司', '陶致远', '15291801410', '成华区二仙桥东三路546号', 'Mango', ' 邮政银行', '6220943385717238', '陶致远', '2020-10-10 23:30:46', '2023-05-12 12:12:55', NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (4, '子异有限责任公司', '于宇宁', '205015838', '海珠区江南西路536号', 'hrange', '浦发银行', '6261597080930940', '于宇宁', '2020-12-22 06:02:01', '2023-05-12 12:13:06', NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (5, '贾有限责任公司', '梁子异', '13351713862', '浦东新区健祥路975号', 'ambi-Strawberry', 'MasterCard', '6200179181979748', '梁子异', '2020-02-03 18:24:01', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (6, '睿物业代理有限责任公司', '尹子异', '18477850084', '罗湖区蔡屋围深南东路496号', 'fambutan', 'UnionPay', '6225194331513314', '尹子异', '2020-11-19 05:32:27', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (7, '陆記发展贸易有限责任公司', '吕宇宁', '13493985903', '延庆区028县道865号', 'iMango', 'MasterCard', '6263980689074641', '吕宇宁', '2022-12-15 22:43:05', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (8, '胡記发展贸易有限责任公司', '徐致远', '14723384265', '天河区天河路82号', 'Kiwi', 'UnionPay', '6273617269766562', '徐致远', '2023-02-02 01:29:25', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (9, '子异有限责任公司', '贾安琪', '2084712952', '海珠区江南西路454号', 'Grape air', 'UnionPay', '6254519854021754', '贾安琪', '2022-05-19 18:53:54', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (10, '璐有限责任公司', '苏子韬', '7551571770', '龙岗区布吉镇西环路411号', 'Gwape', 'Visa', '6206476415440933', '苏子韬', '2022-11-21 03:39:19', NULL, NULL);
INSERT INTO `jcmd_supplier` (`id`, `supplier_name`, `head`, `phone`, `address`, `shop_name`, `branch`, `branch_account`, `supplier_proxy`, `create_date`, `update_time`, `other`) VALUES (11, '邹記顾问有限责任公司', '徐震南', '207964990', '海珠区江南西路118号', 'Ramlutan air', 'UnionPay', '6267092850431869', '徐震南', '2020-09-09 23:37:24', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for jxmd_purchase
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_purchase`;
CREATE TABLE `jxmd_purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购编号',
  `purchase_user` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购人',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品所属类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '采购供应商',
  `quantity` bigint DEFAULT NULL COMMENT '采购数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '采购价格',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `time` datetime DEFAULT NULL COMMENT '采购时间',
  `status` int DEFAULT NULL COMMENT '状态 0 完成 1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位规格  个斤盒',
  `is_destroy` int DEFAULT NULL COMMENT '0存在 1退货，退货后不显示，可增加查询按钮',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jxmd_purchase
-- ----------------------------
BEGIN;
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (7, '718006985', '辰东', '华硕笔记本', '电器类', '璐有限责任公司', 120, 8865.00, 120.00, '2023-05-12 16:59:47', 1, '', '个', 1);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (8, '718006981', '辰东', '鱼', '水产类', '贾有限责任公司', 230, 15.60, 120.00, '2023-05-13 16:11:58', 0, '', '斤', 1);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (9, '210663607', 'test', '苹果', '水产类', '嘉伦工程有限责任公司', 50, 21.00, 120.00, '2023-06-01 21:57:09', 1, '', '件', 1);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (10, '209088791', 'test', '苹果', '水果类', '北京鱼业', 12, 22.00, 120.00, '2023-06-01 21:57:31', 1, '', '斤', 0);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (11, '182185694', '马化龙', '', '', '', NULL, NULL, 120.00, '2023-06-01 21:57:56', 1, '', '', 0);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (12, '915089940', 'test', '雪碧', '饮品类', '子异有限责任公司', 25, 2.00, 120.00, '2023-06-01 21:59:43', 1, '', '个', 0);
INSERT INTO `jxmd_purchase` (`id`, `number`, `purchase_user`, `shop`, `shop_type`, `supplier`, `quantity`, `price`, `total_price`, `time`, `status`, `remark`, `specs`, `is_destroy`) VALUES (13, '210753324', '张三', '', '', '', NULL, NULL, 120.00, '2023-06-01 22:00:04', 1, '', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for jxmd_purchase_exit
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_purchase_exit`;
CREATE TABLE `jxmd_purchase_exit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '采购编号',
  `exit_number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '退采编号',
  `num` int DEFAULT NULL COMMENT '退采数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '日期',
  `reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原因',
  `status` int DEFAULT NULL COMMENT '状态 0 完成 1进行中  ',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位规格 个斤盒',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jxmd_purchase_exit
-- ----------------------------
BEGIN;
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (1, ' 213341123', '12588', 30, 8.50, 25.50, '2023-04-30 23:41:31', '买多了', 1, '', NULL);
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (2, '123456789', '12588', 30, 8.50, 25.50, '2023-04-30 23:41:38', '买多了', 1, NULL, NULL);
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (3, ' 562231358', '123456', 20, 10.90, 120.00, '2023-04-30 23:41:43', '', 1, '', NULL);
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (4, '179910234', '789581103', 230, 7.40, 120.00, '2023-05-18 18:11:25', 'aaaaa', 0, NULL, '个');
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (5, '143649068', '436056446', 300, 6.50, 120.00, '2023-05-18 18:13:23', 'jxc1111111111111111111111111', 0, NULL, '斤');
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (6, '718006985', '204514450', 120, 8865.00, 120.00, '2023-05-23 19:09:32', 'chusd1111111', 0, NULL, '个');
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (7, '718006981', '612573700', 230, 15.60, 120.00, '2023-06-01 21:55:59', '买多了', 1, NULL, '斤');
INSERT INTO `jxmd_purchase_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`, `specs`) VALUES (8, '210663607', '979599307', 50, 21.00, 120.00, '2023-06-03 14:31:21', '测试', 0, NULL, '件');
COMMIT;

-- ----------------------------
-- Table structure for jxmd_sale
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_sale`;
CREATE TABLE `jxmd_sale` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sale_Number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '销售编号',
  `sale_user` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售人',
  `shop` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售商品',
  `shop_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品所属类',
  `supplier` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售客户，需要改成客户英文名',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `specs` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位规格  个斤盒',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '销售时间',
  `status` int DEFAULT NULL COMMENT '状态 0 完成 1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='销售表';

-- ----------------------------
-- Records of jxmd_sale
-- ----------------------------
BEGIN;
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (1, '123456', '王阳明', '苹果', '水果类', '老杨', 5, '箱', 1.00, 5.00, '2023-02-13 23:03:43', 1, NULL);
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (6, '123456', 'yang', '鱼', '水产类', '孙安琪', 60, NULL, 3.50, 120.00, '2023-03-24 00:03:32', 1, '');
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (8, '873479332', '老王', '鱼', '水产类', '方安琪', 52, NULL, 3.20, 166.40, '2023-05-04 22:09:20', 1, '');
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (9, '106103464', '挖', '苹果', '水果类', '张三', 100, NULL, 3.50, 350.00, '2023-05-06 20:33:48', 1, '');
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (11, '343538883', '老三', '华硕笔记本', '电器类', '孙安琪', 56, '个', 20.00, 1120.00, '2023-05-12 00:44:58', 1, '');
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (12, '276558819', '马化腾', '香蕉', '水果类', '毛子韬', 12, '斤', 12.50, 150.00, '2023-05-12 16:39:47', 1, '');
INSERT INTO `jxmd_sale` (`id`, `sale_Number`, `sale_user`, `shop`, `shop_type`, `supplier`, `num`, `specs`, `price`, `total_price`, `time`, `status`, `remark`) VALUES (13, '137694665', '辰东', '雪碧', '饮品类', '罗晓明', 110, '个', 7.80, 858.00, '2023-05-12 16:50:09', 1, '');
COMMIT;

-- ----------------------------
-- Table structure for jxmd_sale_exit
-- ----------------------------
DROP TABLE IF EXISTS `jxmd_sale_exit`;
CREATE TABLE `jxmd_sale_exit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '销售编号',
  `exit_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '退购编号',
  `num` bigint DEFAULT NULL COMMENT '退购数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '日期',
  `reason` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退购原因',
  `status` int DEFAULT NULL COMMENT '状态 0 完成  1进行中',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='销售退货表';

-- ----------------------------
-- Records of jxmd_sale_exit
-- ----------------------------
BEGIN;
INSERT INTO `jxmd_sale_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`) VALUES (1, '123456', '456', 202, 4.00, 7.00, '2023-02-13 23:19:36', '客户不想要了', 1, NULL);
INSERT INTO `jxmd_sale_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`) VALUES (2, '123', '456', 20, 4.00, NULL, '2023-03-24 00:15:40', '1', 1, NULL);
INSERT INTO `jxmd_sale_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`) VALUES (13, '', '267718632', 12, NULL, NULL, '2023-05-04 22:47:06', '', 1, '');
INSERT INTO `jxmd_sale_exit` (`id`, `number`, `exit_number`, `num`, `price`, `total_price`, `time`, `reason`, `status`, `remark`) VALUES (14, '123456', '373916142', 11, 2.00, 6.00, '2023-05-07 15:54:56', '', 1, '');
COMMIT;

-- ----------------------------
-- Table structure for qjmd_table_list
-- ----------------------------
DROP TABLE IF EXISTS `qjmd_table_list`;
CREATE TABLE `qjmd_table_list` (
  `id` bigint NOT NULL,
  `tableName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `md` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务模块',
  `describe` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `sort` bigint DEFAULT NULL COMMENT '表的顺序即业务-菜单显示顺序',
  `children` bigint DEFAULT NULL COMMENT '模块业务的子业务显示顺序',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qjmd_table_list
-- ----------------------------
BEGIN;
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (1, 'jcmd_customer', '基础信息管理模块', '客户表', 1, 1, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (2, 'jcmd_shop', '基础信息管理模块', '商品表', 1, 2, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (3, 'jcmd_supplier', '基础信息管理模块', '供应商表', 1, 3, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (4, 'bjmd_note', '备忘录', '笔记表', 2, 1, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (5, 'jxmd_purchase', '进销管理模块', '采购表', 3, 1, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (6, 'jxmd_purchase_exit', '进销管理模块', '采购退货表', 3, 2, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (7, 'jxmd_sale', '进销管理模块', '销售表', 3, 3, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (8, 'jxmd_sale_exit', '进销管理模块', '销售退货表', 3, 4, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (9, 'ckmd_depository', '仓库管理模块', '仓库表', 4, 2, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (11, 'ckmd_depository_in', '仓库管理模块', '入库清单表', 4, 3, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (12, 'ckmd_depository_out', '仓库管理模块', '出库清单表', 4, 4, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (13, 'xtmd_admin', '系统管理模块', '用户表', 5, 1, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (14, 'xtmd_role', '系统管理模块', '角色表', 5, 2, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (15, 'xtmd_dept', '系统管理模块', '部门表', 5, 3, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (16, 'xtmd_menu', '系统管理模块', '菜单表', 5, 4, NULL);
INSERT INTO `qjmd_table_list` (`id`, `tableName`, `md`, `describe`, `sort`, `children`, `remark`) VALUES (17, 'ckmd_stock', '仓库管理模块', '库存清单列表', 4, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xtmd_admin
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin`;
CREATE TABLE `xtmd_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `dept` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门id或者名称',
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `true_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像路径',
  `status` int DEFAULT '0' COMMENT '0正常 1停用',
  `e_mail` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_ip` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登录ip',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `sex` int DEFAULT '0' COMMENT '0男 1女 ',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_admin` (`id`, `dept`, `user_name`, `true_name`, `password`, `salt`, `icon`, `status`, `e_mail`, `phone`, `create_time`, `login_ip`, `login_date`, `sex`, `remark`) VALUES (1, '107', 'admin', '张三', '$2a$10$iFYzvKv03IKIT19PaBsSOefCNCjNpoC/GS67N9A0X2tFSjg/.E2XS', '', NULL, 0, '107348698@qq.com', '13521253828', '2023-01-08 15:05:52', NULL, '2023-01-08 15:05:00', 0, '12');
INSERT INTO `xtmd_admin` (`id`, `dept`, `user_name`, `true_name`, `password`, `salt`, `icon`, `status`, `e_mail`, `phone`, `create_time`, `login_ip`, `login_date`, `sex`, `remark`) VALUES (15, '2', 'saleMan', '辰东', '$2a$10$S/go13Lz2DFMsqlDiro0Y.cO4.vtRZ4PNubvuVBZCTvcRm4pvYRq.', NULL, NULL, 0, '', '15685545557', NULL, NULL, NULL, 0, '');
INSERT INTO `xtmd_admin` (`id`, `dept`, `user_name`, `true_name`, `password`, `salt`, `icon`, `status`, `e_mail`, `phone`, `create_time`, `login_ip`, `login_date`, `sex`, `remark`) VALUES (16, '10', 'wareHouse', '马化龙', '$2a$10$upiKjmC3tNDe3XDZMqvSWek79ZtSEHIpsLSk/I9hAJCAWdiccLiTC', NULL, NULL, 0, '', '15954263248', NULL, NULL, NULL, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for xtmd_admin_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin_dept_relation`;
CREATE TABLE `xtmd_admin_dept_relation` (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin_dept_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for xtmd_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_admin_role_relation`;
CREATE TABLE `xtmd_admin_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL COMMENT '用户id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xtmd_admin_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (16, 1, 1);
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (18, 6, 1);
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (19, 5, 2);
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (21, 2, 3);
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (22, 15, 2);
INSERT INTO `xtmd_admin_role_relation` (`id`, `admin_id`, `role_id`) VALUES (23, 16, 4);
COMMIT;

-- ----------------------------
-- Table structure for xtmd_dept
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_dept`;
CREATE TABLE `xtmd_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint DEFAULT NULL COMMENT '父部门id',
  `dept_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `order_num` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `status` int DEFAULT NULL COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of xtmd_dept
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, 100, '深圳总公司', '1', '王2', '16482618330', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2024-05-01 14:12:40');
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (3, 100, '长沙分公司', '2', '张三', '13016238720', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2024-05-01 14:12:45');
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (4, 101, '研发部门', '1', '陈思', '16567564426', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2024-05-01 14:12:49');
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (5, 101, '市场部门', '2', '老三', '18489450107', 0, '0', 'admin', '2023-01-08 15:35:55', '', '2023-05-07 12:05:34');
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (6, 101, '测试部门', '3', '张三', '19479515718', 0, '0', 'admin', '2023-01-08 15:35:55', 'admin', '2024-05-01 14:12:55');
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (7, 101, '财务部门', '4', '若依', '13121649314', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (8, 101, '运维部门', '5', '若依', '19224141234', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (9, 102, '市场部门', '1', '若依', '1033684022', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
INSERT INTO `xtmd_dept` (`id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (10, 102, '财务部门', '2', '若依', '19690068075', 0, '0', 'admin', '2023-01-08 15:35:55', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for xtmd_menu
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_menu`;
CREATE TABLE `xtmd_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称 label',
  `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
  `level` int DEFAULT NULL COMMENT '菜单级数',
  `sort` int DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `hidden` int DEFAULT NULL COMMENT '状态 0正常 1隐藏',
  `url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'vue路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='后台菜单表';

-- ----------------------------
-- Records of xtmd_menu
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (1, '基础信息管理', 0, 0, 1, 'jcmd', 'user', '2020-02-02 14:50:36', 0, NULL, '/');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (2, '客户资料', 1, 1, 0, 'customer', 'folder', '2020-02-02 14:51:50', 0, 'jcmd/customer', '/customer');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (3, '供应商资料', 1, 1, 3, 'supplier', 'folder', '2020-02-02 14:52:44', 0, 'jcmd/supplier', '/supplier');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (4, '商品资料', 1, 1, 1, 'shop', 'folder', '2020-02-02 14:53:51', 0, 'jcmd/shop', '/shop');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (7, '进销管理', 0, 0, 3, 'jxmd', 'sell', '2020-02-04 16:18:00', 0, NULL, NULL);
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (8, '采购', 7, 1, 1, 'purchase', 'jxmd-purchase', '2020-02-04 16:19:22', 0, 'jxmd/purchase', '/purchase');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (9, '采购退货', 7, 1, 2, 'purchaseExit', 'jxmd-purchaseExit', '2020-02-04 16:20:16', 0, 'jxmd/purchaseExit', '/purchaseExit');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (10, '销售', 7, 1, 3, 'sale', 'jxmd-sale', '2020-02-07 16:22:38', 0, 'jxmd/sale', '/sale');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (11, '销售退货', 7, 1, 4, 'saleExit', 'jxmd-saleExit', '2020-02-07 16:23:14', 0, 'jxmd/saleExit', '/saleExit');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (12, '仓库管理', 0, 0, 4, 'ckmd', 's-order', '2020-02-07 16:29:13', 0, NULL, '');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (13, '库存列表', 12, 1, 1, 'stockList', 'ckmd-stock', '2020-02-07 16:29:51', 0, 'ckmd/stockList', '/stockList');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (14, '入库清单', 12, 3, 2, 'depositoryIn', 'ckmd-depositoryIn', '2020-02-07 16:30:13', 0, 'ckmd/depositoryIn', '/depositoryIn');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (15, '出库清单', 12, 4, 3, 'depositoryOut', 'ckmd-depositoryOut', '2020-02-07 16:30:53', 0, 'ckmd/depositoryOut', '/depositoryOut');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (16, '系统管理', 0, 0, 5, 'xtmd', 's-platform', '2020-02-07 16:29:13', 0, NULL, NULL);
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (17, '员工管理', 16, 1, 0, 'adminList', 'setting', '2020-02-07 16:30:53', 0, 'xtmd/adminList', '/adminList');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (18, '角色管理', 16, 1, 1, 'roleLlist', 'xtmd-rolelist', '2020-02-07 16:30:53', 0, 'xtmd/roleList', '/roleList');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (19, '部门管理', 16, 1, 2, 'deptList', 'xtmd-deptList', '2020-02-07 16:30:53', 0, 'xtmd/deptList', '/deptList');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (20, '系统配置', 16, 1, 3, 'syscfg', 'setting', '2020-02-07 16:30:53', 0, 'xtmd/syscfg', '/syscfg');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (21, '仓库列表', 12, 2, 0, 'depository', 'setting', '2023-03-29 13:44:34', 0, 'ckmd/depositoryList', '/depository');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (22, '首页', 0, 0, 0, 'home', 's-home', '2023-03-31 16:10:48', 0, NULL, '/');
INSERT INTO `xtmd_menu` (`id`, `title`, `parent_id`, `level`, `sort`, `name`, `icon`, `create_time`, `hidden`, `url`, `path`) VALUES (23, '部门资料', 1, 1, 2, 'dept', 'folder', '2023-03-31 16:14:13', 0, 'jcmd/dept', '/dept');
COMMIT;

-- ----------------------------
-- Table structure for xtmd_role
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_role`;
CREATE TABLE `xtmd_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `status` int DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `build_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';

-- ----------------------------
-- Records of xtmd_role
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_role` (`role_id`, `role_name`, `description`, `status`, `del_flag`, `build_user`, `create_time`, `update_user`, `update_time`, `remark`) VALUES (1, 'admin', '拥有全部菜单', 0, '0', 'admin', '2023-04-25 22:17:15', 'admin', '2023-05-12 15:59:08', '超级管理员');
INSERT INTO `xtmd_role` (`role_id`, `role_name`, `description`, `status`, `del_flag`, `build_user`, `create_time`, `update_user`, `update_time`, `remark`) VALUES (2, 'sale', '首页、基础信息、备忘录、进销模块', 0, '0', 'admin', '2023-05-06 13:44:04', 'admin', '2023-05-12 16:02:06', '销售管理员及其销售人员');
INSERT INTO `xtmd_role` (`role_id`, `role_name`, `description`, `status`, `del_flag`, `build_user`, `create_time`, `update_user`, `update_time`, `remark`) VALUES (3, 'sys', '全部菜单', 0, '0', 'admin', '2023-05-12 15:58:57', 'admin', '2023-05-12 15:59:13', '系统管理员');
INSERT INTO `xtmd_role` (`role_id`, `role_name`, `description`, `status`, `del_flag`, `build_user`, `create_time`, `update_user`, `update_time`, `remark`) VALUES (4, 'warehouse', '首页、基础信息、备忘录、仓库管理', 0, '0', 'admin', '2023-05-12 15:58:59', 'admin', '2023-05-12 16:02:57', '仓库管理员及其仓库人员');
COMMIT;

-- ----------------------------
-- Table structure for xtmd_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `xtmd_role_menu_relation`;
CREATE TABLE `xtmd_role_menu_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xtmd_role_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (173, 2, 22);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (174, 2, 1);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (175, 2, 2);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (176, 2, 4);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (177, 2, 23);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (178, 2, 3);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (179, 2, 5);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (180, 2, 6);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (181, 2, 7);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (182, 2, 8);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (183, 2, 9);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (184, 2, 10);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (185, 2, 11);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (186, 1, 22);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (187, 1, 1);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (188, 1, 2);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (189, 1, 4);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (190, 1, 23);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (191, 1, 3);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (192, 1, 5);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (193, 1, 6);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (194, 1, 7);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (195, 1, 8);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (196, 1, 9);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (197, 1, 10);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (198, 1, 11);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (199, 1, 12);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (200, 1, 21);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (201, 1, 13);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (202, 1, 14);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (203, 1, 15);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (204, 1, 16);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (205, 1, 17);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (206, 1, 18);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (207, 1, 19);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (208, 1, 20);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (209, 3, 22);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (210, 3, 2);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (211, 3, 5);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (212, 3, 6);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (213, 3, 7);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (214, 3, 8);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (215, 3, 9);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (216, 3, 10);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (217, 3, 11);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (218, 3, 12);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (219, 3, 21);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (220, 3, 13);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (221, 3, 14);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (222, 3, 15);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (223, 3, 16);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (224, 3, 17);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (225, 3, 18);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (226, 3, 19);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (227, 3, 20);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (228, 4, 22);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (229, 4, 1);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (230, 4, 2);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (231, 4, 4);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (232, 4, 23);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (233, 4, 3);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (234, 4, 5);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (235, 4, 6);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (236, 4, 12);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (237, 4, 21);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (238, 4, 13);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (239, 4, 14);
INSERT INTO `xtmd_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (240, 4, 15);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
