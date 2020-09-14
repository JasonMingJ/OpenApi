package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.AppInfo;
import com.fucode.service.AppInfoServices;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ApplicationController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/8
 * @Version V1.0
 **/
@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private AppInfoServices appInfoServices;

    @PostMapping("/addApp")
    public AjaxResponse addNewApp(AppInfo appInfo){
        try{
            appInfoServices.insertAppInfo(appInfo);
            return new AjaxResponse(true,"添加应用成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加应用失败");
        }
    }

    @PostMapping("/delApps")
    public AjaxResponse delOneA(Long[] ids){
        if (ids!=null && ids.length>0) {
            appInfoServices.deleteAppInfoByIds(ids);
            return new AjaxResponse(true, "删除应用成功");
        }else
            return new AjaxResponse(false,"删除应用失败");
    }

    @PostMapping("/updateApp")
    public AjaxResponse updateA(AppInfo appInfo){
        try{
            appInfoServices.updateAppInfo(appInfo);
            return new AjaxResponse(true,"修改应用信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改应用信息失败");
        }
    }

    @GetMapping("/getApps")
    public TableData<AppInfo> getTable(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "3") Integer limit){
        TableData<AppInfo> appInfoTableData = new TableData<>();
        PageInfo<AppInfo> appInfoByPage = appInfoServices.getAppInfoByPage(page, limit);
        appInfoTableData.setCount(appInfoByPage.getTotal());
        appInfoTableData.setData(appInfoByPage.getList());
        appInfoTableData.setMsg("查询成功！");
        return appInfoTableData;
    }
}
