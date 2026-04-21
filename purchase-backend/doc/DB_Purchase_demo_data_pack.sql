SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 1) Seed sale orders: 40 orders x 3 rows = 120 rows
INSERT INTO jxmd_sale (
  sale_Number,
  sale_user,
  shop,
  shop_type,
  supplier,
  num,
  specs,
  price,
  total_price,
  time,
  status,
  remark
)
WITH RECURSIVE order_seq AS (
  SELECT 1 AS n
  UNION ALL
  SELECT n + 1 FROM order_seq WHERE n < 40
),
item_seq AS (
  SELECT 1 AS m UNION ALL SELECT 2 UNION ALL SELECT 3
),
product_pick AS (
  SELECT
    ROW_NUMBER() OVER (ORDER BY id) AS rn,
    shop,
    COALESCE(shop_type, 'GEN') AS shop_type,
    COALESCE(speces, 'pcs') AS speces
  FROM ckmd_stock
  ORDER BY id
  LIMIT 3
)
SELECT
  CAST(900000000 + o.n AS CHAR(20)) AS sale_Number,
  CASE WHEN MOD(o.n, 3) = 0 THEN 'manager' ELSE 'admin' END AS sale_user,
  p.shop,
  p.shop_type,
  CONCAT('Customer-', LPAD(o.n, 3, '0')) AS supplier,
  5 + MOD(o.n * i.m, 20) AS num,
  p.speces AS specs,
  CAST(8 + MOD(o.n + i.m, 12) + (i.m * 0.35) AS DECIMAL(10,2)) AS price,
  CAST((5 + MOD(o.n * i.m, 20)) * (8 + MOD(o.n + i.m, 12) + (i.m * 0.35)) AS DECIMAL(10,2)) AS total_price,
  DATE_SUB(NOW(), INTERVAL (o.n * 3 + i.m) HOUR) AS time,
  CASE WHEN MOD(o.n, 4) = 0 THEN 0 ELSE 1 END AS status,
  CASE WHEN MOD(o.n, 5) = 0 AND i.m = 1 THEN 'PARTIAL_SHORTAGE' ELSE NULL END AS remark
FROM order_seq o
JOIN item_seq i
JOIN product_pick p ON p.rn = i.m
WHERE NOT EXISTS (
  SELECT 1
  FROM jxmd_sale s
  WHERE s.sale_Number = CAST(900000000 + o.n AS CHAR(20))
);

-- 2) Seed outbound rows for even-numbered orders (for pick/finish demo)
INSERT INTO ckmd_depository_out (
  source_number,
  out_id,
  depository,
  shop_name,
  shop_price,
  shop_number,
  total_price,
  specs,
  date,
  out_user,
  shop_supplier,
  status,
  out_inspection,
  create_date
)
SELECT
  CAST(s.sale_Number AS UNSIGNED) AS source_number,
  CAST(800000000 + CAST(s.sale_Number AS UNSIGNED) % 1000000 + s.id AS UNSIGNED) AS out_id,
  CASE WHEN MOD(CAST(s.sale_Number AS UNSIGNED), 4) = 0 THEN '虚拟仓库A' ELSE '虚拟仓库B' END AS depository,
  s.shop AS shop_name,
  s.price AS shop_price,
  s.num AS shop_number,
  s.total_price AS total_price,
  s.specs AS specs,
  CASE WHEN s.status = 0 THEN s.time ELSE NULL END AS date,
  CASE WHEN MOD(CAST(s.sale_Number AS UNSIGNED), 3) = 0 THEN 'manager' ELSE 'admin' END AS out_user,
  s.supplier AS shop_supplier,
  CASE WHEN s.status = 0 THEN 0 ELSE 1 END AS status,
  CASE WHEN s.status = 0 THEN 0 ELSE 1 END AS out_inspection,
  DATE_SUB(NOW(), INTERVAL MOD(CAST(s.sale_Number AS UNSIGNED), 120) HOUR) AS create_date
FROM jxmd_sale s
WHERE CAST(s.sale_Number AS UNSIGNED) BETWEEN 900000001 AND 900000040
  AND MOD(CAST(s.sale_Number AS UNSIGNED), 2) = 0
  AND NOT EXISTS (
    SELECT 1
    FROM ckmd_depository_out o
    WHERE o.source_number = CAST(s.sale_Number AS UNSIGNED)
      AND o.shop_name = s.shop
  );

-- 3) Strengthen warning demo: refresh safety_stock and location
UPDATE ckmd_stock
SET
  safety_stock = CASE
    WHEN id % 3 = 1 THEN COALESCE(quantity, 0) + 25
    WHEN id % 3 = 2 THEN COALESCE(quantity, 0) + 10
    ELSE GREATEST(8, COALESCE(quantity, 0) - 3)
  END,
  address = CASE
    WHEN id % 5 = 0 THEN 'A-01-01'
    WHEN id % 5 = 1 THEN 'A-02-03'
    WHEN id % 5 = 2 THEN 'B-01-02'
    WHEN id % 5 = 3 THEN 'B-03-01'
    ELSE 'C-02-02'
  END,
  update_time = NOW();

-- 4) Extra operation logs for timeline richness: +180 rows
INSERT INTO xtmd_operation_log (
  operator,
  action_type,
  biz_type,
  biz_no,
  content,
  request_path,
  request_method,
  ip,
  create_time
)
WITH RECURSIVE log_seq AS (
  SELECT 1 AS n
  UNION ALL
  SELECT n + 1 FROM log_seq WHERE n < 180
)
SELECT
  CASE
    WHEN n % 9 = 0 THEN 'SYSTEM'
    WHEN n % 4 = 0 THEN 'manager'
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
    WHEN n % 8 = 0 THEN 'AUTH'
    WHEN n % 8 IN (5, 6) THEN 'STOCK'
    ELSE 'SALE'
  END AS biz_type,
  CONCAT('DEMO', LPAD(200000 + n, 8, '0')) AS biz_no,
  CONCAT('Demo event #', n) AS content,
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
  CONCAT('10.20.30.', (n % 200) + 1) AS ip,
  DATE_SUB(NOW(), INTERVAL (n + 24) HOUR) AS create_time
FROM log_seq;

SET FOREIGN_KEY_CHECKS = 1;
