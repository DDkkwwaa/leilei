package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.util.ResultCode;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Api(tags = "JxSaleController", description = "jx-3销售表")
@Tag(name = "JxSaleController", description = "进销管理-销售表")
@RequestMapping("/sale")
public class JxSaleController {

    @Resource
    private JxSaleService saleService;

    @ApiOperation("添加 ")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdSale sale) {
        int count = saleService.addOrUpdate(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改 ")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JxmdSale sale) {
        int count = saleService.update(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("批量删除 ")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = saleService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有 ")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JxmdSale>> listAll() {
        List<JxmdSale> customerList = saleService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("根据编号获取")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdSale>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<JxmdSale> saleList = saleService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(saleList));
    }

    @ApiOperation("获取销售编号和销售客户")
    @RequestMapping(value = "/getNumberAndCustomer", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getNumberAndCustomer() {
        List list = saleService.getNumberAndCustomer();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("数据为空");
    }

    @ApiOperation("销售-  出库")
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

    @ApiOperation("回收")
    @RequestMapping(value = "/checkInGoods")
    @ResponseBody
    public commonResult checkInGoods(
            @RequestParam(value = "remark", required = false) String remark,
            @RequestBody JxmdSale saleList
    ) {
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
}
