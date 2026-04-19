package com.shanzhu.purchase.service.Impl;

import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.BjNoteDao;
import com.shanzhu.purchase.mapper.BjmdNoteMapper;
import com.shanzhu.purchase.model.BjmdNote;
import com.shanzhu.purchase.model.BjmdNoteExample;
import com.shanzhu.purchase.service.BjNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BjNoteServiceImpl implements BjNoteService {

    @Resource
    private BjmdNoteMapper noteMapper;

    @Override
    public int create(BjmdNote note) {
        note.setCreateTime(LocalDateTime.now());
        return noteMapper.insert(note);
    }

    @Override
    public int update(BjmdNote note) {
        note.setUpdataTime(LocalDateTime.now());
        return noteMapper.updateByPrimaryKeySelective(note);
    }

    @Override
    public int delete(Long id) {
        return noteMapper.deleteByPrimaryKey(id);
    }

    //查询 分页获取当前用户的笔记
    @Override
    public List<BjmdNote> listByName(String keyword, Integer pageSize, Integer pageNum) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = token.getPrincipal().toString();
        PageHelper.startPage(pageNum, pageSize);
        BjmdNoteExample example = new BjmdNoteExample();
        example.createCriteria().andTitleLike("%" + keyword + "%").andUserNameEqualTo(username);

        return noteMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int createByTitle(String title) {
        BjmdNoteExample bjmdNoteExample = new BjmdNoteExample();

        List<BjmdNote> bjmdNotes = noteMapper.selectByExample(bjmdNoteExample);
        int size = bjmdNotes.size();
        if (size > 8) {
            return 0;
        }   //只能创建8条

        BjmdNote note = new BjmdNote();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = token.getPrincipal().toString();
        note.setCreateTime(LocalDateTime.now());  //
        note.setUserName(userName);
        note.setTitle(title);

        return noteMapper.insertSelective(note);
    }

    @Override
    public List<BjmdNote> list(String userName) {
        BjmdNoteExample bjmdNoteExample = new BjmdNoteExample();
        BjmdNoteExample.Criteria criteria = bjmdNoteExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return noteMapper.selectByExampleWithBLOBs(bjmdNoteExample);  //获取含有text类型的方法
    }

}
