SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS `xtmd_operation_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `operator` VARCHAR(64) NOT NULL COMMENT 'operator',
  `action_type` VARCHAR(64) NOT NULL COMMENT 'action type',
  `biz_type` VARCHAR(64) DEFAULT NULL COMMENT 'biz type',
  `biz_no` VARCHAR(64) DEFAULT NULL COMMENT 'biz no',
  `content` VARCHAR(512) DEFAULT NULL COMMENT 'content',
  `request_path` VARCHAR(255) DEFAULT NULL COMMENT 'request path',
  `request_method` VARCHAR(16) DEFAULT NULL COMMENT 'request method',
  `ip` VARCHAR(64) DEFAULT NULL COMMENT 'ip',
  `create_time` DATETIME NOT NULL COMMENT 'create time',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_action_type` (`action_type`),
  KEY `idx_operator` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='operation log';

DELETE FROM `xtmd_operation_log`;

INSERT INTO `xtmd_operation_log` (
  `operator`,
  `action_type`,
  `biz_type`,
  `biz_no`,
  `content`,
  `request_path`,
  `request_method`,
  `ip`,
  `create_time`
)
WITH RECURSIVE seq AS (
  SELECT 1 AS n
  UNION ALL
  SELECT n + 1 FROM seq WHERE n < 240
)
SELECT
  CASE
    WHEN n % 9 = 0 THEN 'SYSTEM'
    WHEN n % 5 = 0 THEN 'manager'
    ELSE 'admin'
  END AS operator,
  CASE (n % 8)
    WHEN 0 THEN 'LOGIN'
    WHEN 1 THEN 'ORDER_IMPORT'
    WHEN 2 THEN 'WAVE_CREATE'
    WHEN 3 THEN 'PICK_SCAN'
    WHEN 4 THEN 'PICK_FINISH'
    WHEN 5 THEN 'INBOUND'
    WHEN 6 THEN 'OUTBOUND'
    ELSE 'ORDER_STATUS_UPDATE'
  END AS action_type,
  CASE
    WHEN n % 8 IN (0) THEN 'AUTH'
    WHEN n % 8 IN (5,6) THEN 'STOCK'
    ELSE 'SALE'
  END AS biz_type,
  CONCAT('BIZ', LPAD(100000 + n, 8, '0')) AS biz_no,
  CASE (n % 8)
    WHEN 0 THEN CONCAT('User login success #', n)
    WHEN 1 THEN CONCAT('Import sale orders batch #', n)
    WHEN 2 THEN CONCAT('Create wave task #', n)
    WHEN 3 THEN CONCAT('Pick scan confirm #', n)
    WHEN 4 THEN CONCAT('Finish picking #', n)
    WHEN 5 THEN CONCAT('Inbound audit completed #', n)
    WHEN 6 THEN CONCAT('Outbound audit completed #', n)
    ELSE CONCAT('Manual order status update #', n)
  END AS content,
  CASE (n % 8)
    WHEN 0 THEN '/login'
    WHEN 1 THEN '/sale/importExcel'
    WHEN 2 THEN '/sale/createWave'
    WHEN 3 THEN '/sale/pickScanConfirm'
    WHEN 4 THEN '/sale/finishPick'
    WHEN 5 THEN '/depositoryIn/checkById'
    WHEN 6 THEN '/depositoryOut/checkById'
    ELSE '/sale/updateStatus'
  END AS request_path,
  CASE WHEN n % 2 = 0 THEN 'POST' ELSE 'GET' END AS request_method,
  CONCAT('192.168.10.', (n % 200) + 1) AS ip,
  DATE_SUB(NOW(), INTERVAL n HOUR) AS create_time
FROM seq;

SET FOREIGN_KEY_CHECKS = 1;
