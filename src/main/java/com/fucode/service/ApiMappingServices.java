package com.fucode.service;

import com.fucode.pojo.ApiMapping;
import com.github.pagehelper.PageInfo;

public interface ApiMappingServices {
    PageInfo<ApiMapping> getAllApiMapping(Integer page, Integer limit);

    int addNewApiMapping(ApiMapping apiMapping);

    int updateMapping(ApiMapping apiMapping);

    int deleteRoutes(Long[] ids);

    PageInfo<ApiMapping> searchApiMapping(Integer page, Integer limit, String name, Integer state);
}
