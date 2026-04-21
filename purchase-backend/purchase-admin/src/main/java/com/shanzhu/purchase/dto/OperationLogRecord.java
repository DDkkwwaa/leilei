package com.shanzhu.purchase.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationLogRecord {

    private Long id;

    private String operator;

    private String actionType;

    private String bizType;

    private String bizNo;

    private String content;

    private String requestPath;

    private String requestMethod;

    private String ip;

    private LocalDateTime createTime;
}
