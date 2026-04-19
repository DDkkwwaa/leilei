package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdAdminDeptRelation;
import com.shanzhu.purchase.model.XtmdAdminDeptRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdAdminDeptRelationMapper {
    long countByExample(XtmdAdminDeptRelationExample example);

    int deleteByExample(XtmdAdminDeptRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdAdminDeptRelation row);

    int insertSelective(XtmdAdminDeptRelation row);

    List<XtmdAdminDeptRelation> selectByExample(XtmdAdminDeptRelationExample example);

    XtmdAdminDeptRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdAdminDeptRelation row, @Param("example") XtmdAdminDeptRelationExample example);

    int updateByExample(@Param("row") XtmdAdminDeptRelation row, @Param("example") XtmdAdminDeptRelationExample example);

    int updateByPrimaryKeySelective(XtmdAdminDeptRelation row);

    int updateByPrimaryKey(XtmdAdminDeptRelation row);
}