package com.fucode.mapper;

import com.fucode.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    int insertCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteCustomer(Long[] id);

    Customer getCustomerById(@Param("id") Long id);

    List<Customer> getAllCustomers();

    List<Customer> getCustomerByCondition(@Param("username") String username, @Param("status") String status);
}
