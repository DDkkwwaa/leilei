package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.model.JxmdPurchaseExit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxPurchaseExitDao {

    List<JxmdPurchaseExit> selectNumber(@Param("sourceNumber") Long sourceNumber);

    List<JxmdPurchaseExit> getByField(String checkNumber);

}
