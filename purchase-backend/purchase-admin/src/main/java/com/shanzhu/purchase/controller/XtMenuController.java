package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.dto.ShowMenu;
import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.service.XtMenuService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "XtMenuController", description = "xt-3-菜单")
@Tag(name = "XtMenuController", description = "系统管理-菜单")
@RequestMapping("/menu")
public class XtMenuController {

    @Resource
    private XtMenuService menuService;

    @ApiOperation("添加菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody XtmdMenu menu) {
        int count = menuService.create(menu);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@PathVariable Long id, @RequestBody XtmdMenu menu) {
        int count = menuService.update(id, menu);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("批量删除菜单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = menuService.delete(ids);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("修改菜单状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        XtmdMenu menu = new XtmdMenu();
        menu.setHidden(status);
        int count = menuService.update(id, menu);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }


    @ApiOperation("根据角色名称分页获取菜单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<XtmdMenu>> list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XtmdMenu> menuList = menuService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(menuList));
    }

    //通过角色获取菜单
    @ApiOperation("查询父菜单信息")
    @GetMapping(value = "/ListParent")
    public void ListMenuParent() {
        List<XtmdMenu> stringObjectMap = menuService.ListMenuParent();
    }

    @ApiOperation("获取当前登陆用户的所有菜单")
    @GetMapping("/getShowMenu")
    public commonResult<List<ShowMenu>> getShowMenu() {
        List<ShowMenu> showMenus = menuService.selectShowMenuByAdminName();
        return commonResult.success(showMenus);
    }
}
