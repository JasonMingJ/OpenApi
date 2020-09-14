package com.fucode.controller;

import com.fucode.bean.AjaxResponse;
import com.fucode.pojo.AdminUser;
import com.fucode.pojo.Menu;
import com.fucode.service.MenuServices;
import com.fucode.utils.Constant;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName MenuController
 * @Description: TODO
 * @Author user
 * @Date 2020/9/9
 * @Version V1.0
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuServices menuServices;


    @GetMapping("/usermenus")
    public AjaxResponse getMenusByUserRole(HttpSession session, HttpServletResponse response) throws IOException {
        AjaxResponse ajaxResponse = new AjaxResponse();
        AdminUser adminUser = (AdminUser) session.getAttribute(Constant.LOGIN_ADMIN_USER);
        if (adminUser==null){
            response.sendRedirect("/login.html");
        }else {
            long id = adminUser.getId();
            ajaxResponse = menuServices.getMenusByRole(id);
        }
        return ajaxResponse;
    }

}
