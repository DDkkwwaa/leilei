package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.CkmdDepositoryIn;

import java.util.List;

public interface CkDepositoryInService {

    /**
     * 添加入库清单
     */
    int create(CkmdDepositoryIn depositoryIn);

    /**
     * 修改入库清单
     */
    int update(  CkmdDepositoryIn depositoryIn);

    /**
     * 批量删除入库清单
     */
    int delete(Long id);

    /**
     * 获取所有入库清单列表
     */
    List<CkmdDepositoryIn> list();

    /**
     * 分页获取入库清单列表
     */
    List<CkmdDepositoryIn> list(String keyword, Integer pageSize, Integer pageNum);


    boolean checkDepositoryInId(Long depositoryInId);

    int addOrUpdateDepositoryIn(CkmdDepositoryIn depositoryIn);

    int checkById(Long id);

    List<CkmdDepositoryIn> getRowInfoByPurchaseNumber(int number);


}
