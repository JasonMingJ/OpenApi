package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.Recharge;
import com.fucode.service.RechargeServices;
import com.fucode.utils.DateUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName RechargeController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@RestController
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    private RechargeServices rechargeServices;

    @GetMapping("/table")
    public TableData<Recharge> getTable(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "5") Integer limit){
        TableData<Recharge> apiMappingTableData = new TableData<>();
        PageInfo<Recharge> pageInfo=rechargeServices.getAllRecharge(page,limit);
        apiMappingTableData.setCount(pageInfo.getTotal());
        apiMappingTableData.setData(pageInfo.getList());
        return apiMappingTableData;
    }

    @PostMapping("/add")
    public AjaxResponse addNewRecharge(Recharge recharge){
        try {
            recharge.setUpdatetime(new Date());
            recharge.setCreatetime(new Date());
            rechargeServices.addNewRecharge(recharge);
            return new AjaxResponse(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加失败");
        }
    }

    @PostMapping("/update")
    public AjaxResponse updateRecharge(Recharge recharge){
        try {
            recharge.setUpdatetime(DateUtils.utilDateTosqlDate(new Date()));
            rechargeServices.updateRecharge(recharge);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @PostMapping("/delete")
    public AjaxResponse deleteRecharge(Long [] ids){
        try {
            rechargeServices.deleteRecharges(ids);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @GetMapping("/search")
    public TableData<Recharge> searchRecharge(@RequestParam(defaultValue = "1") Integer page ,
                                             @RequestParam(defaultValue = "2") Integer limit,
                                             String name,Integer state){
        TableData<Recharge> tableData = new TableData<>();
        PageInfo<Recharge> pageInfo = rechargeServices.searchRecharge(page,limit,name,state);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
