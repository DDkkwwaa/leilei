package com.shanzhu.purchase.service;


import com.shanzhu.purchase.dto.StockUpDto;
import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.XtmdMenu;

import java.util.List;

/**
 * 仓库 service
 */
public interface CkDepositoryService {

    /**
     * 添加仓库
     */
    int create(CkmdDepository depository);

    /**
     * 修改仓库信息
     */
    int update( CkmdDepository depository);

    /**
     *  删除仓库
     */
    int delete( Long id);

    /**
     * 获取所有仓库列表
     */
    List<XtmdMenu> list();

    /**
     * 分页获取仓库列表
     */
    List<CkmdDepository> list(String keyword, Integer pageSize, Integer pageNum);


    boolean checkUserName(String name);

    int updateOrAddById(CkmdDepository depository);

    /**
     * 更新 仓库的容量
     */
    int  updateAreaByTwoName(StockUpDto stockUpDto);

    List getDepositoryToEChart();

    int getLength();

}
