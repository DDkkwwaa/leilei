package com.shanzhu.purchase.service.Impl;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkStockDao;
import com.shanzhu.purchase.dao.JxSaleDao;
import com.shanzhu.purchase.dto.ShopAndSaleAndStockAndPurchaseDto;
import com.shanzhu.purchase.dto.StockUpDto;
import com.shanzhu.purchase.mapper.CkmdDepositoryMapper;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.CkmdDepositoryExample;
import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.CkmdStockExample;
import com.shanzhu.purchase.service.CkStockService;
import com.shanzhu.purchase.util.calculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存列表
 */
@Service
public class CkStockServiceImpl implements CkStockService {

    @Resource
    private CkmdStockMapper stockMapper;

    @Resource
    private CkStockDao stockDao;  //自定义仓库列表DQL DMl

    @Resource
    private CkmdDepositoryMapper depositoryMapper;   //仓库列表

    @Override
    public int create(CkmdStock stock) {
        stock.setCreateTime(LocalDateTime.now());
        return stockMapper.insert(stock);
    }

    @Override
    public int update(CkmdStock stock) {
        stock.setUpdateTime(LocalDateTime.now());
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int delete(Long id) {
        return stockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CkmdStock> list() {
        return stockMapper.selectByExample(new CkmdStockExample());
    }

    @Override
    public List<CkmdStock> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CkmdStockExample example = new CkmdStockExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andShopLike("%" + keyword + "%");

        }
        return stockMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdateStock(CkmdStock stock) {
        // 判断  主键是否存在，如果存在就走更新的逻辑否则新增数据

        //  增加出现 ：商品名称和 存入库存相同的情况
        //  通过 传入的 商品名称和 存入库存是否和数据库中有相同的    有则更新数据库中的，没有则添加
        int res = 0;
        if (stock.getId() != null && stock.getId() != 0) { //有具体id
            // 表示更新操作
            return this.update(stock);
        }

        return this.create(stock);
    }

    @Override
    public int updateQuantityByTwoName(StockUpDto stockUpDto) {
        if (stockUpDto.getFlag() == 0) {  //增加库存
            return stockDao.addQuantityByTwoName(stockUpDto.getQuantity(), stockUpDto.getShopName(), stockUpDto.getDepositoryName());
        }
        //减少库存
        return stockDao.reduceQuantityByTwoName(stockUpDto.getQuantity(), stockUpDto.getShopName(), stockUpDto.getDepositoryName());
    }

    /**
     * 通过商品和仓库名获取库存列表
     */
    @Override
    public List<CkmdStock> getStockByTwoName(String shopName, String depositoryName) {
        return stockDao.getStockByTwoName(shopName, depositoryName);
    }

    /**
     * Echart地图 柱状图数据
     * 商品1：[采购量   销售量、 库存量 ]  销售总价
     * 三张表 关联统计查询  左连接表 去匹配 三张表  1:n的关系
     * <p>
     * 销售表  1.获取销售量最高的前5个商品  => 5个商品 + 5个销售量
     * 库存表      库存量 查询统计5个商品的库存量，条件是商品名  ==> 库存量
     * 采购表   采购表    查询统计5个商品的采购，条件是商品名  ==>  采购量
     * <p>
     */
    @Override
    public List<Map> histogramList() {
        List<Map> allList = new ArrayList<>();
        List<String> shopList = new ArrayList<>();      //商品集合
        List<Long> saleList = new ArrayList<>();       //销售量集合
        List<Long> stockList = new ArrayList<>();      //库存量集合
        List<Long> purchaseList = new ArrayList<>();     //采购量集合
        Map<String, List> map = new HashMap<>();
        Long base = 0l;
        List<ShopAndSaleAndStockAndPurchaseDto> histogramData = stockDao.getNumberToShopAndStockAndPurchase();
        if (histogramData != null) {
            for (ShopAndSaleAndStockAndPurchaseDto dto : histogramData) {
                // 商品list  销售量list  库存量list  采购量list
                String shopName = dto.getShopName();
                Long stockNumber = dto.getStockNumber();
                Long saleNumber = dto.getSaleNumber();
                Long purchaseNumber = dto.getPurchaseNumber();
                shopList.add(shopName);
                //判断 是否为空 销售、库存、采购
                if (stockNumber != null && stockNumber.toString().length() > 0) {
                    stockList.add(stockNumber);
                } else {
                    stockList.add(base);
                }
                if (saleNumber != null && stockNumber.toString().length() > 0) {
                    saleList.add(dto.getSaleNumber());
                } else {
                    saleList.add(base);
                }
                if (purchaseNumber != null && purchaseNumber.toString().length() > 0) {
                    purchaseList.add(dto.getPurchaseNumber());
                } else {
                    purchaseList.add(base);
                }
            }
        }
        map.put("shopList", shopList);  //商品
        map.put("saleList", saleList); //销售量
        map.put("stockList", stockList);  //库存量
        map.put("purchaseList", purchaseList);  //采购量
        allList.add(map);
        return allList;  //需要处理
    }


    /**
     * 饼图 商品库存列表
     * 库存   k:商品  value: 公式计算 库存量 / 总仓库容量
     */
    @Override
    public List<Map> shopMap() {
        List<Map> list = new ArrayList<>();
        //查询仓库总容量
        Long totalVolume = 0l;
        List<CkmdDepository> depositoryList = depositoryMapper.selectByExample(new CkmdDepositoryExample());
        for (CkmdDepository depository : depositoryList) {
            Long stockTotal = depository.getStockTotal();
            totalVolume += stockTotal;
        }
        //计算  容量转为kg
        BigDecimal bigKg = calculationUtils.INTCalculatingVolumeToWeight(totalVolume);
        //获取商品库存列表
        List<CkmdStock> stockList = stockMapper.selectByExample(new CkmdStockExample());
        for (CkmdStock stock : stockList) {
            String shop = stock.getShop(); //商品名
            Long quantity = stock.getQuantity(); //库存量
            BigDecimal bigDecimal = new BigDecimal(quantity);
            BigDecimal divideValue = bigDecimal.divide(bigKg, 0);   // 除后的
            if (10 > divideValue.signum()) {
                divideValue = BigDecimal.valueOf(10);
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("name", shop);
            map.put("value", String.valueOf(divideValue));
            list.add(map);
        }
        return list;
    }

}
