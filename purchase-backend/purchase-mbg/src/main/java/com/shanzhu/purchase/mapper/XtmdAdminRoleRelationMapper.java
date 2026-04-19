package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdAdminRoleRelation;
import com.shanzhu.purchase.model.XtmdAdminRoleRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdAdminRoleRelationMapper {
    long countByExample(XtmdAdminRoleRelationExample example);

    int deleteByExample(XtmdAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdAdminRoleRelation row);

    int insertSelective(XtmdAdminRoleRelation row);

    List<XtmdAdminRoleRelation> selectByExample(XtmdAdminRoleRelationExample example);

    XtmdAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdAdminRoleRelation row, @Param("example") XtmdAdminRoleRelationExample example);

    int updateByExample(@Param("row") XtmdAdminRoleRelation row, @Param("example") XtmdAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(XtmdAdminRoleRelation row);

    int updateByPrimaryKey(XtmdAdminRoleRelation row);
}