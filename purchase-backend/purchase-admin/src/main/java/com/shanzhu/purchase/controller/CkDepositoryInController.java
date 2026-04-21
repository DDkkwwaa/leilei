package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdDepositoryIn;
import com.shanzhu.purchase.service.CkDepositoryInService;
import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(value = "CkController", description = "Inbound list")
@Tag(name = "CkDepositoryInController", description = "Warehouse - Inbound list")
@RequestMapping("/depositoryIn")
public class CkDepositoryInController {

    @Resource
    private CkDepositoryInService depositoryInService;

    @Resource
    private OperationLogService operationLogService;

    @ApiOperation("Create inbound order")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdDepositoryIn depositoryIn) {
        int count = depositoryInService.addOrUpdateDepositoryIn(depositoryIn);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("Update inbound order")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody CkmdDepositoryIn depositoryIn) {
        int count = depositoryInService.update(depositoryIn);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("Delete inbound order")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = depositoryInService.delete(id);
        if (count > 0) {
            return commonResult.success("Success");
        }
        return commonResult.failed();
    }

    @ApiOperation("Paged list by keyword")
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

    @ApiOperation(value = "Check if inbound id exists", notes = "Check if inbound id exists")
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

    @ApiOperation(value = "Inbound audit")
    @RequestMapping(value = "/checkById", method = RequestMethod.POST)
    @ResponseBody
    public commonResult checkById(@RequestParam(value = "id") Long id, HttpServletRequest request) {
        int count = depositoryInService.checkById(id);
        if (count > 0) {
            operationLogService.record(null, "INBOUND", "INBOUND", String.valueOf(id),
                    "Inbound audit completed", request);
            return commonResult.success(count);
        }
        return commonResult.failed();
    }
}
