package com.fucode.mapper;

import com.fucode.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppInfoMapper {

    int insertAppInfo(AppInfo appinfo);

    int deleteAppInfo(@Param("id") Long id);

    int deleteAppInfoByIds(Long[] ids);

    int updateAppInfo(AppInfo appinfo);

    AppInfo getAppInfoById(@Param("id") Long id);

    List<AppInfo> getAllAppinfo();

    List<AppInfo> getAppinfoByCondition(String name);
}
