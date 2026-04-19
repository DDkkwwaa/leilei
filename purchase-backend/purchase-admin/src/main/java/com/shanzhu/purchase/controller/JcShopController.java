package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.JcmdShop;
import com.shanzhu.purchase.model.JcmdShopType;
import com.shanzhu.purchase.service.JcShopService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(tags = "JcShopCustomer", description = "jc-2商品表")
@Tag(name = "JcShopCustomer", description = "基础信息管理-商品")
@RequestMapping("/shop")
public class JcShopController {

    @Resource
    private JcShopService shopService;

    @ApiOperation("添加商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JcmdShop shop) {
        int count = shopService.updateOrAddById(shop);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@PathVariable Long id, @RequestBody JcmdShop shop) {
        int count = shopService.update(shop);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = shopService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("根据商品名或 地区获取商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JcmdShop>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<JcmdShop> shopList = shopService.list(keyword, pageNum, pageSize);
        return commonResult.success(commonPage.restPage(shopList));
    }

    @ApiOperation("获取所有的商品id和名称")
    @RequestMapping(value = "/getShopNameAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<ArrayList<Object>> getShopNameAll() {
        ArrayList<Object> shopNameAll = shopService.getShopNameAll();

        return commonResult.success(shopNameAll);
    }

    @ApiOperation("根据商品名称获取商品类型")
    @RequestMapping(value = "/getShopTypeByName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<JcmdShopType> selectShopTypeByName(String shopName) {
        JcmdShopType shopType = shopService.selectShopTypeByName(shopName);

        return commonResult.success(shopType);
    }

    //获取商品类型
    @ApiOperation("获取商品类型列表")
    @RequestMapping(value = "/getShopTypeList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JcmdShopType>> selectShopTypeList() {
        List<JcmdShopType> shopTypeList = shopService.selectShopTypeList();
        return commonResult.success(shopTypeList);
    }

}
