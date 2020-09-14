package com.fucode.service;

import com.fucode.pojo.AppInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppInfoServices {

    int insertAppInfo(AppInfo appInfo);

    int updateAppInfo(AppInfo appInfo);

    int deleteAppInfoById(Long id);

    int deleteAppInfoByIds(Long[] ids);

    AppInfo getAppInfoById(Long id);

    List<AppInfo> getAppInfos();

    //查询分页
    PageInfo<AppInfo> getAppInfoByPage(Integer page, Integer limit);
}
