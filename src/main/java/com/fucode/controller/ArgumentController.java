package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.ApiSystemparam;
import com.fucode.pojo.ApiSystemparam;
import com.fucode.service.ApiSysArgsServices;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArgumentController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@RestController
@RequestMapping("/args")
public class ArgumentController {

    @Autowired
    private ApiSysArgsServices apiSysArgsServices;

    @GetMapping("/table")
    public TableData<ApiSystemparam> getAllArgs(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "5") Integer limit){
        TableData<ApiSystemparam> tableData = new TableData<>();
        PageInfo<ApiSystemparam> pageInfo = apiSysArgsServices. getAllArgs(page,limit);
        tableData.setData(pageInfo.getList());
        tableData.setCount(pageInfo.getTotal());
        return tableData;
    }

    @PostMapping("/add")
    public AjaxResponse addNewRoute(ApiSystemparam apiSysArgs){
        try {
            apiSysArgsServices.addNewApiSystemparam(apiSysArgs);
            return new AjaxResponse(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加失败");
        }
    }

    @PostMapping("/update")
    public AjaxResponse updateRoute(ApiSystemparam apiSysArgs){
        try {
            apiSysArgsServices.updateMapping(apiSysArgs);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @PostMapping("/delete")
    public AjaxResponse deleteRoute(Long [] ids){
        try {
            apiSysArgsServices.deleteRoutes(ids);
            return new AjaxResponse(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"删除失败");
        }
    }

    @GetMapping("/search")
    public TableData<ApiSystemparam> searchRoute(@RequestParam(defaultValue = "1") Integer page ,
                                             @RequestParam(defaultValue = "2") Integer limit,
                                             String name,Integer state){
        TableData<ApiSystemparam> tableData = new TableData<>();
        PageInfo<ApiSystemparam> pageInfo = apiSysArgsServices.searchApiSystemparam(page,limit,name,state);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
