package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleExitMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.model.CkmdDepositoryOutExample;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        return createWave(saleNumber, depositoryName, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createWave(String saleNumber, String depositoryName, boolean forceReplan) {
        if (StrUtil.isBlank(saleNumber) || StrUtil.isBlank(depositoryName)) {
            return 4;
        }
        List<JxmdSale> saleRows = getSaleRowsBySaleNumber(saleNumber);
        if (saleRows.isEmpty()) {
            return 5;
        }
        List<CkmdDepositoryOut> outRows = getOutRowsBySaleNumber(saleNumber);
        if (!outRows.isEmpty()) {
            if (!forceReplan) {
                return 2;
            }
            for (CkmdDepositoryOut row : outRows) {
                if (row != null && row.getStatus() != null && row.getStatus() == 0) {
                    return 7;
                }
            }
        }

        for (JxmdSale sale : saleRows) {
            if (!hasEnoughStockForWaveRow(sale, depositoryName)) {
                return 3;
            }
        }

        if (!outRows.isEmpty() && forceReplan) {
            CkmdDepositoryOutExample deleteExample = new CkmdDepositoryOutExample();
            deleteExample.createCriteria().andSourceNumberEqualTo(parseLongQuietly(saleNumber));
            depositoryOutMapper.deleteByExample(deleteExample);
        }

        for (JxmdSale sale : saleRows) {
            int insertResult = insertWaveOutRow(sale, depositoryName);
            if (insertResult <= 0) {
                throw new IllegalStateException("create wave row failed");
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
        boolean hasPending = false;
        for (CkmdDepositoryOut out : outRows) {
            if (out.getStatus() != null && out.getStatus() == 1) {
                hasPending = true;
                if (!hasStockRow(out.getShopName(), out.getDepository())) {
                    return 6;
                }
                int checkResult = depositoryOutService.checkById(out.getId());
                if (checkResult <= 0) {
                    return 3;
                }
            }
        }
        if (!hasPending) {
            return 2;
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
        Map<String, Map<String, Object>> merged = new LinkedHashMap<>();
        for (CkmdDepositoryOut out : outRows) {
            if (out == null || out.getShopName() == null) {
                continue;
            }
            String location = resolveLocation(out.getShopName(), out.getDepository());
            String key = location + "::" + out.getShopName();
            Map<String, Object> row = merged.computeIfAbsent(key, k -> {
                Map<String, Object> obj = new HashMap<>();
                obj.put("depository", out.getDepository());
                obj.put("shopName", out.getShopName());
                obj.put("needQuantity", 0L);
                obj.put("location", location);
                return obj;
            });
            long now = ((Number) row.get("needQuantity")).longValue();
            row.put("needQuantity", now + (out.getShopNumber() == null ? 0L : out.getShopNumber()));
        }

        List<String> uniqueLocations = new ArrayList<>();
        for (Map<String, Object> row : merged.values()) {
            String location = String.valueOf(row.get("location"));
            if (!uniqueLocations.contains(location)) {
                uniqueLocations.add(location);
            }
        }
        uniqueLocations.sort(this::compareLocation);
        Map<String, Integer> locationOrderMap = new HashMap<>();
        for (int i = 0; i < uniqueLocations.size(); i++) {
            locationOrderMap.put(uniqueLocations.get(i), i + 1);
        }
        String routeSequence = String.join(" -> ", uniqueLocations);

        result.addAll(merged.values());
        result.sort(Comparator
                .comparing((Map<String, Object> x) -> locationOrderMap.getOrDefault(String.valueOf(x.get("location")), Integer.MAX_VALUE))
                .thenComparing(x -> String.valueOf(x.get("shopName"))));

        for (Map<String, Object> row : result) {
            String location = String.valueOf(row.get("location"));
            row.put("locationOrder", locationOrderMap.getOrDefault(location, 0));
            row.put("locationCount", uniqueLocations.size());
            row.put("routeSequence", routeSequence);
            row.put("routeLocations", uniqueLocations);
        }
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
        return depositoryOutService.getRowInfoByPurchaseNumber(sourceNumber);
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
        List<String> ta = splitLocationTokens(aa);
        List<String> tb = splitLocationTokens(bb);
        int size = Math.min(ta.size(), tb.size());
        for (int i = 0; i < size; i++) {
            String xa = ta.get(i);
            String xb = tb.get(i);
            boolean na = isDigits(xa);
            boolean nb = isDigits(xb);
            if (na && nb) {
                int cmp = Integer.compare(parseIntSafe(xa), parseIntSafe(xb));
                if (cmp != 0) {
                    return cmp;
                }
            } else {
                int cmp = xa.compareToIgnoreCase(xb);
                if (cmp != 0) {
                    return cmp;
                }
            }
        }
        int lenCmp = Integer.compare(ta.size(), tb.size());
        if (lenCmp != 0) {
            return lenCmp;
        }
        return aa.compareToIgnoreCase(bb);
    }

    private List<String> splitLocationTokens(String value) {
        List<String> tokens = new ArrayList<>();
        if (StrUtil.isBlank(value)) {
            return tokens;
        }
        StringBuilder current = new StringBuilder();
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (Character.isLetterOrDigit(c)) {
                current.append(c);
            } else if (current.length() > 0) {
                tokens.add(current.toString());
                current.setLength(0);
            }
        }
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        return tokens;
    }

    private boolean isDigits(String value) {
        if (StrUtil.isBlank(value)) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
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

    private boolean hasStockRow(String shopName, String depository) {
        if (StrUtil.isBlank(shopName) || StrUtil.isBlank(depository)) {
            return false;
        }
        CkmdStockExample example = new CkmdStockExample();
        example.createCriteria().andShopEqualTo(shopName).andDepositoryEqualTo(depository);
        List<CkmdStock> list = stockMapper.selectByExample(example);
        return list != null && !list.isEmpty();
    }

    private boolean hasEnoughStockForWaveRow(JxmdSale sale, String depositoryName) {
        if (sale == null || StrUtil.isBlank(sale.getShop()) || sale.getNum() == null || StrUtil.isBlank(depositoryName)) {
            return false;
        }
        CkmdStockExample stockExample = new CkmdStockExample();
        stockExample.createCriteria()
                .andDepositoryEqualTo(depositoryName)
                .andShopEqualTo(sale.getShop());
        List<CkmdStock> stockList = stockMapper.selectByExample(stockExample);
        if (stockList == null || stockList.isEmpty()) {
            return false;
        }
        CkmdStock stock = stockList.get(0);
        return stock.getQuantity() != null && stock.getQuantity() >= sale.getNum();
    }

    private int insertWaveOutRow(JxmdSale sale, String depositoryName) {
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
        return depositoryOutMapper.insertSelective(out);
    }
}
