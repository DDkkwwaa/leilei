package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.model.JxmdPurchaseExit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxPurchaseDao {

    int checkAndExitGoods(@Param("id") Long id, @Param("remark") String remark);

    List<JxmdPurchaseExit> getByField(@Param("NumBer") String NumBer);

    int UpIfDesTory(@Param("flag") int flag, @Param("id") Long id);
}
