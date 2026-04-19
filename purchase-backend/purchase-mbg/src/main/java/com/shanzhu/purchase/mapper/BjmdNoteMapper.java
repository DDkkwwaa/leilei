package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.BjmdNote;
import com.shanzhu.purchase.model.BjmdNoteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjmdNoteMapper {
    long countByExample(BjmdNoteExample example);

    int deleteByExample(BjmdNoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BjmdNote row);

    int insertSelective(BjmdNote row);

    List<BjmdNote> selectByExampleWithBLOBs(BjmdNoteExample example);

    List<BjmdNote> selectByExample(BjmdNoteExample example);

    BjmdNote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") BjmdNote row, @Param("example") BjmdNoteExample example);

    int updateByExampleWithBLOBs(@Param("row") BjmdNote row, @Param("example") BjmdNoteExample example);

    int updateByExample(@Param("row") BjmdNote row, @Param("example") BjmdNoteExample example);

    int updateByPrimaryKeySelective(BjmdNote row);

    int updateByPrimaryKeyWithBLOBs(BjmdNote row);

    int updateByPrimaryKey(BjmdNote row);
}