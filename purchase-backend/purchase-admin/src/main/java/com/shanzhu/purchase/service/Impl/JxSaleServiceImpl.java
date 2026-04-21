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
        sale.setSaleNumber(String.valueOf(UUidUtils.uuid()));
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
            for (JxmdSale row : orderRows) {
                totalQuantity += row.getNum() == null ? 0L : row.getNum();
                if (row.getTotalPrice() != null) {
                    totalAmount = totalAmount.add(row.getTotalPrice());
                } else if (row.getPrice() != null && row.getNum() != null) {
                    totalAmount = totalAmount.add(row.getPrice().multiply(BigDecimal.valueOf(row.getNum())));
                }
            }

            List<CkmdDepositoryOut> outRows = getOutRowsBySaleNumber(saleNumber);
            String statusCode = "WAITING_WAVE";
            String statusLabel = "待生成波次";
            String waveNo = "";
            String depository = "";
            if (first.getStatus() != null && first.getStatus() == 0) {
                statusCode = "COMPLETED";
                statusLabel = "已完成";
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
                    statusLabel = "拣货中";
                } else {
                    statusCode = "COMPLETED";
                    statusLabel = "已完成";
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
}
