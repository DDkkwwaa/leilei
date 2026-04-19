package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.BjmdNote;
import com.shanzhu.purchase.service.BjNoteService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "BjNoteController", description = "bj-笔记")
@Tag(name = "BjNoteController", description = "系统管理-笔记")
@RequestMapping("/bjNote")
public class BjNoteController {

    @Resource
    private BjNoteService noteService;

    @ApiOperation("添加笔记")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody BjmdNote note) {
        int count = noteService.create(note);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("创建笔记，创建笔记名")
    @RequestMapping(value = "/createByTitle", method = RequestMethod.POST)
    @ResponseBody
    public commonResult createByTitle(String title) {
        int result = noteService.createByTitle(title);
        if (result > 0) {
            return commonResult.success(result);
        }
        return commonResult.failed("系统暂时只能创建8条 ");
    }

    @ApiOperation("更新笔记")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody BjmdNote note) {
        int count = noteService.update(note);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("获取当前用户的所有笔记")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<BjmdNote>> listAll() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) token.getPrincipal();
        List<BjmdNote> noteList = noteService.list(userName);
        return commonResult.success(noteList);
    }

    @ApiOperation("分页查询笔记")
    @RequestMapping(value = "/listByName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<BjmdNote>> listByName(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<BjmdNote> noteList = noteService.listByName(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(noteList));
    }

    @ApiOperation("删除笔记")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public commonResult deleteNote(Long id) {
        int count = noteService.delete(id);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

}
