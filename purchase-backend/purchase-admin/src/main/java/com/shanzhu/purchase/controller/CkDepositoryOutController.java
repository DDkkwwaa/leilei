package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.service.CkDepositoryOutService;
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
@Api(value = "CkOutDepositoryController", description = "ck-4出库清单")
@Tag(name = "CkOutDepositoryController", description = "系统管理-出库清单")
@RequestMapping("/depositoryOut")
public class CkDepositoryOutController {

    @Resource
    private CkDepositoryOutService depositoryOutService;

    @ApiOperation("添加仓库")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdDepositoryOut CkmdDepositoryOut) {
        int count = depositoryOutService.addOrUpdateDepositoryOut(CkmdDepositoryOut);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("删除仓库清单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = depositoryOutService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有仓库")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<CkmdDepositoryOut>> listAll() {
        List<CkmdDepositoryOut> outList = depositoryOutService.list();
        return commonResult.success(outList);
    }

    @ApiOperation("根据角色名称分页获取仓库列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<CkmdDepositoryOut>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<CkmdDepositoryOut> outList = depositoryOutService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(outList));
    }

    @ApiOperation("修改仓库状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer OutInspection) {
        CkmdDepositoryOut depositoryOut = new CkmdDepositoryOut();
        depositoryOut.setOutInspection(OutInspection);
        int count = depositoryOutService.update(depositoryOut);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation(value = "出库清单-审核")
    @RequestMapping(value = "/checkById", method = RequestMethod.POST)
    @ResponseBody
    public commonResult checkById(@RequestParam(value = "id") Long id) {
        int count = depositoryOutService.checkById(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed("库存不足");
    }

}
