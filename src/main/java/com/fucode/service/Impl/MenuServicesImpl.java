package com.fucode.service.Impl;

import com.fucode.bean.AjaxResponse;
import com.fucode.mapper.MenuMapper;
import com.fucode.pojo.Menu;
import com.fucode.service.MenuServices;
import com.fucode.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/9
 * @Version V1.0
 **/
@Service
public class MenuServicesImpl implements MenuServices {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public AjaxResponse getMenusByRole(long id) {

        /*先获取一级菜单的List*/
        List<Menu> fatherMenuList = menuMapper.getFatherMenuByUserId(id);
        List<Menu> menus = postHandleMenu(fatherMenuList);
        return new AjaxResponse(true,"",menus);
    }

    /*处理对应用户能看见的菜单集合，在每个父Menu对象里面存自己的子Menu的集合*/
    private List<Menu> postHandleMenu(List<Menu> originMenuList){

        //创建一个集合存父菜单
        List<Menu> fatherMenuList = new ArrayList<>();
        //先将原始的Menu集合处理一下，key为id,value为Menu，方便取用
        Map<Integer,Menu> menuMap = new HashMap<>();
        /*首先处理原始的MenuList，将其中的所有一级菜单存到新的fatherMenuList集合里*/
        for (Menu menu : originMenuList) {
            menuMap.put(menu.getId(),menu);
            //如过parent_id为空，那么这个Menu就是一个父菜单
            if((menu.getParentId())==null){
                fatherMenuList.add(menu);
            }
        }

        /* 向fatherMenuList里Menu对象的子菜单集合添加对应的子菜单
        *  遍历原始菜单集合，忽略类型为2的菜单，将子菜单保存到父菜单每个对象的属性里*/
        for (Menu menu : originMenuList) {
            if (!menu.getType().equals(Constant.OPERATION_MENU_TYPE)){
              if (menu.getParentId()!=null && menuMap.containsKey(menu.getParentId())){
                  //取出menuMap中的menu，将子菜单注入
                  menuMap.get(menu.getParentId()).getChildrenMenuList().add(menu);
              }
            }
        }
        //返回处理完成的一级菜单集合
        return fatherMenuList;
    }
}
