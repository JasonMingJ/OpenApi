package com.fucode.service.Impl;

import com.fucode.mapper.ApiMappingMapper;
import com.fucode.pojo.ApiMapping;
import com.fucode.service.ApiMappingServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ApiMappingServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@Service
public class ApiMappingServicesImpl implements ApiMappingServices {

    @Autowired
    ApiMappingMapper apiMappingMapper;
    @Override
    public PageInfo<ApiMapping> getAllApiMapping(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ApiMapping> apiMappingList=apiMappingMapper.getAllApiMapping();
        return new PageInfo<ApiMapping>(apiMappingList);
    }

    @Override
    public int addNewApiMapping(ApiMapping apiMapping) {
        return apiMappingMapper.insertNewApiMapping(apiMapping);
    }

    @Override
    public int updateMapping(ApiMapping apiMapping) {
        return apiMappingMapper.updateApiMapping(apiMapping);
    }

    @Override
    public int deleteRoutes(Long[] ids) {
        return apiMappingMapper.deleteApiMapping(ids);
    }

    @Override
    public PageInfo<ApiMapping> searchApiMapping(Integer page, Integer limit, String name, Integer state) {
        PageHelper.startPage(page,limit);
        List<ApiMapping> apiMappingList = apiMappingMapper.searchApiMapping(name,state);
        return new PageInfo<>(apiMappingList);
    }
}
