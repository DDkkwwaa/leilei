package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.XtmdDept;

import java.util.List;

public interface XtDeptService {

    /**
     * 添加部门
     */
    int create(XtmdDept dept);

    /**
     * 修改部门信息
     */
    int update(  XtmdDept dept);

    /**
     * 批量删除部门
     */
    int delete(Long id);

    /**
     * 获取所有部门列表
     */
    List<XtmdDept> list();

    /**
     * 分页获取部门列表
     */
    List<XtmdDept> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 验证部门是否存在
     * @param deptName
     * @return
     */
    boolean checkDeptName(String deptName);

    int updateOrAddById(XtmdDept dept);
}
