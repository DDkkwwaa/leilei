package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdDepositoryIn;
import com.shanzhu.purchase.service.CkDepositoryInService;
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
@Api(value = "CkController", description = "ck-4入库清单")
@Tag(name = "CkDepositoryInController", description = "仓库管理-3入库清单")
@RequestMapping("/depositoryIn")
public class CkDepositoryInController {

    @Resource
    private CkDepositoryInService depositoryInService;

    @ApiOperation("添加入库单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdDepositoryIn depositoryIn) {
        int count = depositoryInService.addOrUpdateDepositoryIn(depositoryIn);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改入库单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody CkmdDepositoryIn depositoryIn) {
        int count = depositoryInService.update(depositoryIn);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除入库单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = depositoryInService.delete(id);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("根据仓库名称分页获取入库单信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<CkmdDepositoryIn>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<CkmdDepositoryIn> menuList = depositoryInService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(menuList));
    }


    @ApiOperation(value = "检验--是否已入库-入库编号", notes = "校验是否已入库")
    @RequestMapping(value = "/checkDepositoryInId", method = RequestMethod.GET)
    @ResponseBody
    public commonResult checkRoleName(Long DepositoryInId) {
        boolean result = depositoryInService.checkDepositoryInId(DepositoryInId);
        if (result) {
            return commonResult.success("YES");
        } else {
            return commonResult.success("NO");
        }
    }

    @ApiOperation(value = "入库清单-质检")
    @RequestMapping(value = "/checkById", method = RequestMethod.POST)
    @ResponseBody
    public commonResult checkById(@RequestParam(value = "id") Long id) {
        int count = depositoryInService.checkById(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

}
