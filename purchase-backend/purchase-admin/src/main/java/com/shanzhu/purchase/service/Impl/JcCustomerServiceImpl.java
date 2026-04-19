package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JcCustomerDao;
import com.shanzhu.purchase.mapper.JcmdCustomerMapper;
import com.shanzhu.purchase.model.JcmdCustomer;
import com.shanzhu.purchase.model.JcmdCustomerExample;
import com.shanzhu.purchase.service.JcCustomerService;
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
public class JcCustomerServiceImpl implements JcCustomerService {

    @Resource
    private JcmdCustomerMapper customerMapper;

    @Override
    public int create( JcmdCustomer customer) {
        customer.setBuildDate(LocalDateTime.now());
        return customerMapper.insert(customer);
    }

    @Override
    public int update( JcmdCustomer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public int delete( long id) {

        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JcmdCustomer> list() {
        return customerMapper.selectByExample(new JcmdCustomerExample());
    }

    @Override
    public List<JcmdCustomer> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        JcmdCustomerExample example = new JcmdCustomerExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        //初始化没有查询条件就全部查询
        return customerMapper.selectByExample(example);
    }

    /**
     * 获取 id 和 客户名
     */
    @Override
    public List getIDAndCustomerName() {
        List<Map<String, String>> list = new ArrayList<>();
        List<JcmdCustomer> customerList = customerMapper.selectByExample(new JcmdCustomerExample());
        if (customerList != null) {
            for (JcmdCustomer customer : customerList) {
                Long id = customer.getId();
                String customerName = customer.getName();
                //编号不能为空
                if (id != null && customerName != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", customerName);
                    map.put("id", String.valueOf(id));
                    list.add(map);
                }
            }
        }
        return list;
    }

}
