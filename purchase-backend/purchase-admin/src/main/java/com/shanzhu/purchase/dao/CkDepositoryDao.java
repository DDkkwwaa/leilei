package com.shanzhu.purchase.dao;

import org.apache.ibatis.annotations.Param;

public interface CkDepositoryDao {

    int addArea(@Param("depositoryName") String depositoryName, @Param("quantity") Long quantity);

    int reduceArea(@Param("depositoryName") String depositoryName, @Param("quantity") Long quantity);

    int getCount();
}
