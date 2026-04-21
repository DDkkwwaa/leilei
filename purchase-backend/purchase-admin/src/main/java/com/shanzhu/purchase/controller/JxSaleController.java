package com.shanzhu.purchase.controller;

import cn.hutool.core.util.StrUtil;
import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.ResultCode;
import com.shanzhu.purchase.util.UUidUtils;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "JxSaleController", description = "Sale order")
@Tag(name = "JxSaleController", description = "Purchase & Sale - Sale order")
@RequestMapping("/sale")
public class JxSaleController {

    @Resource
    private JxSaleService saleService;

    @Resource
    private OperationLogService operationLogService;

    @ApiOperation("Create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdSale sale) {
        int count = saleService.addOrUpdate(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("Import sale orders from Excel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public commonResult<Map<String, Object>> importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return commonResult.failed("File is empty");
        }
        int total = 0;
        int success = 0;
        int partialShortage = 0;
        List<String> errors = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return commonResult.failed("No data in Excel");
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                total++;
                try {
                    JxmdSale sale = mapRowToSale(row);
                    int count = saleService.create(sale);
                    if (count > 0) {
                        success++;
                        if (sale.getRemark() != null && sale.getRemark().contains("PARTIAL_SHORTAGE")) {
                            partialShortage++;
                        }
                    } else {
                        errors.add("Row " + (i + 1) + " import failed");
                    }
                } catch (Exception ex) {
                    errors.add("Row " + (i + 1) + " format error: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            return commonResult.failed("Import failed: " + ex.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("success", success);
        result.put("partialShortage", partialShortage);
        result.put("failed", Math.max(0, total - success));
        result.put("errors", errors);
        operationLogService.record(null, "ORDER_IMPORT", "SALE", null,
                "Import sale orders, total=" + total + ", success=" + success, request);
        return commonResult.success(result);
    }

    @ApiOperation("Update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JxmdSale sale) {
        int count = saleService.update(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("Delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = saleService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("List all")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JxmdSale>> listAll() {
        List<JxmdSale> customerList = saleService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("Page list with orderNo/status/time")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdSale>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        LocalDateTime startTime = startDate == null ? null : startDate.atStartOfDay();
        LocalDateTime endTime = endDate == null ? null : endDate.atTime(LocalTime.MAX);
        List<JxmdSale> saleList = saleService.list(keyword, status, startTime, endTime, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(saleList));
    }

    @ApiOperation("Get sale number and customer")
    @RequestMapping(value = "/getNumberAndCustomer", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getNumberAndCustomer() {
        List list = saleService.getNumberAndCustomer();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("No data");
    }

    @ApiOperation("Sale outbound")
    @RequestMapping(value = "/outSale")
    @ResponseBody
    public commonResult outSale(@RequestParam(value = "depositoryName", required = false) String depositoryName,
                                @RequestBody JxmdSale saleList) {
        Map<String, Object> map = new HashMap<>();
        map.put("depositoryName", depositoryName);
        map.put("saleList", saleList);
        int result = saleService.outSaleAndCheck(map);
        if (result == 1) {
            return commonResult.failed(ResultCode.LACK_STOCK);
        } else if (result == 2) {
            return commonResult.failed(ResultCode.OVER_EXIT_SALE);
        }
        return commonResult.success(ResultCode.SUCCESS);
    }

    @ApiOperation("Sale return")
    @RequestMapping(value = "/checkInGoods")
    @ResponseBody
    public commonResult checkInGoods(@RequestParam(value = "remark", required = false) String remark,
                                     @RequestBody JxmdSale saleList) {
        Map<String, Object> map = new HashMap<>();
        map.put("remark", remark);
        map.put("saleList", saleList);
        int result = saleService.checkInGoods(map);
        if (result == 1) {
            return commonResult.failed(ResultCode.CREATE_S);
        } else if (result == 2) {
            return commonResult.failed(ResultCode.NOT_EXIT);
        }
        return commonResult.success(ResultCode.SUCCESS);
    }

    @ApiOperation("Wave and pick task list")
    @RequestMapping(value = "/waveList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> waveList() {
        return commonResult.success(saleService.waveList());
    }

    @ApiOperation("Create wave and dispatch picking")
    @RequestMapping(value = "/createWave", method = RequestMethod.POST)
    @ResponseBody
    public commonResult createWave(@RequestParam("saleNumber") String saleNumber,
                                   @RequestParam("depositoryName") String depositoryName,
                                   HttpServletRequest request) {
        int result = saleService.createWave(saleNumber, depositoryName);
        if (result == 1) {
            operationLogService.record(null, "WAVE_CREATE", "SALE", saleNumber,
                    "Create wave and dispatch picking, depository=" + depositoryName, request);
            return commonResult.success("Success");
        }
        if (result == 2) {
            return commonResult.failed("Wave already exists");
        }
        if (result == 4) {
            return commonResult.failed("Invalid params");
        }
        if (result == 5) {
            return commonResult.failed("Order not found");
        }
        return commonResult.failed("Stock not enough or create failed");
    }

    @ApiOperation("Get mobile pick path")
    @RequestMapping(value = "/pickPath", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> pickPath(@RequestParam("saleNumber") String saleNumber) {
        return commonResult.success(saleService.pickPath(saleNumber));
    }

    @ApiOperation("Mobile scan confirm for weak-network sync")
    @RequestMapping(value = "/pickScanConfirm", method = RequestMethod.POST)
    @ResponseBody
    public commonResult pickScanConfirm(@RequestParam("saleNumber") String saleNumber,
                                        @RequestParam("shopName") String shopName,
                                        @RequestParam("quantity") Long quantity,
                                        HttpServletRequest request) {
        if (StrUtil.isBlank(saleNumber) || StrUtil.isBlank(shopName) || quantity == null || quantity <= 0) {
            return commonResult.failed("Invalid params");
        }
        operationLogService.record(null, "PICK_SCAN", "SALE", saleNumber,
                "Scan confirm product=" + shopName + ", quantity=" + quantity, request);
        return commonResult.success("OK");
    }

    @ApiOperation("Finish picking")
    @RequestMapping(value = "/finishPick", method = RequestMethod.POST)
    @ResponseBody
    public commonResult finishPick(@RequestParam("saleNumber") String saleNumber, HttpServletRequest request) {
        int result = saleService.finishPick(saleNumber);
        if (result == 1) {
            operationLogService.record(null, "PICK_FINISH", "SALE", saleNumber,
                    "Finish picking and update sale status", request);
            return commonResult.success("Success");
        }
        if (result == 2) {
            return commonResult.failed("No executable wave task");
        }
        if (result == 4) {
            return commonResult.failed("Invalid params");
        }
        return commonResult.failed("Stock not enough or audit failed");
    }

    @ApiOperation("Manual update order status")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@RequestParam("saleNumber") String saleNumber,
                                     @RequestParam("status") Integer status,
                                     HttpServletRequest request) {
        int result = saleService.updateStatusBySaleNumber(saleNumber, status);
        if (result > 0) {
            operationLogService.record(null, "ORDER_STATUS_UPDATE", "SALE", saleNumber,
                    "Manual update order status to:" + status, request);
            return commonResult.success(result);
        }
        return commonResult.failed("Update failed");
    }

    private JxmdSale mapRowToSale(Row row) {
        JxmdSale sale = new JxmdSale();
        String saleNumber = text(row.getCell(0));
        sale.setSaleNumber(StrUtil.isBlank(saleNumber) ? String.valueOf(UUidUtils.uuid()) : saleNumber);
        sale.setSupplier(text(row.getCell(1)));
        sale.setShop(text(row.getCell(2)));
        sale.setNum(longValue(row.getCell(3)));
        sale.setPrice(decimalValue(row.getCell(4)));
        sale.setSpecs(text(row.getCell(5)));
        sale.setSaleUser(text(row.getCell(6)));
        String timeStr = text(row.getCell(7));
        if (!StrUtil.isBlank(timeStr)) {
            try {
                sale.setTime(LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (Exception ignored) {
            }
        }
        if (sale.getPrice() != null && sale.getNum() != null) {
            sale.setTotalPrice(sale.getPrice().multiply(BigDecimal.valueOf(sale.getNum())));
        }
        sale.setStatus(1);
        return sale;
    }

    private String text(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue() == null ? "" : cell.getStringCellValue().trim();
    }

    private Long longValue(Cell cell) {
        String val = text(cell);
        if (StrUtil.isBlank(val)) {
            return 0L;
        }
        try {
            return Long.parseLong(val.replace(".0", ""));
        } catch (Exception ex) {
            return 0L;
        }
    }

    private BigDecimal decimalValue(Cell cell) {
        String val = text(cell);
        if (StrUtil.isBlank(val)) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(val);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }
}
