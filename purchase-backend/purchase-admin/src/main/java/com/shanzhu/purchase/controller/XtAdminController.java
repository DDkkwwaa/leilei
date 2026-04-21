package com.shanzhu.purchase.controller;

import com.shanzhu.purchase.dto.ForgotPasswordParam;
import com.shanzhu.purchase.dto.UpdateAdminPasswordParam;
import com.shanzhu.purchase.dto.UpdateAdminProfileParam;
import com.shanzhu.purchase.dto.adminRoleRelationDto;
import com.shanzhu.purchase.model.XtmdAdmin;
import com.shanzhu.purchase.service.XtAdminService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "XtMdAdminController", description = "xt-1-用户管理")
@Tag(name = "AdminController", description = "系统管理-用户")
@RequestMapping("/admin")
public class XtAdminController {

    @Resource
    private XtAdminService AdminService;

    @ApiOperation(value = "通过 token 获取当前用户信息")
    @RequestMapping(value = "/getAdminInfo", method = RequestMethod.GET)
    @ResponseBody
    public commonResult getAdminInfo() {
        UsernamePasswordAuthenticationToken token =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) token.getPrincipal();
        List<XtmdAdmin> infoList = AdminService.getAdminInfo(userName);
        return commonResult.success(infoList);
    }

    @ApiOperation(value = "用户是否存在", notes = "校验用户名")
    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult checkRoleName(@RequestParam(value = "userName") String userName) {
        boolean result = AdminService.checkUserName(userName);
        if (result) {
            return commonResult.success("YES");
        } else {
            return commonResult.success("NO");
        }
    }

    @ApiOperation(value = "根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/List", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<XtmdAdmin>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<XtmdAdmin> adminList = AdminService.getAdminList(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(adminList));
    }

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody XtmdAdmin admin) {
        int count = AdminService.updateOrAddById(admin);
        if (count > 0) {
            return commonResult.success("成功");
        }
        return commonResult.failed();
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public commonResult update(XtmdAdmin admin) {
        int count = AdminService.update(admin);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("登录时旧密码校验")
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    public commonResult checkPassword(@RequestBody UpdateAdminPasswordParam updateCheck) {
        boolean flag = AdminService.checkPassword(updateCheck.getUsername(), updateCheck.getOldPassword());
        if (flag) {
            return commonResult.success("true");
        }
        return commonResult.success("false");
    }

    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updatePassword(@RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = AdminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return commonResult.success("修改成功");
        }
        return commonResult.failed();
    }

    @ApiOperation("忘记密码")
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    @ResponseBody
    public commonResult forgotPassword(@RequestBody ForgotPasswordParam forgotPasswordParam) {
        int status = AdminService.forgotPassword(forgotPasswordParam);
        if (status > 0) {
            return commonResult.success("重置密码成功");
        }
        if (status == -1) {
            return commonResult.failed("用户不存在");
        }
        if (status == -2) {
            return commonResult.failed("手机号不匹配");
        }
        return commonResult.failed("重置密码失败");
    }

    @ApiOperation("修改当前用户个人信息")
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateProfile(@RequestBody UpdateAdminProfileParam updateAdminProfileParam) {
        UsernamePasswordAuthenticationToken token =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) token.getPrincipal();
        int count = AdminService.updateProfile(userName, updateAdminProfileParam);
        if (count > 0) {
            return commonResult.success("修改成功");
        }
        return commonResult.failed("修改失败");
    }

    @ApiOperation(value = "删除用户-备用")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult deleAdminById(Long id) {
        int count = AdminService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation(value = "批量删除用户")
    @RequestMapping(value = "/deleAdminList", method = RequestMethod.GET)
    @ResponseBody
    public commonResult deleAdminList(List list) {
        return commonResult.success("1");
    }

    @ApiOperation("通过用户 id 获取角色")
    @RequestMapping(value = "/getRoleByAdminId", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<Map<String, Object>> listMenu(@RequestParam("adminId") Long adminId) {
        Map<String, Object> roleList = AdminService.getRoleByAdminId(adminId);
        return commonResult.success(roleList);
    }

    @ApiOperation("用户分配角色")
    @RequestMapping(value = "/adminToRole", method = RequestMethod.POST)
    @ResponseBody
    public commonResult roleToAuth(@RequestBody adminRoleRelationDto adminRole) {
        int size = AdminService.adminToRoleRelationship(adminRole);
        if (size > 0) {
            return commonResult.success("成功");
        } else {
            return commonResult.success("失败");
        }
    }

    @ApiOperation("密码初始化")
    @RequestMapping(value = "/pwdInit", method = RequestMethod.POST)
    @ResponseBody
    public commonResult pwdInit(Long id) {
        int result = AdminService.pwdInit(id);
        if (result > 0) {
            return commonResult.success("成功");
        } else {
            return commonResult.failed("失败");
        }
    }

    @ApiOperation("获取所有员工信息[id, name]")
    @RequestMapping(value = "/adminAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List> adminAll() {
        List adminList = AdminService.getAdminAll();
        if (adminList != null) {
            return commonResult.success(adminList);
        }
        return commonResult.failed();
    }
}
