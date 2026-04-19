package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JxSaleDao;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JxmdSaleExitMapper;
import com.shanzhu.purchase.mapper.JxmdSaleMapper;
import com.shanzhu.purchase.model.*;
import com.shanzhu.purchase.service.CkDepositoryOutService;
import com.shanzhu.purchase.service.JxSaleService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JxSaleServiceImpl implements JxSaleService {

    @Resource
    private JxmdSaleMapper saleMapper;

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;  //出库

    @Resource
    private CkDepositoryOutService depositoryOutService;  //服务层

    @Resource
    private JxmdSaleExitMapper saleExitMapper;

    @Resource
    private CkmdStockMapper stockMapper;  //库存清单

    @Override
    public int create(JxmdSale sale) {
        sale.setTime(LocalDateTime.now());
        BigDecimal TotalPrice;
        BigDecimal price = sale.getPrice();
        Long num = sale.getNum();
        if (price != null && num != null) {
            BigDecimal bigDecimal = new BigDecimal(num);  //其他类型转BigDecimal需要new来转化
            TotalPrice = bigDecimal.multiply(price);
            sale.setTotalPrice(TotalPrice); //计算总价
        }
        sale.setStatus(1); //默认进行中
        sale.setSaleNumber(String.valueOf(UUidUtils.uuid())); //销售编号uuid
        return saleMapper.insertSelective(sale);
    }

    @Override
    public int update(JxmdSale sale) {
        //传入字段为null不更新数据库字段
        return saleMapper.updateByPrimaryKeySelective(sale);
    }

    @Override
    public int delete(Long id) {
        return saleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JxmdSale> list() {
        return saleMapper.selectByExample(new JxmdSaleExample());
    }

    @Override
    public List<JxmdSale> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        JxmdSaleExample example = new JxmdSaleExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andSaleUserLike("%" + keyword + "%");
        }
        return saleMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdate(JxmdSale sale) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res = 0;
        if (sale.getId() != null && sale.getId() != 0) {
            // 表示更新操作
            res = this.update(sale);
        } else {
            res = this.create(sale);
        }
        return res;
    }


    /**
     * 获取 销售编号和客户
     */
    @Override
    public List getNumberAndCustomer() {
        List<Map<String, String>> list = new ArrayList<>();
        List<JxmdSale> supplierList = saleMapper.selectByExample(new JxmdSaleExample());
        if (supplierList != null) {
            for (JxmdSale sale : supplierList) {
                String customer = sale.getSupplier(); // 客户
                String number = sale.getSaleNumber(); //编号
                //编号不能为空
                if (number != null && !number.equals("")) {
                    Map<String, String> map = new HashMap<>();
                    map.put("value", number);
                    map.put("customer", customer);
                    list.add(map);
                }
            }
        }
        return list;
    }

    /**
     * 出库
     */
    @Override
    public int outSaleAndCheck(Map<String, Object> map) {
        String depositoryName = map.get("depositoryName").toString();
        JxmdSale saleList = (JxmdSale) map.get("saleList");

        /**
         * 避免重复出库
         *   查询出库清单中是否已经存在
         */
        List<CkmdDepositoryOut> rowInfoByPurchaseNumber = depositoryOutService.getRowInfoByPurchaseNumber(Integer.parseInt(saleList.getSaleNumber()));
        if (rowInfoByPurchaseNumber != null && rowInfoByPurchaseNumber.size() > 0) { //证明已 出库
            return 2;  //不能重复出库
        }
        /**
         *   1. 销售出库  该仓库是否拥有该商品，且数量是否充足。
         *   2. 出库 生成出库清单，(不需要改库存变化，统一审核后更改  或者审核时校验是否有充足的库存，审核成功则需更改销售状态为完成)
         */
        CkmdStockExample stockExample = new CkmdStockExample(); //条件
        CkmdStockExample.Criteria criteria = stockExample.createCriteria();
        CkmdStockExample.Criteria criteria1 = criteria.andDepositoryEqualTo(depositoryName).andShopEqualTo(saleList.getShop());
        List<CkmdStock> stockList = stockMapper.selectByExample(stockExample);
        CkmdStock stock = stockList.get(0);

        //2 库存充足-----> 生成出库清单
        if (stock != null && stock.getQuantity() >= saleList.getNum()) {
            CkmdDepositoryOut depositoryOut = new CkmdDepositoryOut();
            depositoryOut.setSourceNumber(Long.valueOf(saleList.getSaleNumber()));  //源 编号
            depositoryOut.setOutId(Long.valueOf(UUidUtils.uuid()));  //编号
            depositoryOut.setDepository(stock.getDepository());  //仓库
            depositoryOut.setShopName(saleList.getShop()); //商品
            depositoryOut.setShopNumber(saleList.getNum());  //数量
            depositoryOut.setShopPrice(saleList.getPrice());  //价格
            depositoryOut.setTotalPrice(saleList.getTotalPrice()); //总价
            depositoryOut.setSpecs(saleList.getSpecs());  //规格
            UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            String userName = token.getPrincipal().toString();
            depositoryOut.setOutUser(userName);  //出库人
            depositoryOut.setShopSupplier(saleList.getSupplier()); // 客户
            depositoryOut.setStatus(1); //是否出库 1   需审核
            depositoryOut.setOutInspection(1);  //未审核
            depositoryOut.setCreateDate(LocalDateTime.now());  //创建时间
            int i = depositoryOutMapper.insertSelective(depositoryOut);  //生成出库单
        } else {
            return 1;  //出库失败
        }

        return 0;  //成功
    }

    /**
     * 销售    回收   2023-6.2
     */
    @Override
    public int checkInGoods(Map<String, Object> map) {
        String remark = map.get("remark").toString();   //销售退货原因
        JxmdSale saleList = (JxmdSale) map.get("saleList");  //销售信息

        /**
         * 通过销售编号查询 是否已经存在销售退货订单了  无：返回
         */
        JxmdSaleExitExample saleExitExample = new JxmdSaleExitExample();
        JxmdSaleExitExample.Criteria criteria = saleExitExample.createCriteria().andExitNumberEqualTo(saleList.getSaleNumber());

        List<JxmdSaleExit> saleExits = saleExitMapper.selectByExample(saleExitExample);
        if (saleExits != null) return 1; // 存在则直接返回
        /**
         *      1. 状态为0/1 都生成 销售退货单，并添加退货原因|| 销售单是进行中：无其他操作  销售退货单状态为完成0；而外生成 生成入库清单
         *      2.判断是否出库（出库-就需要生成：【销售退货+入库清单】，未出库则直接生成：【销售退货】），
         *          判断其状态是否完成 （完成：表示已到客户手中，需要【入库清单，销售退货单】； 未完成：直接生成【销售退货单】）
         */
        JxmdSaleExit saleExitEntity = new JxmdSaleExit();
        saleExitEntity.setNumber(String.valueOf(UUidUtils.uuid())); //销售退货单编号
        saleExitEntity.setExitNumber(saleList.getSaleNumber());  //销售单编号
        saleExitEntity.setNum(saleList.getNum());  //  数量
        saleExitEntity.setPrice(saleList.getPrice()); //价格
        saleExitEntity.setTotalPrice(saleList.getTotalPrice()); //总价
        saleExitEntity.setTime(LocalDateTime.now()); //时间
        saleExitEntity.setReason(remark); //原因
        if (saleList.getStatus() == 1) {  //        销售单进行中
            saleExitEntity.setStatus(0); //销售退货   完成
        }
        //销售单 完成情况
        else {  //生成入库清单  通过查询出 出库清单 来生成 入库清单      ---------先完成出库功能
            List<CkmdDepositoryOut> DepositoryOutList = depositoryOutService.getRowInfoByPurchaseNumber(Integer.parseInt(saleList.getSaleNumber()));
            CkmdDepositoryOut rowDepositoryOut = DepositoryOutList.get(0);
            if (rowDepositoryOut == null) {
                return 2;  //不存在对应的出库清单，回收异常
            }
            CkmdDepositoryOut depositoryOut = new CkmdDepositoryOut();
            depositoryOut.setSourceNumber(Long.valueOf(saleList.getSaleNumber()));  //来源编号
            depositoryOut.setOutId(Long.valueOf(UUidUtils.uuid()));  //设置随机数 uuid
            int i = depositoryOutMapper.insertSelective(depositoryOut); //  出库清单
            saleExitEntity.setStatus(1); //进行中
        }
        int i = saleExitMapper.insertSelective(saleExitEntity); //增加销售退货单
        return 0;
    }

}
