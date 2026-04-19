package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkDepositoryDao;
import com.shanzhu.purchase.dto.StockUpDto;
import com.shanzhu.purchase.mapper.CkmdDepositoryMapper;
import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.CkmdDepositoryExample;
import com.shanzhu.purchase.service.CkDepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CkDepositoryServiceImpl implements CkDepositoryService {

    @Resource
    private CkDepositoryDao depositoryDao;

    @Resource
    private CkmdDepositoryMapper depositoryMapper;

    @Override
    public int create(CkmdDepository depository) {
        depository.setBuildDate(LocalDateTime.now());
        depository.setStatus(0);
        depository.setArea("立方米");
        depository.setSurplus(depository.getStockTotal());
        return depositoryMapper.insert(depository);
    }

    @Override
    public int update(CkmdDepository depository) {
        //仓库名称更新后，也需要同步更新入库单和出库单 商品库存中的仓库名称？
        //如果提交的参数和数据库中的 仓库名称数据一样则直接更新
        //否则 同步更新 入库单和出库单 商品库存中的仓库名称
        return depositoryMapper.updateByPrimaryKeySelective(depository);
    }

    @Override
    public int delete(Long id) {
        return depositoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List list() {
        return depositoryMapper.selectByExample(new CkmdDepositoryExample());
    }

    @Override
    public List<CkmdDepository> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CkmdDepositoryExample example = new CkmdDepositoryExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return depositoryMapper.selectByExample(example);
    }

    @Override
    public boolean checkUserName(String name) {
        CkmdDepositoryExample example = new CkmdDepositoryExample();
        CkmdDepositoryExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return depositoryMapper.countByExample(example) > 0;
    }

    @Override
    public int updateOrAddById(CkmdDepository depository) {
        if (depository.getId() != null && depository.getId() != 0) { //更新
            this.update(depository);
        } else {
            this.create(depository);
        }
        return 1;  //默认成功
    }

    /**
     * 更新 某仓库剩余量
     */
    @Override
    public int updateAreaByTwoName(StockUpDto stockUpDto) {
        if (stockUpDto.getFlag() == 0) {
            return depositoryDao.addArea(stockUpDto.getDepositoryName(), stockUpDto.getQuantity());
        }
        return depositoryDao.reduceArea(stockUpDto.getDepositoryName(), stockUpDto.getQuantity());
    }

    @Override
    public List getDepositoryToEChart() {
        List<Map<String, String>> list = new ArrayList<>();
        List<CkmdDepository> depositoryList = depositoryMapper.selectByExample(new CkmdDepositoryExample());
        if (depositoryList != null) {
            for (CkmdDepository depository : depositoryList) {
                String address = depository.getAddress();
                String subAddress = address.substring(0, address.indexOf("/"));
                if (subAddress != null && !subAddress.equals("")) {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", subAddress);
                    map.put("value", String.valueOf(70));
                    list.add(map);
                }
            }
        }
        return list;
    }


    /**
     * 获取仓库的数量
     */
    @Override
    public int getLength() {
        int count = depositoryDao.getCount();
        return count;
    }

}
