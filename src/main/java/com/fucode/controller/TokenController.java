package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.UserToken;
import com.fucode.service.TokenServices;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TokenController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/11
 * @Version V1.0
 **/
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenServices tokenServices;

    @GetMapping("/table")
    public TableData<UserToken> getTable(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "5") Integer limit){
        TableData<UserToken> apiMappingTableData = new TableData<>();
        PageInfo<UserToken> pageInfo=tokenServices.getAllUserToken(page,limit);
        apiMappingTableData.setCount(pageInfo.getTotal());
        apiMappingTableData.setData(pageInfo.getList());
        return apiMappingTableData;
    }

    @PostMapping("/add")
    public AjaxResponse addNewUserToken(UserToken userToken){
        try {
            tokenServices.addNewUserToken(userToken);
            return new AjaxResponse(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加失败");
        }
    }

    @RequestMapping("/update")
    public AjaxResponse updateUserToken(@RequestBody UserToken userToken){
        try {
            tokenServices.updateUserToken(userToken);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @PostMapping("/delete")
    public AjaxResponse deleteUserToken(Long [] ids){
        try {
            tokenServices.deleteUserTokens(ids);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @GetMapping("/search")
    public TableData<UserToken> searchUserToken(@RequestParam(defaultValue = "1") Integer page ,
                                              @RequestParam(defaultValue = "2") Integer limit,
                                              String name,Integer state){
        TableData<UserToken> tableData = new TableData<>();
        PageInfo<UserToken> pageInfo = tokenServices.searchUserToken(page,limit,name,state);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
