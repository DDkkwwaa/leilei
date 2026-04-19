package com.shanzhu.purchase.service;


import com.shanzhu.purchase.model.CkmdDepositoryOut;

import java.util.List;

public interface CkDepositoryOutService {


    /**
     * 添加出库清单
     */
    int create(CkmdDepositoryOut depositoryOut);

    /**
     * 修改出库清单
     */
    int update(  CkmdDepositoryOut depositoryOut);

    /**
     * 批量删除出库清单
     */
    int delete(Long id);

    /**
     * 获取所有出库清单列表
     */
    List<CkmdDepositoryOut> list();

    /**
     * 分页获取出库清单列表
     */
    List<CkmdDepositoryOut> list(String keyword, Integer pageSize, Integer pageNum);


    int addOrUpdateDepositoryOut(CkmdDepositoryOut ckmdDepositoryOut);

    int checkById(Long id);

    /**
     * 通过编号查询 source_number
     */
    List<CkmdDepositoryOut> getRowInfoByPurchaseNumber(int number);

}
