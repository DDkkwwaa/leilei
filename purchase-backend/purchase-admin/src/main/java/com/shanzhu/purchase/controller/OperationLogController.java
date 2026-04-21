package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.service.OperationLogService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "OperationLogController", description = "xt-操作日志")
@Tag(name = "OperationLogController", description = "系统管理-操作日志")
@RequestMapping("/operationLog")
public class OperationLogController {

    @Resource
    private OperationLogService operationLogService;

    @ApiOperation("分页查询操作日志")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<Map<String, Object>>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "operator", required = false) String operator,
            @RequestParam(value = "actionType", required = false) String actionType,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        if (!isAdmin()) {
            return commonResult.forbidden(null);
        }
        LocalDateTime startTime = startDate == null ? null : startDate.atStartOfDay();
        LocalDateTime endTime = endDate == null ? null : endDate.atTime(LocalTime.MAX);
        List<Map<String, Object>> rows = operationLogService.list(keyword, operator, actionType, startTime, endTime, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(rows));
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            return false;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority == null) {
                continue;
            }
            String role = authority.getAuthority();
            if ("admin".equalsIgnoreCase(role) || "ROLE_ADMIN".equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}
