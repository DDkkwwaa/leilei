package com.shanzhu.purchase.service.Impl;

import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.OperationLogDao;
import com.shanzhu.purchase.dto.OperationLogRecord;
import com.shanzhu.purchase.service.OperationLogService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    private OperationLogDao operationLogDao;

    @Override
    public void record(String operator, String actionType, String bizType, String bizNo, String content, HttpServletRequest request) {
        OperationLogRecord record = new OperationLogRecord();
        record.setOperator(resolveOperator(operator));
        record.setActionType(actionType);
        record.setBizType(bizType);
        record.setBizNo(bizNo);
        record.setContent(content);
        record.setRequestPath(request == null ? null : request.getRequestURI());
        record.setRequestMethod(request == null ? null : request.getMethod());
        record.setIp(resolveIp(request));
        record.setCreateTime(LocalDateTime.now());
        operationLogDao.insertLog(record);
    }

    @Override
    public List<Map<String, Object>> list(String keyword, String operator, String actionType, LocalDateTime startTime, LocalDateTime endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return operationLogDao.list(keyword, operator, actionType, startTime, endTime);
    }

    private String resolveOperator(String operator) {
        if (operator != null && !operator.trim().isEmpty()) {
            return operator.trim();
        }
        try {
            UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (token != null && token.getPrincipal() != null) {
                return String.valueOf(token.getPrincipal());
            }
        } catch (Exception ignored) {
        }
        return "SYSTEM";
    }

    private String resolveIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.trim().isEmpty() && !"unknown".equalsIgnoreCase(xff)) {
            return xff.split(",")[0].trim();
        }
        String xrip = request.getHeader("X-Real-IP");
        if (xrip != null && !xrip.trim().isEmpty() && !"unknown".equalsIgnoreCase(xrip)) {
            return xrip.trim();
        }
        return request.getRemoteAddr();
    }
}
