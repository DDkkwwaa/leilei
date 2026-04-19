package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdDept;
import com.shanzhu.purchase.model.XtmdDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdDeptMapper {
    long countByExample(XtmdDeptExample example);

    int deleteByExample(XtmdDeptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdDept row);

    int insertSelective(XtmdDept row);

    List<XtmdDept> selectByExample(XtmdDeptExample example);

    XtmdDept selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdDept row, @Param("example") XtmdDeptExample example);

    int updateByExample(@Param("row") XtmdDept row, @Param("example") XtmdDeptExample example);

    int updateByPrimaryKeySelective(XtmdDept row);

    int updateByPrimaryKey(XtmdDept row);
}