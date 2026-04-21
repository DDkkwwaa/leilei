package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.ResultCode;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "JxSaleController", description = "销售订单")
@Tag(name = "JxSaleController", description = "进销管理-销售订单")
@RequestMapping("/sale")
public class JxSaleController {

    @Resource
    private JxSaleService saleService;

    @Resource
    private OperationLogService operationLogService;

    @ApiOperation("新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody JxmdSale sale) {
        int count = saleService.addOrUpdate(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody JxmdSale sale) {
        int count = saleService.update(sale);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = saleService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("获取所有")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<JxmdSale>> listAll() {
        List<JxmdSale> customerList = saleService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("分页查询(支持单号/状态/时间)")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<JxmdSale>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        LocalDateTime startTime = startDate == null ? null : startDate.atStartOfDay();
        LocalDateTime endTime = endDate == null ? null : endDate.atTime(LocalTime.MAX);
        List<JxmdSale> saleList = saleService.list(keyword, status, startTime, endTime, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(saleList));
    }

    @ApiOperation("获取销售编号和客户")
    @RequestMapping(value = "/getNumberAndCustomer", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> getNumberAndCustomer() {
        List list = saleService.getNumberAndCustomer();
        if (list != null) {
            return commonResult.success(list);
        }
        return commonResult.failed("数据为空");
    }

    @ApiOperation("销售出库")
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

    @ApiOperation("波次与拣货任务列表")
    @RequestMapping(value = "/waveList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<Map<String, Object>>> waveList() {
        return commonResult.success(saleService.waveList());
    }

    @ApiOperation("生成波次并派发拣货")
    @RequestMapping(value = "/createWave", method = RequestMethod.POST)
    @ResponseBody
    public commonResult createWave(@RequestParam("saleNumber") String saleNumber,
                                   @RequestParam("depositoryName") String depositoryName,
                                   HttpServletRequest request) {
        int result = saleService.createWave(saleNumber, depositoryName);
        if (result == 1) {
            operationLogService.record(null, "WAVE_CREATE", "SALE", saleNumber,
                    "生成波次并派发拣货任务，仓库=" + depositoryName, request);
            return commonResult.success("生成成功");
        }
        if (result == 2) {
            return commonResult.failed("该订单已存在波次任务");
        }
        if (result == 4) {
            return commonResult.failed("参数不完整");
        }
        if (result == 5) {
            return commonResult.failed("订单不存在");
        }
        return commonResult.failed("库存不足或创建失败");
    }

    @ApiOperation("完成拣货任务")
    @RequestMapping(value = "/finishPick", method = RequestMethod.POST)
    @ResponseBody
    public commonResult finishPick(@RequestParam("saleNumber") String saleNumber, HttpServletRequest request) {
        int result = saleService.finishPick(saleNumber);
        if (result == 1) {
            operationLogService.record(null, "PICK_FINISH", "SALE", saleNumber,
                    "完成拣货并更新销售状态", request);
            return commonResult.success("完成成功");
        }
        if (result == 2) {
            return commonResult.failed("未找到可执行的波次任务");
        }
        if (result == 4) {
            return commonResult.failed("参数不完整");
        }
        return commonResult.failed("库存不足或审核失败");
    }

    @ApiOperation("手动修改订单状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@RequestParam("saleNumber") String saleNumber,
                                     @RequestParam("status") Integer status,
                                     HttpServletRequest request) {
        int result = saleService.updateStatusBySaleNumber(saleNumber, status);
        if (result > 0) {
            operationLogService.record(null, "ORDER_STATUS_UPDATE", "SALE", saleNumber,
                    "手动修改订单状态为:" + status, request);
            return commonResult.success(result);
        }
        return commonResult.failed("修改失败");
    }
}
