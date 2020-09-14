package com.fucode.mapper;

import com.fucode.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {


    AdminUser getAdminUser(@Param("email") String email, @Param("password") String password);
}
