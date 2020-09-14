package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.bean.TableData;
import com.fucode.pojo.Customer;
import com.fucode.service.ICustomerServices;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CustomerController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/7
 * @Version V1.0
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerServices iCustomerServices;

    @PostMapping("/testadd")
    public String add(Customer customer){
        iCustomerServices.insertCustomer(customer);
        return "SUCCESS";
    }

    @GetMapping("/getcus")
    public List<Customer> get(){
        List<Customer> allCustomer = iCustomerServices.getAllCustomer();
        System.out.println(allCustomer);
        return allCustomer;
    }

    @GetMapping("/table")
    public TableData<Customer> getTable(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit){

        TableData<Customer> tableData = new TableData<>();
        PageInfo<Customer> pageInfo  = iCustomerServices.getAllCustomerPages(page,limit);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        System.out.println(tableData);
        return tableData;
    }

    @PostMapping("/add")
    public AjaxResponse addCustomer(Customer customer){
        try{
            iCustomerServices.insertCustomer(customer);
            return new AjaxResponse(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"添加失败");
        }
    }

    @PostMapping("/update")
    public AjaxResponse edit(Customer customer){
        try{
            iCustomerServices.updateCustomer(customer);
            return new AjaxResponse(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"修改失败");
        }
    }

    @PostMapping("/delete")
    public AjaxResponse del(Long[] ids){
        try{
            iCustomerServices.deleteCustomer(ids);
            return new AjaxResponse(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResponse(false,"删除失败");
        }
    }

    @GetMapping("/search")
    public TableData<Customer> getCusByCondition(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "3") Integer limit,
                                                 @RequestParam("name") String username,
                                                 @RequestParam("state") String status){
        TableData<Customer> tableData = new TableData<>();
        PageInfo<Customer> pageInfo  = iCustomerServices.getCustomersPagesByCondition(page,limit,username,status);
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
