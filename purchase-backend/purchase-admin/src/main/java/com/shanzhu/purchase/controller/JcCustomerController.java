package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.JcmdCustomer;
import com.shanzhu.purchase.service.JcCustomerService;
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
@Api(tags = "JcCustomerController", description = "jc-1客户表")
@Tag(name = "JcCustomerController", description = "基础信息管理-客户表")
@RequestMapping("/customer")
public class JcCustomerController {

    @Resource
    private JcCustomerService customerService;

    @ApiOperation("添加客户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JcmdCustomer customer) {
        int count = customerService.create(customer);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改客户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JcmdCustomer customer) {
        int count = customerService.update(customer);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除客户")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = customerService.delete(id);
        if (count > 0) {
            return commonResult.success("删除成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有客户")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JcmdCustomer>> listAll() {
        List<JcmdCustomer> customerList = customerService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("根据客户姓名或 地区获取客户")
    @RequestMapping(value = "/listByName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JcmdCustomer>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        //获取查询的list
        List<JcmdCustomer> customerList = customerService.list(keyword, pageNum, pageSize);
        return commonResult.success(commonPage.restPage(customerList));
    }

    @ApiOperation("获取销售编号和销售客户")
    @RequestMapping(value = "/getIDAndCustomerName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getNumberAndCustomer() {
        List list = customerService.getIDAndCustomerName();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("数据为空");
    }

}
