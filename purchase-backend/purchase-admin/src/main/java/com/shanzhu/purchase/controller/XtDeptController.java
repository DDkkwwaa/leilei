package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.XtmdDept;
import com.shanzhu.purchase.service.XtDeptService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(tags = "XtMdDeptController", description = "xt-4-部门管理")
@Tag(name = "XtMdDeptController", description = "系统管理-部门")
@RequestMapping("/dept")
public class XtDeptController {

    @Resource
    private XtDeptService deptService;

    @ApiOperation("添加部门")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public commonResult create(@RequestBody XtmdDept dept) {
        int count = deptService.updateOrAddById(dept);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }


    @ApiOperation(value = "部门是否存在", notes = "校验部门名称")
    @RequestMapping(value = "/checkdeptName", method = RequestMethod.GET)
    @ResponseBody
    public commonResult checkDeptName(@RequestParam(value = "deptName") String deptName) {
        boolean result = deptService.checkDeptName(deptName);
        if (result) {
            return commonResult.success("YES");
        } else {
            return commonResult.success("NO");
        }
    }

    @ApiOperation("删除部门")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public commonResult delete(Long id) {
        int count = deptService.delete(id);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }

    @ApiOperation("根据部门名称分页获取部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<XtmdDept>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<XtmdDept> deptList = deptService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(deptList));
    }

    @ApiOperation("修改部门状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public commonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        XtmdDept XtDept = new XtmdDept();
        XtDept.setStatus(status);
        int count = deptService.update(XtDept);
        if (count > 0) {
            return commonResult.success(count);
        }
        return commonResult.failed();
    }


}
