package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JcShopDao;
import com.shanzhu.purchase.mapper.JcmdShopMapper;
import com.shanzhu.purchase.mapper.JcmdShopTypeMapper;
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

}
