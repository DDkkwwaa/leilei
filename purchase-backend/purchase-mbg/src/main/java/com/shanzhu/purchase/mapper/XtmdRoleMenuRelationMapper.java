package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdRoleMenuRelation;
import com.shanzhu.purchase.model.XtmdRoleMenuRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdRoleMenuRelationMapper {
    long countByExample(XtmdRoleMenuRelationExample example);

    int deleteByExample(XtmdRoleMenuRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdRoleMenuRelation row);

    int insertSelective(XtmdRoleMenuRelation row);

    List<XtmdRoleMenuRelation> selectByExample(XtmdRoleMenuRelationExample example);

    XtmdRoleMenuRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdRoleMenuRelation row, @Param("example") XtmdRoleMenuRelationExample example);

    int updateByExample(@Param("row") XtmdRoleMenuRelation row, @Param("example") XtmdRoleMenuRelationExample example);

    int updateByPrimaryKeySelective(XtmdRoleMenuRelation row);

    int updateByPrimaryKey(XtmdRoleMenuRelation row);
}