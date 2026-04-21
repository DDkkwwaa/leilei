package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.dto.OperationLogRecord;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OperationLogDao {

    int insertLog(OperationLogRecord record);

    List<Map<String, Object>> list(@Param("keyword") String keyword,
                                   @Param("operator") String operator,
                                   @Param("actionType") String actionType,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);
}
