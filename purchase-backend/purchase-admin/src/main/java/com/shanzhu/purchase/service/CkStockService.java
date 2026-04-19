package com.shanzhu.purchase.service;

import com.shanzhu.purchase.dto.StockUpDto;
import com.shanzhu.purchase.model.CkmdStock;

import java.util.List;
import java.util.Map;

/**
 * 仓库-库存 service
 */
public interface CkStockService {

    /**
     * 添加库存
     */
    int create(CkmdStock stock);

    /**
     * 修改库存信息-------无
     */
    int update(CkmdStock stock);

    /**
     * 删除库存
     */
    int delete(Long id);

    /**
     * 获取所有库存列表
     */
    List<CkmdStock> list();

    /**
     * 分页获取库存列表
     */
    List<CkmdStock> list(String keyword, Integer pageSize, Integer pageNum);

    int addOrUpdateStock(CkmdStock stock);

    /**
     * 更新库存列表的库存数据
     * @param
     * @param
     * @return
     */
    int updateQuantityByTwoName(StockUpDto stockUpDto);

    /**
     * 通过商品和仓库名获取库存列表
     * @param shopName
     * @param depositoryName
     * @return
     */
    List<CkmdStock> getStockByTwoName( String shopName, String depositoryName);


    /**  柱状图
     * 商品1：[采购量   销售量、 库存量 ]
     * @return
     */
    List<Map> histogramList();


    /**
     *  饼图
     * @return
     */
    List shopMap();
}
