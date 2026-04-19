package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.JxmdPurchase;
import com.shanzhu.purchase.service.JxPurchaseService;
import com.shanzhu.purchase.util.ResultCode;
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
@Api(tags = "JxPurchaseController", description = "jx-1采购表")
@Tag(name = "JxPurchaseController", description = "进销管理-采购表")
@RequestMapping("/purchase")
public class JxPurchaseController {

    @Resource
    private JxPurchaseService purchaseService;

    @ApiOperation("添加采购单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdPurchase purchase) {
        int count = purchaseService.addOrUpdate(purchase);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.success(count);
    }

    @ApiOperation("修改采购-合并到add")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JxmdPurchase purchase) {
        int count = purchaseService.update(purchase);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation(" 删除采购")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = purchaseService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有采购表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JxmdPurchase>> listAll() {
        List<JxmdPurchase> customerList = purchaseService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("根据采购人 获取 ")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdPurchase>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<JxmdPurchase> customerList = purchaseService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(customerList));
    }

    /**
     * 入库操作
     * 前端传到后端的参数为 null问题
     *
     * @param
     * @return
     */
    @ApiOperation("采购单完成后 入库")
    @RequestMapping(value = "/putStock", method = RequestMethod.POST)
    @ResponseBody
    public commonResult putStock(@RequestParam(value = "depositoryName", required = false) String depositoryName,
                                 @RequestBody JxmdPurchase purchaseList) {

        int count = purchaseService.putStock(depositoryName, purchaseList);
        if (count == 0) {
            return commonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        if (count == 2) {
            return commonResult.failed(ResultCode.LACK_STOCK);
        }
        if (count == 3) {
            return commonResult.failed(ResultCode.REPEAT);
        }
        return commonResult.success(ResultCode.SUCCESS);
    }

    @ApiOperation("获取采购编号和采购供应商")
    @RequestMapping(value = "/getNumberAndSupplierName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getNumberAndSupplierName() {
        List list = purchaseService.getNumberAndSupplierName();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("数据为空");
    }

    @ApiOperation("校验后----退货")
    @RequestMapping(value = "/checkAndExitGoods", method = RequestMethod.POST)
    @ResponseBody
    public commonResult checkAndExitGoods(@RequestParam(value = "remark") String remark,
                                          @RequestBody JxmdPurchase purchase) {
        purchase.setRemark(remark);
        int count = purchaseService.checkAndExitGoods(purchase);
        if (count == 0) {  //已退货
            return commonResult.success(ResultCode.OVER_EXIT_GOODS.getCode(), ResultCode.OVER_EXIT_GOODS.getMessage());
        }
        //有问题
        return commonResult.success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

}
