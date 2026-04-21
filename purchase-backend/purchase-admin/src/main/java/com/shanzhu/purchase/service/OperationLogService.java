package com.shanzhu.purchase.service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OperationLogService {

    void record(String operator,
                String actionType,
                String bizType,
                String bizNo,
                String content,
                HttpServletRequest request);

    List<Map<String, Object>> list(String keyword,
                                   String operator,
                                   String actionType,
                                   LocalDateTime startTime,
                                   LocalDateTime endTime,
                                   Integer pageSize,
                                   Integer pageNum);
}
