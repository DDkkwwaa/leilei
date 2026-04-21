package com.shanzhu.purchase.service;


import com.shanzhu.purchase.model.JxmdSale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface JxSaleService {

    /**
     * 添加客户
     */
    int create(JxmdSale sale);

    /**
     * 修改客户信息
     */
    int update(JxmdSale sale);

    /**
     * 批量删除客户
     */
    int delete(Long id);

    /**
     * 获取所有客户
     */
    List<JxmdSale > list();

    /**
     * 分页获取客户列表
     */
    List<JxmdSale> list(String keyword, Integer pageSize, Integer pageNum);

    List<JxmdSale> list(String keyword,
                        Integer status,
                        LocalDateTime startTime,
                        LocalDateTime endTime,
                        Integer pageSize,
                        Integer pageNum);

    int addOrUpdate(JxmdSale sale);

    List getNumberAndCustomer();

    //销售 出库
    int outSaleAndCheck(Map<String,Object> map);

    int checkInGoods(Map<String, Object> map);

    List<Map<String, Object>> waveList();

    int createWave(String saleNumber, String depositoryName);

    int createWave(String saleNumber, String depositoryName, boolean forceReplan);

    int finishPick(String saleNumber);

    int updateStatusBySaleNumber(String saleNumber, Integer status);

    List<Map<String, Object>> pickPath(String saleNumber);
}
