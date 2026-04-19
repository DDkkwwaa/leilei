package com.shanzhu.purchase.service;


import com.shanzhu.purchase.model.BjmdNote;

import java.util.List;

/**
 * 笔记 service
 */
public interface BjNoteService {

    /**
     * 添加笔记
     */
    int create(BjmdNote note);

    /**
     * 修改笔记信息
     */
    int update(BjmdNote note);

    /**
     * 删除笔记
     */
    int delete(Long id);

    List<BjmdNote> list(String userName);

    /**
     * 分页获取所有笔记列表-------只能获取自己的
     */
    List<BjmdNote> listByName(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 创建新笔记
     *
     * @param title
     * @return
     */
    int createByTitle(String title);
}
