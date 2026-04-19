package com.shanzhu.purchase.controller;


import com.shanzhu.purchase.model.XtmdDept;
import com.shanzhu.purchase.service.XtDeptService;
import com.shanzhu.purchase.util.commonPage;
import com.shanzhu.purchase.util.commonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@Api(tags = "JcDeptController", description = "jc-3部门表")
@Tag(name = "JcDeptController", description = "基础信息管理-部门")
@RequestMapping("/jcDept")
public class JcDeptController {

    @Resource
    private XtDeptService deptService;

    @ApiOperation("获取所有部门")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<List<XtmdDept>> listAll() {
        List<XtmdDept> customerList = deptService.list();
        return commonResult.success(customerList);
    }

    @ApiOperation("根据部门名或 地区获取部门")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public commonResult<commonPage<XtmdDept>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<XtmdDept> customerList = deptService.list(keyword, pageSize, pageNum);
        return commonResult.success(commonPage.restPage(customerList));
    }

}
