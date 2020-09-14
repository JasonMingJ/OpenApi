package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.pojo.AdminUser;
import com.fucode.service.AdminUserServices;
import com.fucode.utils.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminUserController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/9
 * @Version V1.0
 **/
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    @Resource
    private AdminUserServices adminUserServices;

    @PostMapping("/login")
    public AjaxResponse login(String email, String password, HttpSession session){
        AdminUser adminUser1 = adminUserServices.checkAdminUserExistence(email,password);
        if (adminUser1!=null){
            session.setAttribute(Constant.LOGIN_ADMIN_USER,adminUser1);
            return new AjaxResponse(true,"登陆成功");
        }else{
            return new AjaxResponse(false,"登陆失败");
        }
    }

    @GetMapping("getName")
    public AjaxResponse getName(HttpServletRequest request){
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constant.LOGIN_ADMIN_USER);
        return new AjaxResponse(true,"",adminUser.getEmail());
    }
}
