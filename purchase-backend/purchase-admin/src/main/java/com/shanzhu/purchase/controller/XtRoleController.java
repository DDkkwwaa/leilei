package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.dto.RoleMenuRelationDto;
import com.shanzhu.purchase.model.XtmdRole;
import com.shanzhu.purchase.service.XtRoleService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "XtMdRoleController", description = "xt-2-角色")
@Tag(name = "XtMdRoleController", description = "系统管理-角色")
@RequestMapping("/role")
public class XtRoleController {

    @Resource
    private XtRoleService roleService;

    @ApiOperation(value = "角色名-角色是否存在", notes = "校验角色名称")
    @RequestMapping(value = "/checkRoleName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult checkRoleName(@RequestParam(value = "roleName") String roleName) {
        boolean result = roleService.checkRoleName(roleName);
        if (result) {
            return commonResult.success("YES");
        } else {
            return commonResult.success("NO");
        }
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult add(@RequestBody XtmdRole role) {
        int count = roleService.addOrUpdateRole(role);
        if (count > 0) {
            return commonResult.success("添加成功");
        } else {
            return commonResult.failed("添加失败");
        }
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(@RequestBody XtmdRole role) {
        int count = roleService.update(role);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("删除角色>同时删除菜单关系")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long roleId) {
        boolean flag = roleService.delete(roleId);
        if (flag) {
            return commonResult.success("true");
        }
        return commonResult.failed("false");
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<XtmdRole>> listAll() {
        List<XtmdRole> roleList = roleService.list();
        return commonResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<XtmdRole>> list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XtmdRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        XtmdRole xtRole = new XtmdRole();
        xtRole.setStatus(status);
        int count = roleService.update(xtRole);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("通过角色id获取相关菜单")
    @RequestMapping(value = "/getRoleMenuById", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<Map<String, Object>> listMenu(Long roleId) {
        Map<String, Object> roleList = roleService.getRoleMenuById(roleId);
        return commonResult.success(roleList);
    }


    @ApiOperation("角色分配菜单")
    @RequestMapping(value = "/roleToAuth", method = RequestMethod.POST)
    @ResponseBody
    public commonResult roleToAuth(@RequestBody RoleMenuRelationDto roleMenu) {
        int size = roleService.roleToAuth(roleMenu);
        if (size > 0) {
            return commonResult.success("成功");
        } else {
            return commonResult.success("失败");
        }
    }

}
