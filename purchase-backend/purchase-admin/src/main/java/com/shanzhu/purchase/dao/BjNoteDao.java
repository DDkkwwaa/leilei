package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.model.BjmdNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjNoteDao {

    List<BjmdNote> getNoteList(@Param("userName") String userName);
}
