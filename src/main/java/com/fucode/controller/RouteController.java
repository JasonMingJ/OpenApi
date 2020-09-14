package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.ApiMapping;
import com.fucode.service.ApiMappingServices;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RouteController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    ApiMappingServices apiMappingServices;

    @GetMapping("/table")
    public TableData<ApiMapping> getTable(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "5") Integer limit){
        TableData<ApiMapping> apiMappingTableData = new TableData<>();
        PageInfo<ApiMapping> pageInfo=apiMappingServices.getAllApiMapping(page,limit);
        apiMappingTableData.setCount(pageInfo.getTotal());
        apiMappingTableData.setData(pageInfo.getList());
        return apiMappingTableData;
    }

    @PostMapping("/add")
    public AjaxResponse addNewRoute(ApiMapping apiMapping){
        try {
            apiMappingServices.addNewApiMapping(apiMapping);
            return new AjaxResponse(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加失败");
        }
    }

    @PostMapping("/update")
    public AjaxResponse updateRoute(ApiMapping apiMapping){
        try {
            apiMappingServices.updateMapping(apiMapping);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @PostMapping("/delete")
    public AjaxResponse deleteRoute(Long [] ids){
        try {
            apiMappingServices.deleteRoutes(ids);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @GetMapping("/search")
    public TableData<ApiMapping> searchRoute(@RequestParam(defaultValue = "1") Integer page ,
                                             @RequestParam(defaultValue = "2") Integer limit,
                                             String name,Integer state){
        TableData<ApiMapping> tableData = new TableData<>();
        PageInfo<ApiMapping> pageInfo = apiMappingServices.searchApiMapping(page,limit,name,state);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
