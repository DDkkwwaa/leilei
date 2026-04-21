package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleExitMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.CkmdStockExample;
import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.model.JxmdSaleExample;
import com.shanzhu.purchase.model.JxmdSaleExit;
import com.shanzhu.purchase.model.JxmdSaleExitExample;
import com.shanzhu.purchase.service.CkDepositoryOutService;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JxSaleServiceImpl implements JxSaleService {

    private static final String REMARK_PARTIAL_SHORTAGE = "PARTIAL_SHORTAGE";

    @Resource
    private JxmdSaleMapper saleMapper;

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;

    @Resource
    private CkDepositoryOutService depositoryOutService;

    @Resource
    private JxmdSaleExitMapper saleExitMapper;

    @Resource
    private CkmdStockMapper stockMapper;

    @Override
    public int create(JxmdSale sale) {
        sale.setTime(LocalDateTime.now());
        if (sale.getPrice() != null && sale.getNum() != null) {
            sale.setTotalPrice(sale.getPrice().multiply(BigDecimal.valueOf(sale.getNum())));
        }
        sale.setStatus(1);
        if (StrUtil.isBlank(sale.getSaleNumber())) {
            sale.setSaleNumber(String.valueOf(UUidUtils.uuid()));
        }
        if (isStockShortage(sale.getShop(), sale.getNum())) {
            sale.setRemark(REMARK_PARTIAL_SHORTAGE);
        }
        return saleMapper.insertSelective(sale);
    }

    @Override
    public int update(JxmdSale sale) {
        return saleMapper.updateByPrimaryKeySelective(sale);
    }

    @Override
    public int delete(Long id) {
        return saleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JxmdSale> list() {
        return saleMapper.selectByExample(new JxmdSaleExample());
    }

    @Override
    public List<JxmdSale> list(String keyword, Integer pageSize, Integer pageNum) {
        return list(keyword, null, null, null, pageSize, pageNum);
    }

    @Override
    public List<JxmdSale> list(String keyword, Integer status, LocalDateTime startTime, LocalDateTime endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        JxmdSaleExample example = new JxmdSaleExample();
        JxmdSaleExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andSaleNumberLike("%" + keyword + "%");
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (startTime != null) {
            criteria.andTimeGreaterThanOrEqualTo(startTime);
        }
        if (endTime != null) {
            criteria.andTimeLessThanOrEqualTo(endTime);
        }
        return saleMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdate(JxmdSale sale) {
        if (sale.getId() != null && sale.getId() != 0) {
            return this.update(sale);
        }
        return this.create(sale);
    }

    @Override
    public List<Map<String, String>> getNumberAndCustomer() {
        List<Map<String, String>> list = new ArrayList<>();
        List<JxmdSale> saleList = saleMapper.selectByExample(new JxmdSaleExample());
        for (JxmdSale sale : saleList) {
            if (StrUtil.isBlank(sale.getSaleNumber())) {
                continue;
            }
            Map<String, String> item = new HashMap<>();
            item.put("value", sale.getSaleNumber());
            item.put("customer", sale.getSupplier());
            list.add(item);
        }
        return list;
    }

    @Override
    public int outSaleAndCheck(Map<String, Object> map) {
        String depositoryName = String.valueOf(map.get("depositoryName"));
        JxmdSale sale = (JxmdSale) map.get("saleList");
        if (sale == null || StrUtil.isBlank(sale.getSaleNumber())) {
            return 2;
        }
        List<CkmdDepositoryOut> exists = getOutRowsBySaleNumber(sale.getSaleNumber());
        if (!exists.isEmpty()) {
            return 2;
        }

        CkmdStockExample stockExample = new CkmdStockExample();
        stockExample.createCriteria()
                .andDepositoryEqualTo(depositoryName)
                .andShopEqualTo(sale.getShop());
        List<CkmdStock> stockList = stockMapper.selectByExample(stockExample);
        if (stockList == null || stockList.isEmpty()) {
            return 1;
        }
        CkmdStock stock = stockList.get(0);
        if (stock.getQuantity() == null || sale.getNum() == null || stock.getQuantity() < sale.getNum()) {
            return 1;
        }

        CkmdDepositoryOut out = new CkmdDepositoryOut();
        out.setSourceNumber(parseLongQuietly(sale.getSaleNumber()));
        out.setOutId(Long.valueOf(UUidUtils.uuid()));
        out.setDepository(depositoryName);
        out.setShopName(sale.getShop());
        out.setShopNumber(sale.getNum());
        out.setShopPrice(sale.getPrice());
        out.setTotalPrice(sale.getTotalPrice());
        out.setSpecs(sale.getSpecs());
        UsernamePasswordAuthenticationToken token =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        out.setOutUser(token == null ? "" : String.valueOf(token.getPrincipal()));
        out.setShopSupplier(sale.getSupplier());
        out.setStatus(1);
        out.setOutInspection(1);
        out.setCreateDate(LocalDateTime.now());
        depositoryOutMapper.insertSelective(out);
        return 0;
    }

    @Override
    public int checkInGoods(Map<String, Object> map) {
        JxmdSale sale = (JxmdSale) map.get("saleList");
        String remark = String.valueOf(map.get("remark"));
        if (sale == null || StrUtil.isBlank(sale.getSaleNumber())) {
            return 2;
        }

        JxmdSaleExitExample saleExitExample = new JxmdSaleExitExample();
        saleExitExample.createCriteria().andExitNumberEqualTo(sale.getSaleNumber());
        if (saleExitMapper.countByExample(saleExitExample) > 0) {
            return 1;
        }

        JxmdSaleExit saleExit = new JxmdSaleExit();
        saleExit.setNumber(String.valueOf(UUidUtils.uuid()));
        saleExit.setExitNumber(sale.getSaleNumber());
        saleExit.setNum(sale.getNum());
        saleExit.setPrice(sale.getPrice());
        saleExit.setTotalPrice(sale.getTotalPrice());
        saleExit.setTime(LocalDateTime.now());
        saleExit.setReason(remark);
        saleExit.setStatus(sale.getStatus() != null && sale.getStatus() == 1 ? 0 : 1);
        saleExitMapper.insertSelective(saleExit);
        return 0;
    }

    @Override
    public List<Map<String, Object>> waveList() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<JxmdSale> rows = saleMapper.selectByExample(new JxmdSaleExample());
        Map<String, List<JxmdSale>> groups = new HashMap<>();
        for (JxmdSale row : rows) {
            if (row.getSaleNumber() == null) {
                continue;
            }
            groups.computeIfAbsent(row.getSaleNumber(), key -> new ArrayList<>()).add(row);
        }

        for (Map.Entry<String, List<JxmdSale>> entry : groups.entrySet()) {
            String saleNumber = entry.getKey();
            List<JxmdSale> orderRows = entry.getValue();
            if (orderRows == null || orderRows.isEmpty()) {
                continue;
            }
            JxmdSale first = orderRows.get(0);
            long totalQuantity = 0L;
            BigDecimal totalAmount = BigDecimal.ZERO;
            boolean partialShortage = false;
            for (JxmdSale row : orderRows) {
                totalQuantity += row.getNum() == null ? 0L : row.getNum();
                if (row.getTotalPrice() != null) {
                    totalAmount = totalAmount.add(row.getTotalPrice());
                } else if (row.getPrice() != null && row.getNum() != null) {
                    totalAmount = totalAmount.add(row.getPrice().multiply(BigDecimal.valueOf(row.getNum())));
                }
                if (row.getRemark() != null && row.getRemark().contains(REMARK_PARTIAL_SHORTAGE)) {
                    partialShortage = true;
                }
            }

            List<CkmdDepositoryOut> outRows = getOutRowsBySaleNumber(saleNumber);
            String statusCode = "WAITING_WAVE";
            String statusLabel = "Waiting Wave";
            String waveNo = "";
            String depository = "";
            if (first.getStatus() != null && first.getStatus() == 0) {
                statusCode = "COMPLETED";
                statusLabel = "Completed";
            } else if (partialShortage) {
                statusCode = "PARTIAL_SHORTAGE";
                statusLabel = "Partial shortage";
            } else if (!outRows.isEmpty()) {
                CkmdDepositoryOut firstOut = outRows.get(0);
                waveNo = buildWaveNo(firstOut);
                depository = firstOut.getDepository();
                boolean hasPending = false;
                for (CkmdDepositoryOut out : outRows) {
                    if (out.getStatus() != null && out.getStatus() == 1) {
                        hasPending = true;
                        break;
                    }
                }
                if (hasPending) {
                    statusCode = "PICKING";
                    statusLabel = "Picking";
                } else {
                    statusCode = "COMPLETED";
                    statusLabel = "Completed";
                }
            }

            Map<String, Object> item = new HashMap<>();
            item.put("saleNumber", saleNumber);
            item.put("customer", first.getSupplier());
            item.put("saleUser", first.getSaleUser());
            item.put("time", first.getTime());
            item.put("rowCount", orderRows.size());
            item.put("totalQuantity", totalQuantity);
            item.put("totalAmount", totalAmount);
            item.put("statusCode", statusCode);
            item.put("statusLabel", statusLabel);
            item.put("waveNo", waveNo);
            item.put("depository", depository);
            result.add(item);
        }

        result.sort((a, b) -> {
            LocalDateTime timeA = (LocalDateTime) a.get("time");
            LocalDateTime timeB = (LocalDateTime) b.get("time");
            if (timeA == null && timeB == null) {
                return 0;
            }
            if (timeA == null) {
                return 1;
            }
            if (timeB == null) {
                return -1;
            }
            return timeB.compareTo(timeA);
        });
        return result;
    }

    @Override
    public int createWave(String saleNumber, String depositoryName) {
        if (StrUtil.isBlank(saleNumber) || StrUtil.isBlank(depositoryName)) {
            return 4;
        }
        List<JxmdSale> saleRows = getSaleRowsBySaleNumber(saleNumber);
        if (saleRows.isEmpty()) {
            return 5;
        }
        if (!getOutRowsBySaleNumber(saleNumber).isEmpty()) {
            return 2;
        }
        for (JxmdSale sale : saleRows) {
            Map<String, Object> payload = new HashMap<>();
            payload.put("depositoryName", depositoryName);
            payload.put("saleList", sale);
            int code = this.outSaleAndCheck(payload);
            if (code != 0) {
                return code;
            }
        }
        return 1;
    }

    @Override
    public int finishPick(String saleNumber) {
        if (StrUtil.isBlank(saleNumber)) {
            return 4;
        }
        List<CkmdDepositoryOut> outRows = getOutRowsBySaleNumber(saleNumber);
        if (outRows.isEmpty()) {
            return 2;
        }
        for (CkmdDepositoryOut out : outRows) {
            if (out.getStatus() != null && out.getStatus() == 1) {
                int checkResult = depositoryOutService.checkById(out.getId());
                if (checkResult <= 0) {
                    return 3;
                }
            }
        }
        JxmdSale update = new JxmdSale();
        update.setStatus(0);
        JxmdSaleExample example = new JxmdSaleExample();
        example.createCriteria().andSaleNumberEqualTo(saleNumber);
        saleMapper.updateByExampleSelective(update, example);
        return 1;
    }

    @Override
    public int updateStatusBySaleNumber(String saleNumber, Integer status) {
        if (StrUtil.isBlank(saleNumber) || status == null) {
            return 0;
        }
        JxmdSale update = new JxmdSale();
        update.setStatus(status);
        JxmdSaleExample example = new JxmdSaleExample();
        example.createCriteria().andSaleNumberEqualTo(saleNumber);
        return saleMapper.updateByExampleSelective(update, example);
    }

    @Override
    public List<Map<String, Object>> pickPath(String saleNumber) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<CkmdDepositoryOut> outRows = getOutRowsBySaleNumber(saleNumber);
        Map<String, Map<String, Object>> merged = new HashMap<>();
        for (CkmdDepositoryOut out : outRows) {
            if (out == null || out.getShopName() == null) {
                continue;
            }
            String key = out.getDepository() + "::" + out.getShopName();
            Map<String, Object> row = merged.computeIfAbsent(key, k -> {
                Map<String, Object> obj = new HashMap<>();
                obj.put("depository", out.getDepository());
                obj.put("shopName", out.getShopName());
                obj.put("needQuantity", 0L);
                obj.put("location", resolveLocation(out.getShopName(), out.getDepository()));
                return obj;
            });
            long now = ((Number) row.get("needQuantity")).longValue();
            row.put("needQuantity", now + (out.getShopNumber() == null ? 0L : out.getShopNumber()));
        }
        result.addAll(merged.values());
        result.sort((a, b) -> compareLocation(String.valueOf(a.get("location")), String.valueOf(b.get("location"))));
        return result;
    }

    private List<JxmdSale> getSaleRowsBySaleNumber(String saleNumber) {
        JxmdSaleExample example = new JxmdSaleExample();
        example.createCriteria().andSaleNumberEqualTo(saleNumber);
        return saleMapper.selectByExample(example);
    }

    private List<CkmdDepositoryOut> getOutRowsBySaleNumber(String saleNumber) {
        Long sourceNumber = parseLongQuietly(saleNumber);
        if (sourceNumber == null) {
            return new ArrayList<>();
        }
        return depositoryOutService.getRowInfoByPurchaseNumber(sourceNumber.intValue());
    }

    private Long parseLongQuietly(String value) {
        try {
            return Long.valueOf(value);
        } catch (Exception ex) {
            return null;
        }
    }

    private String buildWaveNo(CkmdDepositoryOut out) {
        if (out == null) {
            return "";
        }
        LocalDateTime baseTime = out.getCreateDate() != null ? out.getCreateDate() : out.getDate();
        if (baseTime == null) {
            return "WAVE-" + out.getOutId();
        }
        String timePart = baseTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String slot = baseTime.getMinute() < 30 ? "A" : "B";
        return "WAVE-" + timePart + "-" + slot;
    }

    private String resolveLocation(String shopName, String depository) {
        CkmdStockExample example = new CkmdStockExample();
        example.createCriteria().andShopEqualTo(shopName).andDepositoryEqualTo(depository);
        List<CkmdStock> stocks = stockMapper.selectByExample(example);
        if (stocks != null && !stocks.isEmpty() && stocks.get(0).getAddress() != null) {
            return stocks.get(0).getAddress();
        }
        return depository == null ? "" : depository;
    }

    private int compareLocation(String a, String b) {
        String aa = a == null ? "" : a.trim();
        String bb = b == null ? "" : b.trim();
        String ap = aa.replaceAll("[0-9]", "");
        String bp = bb.replaceAll("[0-9]", "");
        int pre = ap.compareToIgnoreCase(bp);
        if (pre != 0) {
            return pre;
        }
        return Integer.compare(parseFirstNumber(aa), parseFirstNumber(bb));
    }

    private int parseFirstNumber(String value) {
        String num = value == null ? "" : value.replaceAll("[^0-9]", "");
        if (num.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(num);
        } catch (Exception ex) {
            return 0;
        }
    }

    private boolean isStockShortage(String shopName, Long saleNum) {
        if (shopName == null || saleNum == null) {
            return false;
        }
        CkmdStockExample example = new CkmdStockExample();
        example.createCriteria().andShopEqualTo(shopName);
        List<CkmdStock> stocks = stockMapper.selectByExample(example);
        long total = 0L;
        for (CkmdStock stock : stocks) {
            total += stock.getQuantity() == null ? 0L : stock.getQuantity();
        }
        return total < saleNum;
    }
}
