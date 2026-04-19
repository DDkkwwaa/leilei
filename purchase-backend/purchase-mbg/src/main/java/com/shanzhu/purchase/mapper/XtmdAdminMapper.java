package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdAdmin;
import com.shanzhu.purchase.model.XtmdAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdAdminMapper {
    long countByExample(XtmdAdminExample example);

    int deleteByExample(XtmdAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdAdmin row);

    int insertSelective(XtmdAdmin row);

    List<XtmdAdmin> selectByExample(XtmdAdminExample example);

    XtmdAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdAdmin row, @Param("example") XtmdAdminExample example);

    int updateByExample(@Param("row") XtmdAdmin row, @Param("example") XtmdAdminExample example);

    int updateByPrimaryKeySelective(XtmdAdmin row);

    int updateByPrimaryKey(XtmdAdmin row);
}