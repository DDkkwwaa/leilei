package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.JxmdPurchase;
import com.shanzhu.purchase.service.JxPurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@Api(tags = "pdfController", description = "所有的pdf导出功能")
@Tag(name = "pdfController", description = "所有的pdf导出功能")
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private JxPurchaseService purchaseService;

    @ApiOperation("采购pdf导出")
    @RequestMapping(value = "/pdfToPurchase", method = RequestMethod.GET)
    @ResponseBody
    public void pdfCreate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建HSSFWorkbook对象,  excel的文档对象
        HSSFSheet sheet = workbook.createSheet("信息表"); //excel的表单

        List<JxmdPurchase> PurchaseList = purchaseService.list(); //获取采购的所有数据

        String fileName = "userinf" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"学号", "姓名", "身份类型", "登录密码"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }


        //在表中存放查询到的数据放入对应的列
        for (JxmdPurchase purchase : PurchaseList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(purchase.getId());
            row1.createCell(1).setCellValue(purchase.getNumber());
            row1.createCell(2).setCellValue(purchase.getPurchaseUser());
            row1.createCell(3).setCellValue(purchase.getShop());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
