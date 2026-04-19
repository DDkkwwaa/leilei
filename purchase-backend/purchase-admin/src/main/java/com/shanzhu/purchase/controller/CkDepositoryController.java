package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.service.CkDepositoryService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "CkDepositoryController", description = "ck-1仓库列表")
@Tag(name = "CkDepositoryController", description = "仓库管理-仓库列表")
@RequestMapping("/depository")
public class CkDepositoryController {

    @Resource
    private CkDepositoryService depositoryService;

    @ApiOperation("添加仓库")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdDepository depository) {
        int count = depositoryService.updateOrAddById(depository);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("修改仓库-归并到add")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody CkmdDepository depository) {
        int count = depositoryService.update(depository);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除仓库")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = depositoryService.delete(id);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有仓库")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<XtmdMenu>> listAll() {
        List<XtmdMenu> depositoryList = depositoryService.list();
        return commonResult.success(depositoryList);
    }

    @ApiOperation("根据角色名称分页获取仓库列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<CkmdDepository>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<CkmdDepository> menuList = depositoryService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(menuList));
    }

    @ApiOperation(value = "仓库是否存在", notes = "校验仓库名称")
    @RequestMapping(value = "/checkDepositoryName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult checkDepositoryName(String depositoryName) {
        boolean result = depositoryService.checkUserName(depositoryName);
        if (result) {
            return commonResult.success("YES");
        } else {
            return commonResult.success("NO");
        }
    }

    @ApiOperation(value = "大屏-全国仓库地图", notes = "地图")
    @RequestMapping(value = "/getDepositoryToEChart", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getDepositoryToEChart() {
        List list = depositoryService.getDepositoryToEChart();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("数据为空");
    }

    @ApiOperation(value = "获取仓库数量", notes = "仓库数量")
    @RequestMapping(value = "/getLength", method = RequestMethod.GET)
    @ResponseBody
    public commonResult getLengthToDepository() {
        int count = depositoryService.getLength();
        if (count > 0) {
            return commonResult.success(count);
        } else {
            return commonResult.failed();
        }
    }

}
