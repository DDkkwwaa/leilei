package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JcShopDao;
import com.shanzhu.purchase.mapper.CkmdStockMapper;
import com.shanzhu.purchase.mapper.JcmdShopMapper;
import com.shanzhu.purchase.mapper.JcmdShopTypeMapper;
import com.shanzhu.purchase.model.CkmdStockExample;
import com.shanzhu.purchase.model.JcmdShop;
import com.shanzhu.purchase.model.JcmdShopExample;
import com.shanzhu.purchase.model.JcmdShopType;
import com.shanzhu.purchase.model.JcmdShopTypeExample;
import com.shanzhu.purchase.service.JcShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门  service
 */
@Service
public class JcShopServiceImpl implements JcShopService {

    @Resource
    private JcmdShopMapper shopMapper;

    @Resource
    private JcmdShopTypeMapper shopTypeMapper;

    @Resource
    private JcShopDao shopDao;

    @Resource
    private CkmdStockMapper stockMapper;

    @Override
    public int create(JcmdShop shop) {
        shop.setBuildDate(LocalDateTime.now());
        return shopMapper.insert(shop);
    }

    @Override
    public int update(JcmdShop shop) {
        shop.setUpdateDate(LocalDateTime.now());
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public int updateOrAddById(JcmdShop shop) {
        if (shop.getId() != null && shop.getId() != 0) {//更新
            this.update(shop);
        } else {
            this.create(shop);
        }
        //先默认删除/修改 成功
        return 1;
    }

    @Override
    public int delete(Long id) {
        if (id == null) {
            return 0;
        }
        JcmdShop shop = shopMapper.selectByPrimaryKey(id);
        if (shop == null || StrUtil.isBlank(shop.getName())) {
            return 0;
        }
        CkmdStockExample stockExample = new CkmdStockExample();
        stockExample.createCriteria().andShopEqualTo(shop.getName());
        long linkedStockCount = stockMapper.countByExample(stockExample);
        if (linkedStockCount > 0) {
            return -1;
        }
        int count = shopMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public List<JcmdShop> list() {
        return null;
    }

    @Override
    public List<JcmdShop> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        JcmdShopExample example = new JcmdShopExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return shopMapper.selectByExample(example);
    }

    @Override
    public JcmdShopType selectShopTypeByName(String shopName) {
        return shopDao.selectShopTypeByName(shopName);
    }

    @Override
    public ArrayList<Object> getShopNameAll() {
        List<JcmdShop> jcmdShops = shopMapper.selectByExample(new JcmdShopExample());
        ArrayList<Object> shopNameList = new ArrayList<>();
        if (jcmdShops != null && jcmdShops.size() > 0) {
            for (JcmdShop shop : jcmdShops) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", shop.getId());
                map.put("name", shop.getName());
                shopNameList.add(map);
            }
        }
        return shopNameList;
    }

    /**
     * 获取商品类型的
     */
    @Override
    public List<JcmdShopType> selectShopTypeList() {
        return shopTypeMapper.selectByExample(new JcmdShopTypeExample());
    }

    @Override
    public int saveShopType(JcmdShopType shopType) {
        if (shopType == null || StrUtil.isBlank(shopType.getShopType())) {
            return 0;
        }
        String name = shopType.getShopType().trim();
        JcmdShopTypeExample checkExample = new JcmdShopTypeExample();
        JcmdShopTypeExample.Criteria criteria = checkExample.createCriteria().andShopTypeEqualTo(name);
        if (shopType.getId() != null) {
            criteria.andIdNotEqualTo(shopType.getId());
        }
        if (shopTypeMapper.countByExample(checkExample) > 0) {
            return -2;
        }
        shopType.setShopType(name);
        if (shopType.getId() != null && shopType.getId() > 0) {
            return shopTypeMapper.updateByPrimaryKeySelective(shopType);
        }
        if (shopType.getClassId() == null) {
            shopType.setClassId(System.currentTimeMillis());
        }
        return shopTypeMapper.insertSelective(shopType);
    }

    @Override
    public int deleteShopType(Integer id) {
        if (id == null || id <= 0) {
            return 0;
        }
        JcmdShopExample linkedShopExample = new JcmdShopExample();
        linkedShopExample.createCriteria().andParentIdEqualTo(Long.valueOf(id));
        if (shopMapper.countByExample(linkedShopExample) > 0) {
            return -1;
        }
        return shopTypeMapper.deleteByPrimaryKey(id);
    }

}
