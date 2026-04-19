package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdResource;
import com.shanzhu.purchase.model.XtmdResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdResourceMapper {
    long countByExample(XtmdResourceExample example);

    int deleteByExample(XtmdResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdResource row);

    int insertSelective(XtmdResource row);

    List<XtmdResource> selectByExample(XtmdResourceExample example);

    XtmdResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdResource row, @Param("example") XtmdResourceExample example);

    int updateByExample(@Param("row") XtmdResource row, @Param("example") XtmdResourceExample example);

    int updateByPrimaryKeySelective(XtmdResource row);

    int updateByPrimaryKey(XtmdResource row);
}