package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.XtDeptDao;
import com.shanzhu.purchase.mapper.XtmdDeptMapper;
import com.shanzhu.purchase.model.XtmdDept;
import com.shanzhu.purchase.model.XtmdDeptExample;
import com.shanzhu.purchase.service.XtDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门  service
 */
@Service
public class XtDeptServiceImpl implements XtDeptService {

    @Resource
    private XtmdDeptMapper deptMapper;

    @Override
    public int create(XtmdDept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setStatus(0);  //正常
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = token.getPrincipal().toString();
        dept.setCreateBy(userName);
        return deptMapper.insertSelective(dept);
    }

    @Override
    public int update(XtmdDept dept) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = token.getPrincipal().toString();
        dept.setUpdateTime(LocalDateTime.now());
        dept.setUpdateBy(userName);
        return deptMapper.updateByPrimaryKeySelective(dept);
    }

    @Override
    public int delete(Long id) {
        return deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<XtmdDept> list() {
        return deptMapper.selectByExample(new XtmdDeptExample());
    }

    @Override
    public List<XtmdDept> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XtmdDeptExample example = new XtmdDeptExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andDeptNameLike("%" + keyword + "%");
        }
        return deptMapper.selectByExample(example);
    }


    @Override
    public boolean checkDeptName(String deptName) {
        XtmdDeptExample example = new XtmdDeptExample();
        XtmdDeptExample.Criteria criteria = example.createCriteria();
        criteria.andDeptNameEqualTo(deptName);
        //大于0 说明存在 否则不存在
        return deptMapper.countByExample(example) > 0;
    }

    @Override
    public int updateOrAddById(XtmdDept dept) {
        if (dept.getId() != null && dept.getId() != 0) { //更新
            this.update(dept);
        } else {
            this.create(dept);
        }
        return 1;
    }

}
