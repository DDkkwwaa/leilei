package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdResourceCategory;
import com.shanzhu.purchase.model.XtmdResourceCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdResourceCategoryMapper {
    long countByExample(XtmdResourceCategoryExample example);

    int deleteByExample(XtmdResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdResourceCategory row);

    int insertSelective(XtmdResourceCategory row);

    List<XtmdResourceCategory> selectByExample(XtmdResourceCategoryExample example);

    XtmdResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdResourceCategory row, @Param("example") XtmdResourceCategoryExample example);

    int updateByExample(@Param("row") XtmdResourceCategory row, @Param("example") XtmdResourceCategoryExample example);

    int updateByPrimaryKeySelective(XtmdResourceCategory row);

    int updateByPrimaryKey(XtmdResourceCategory row);
}