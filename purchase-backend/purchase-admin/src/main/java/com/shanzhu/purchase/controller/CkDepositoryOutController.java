package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.service.CkDepositoryOutService;
import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(value = "CkOutDepositoryController", description = "Outbound list")
@Tag(name = "CkOutDepositoryController", description = "Warehouse - Outbound list")
@RequestMapping("/depositoryOut")
public class CkDepositoryOutController {

    @Resource
    private CkDepositoryOutService depositoryOutService;

    @Resource
    private OperationLogService operationLogService;

    @ApiOperation("Create outbound order")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody CkmdDepositoryOut ckmdDepositoryOut) {
        int count = depositoryOutService.addOrUpdateDepositoryOut(ckmdDepositoryOut);
        if (count > 0) {
            return commonResult.success("Success");
        }
        return commonResult.failed();
    }

    @ApiOperation("Delete outbound order")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = depositoryOutService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("List all outbound orders")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<CkmdDepositoryOut>> listAll() {
        List<CkmdDepositoryOut> outList = depositoryOutService.list();
        return commonResult.success(outList);
    }

    @ApiOperation("Paged list by keyword")
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

    @ApiOperation("Update outbound status")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer outInspection) {
        CkmdDepositoryOut depositoryOut = new CkmdDepositoryOut();
        depositoryOut.setOutInspection(outInspection);
        int count = depositoryOutService.update(depositoryOut);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation(value = "Outbound audit")
    @RequestMapping(value = "/checkById", method = RequestMethod.POST)
    @ResponseBody
    public commonResult checkById(@RequestParam(value = "id") Long id, HttpServletRequest request) {
        int count = depositoryOutService.checkById(id);
        if (count > 0) {
            operationLogService.record(null, "OUTBOUND", "OUTBOUND", String.valueOf(id),
                    "Outbound audit completed", request);
            return commonResult.success(count);
        }
        return commonResult.failed("Insufficient stock");
    }
}
