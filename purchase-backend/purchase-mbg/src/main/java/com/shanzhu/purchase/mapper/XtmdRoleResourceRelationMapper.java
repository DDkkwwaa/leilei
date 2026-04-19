package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdRoleResourceRelation;
import com.shanzhu.purchase.model.XtmdRoleResourceRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdRoleResourceRelationMapper {
    long countByExample(XtmdRoleResourceRelationExample example);

    int deleteByExample(XtmdRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdRoleResourceRelation row);

    int insertSelective(XtmdRoleResourceRelation row);

    List<XtmdRoleResourceRelation> selectByExample(XtmdRoleResourceRelationExample example);

    XtmdRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdRoleResourceRelation row, @Param("example") XtmdRoleResourceRelationExample example);

    int updateByExample(@Param("row") XtmdRoleResourceRelation row, @Param("example") XtmdRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(XtmdRoleResourceRelation row);

    int updateByPrimaryKey(XtmdRoleResourceRelation row);
}