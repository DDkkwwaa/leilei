package com.shanzhu.purchase.task;

import com.shanzhu.purchase.service.CkStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class StockWarningScanTask {

    @Resource
    private CkStockService stockService;

    @Scheduled(cron = "0 0 * * * ?")
    public void scanStockWarning() {
        long count = stockService.warningCount();
        log.info("stock warning scan finished, warning count={}", count);
    }
}
