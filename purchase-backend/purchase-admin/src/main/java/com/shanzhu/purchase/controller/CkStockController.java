package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.service.CkStockService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@Api(tags = "CkStockController", description = "ck-2商品库存")
@Tag(name = "CkStockController", description = "系统管理-商品库存")
@RequestMapping("/stock")
public class CkStockController {

    @Resource
    private CkStockService stockService;

    @ApiOperation("添加库存")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdStock stock) {
        int count = stockService.addOrUpdateStock(stock);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("修改库存-整合到add")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody CkmdStock stock) {
        int count = stockService.update(stock);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除库存")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = stockService.delete(id);
        return commonResult.success(count);
    }

    @ApiOperation("获取所有库存")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<CkmdStock>> listAll() {
        List<CkmdStock> stockList = stockService.list();
        return commonResult.success(stockList);
    }

    @ApiOperation("根据商品或库存数量获取库存")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<CkmdStock>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<CkmdStock> menuList = stockService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(menuList));
    }

    @ApiOperation("柱状图-库存列表-仓库集合的")
    @RequestMapping(value = "/histogramList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> histogramList() {
        List<Map> histogramList = stockService.histogramList();

        return commonResult.success(histogramList);
    }

    @ApiOperation("饼图-库存列表-仓库集合的")
    @RequestMapping(value = "/Pie", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> Pie() {
        List shopMap = stockService.shopMap();
        return commonResult.success(shopMap);
    }

    @ApiOperation("搴撳瓨棰勮鍒楄〃")
    @RequestMapping(value = "/warningList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> warningList() {
        List<Map<String, Object>> warningList = stockService.warningList();
        return commonResult.success(warningList);
    }

    @ApiOperation("预警数量")
    @RequestMapping(value = "/warningCount", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<Long> warningCount() {
        return commonResult.success(stockService.warningCount());
    }

    @ApiOperation("琛ヨ揣寤鸿鍒楄〃")
    @RequestMapping(value = "/replenishList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> replenishList() {
        List<Map<String, Object>> replenishList = stockService.replenishSuggestions();
        return commonResult.success(replenishList);
    }

    @ApiOperation("库存分析列表")
    @RequestMapping(value = "/analysisList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> analysisList(
            @RequestParam(value = "shopType", required = false) String shopType
    ) {
        return commonResult.success(stockService.analysisList(shopType));
    }

    @ApiOperation("导出库存分析")
    @RequestMapping(value = "/exportAnalysisExcel", method = RequestMethod.GET)
    public void exportAnalysisExcel(@RequestParam(value = "shopType", required = false) String shopType,
                                    HttpServletResponse response) throws IOException {
        List<Map<String, Object>> analysisList = stockService.analysisList(shopType);
        List<Map<String, Object>> replenishList = stockService.replenishSuggestions();
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet analysisSheet = workbook.createSheet("stock_analysis");
        HSSFRow analysisHead = analysisSheet.createRow(0);
        analysisHead.createCell(0).setCellValue("shop");
        analysisHead.createCell(1).setCellValue("shopType");
        analysisHead.createCell(2).setCellValue("stockQuantity");
        analysisHead.createCell(3).setCellValue("recentSevenDaySale");
        analysisHead.createCell(4).setCellValue("shortageFrequency");
        for (int i = 0; i < analysisList.size(); i++) {
            Map<String, Object> row = analysisList.get(i);
            HSSFRow line = analysisSheet.createRow(i + 1);
            line.createCell(0).setCellValue(String.valueOf(row.get("shop")));
            line.createCell(1).setCellValue(String.valueOf(row.get("shopType")));
            line.createCell(2).setCellValue(Long.parseLong(String.valueOf(row.get("stockQuantity"))));
            line.createCell(3).setCellValue(Long.parseLong(String.valueOf(row.get("recentSevenDaySale"))));
            line.createCell(4).setCellValue(Long.parseLong(String.valueOf(row.get("shortageFrequency"))));
        }

        HSSFSheet replenishSheet = workbook.createSheet("replenish");
        HSSFRow replenishHead = replenishSheet.createRow(0);
        replenishHead.createCell(0).setCellValue("shop");
        replenishHead.createCell(1).setCellValue("depository");
        replenishHead.createCell(2).setCellValue("quantity");
        replenishHead.createCell(3).setCellValue("targetStock");
        replenishHead.createCell(4).setCellValue("suggestQuantity");
        for (int i = 0; i < replenishList.size(); i++) {
            Map<String, Object> row = replenishList.get(i);
            HSSFRow line = replenishSheet.createRow(i + 1);
            line.createCell(0).setCellValue(String.valueOf(row.get("shop")));
            line.createCell(1).setCellValue(String.valueOf(row.get("depository")));
            line.createCell(2).setCellValue(Long.parseLong(String.valueOf(row.get("quantity"))));
            line.createCell(3).setCellValue(Long.parseLong(String.valueOf(row.get("targetStock"))));
            line.createCell(4).setCellValue(Long.parseLong(String.valueOf(row.get("suggestQuantity"))));
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=stock_analysis.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
