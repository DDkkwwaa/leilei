package com.shanzhu.purchase.task;

import cn.hutool.core.util.StrUtil;
import com.shanzhu.purchase.mapper.CkmdDepositoryMapper;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.JcmdCustomerMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.CkmdDepositoryExample;
import com.shanzhu.purchase.model.CkmdDepositoryOutExample;
import com.shanzhu.purchase.model.JcmdCustomer;
import com.shanzhu.purchase.model.JcmdCustomerExample;
import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.model.JxmdSaleExample;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WaveAutoScheduler {

    @Resource
    private JxmdSaleMapper saleMapper;

    @Resource
    private JcmdCustomerMapper customerMapper;

    @Resource
    private CkmdDepositoryMapper depositoryMapper;

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;

    @Resource
    private JxSaleService saleService;

    @Resource
    private OperationLogService operationLogService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void autoCreateWaveByRegion() {
        try {
            List<JxmdSale> pendingRows = queryPendingSales();
            if (pendingRows.isEmpty()) {
                return;
            }
            Map<String, List<JxmdSale>> bySaleNumber = groupBySaleNumber(pendingRows);
            List<CkmdDepository> depositories = queryEnabledDepositories();
            if (depositories.isEmpty()) {
                log.warn("autoCreateWaveByRegion skip: no enabled depository");
                return;
            }

            for (Map.Entry<String, List<JxmdSale>> entry : bySaleNumber.entrySet()) {
                String saleNumber = entry.getKey();
                if (StrUtil.isBlank(saleNumber) || hasOutRows(saleNumber)) {
                    continue;
                }
                JxmdSale first = entry.getValue().get(0);
                String region = resolveRegionByCustomer(first.getSupplier());
                String depositoryName = pickDepositoryByRegion(region, depositories);
                int result = saleService.createWave(saleNumber, depositoryName);
                if (result == 1) {
                    operationLogService.record("SYSTEM", "WAVE_CREATE", "SALE", saleNumber,
                            "定时任务自动生成波次，区域=" + region + "，仓库=" + depositoryName, null);
                }
            }
        } catch (Exception ex) {
            log.error("autoCreateWaveByRegion failed", ex);
        }
    }

    private List<JxmdSale> queryPendingSales() {
        JxmdSaleExample example = new JxmdSaleExample();
        example.createCriteria().andStatusEqualTo(1);
        return saleMapper.selectByExample(example);
    }

    private Map<String, List<JxmdSale>> groupBySaleNumber(List<JxmdSale> rows) {
        Map<String, List<JxmdSale>> groups = new LinkedHashMap<>();
        for (JxmdSale row : rows) {
            if (row == null || StrUtil.isBlank(row.getSaleNumber())) {
                continue;
            }
            groups.computeIfAbsent(row.getSaleNumber(), key -> new ArrayList<>()).add(row);
        }
        return groups;
    }

    private List<CkmdDepository> queryEnabledDepositories() {
        CkmdDepositoryExample enabled = new CkmdDepositoryExample();
        enabled.createCriteria().andStatusEqualTo(0);
        List<CkmdDepository> list = depositoryMapper.selectByExample(enabled);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return depositoryMapper.selectByExample(new CkmdDepositoryExample());
    }

    private boolean hasOutRows(String saleNumber) {
        Long sourceNumber = parseLongQuietly(saleNumber);
        if (sourceNumber == null) {
            return false;
        }
        CkmdDepositoryOutExample outExample = new CkmdDepositoryOutExample();
        outExample.createCriteria().andSourceNumberEqualTo(sourceNumber);
        return depositoryOutMapper.countByExample(outExample) > 0;
    }

    private String resolveRegionByCustomer(String customerName) {
        if (StrUtil.isBlank(customerName)) {
            return "默认";
        }
        JcmdCustomerExample example = new JcmdCustomerExample();
        example.createCriteria().andNameEqualTo(customerName);
        List<JcmdCustomer> rows = customerMapper.selectByExample(example);
        if (rows == null || rows.isEmpty()) {
            return "默认";
        }
        return normalizeRegion(rows.get(0).getAddress());
    }

    private String normalizeRegion(String address) {
        if (StrUtil.isBlank(address)) {
            return "默认";
        }
        String val = address.trim();
        String[] splitChars = new String[]{"-", " ", "，", ","};
        for (String splitChar : splitChars) {
            if (val.contains(splitChar)) {
                String first = val.split(splitChar)[0].trim();
                if (StrUtil.isNotBlank(first)) {
                    return first;
                }
            }
        }
        String[] suffix = new String[]{"区", "县", "市"};
        for (String s : suffix) {
            int idx = val.indexOf(s);
            if (idx > 0) {
                return val.substring(0, idx + 1);
            }
        }
        return val.length() > 6 ? val.substring(0, 6) : val;
    }

    private String pickDepositoryByRegion(String region, List<CkmdDepository> depositories) {
        if (depositories == null || depositories.isEmpty()) {
            return "";
        }
        for (CkmdDepository depository : depositories) {
            if (depository == null) {
                continue;
            }
            String area = depository.getArea();
            String address = depository.getAddress();
            String name = depository.getName();
            if ((area != null && area.contains(region))
                    || (address != null && address.contains(region))
                    || (name != null && name.contains(region))) {
                return name;
            }
        }
        return depositories.get(0).getName();
    }

    private Long parseLongQuietly(String value) {
        try {
            return Long.valueOf(value);
        } catch (Exception ex) {
            return null;
        }
    }
}
