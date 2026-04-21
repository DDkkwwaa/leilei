package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleExitMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.CkmdStockExample;
import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.model.JxmdSaleExample;
import com.shanzhu.purchase.model.JxmdSaleExit;
import com.shanzhu.purchase.model.JxmdSaleExitExample;
import com.shanzhu.purchase.service.CkStockService;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "DashboardController", description = "首页看板")
@Tag(name = "DashboardController", description = "首页看板")
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private JxmdSaleMapper saleMapper;

    @Resource
    private JxmdSaleExitMapper saleExitMapper;

    @Resource
    private CkmdStockMapper stockMapper;

    @Resource
    private CkStockService stockService;

    @ApiOperation("看板汇总")
    @GetMapping("/summary")
    public commonResult<Map<String, Object>> summary() {
        Map<String, Object> result = new LinkedHashMap<>();
        List<JxmdSale> sales = saleMapper.selectByExample(new JxmdSaleExample());
        List<JxmdSaleExit> exits = saleExitMapper.selectByExample(new JxmdSaleExitExample());
        List<CkmdStock> stocks = stockMapper.selectByExample(new CkmdStockExample());
        List<Map<String, Object>> warningList = stockService.warningList();

        LocalDate today = LocalDate.now();
        long todayOrderCount = sales.stream()
                .filter(row -> row.getTime() != null && row.getTime().toLocalDate().isEqual(today))
                .map(JxmdSale::getSaleNumber)
                .distinct()
                .count();
        BigDecimal todaySalesAmount = sales.stream()
                .filter(row -> row.getTime() != null && row.getTime().toLocalDate().isEqual(today))
                .map(row -> row.getTotalPrice() == null ? BigDecimal.ZERO : row.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long warningCount = warningList.stream()
                .filter(item -> Boolean.TRUE.equals(item.get("warning")))
                .count();

        BigDecimal todayExitAmount = exits.stream()
                .filter(row -> row.getTime() != null && row.getTime().toLocalDate().isEqual(today))
                .map(row -> row.getTotalPrice() == null ? BigDecimal.ZERO : row.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal lossRate = BigDecimal.ZERO;
        if (todaySalesAmount.compareTo(BigDecimal.ZERO) > 0) {
            lossRate = todayExitAmount.multiply(BigDecimal.valueOf(100))
                    .divide(todaySalesAmount, 2, BigDecimal.ROUND_HALF_UP);
        }

        result.put("todayOrderCount", todayOrderCount);
        result.put("todaySalesAmount", todaySalesAmount);
        result.put("warningCount", warningCount);
        result.put("lossRate", lossRate);
        result.put("orderTrend7", buildOrderTrend(sales, 7));
        result.put("orderTrend30", buildOrderTrend(sales, 30));
        result.put("categoryStock", buildCategoryStock(stocks));
        return commonResult.success(result);
    }

    @ApiOperation("看板与分析数据导出")
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        Map<String, Object> summary = summary().getData();
        List<Map<String, Object>> warningList = stockService.warningList();
        List<Map<String, Object>> replenishList = stockService.replenishSuggestions();

        HSSFWorkbook workbook = new HSSFWorkbook();
        writeTrendSheet(workbook, "order_trend_30d", castList(summary.get("orderTrend30")));
        writeWarningSheet(workbook, "stock_warning", warningList);
        writeReplenishSheet(workbook, "replenish", replenishList);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=dashboard_analysis.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    private List<Map<String, Object>> buildOrderTrend(List<JxmdSale> sales, int days) {
        Map<LocalDate, Long> trend = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = days - 1; i >= 0; i--) {
            trend.put(today.minusDays(i), 0L);
        }
        for (JxmdSale sale : sales) {
            LocalDateTime time = sale.getTime();
            if (time == null) {
                continue;
            }
            LocalDate date = time.toLocalDate();
            if (trend.containsKey(date)) {
                trend.put(date, trend.get(date) + 1L);
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<LocalDate, Long> entry : trend.entrySet()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("date", entry.getKey().toString());
            item.put("value", entry.getValue());
            result.add(item);
        }
        return result;
    }

    private List<Map<String, Object>> buildCategoryStock(List<CkmdStock> stocks) {
        Map<String, Long> grouped = new LinkedHashMap<>();
        for (CkmdStock stock : stocks) {
            String category = stock.getShopType();
            if (category == null || category.trim().isEmpty()) {
                category = "未分类";
            }
            long qty = stock.getQuantity() == null ? 0L : stock.getQuantity();
            grouped.put(category, grouped.getOrDefault(category, 0L) + qty);
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : grouped.entrySet()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("name", entry.getKey());
            item.put("value", entry.getValue());
            result.add(item);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> castList(Object value) {
        if (value == null) {
            return new ArrayList<>();
        }
        return (List<Map<String, Object>>) value;
    }

    private void writeTrendSheet(HSSFWorkbook workbook, String name, List<Map<String, Object>> rows) {
        HSSFSheet sheet = workbook.createSheet(name);
        HSSFRow head = sheet.createRow(0);
        head.createCell(0).setCellValue("date");
        head.createCell(1).setCellValue("orderCount");
        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> row = rows.get(i);
            HSSFRow line = sheet.createRow(i + 1);
            line.createCell(0).setCellValue(String.valueOf(row.get("date")));
            line.createCell(1).setCellValue(Long.parseLong(String.valueOf(row.get("value"))));
        }
    }

    private void writeWarningSheet(HSSFWorkbook workbook, String name, List<Map<String, Object>> rows) {
        HSSFSheet sheet = workbook.createSheet(name);
        HSSFRow head = sheet.createRow(0);
        String[] columns = {"shop", "depository", "quantity", "safeStock", "gap", "warning"};
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = head.createCell(i);
            cell.setCellValue(columns[i]);
        }
        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> row = rows.get(i);
            HSSFRow line = sheet.createRow(i + 1);
            line.createCell(0).setCellValue(String.valueOf(row.get("shop")));
            line.createCell(1).setCellValue(String.valueOf(row.get("depository")));
            line.createCell(2).setCellValue(Long.parseLong(String.valueOf(row.get("quantity"))));
            line.createCell(3).setCellValue(Long.parseLong(String.valueOf(row.get("safeStock"))));
            line.createCell(4).setCellValue(Long.parseLong(String.valueOf(row.get("gap"))));
            line.createCell(5).setCellValue(Boolean.TRUE.equals(row.get("warning")) ? "Y" : "N");
        }
    }

    private void writeReplenishSheet(HSSFWorkbook workbook, String name, List<Map<String, Object>> rows) {
        HSSFSheet sheet = workbook.createSheet(name);
        HSSFRow head = sheet.createRow(0);
        String[] columns = {"shop", "depository", "quantity", "targetStock", "suggestQuantity"};
        for (int i = 0; i < columns.length; i++) {
            head.createCell(i).setCellValue(columns[i]);
        }
        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> row = rows.get(i);
            HSSFRow line = sheet.createRow(i + 1);
            line.createCell(0).setCellValue(String.valueOf(row.get("shop")));
            line.createCell(1).setCellValue(String.valueOf(row.get("depository")));
            line.createCell(2).setCellValue(Long.parseLong(String.valueOf(row.get("quantity"))));
            line.createCell(3).setCellValue(Long.parseLong(String.valueOf(row.get("targetStock"))));
            line.createCell(4).setCellValue(Long.parseLong(String.valueOf(row.get("suggestQuantity"))));
        }
    }
}
