package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.JxmdSaleExit;
import com.shanzhu.purchase.service.JxSaleExitService;
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
@Api(tags = "JxSaleExitController", description = "jx-3销售退货表")
@Tag(name = "JxSaleExitController", description = "进销管理-销售退货表")
@RequestMapping("/saleExit")
public class JxSaleExitController {

    @Resource
    private JxSaleExitService saleExitService;

    @ApiOperation("添加 ")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdSaleExit saleExit) {
        int count = saleExitService.addOrUpdate(saleExit);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("批量删除 ")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = saleExitService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有 ")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JxmdSaleExit>> listAll() {
        List<JxmdSaleExit> saleExit = saleExitService.list();
        return commonResult.success(saleExit);
    }

    @ApiOperation("根据编号获取")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdSaleExit>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<JxmdSaleExit> saleList = saleExitService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(saleList));
    }

}
