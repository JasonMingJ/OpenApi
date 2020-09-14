package com.fucode.service.Impl;

import com.fucode.mapper.AdminUserMapper;
import com.fucode.pojo.AdminUser;
import com.fucode.service.AdminUserServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AdminUserServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/9
 * @Version V1.0
 **/
@Service
public class AdminUserServicesImpl implements AdminUserServices {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser checkAdminUserExistence(String email, String password) {
        return adminUserMapper.getAdminUser(email,password);
    }
}
