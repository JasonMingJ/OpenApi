package com.fucode.service.Impl;

import com.fucode.mapper.ApiSysArgsMapper;
import com.fucode.pojo.ApiSystemparam;
import com.fucode.service.ApiSysArgsServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ApiSysArgsServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@Service
public class ApiSysArgsServicesImpl implements ApiSysArgsServices {

    @Autowired
    private ApiSysArgsMapper apiSysArgsMapper;

    @Override
    public PageInfo<ApiSystemparam> getAllArgs(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ApiSystemparam> apiSystemparamList = apiSysArgsMapper.getArgs();
        return new PageInfo<>(apiSystemparamList);
    }

    @Override
    public void addNewApiSystemparam(ApiSystemparam apiSysArgs) {
        apiSysArgsMapper.addNew(apiSysArgs);
    }

    @Override
    public void updateMapping(ApiSystemparam apiSysArgs) {
        apiSysArgsMapper.updateArgs(apiSysArgs);
    }

    @Override
    public void deleteRoutes(Long[] ids) {
        apiSysArgsMapper.deleteArgs(ids);
    }

    @Override
    public PageInfo<ApiSystemparam> searchApiSystemparam(Integer page, Integer limit, String name, Integer state) {
        PageHelper.startPage(page,limit);
        List<ApiSystemparam> apiSystemparamList = apiSysArgsMapper.searchArgs(name,state);
        return new PageInfo<>(apiSystemparamList);
    }
}
