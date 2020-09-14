package com.fucode.service.Impl;

import com.fucode.mapper.CustomerMapper;
import com.fucode.pojo.Customer;
import com.fucode.service.ICustomerServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ICustomerServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/7
 * @Version V1.0
 **/
@Service
public class ICustomerServicesImpl implements ICustomerServices {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int insertCustomer(Customer customer){
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer){
        if(customer!=null){
            return customerMapper.updateCustomer(customer);
        }else {
            System.out.println("customer不能为空！！！");
        }
        return 0;
    }

    @Override
    public int deleteCustomer(Long[] ids){
        if (ids!=null){
            customerMapper.deleteCustomer(ids);
        }else {
            System.out.println("id不能为空！！！");
        }
        return 0;
    }

    @Override
    public Customer getCustomerById(Long id){
        if (id!=null) {
            return customerMapper.getCustomerById(id);
        }else {
            System.out.println("id不能为空！！！");
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomer(){
        return customerMapper.getAllCustomers();
    }

    @Override
    public PageInfo<Customer> getAllCustomerPages(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Customer> allCustomers = customerMapper.getAllCustomers();
        return new PageInfo<>(allCustomers);
    }

    @Override
    public PageInfo<Customer> getCustomersPagesByCondition(Integer page, Integer limit, String username, String status) {
        PageHelper.startPage(page,limit);
        List<Customer> allCustomers = customerMapper.getCustomerByCondition(username, status);
        return new PageInfo<>(allCustomers);
    }
}
