package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.JxmdPurchaseExit;
import com.shanzhu.purchase.service.JxPurchaseExitService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Controller
@Api(tags = "JxPurchaseExitController", description = "jx-1采购退货表")
@Tag(name = "JxPurchaseExitController", description = "进销管理-采购退货表")
@RequestMapping("/purchaseExit")
public class JxPurchaseExitController {

    @Resource
    private JxPurchaseExitService purchaseExitService;

    @ApiOperation("添加记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdPurchaseExit purchaseExit) {
        int count = purchaseExitService.addOrUpdate(purchaseExit);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改记录")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JxmdPurchaseExit purchaseExit) {
        int count = purchaseExitService.update(purchaseExit);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除采购退货单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = purchaseExitService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("根据退货编号-分页获取 ")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdPurchaseExit>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<JxmdPurchaseExit> customerList = purchaseExitService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(customerList));
    }

}
