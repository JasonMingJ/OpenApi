package com.fucode.service;

import com.fucode.pojo.Customer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerServices {

    int insertCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteCustomer(Long[] id);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomer();

    PageInfo<Customer> getAllCustomerPages(Integer page, Integer limit);

    PageInfo<Customer> getCustomersPagesByCondition(Integer page, Integer limit, String username, String status);
}
