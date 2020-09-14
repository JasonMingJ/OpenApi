package com.fucode.service;

import com.fucode.pojo.AdminUser;

public interface  AdminUserServices {

    AdminUser checkAdminUserExistence(String email, String password);
}
