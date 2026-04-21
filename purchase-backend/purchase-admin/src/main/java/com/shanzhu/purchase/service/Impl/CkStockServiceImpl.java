package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkStockDao;
import com.shanzhu.purchase.dto.ShopAndSaleAndStockAndPurchaseDto;
import com.shanzhu.purchase.dto.StockUpDto;
import com.shanzhu.purchase.mapper.CkmdDepositoryMapper;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.CkmdDepositoryExample;
import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.CkmdStockExample;
import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.model.JxmdSaleExample;
import com.shanzhu.purchase.service.CkStockService;
import com.shanzhu.purchase.util.calculationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CkStockServiceImpl implements CkStockService {

    @Resource
    private CkmdStockMapper stockMapper;

    @Resource
    private CkStockDao stockDao;

    @Resource
    private CkmdDepositoryMapper depositoryMapper;

    @Resource
    private JxmdSaleMapper saleMapper;

    @Override
    public int create(CkmdStock stock) {
        stock.setCreateTime(LocalDateTime.now());
        return stockMapper.insert(stock);
    }

    @Override
    public int update(CkmdStock stock) {
        stock.setUpdateTime(LocalDateTime.now());
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int delete(Long id) {
        return stockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CkmdStock> list() {
        return stockMapper.selectByExample(new CkmdStockExample());
    }

    @Override
    public List<CkmdStock> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CkmdStockExample example = new CkmdStockExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andShopLike("%" + keyword + "%");
        }
        return stockMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdateStock(CkmdStock stock) {
        if (stock.getId() != null && stock.getId() != 0) {
            return this.update(stock);
        }
        return this.create(stock);
    }

    @Override
    public int updateQuantityByTwoName(StockUpDto stockUpDto) {
        if (stockUpDto.getFlag() == 0) {
            return stockDao.addQuantityByTwoName(stockUpDto.getQuantity(), stockUpDto.getShopName(), stockUpDto.getDepositoryName());
        }
        return stockDao.reduceQuantityByTwoName(stockUpDto.getQuantity(), stockUpDto.getShopName(), stockUpDto.getDepositoryName());
    }

    @Override
    public List<CkmdStock> getStockByTwoName(String shopName, String depositoryName) {
        return stockDao.getStockByTwoName(shopName, depositoryName);
    }

    @Override
    public List<Map> histogramList() {
        List<Map> allList = new ArrayList<>();
        List<String> shopList = new ArrayList<>();
        List<Long> saleList = new ArrayList<>();
        List<Long> stockList = new ArrayList<>();
        List<Long> purchaseList = new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        long base = 0L;
        List<ShopAndSaleAndStockAndPurchaseDto> histogramData = stockDao.getNumberToShopAndStockAndPurchase();
        if (histogramData != null) {
            for (ShopAndSaleAndStockAndPurchaseDto dto : histogramData) {
                shopList.add(dto.getShopName());
                stockList.add(dto.getStockNumber() == null ? base : dto.getStockNumber());
                saleList.add(dto.getSaleNumber() == null ? base : dto.getSaleNumber());
                purchaseList.add(dto.getPurchaseNumber() == null ? base : dto.getPurchaseNumber());
            }
        }
        map.put("shopList", shopList);
        map.put("saleList", saleList);
        map.put("stockList", stockList);
        map.put("purchaseList", purchaseList);
        allList.add(map);
        return allList;
    }

    @Override
    public List shopMap() {
        List<Map> list = new ArrayList<>();
        long totalVolume = 0L;
        List<CkmdDepository> depositoryList = depositoryMapper.selectByExample(new CkmdDepositoryExample());
        for (CkmdDepository depository : depositoryList) {
            totalVolume += depository.getStockTotal() == null ? 0L : depository.getStockTotal();
        }
        BigDecimal bigKg = calculationUtils.INTCalculatingVolumeToWeight(totalVolume);
        List<CkmdStock> stockList = stockMapper.selectByExample(new CkmdStockExample());
        for (CkmdStock stock : stockList) {
            String shop = stock.getShop();
            long quantity = stock.getQuantity() == null ? 0L : stock.getQuantity();
            BigDecimal divideValue = bigKg.compareTo(BigDecimal.ZERO) == 0
                    ? BigDecimal.TEN
                    : BigDecimal.valueOf(quantity).divide(bigKg, 0, BigDecimal.ROUND_UP);
            if (divideValue.compareTo(BigDecimal.TEN) < 0) {
                divideValue = BigDecimal.TEN;
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("name", shop);
            map.put("value", String.valueOf(divideValue));
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> warningList() {
        List<Map<String, Object>> warningList = new ArrayList<>();
        List<Map<String, Object>> baseRows = stockDao.listWarningBase();
        if (baseRows == null || baseRows.isEmpty()) {
            return warningList;
        }
        LocalDate nearExpireDay = LocalDate.now().plusDays(3);
        for (Map<String, Object> row : baseRows) {
            long quantity = numberValue(row.get("quantity"));
            long safeStock = Math.max(0L, numberValue(row.get("safeStock")));
            LocalDate expireDate = dateValue(row.get("expireDate"));
            boolean lowStockWarning = quantity < safeStock;
            boolean nearExpireWarning = expireDate != null && !expireDate.isAfter(nearExpireDay);
            boolean warning = lowStockWarning || nearExpireWarning;
            long gap = Math.max(0L, safeStock - quantity);

            Map<String, Object> item = new HashMap<>();
            item.put("stockId", row.get("stockId"));
            item.put("shop", row.get("shop"));
            item.put("shopType", row.get("shopType"));
            item.put("depository", row.get("depository"));
            item.put("quantity", quantity);
            item.put("safeStock", safeStock);
            item.put("expireDate", expireDate == null ? null : expireDate.toString());
            item.put("warningType", lowStockWarning && nearExpireWarning ? "BOTH" : (lowStockWarning ? "LOW_STOCK" : (nearExpireWarning ? "NEAR_EXPIRE" : "NONE")));
            item.put("warning", warning);
            item.put("gap", gap);
            warningList.add(item);
        }
        warningList.sort((a, b) -> {
            boolean aWarning = Boolean.TRUE.equals(a.get("warning"));
            boolean bWarning = Boolean.TRUE.equals(b.get("warning"));
            if (aWarning != bWarning) {
                return aWarning ? -1 : 1;
            }
            return Long.compare(numberValue(b.get("gap")), numberValue(a.get("gap")));
        });
        return warningList;
    }

    @Override
    public List<Map<String, Object>> replenishSuggestions() {
        List<Map<String, Object>> suggestions = new ArrayList<>();
        Map<String, Long> recentSaleMap = buildRecentSaleMap();
        Map<String, Integer> effectiveDaysMap = buildRecentSaleEffectiveDays();
        List<CkmdStock> stocks = stockMapper.selectByExample(new CkmdStockExample());
        if (stocks == null || stocks.isEmpty()) {
            return suggestions;
        }
        for (CkmdStock stock : stocks) {
            long quantity = stock.getQuantity() == null ? 0L : stock.getQuantity();
            long recentSevenDaySale = recentSaleMap.getOrDefault(stock.getShop(), 0L);
            int effectiveDays = Math.max(1, effectiveDaysMap.getOrDefault(stock.getShop(), 1));
            long averageDailySale = (long) Math.ceil((double) recentSevenDaySale / (double) effectiveDays);
            long suggestQuantity = averageDailySale * 3L - quantity;
            if (suggestQuantity < 0L) {
                suggestQuantity = 0L;
            } else if (suggestQuantity > 0L && suggestQuantity < 5L) {
                suggestQuantity = 5L;
            }
            if (suggestQuantity <= 0L) {
                continue;
            }
            Map<String, Object> item = new HashMap<>();
            item.put("stockId", stock.getId());
            item.put("shop", stock.getShop());
            item.put("depository", stock.getDepository());
            item.put("quantity", quantity);
            item.put("safeStock", averageDailySale * 3L);
            item.put("targetStock", averageDailySale * 3L);
            item.put("suggestQuantity", suggestQuantity);
            item.put("recentSevenDaySale", recentSevenDaySale);
            item.put("effectiveDays", effectiveDays);
            item.put("reason", "近7日销量移动平均补货建议");
            suggestions.add(item);
        }
        suggestions.sort((a, b) -> Long.compare(numberValue(b.get("suggestQuantity")), numberValue(a.get("suggestQuantity"))));
        return suggestions;
    }

    @Override
    public Long warningCount() {
        return warningList().stream().filter(item -> Boolean.TRUE.equals(item.get("warning"))).count();
    }

    @Override
    public List<Map<String, Object>> analysisList(String shopType) {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Long> recentSaleMap = buildRecentSaleMap();
        Map<String, Long> stockTotalMap = new HashMap<>();
        Map<String, String> stockTypeMap = new HashMap<>();
        List<CkmdStock> stocks = stockMapper.selectByExample(new CkmdStockExample());
        for (CkmdStock stock : stocks) {
            if (stock == null || stock.getShop() == null) {
                continue;
            }
            if (shopType != null && shopType.trim().length() > 0 && !shopType.equals(stock.getShopType())) {
                continue;
            }
            stockTotalMap.put(stock.getShop(), stockTotalMap.getOrDefault(stock.getShop(), 0L) + (stock.getQuantity() == null ? 0L : stock.getQuantity()));
            stockTypeMap.put(stock.getShop(), stock.getShopType());
        }

        Map<String, Long> shortageCountMap = new HashMap<>();
        JxmdSaleExample saleExample = new JxmdSaleExample();
        saleExample.createCriteria().andTimeGreaterThanOrEqualTo(LocalDateTime.now().minusDays(30));
        List<JxmdSale> saleRows = saleMapper.selectByExample(saleExample);
        for (JxmdSale sale : saleRows) {
            if (sale == null || sale.getShop() == null || sale.getNum() == null) {
                continue;
            }
            if (!stockTotalMap.containsKey(sale.getShop())) {
                continue;
            }
            if (stockTotalMap.getOrDefault(sale.getShop(), 0L) < sale.getNum()) {
                shortageCountMap.put(sale.getShop(), shortageCountMap.getOrDefault(sale.getShop(), 0L) + 1L);
            }
        }

        for (Map.Entry<String, Long> entry : stockTotalMap.entrySet()) {
            String shop = entry.getKey();
            Map<String, Object> row = new HashMap<>();
            row.put("shop", shop);
            row.put("shopType", stockTypeMap.get(shop));
            row.put("stockQuantity", entry.getValue());
            row.put("recentSevenDaySale", recentSaleMap.getOrDefault(shop, 0L));
            row.put("shortageFrequency", shortageCountMap.getOrDefault(shop, 0L));
            result.add(row);
        }
        result.sort((a, b) -> {
            long aFreq = numberValue(a.get("shortageFrequency"));
            long bFreq = numberValue(b.get("shortageFrequency"));
            if (aFreq != bFreq) {
                return Long.compare(bFreq, aFreq);
            }
            return String.valueOf(a.get("shop")).compareTo(String.valueOf(b.get("shop")));
        });
        return result;
    }

    private Map<String, Long> buildRecentSaleMap() {
        Map<String, Long> saleMap = new HashMap<>();
        List<Map<String, Object>> recentSales = stockDao.getRecentSevenDaySales();
        if (recentSales == null || recentSales.isEmpty()) {
            return saleMap;
        }
        for (Map<String, Object> item : recentSales) {
            if (item == null || item.get("shop") == null) {
                continue;
            }
            saleMap.put(String.valueOf(item.get("shop")), numberValue(item.get("saleTotal")));
        }
        return saleMap;
    }

    private Map<String, Integer> buildRecentSaleEffectiveDays() {
        Map<String, Integer> effectiveDaysMap = new HashMap<>();
        JxmdSaleExample example = new JxmdSaleExample();
        example.createCriteria().andTimeGreaterThanOrEqualTo(LocalDateTime.now().minusDays(7));
        List<JxmdSale> rows = saleMapper.selectByExample(example);
        Map<String, Map<LocalDate, Boolean>> dateMark = new HashMap<>();
        for (JxmdSale row : rows) {
            if (row == null || row.getShop() == null || row.getTime() == null || row.getNum() == null || row.getNum() <= 0) {
                continue;
            }
            dateMark.computeIfAbsent(row.getShop(), key -> new HashMap<>()).put(row.getTime().toLocalDate(), Boolean.TRUE);
        }
        for (Map.Entry<String, Map<LocalDate, Boolean>> entry : dateMark.entrySet()) {
            effectiveDaysMap.put(entry.getKey(), Math.max(1, entry.getValue().size()));
        }
        return effectiveDaysMap;
    }

    private long numberValue(Object value) {
        if (value == null) {
            return 0L;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            return Long.parseLong(String.valueOf(value));
        } catch (Exception ex) {
            return 0L;
        }
    }

    private LocalDate dateValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }
        if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalDate();
        }
        String text = String.valueOf(value);
        if (text.length() >= 10) {
            text = text.substring(0, 10);
        }
        try {
            return LocalDate.parse(text);
        } catch (Exception ex) {
            return null;
        }
    }
}
