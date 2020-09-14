package com.fucode.service;

import com.fucode.pojo.ApiSystemparam;
import com.github.pagehelper.PageInfo;

public interface ApiSysArgsServices {
    PageInfo<ApiSystemparam> getAllArgs(Integer page, Integer limit);

    void addNewApiSystemparam(ApiSystemparam apiSysArgs);

    void updateMapping(ApiSystemparam apiSysArgs);

    void deleteRoutes(Long[] ids);

    PageInfo<ApiSystemparam> searchApiSystemparam(Integer page, Integer limit, String name, Integer state);
}
