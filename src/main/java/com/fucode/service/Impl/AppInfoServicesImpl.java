package com.fucode.service.Impl;

import com.fucode.mapper.AppInfoMapper;
import com.fucode.mapper.CustomerMapper;
import com.fucode.pojo.AppInfo;
import com.fucode.pojo.Customer;
import com.fucode.service.AppInfoServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AppInfoServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/8
 * @Version V1.0
 **/
@Service
public class AppInfoServicesImpl implements AppInfoServices {

    @Resource
    private AppInfoMapper appInfoMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public int insertAppInfo(AppInfo appInfo) {
        String cusId = appInfo.getCusId();
        Customer customerById = customerMapper.getCustomerById(Long.valueOf(cusId));
        appInfo.setCorpName(customerById.getNickname());
        return appInfoMapper.insertAppInfo(appInfo);
    }

    @Override
    public int updateAppInfo(AppInfo appInfo) {
        return appInfoMapper.updateAppInfo(appInfo);
    }

    @Override
    public int deleteAppInfoById(Long id) {
        return appInfoMapper.deleteAppInfo(id);
    }

    @Override

    public int deleteAppInfoByIds(Long[] ids) {
        return appInfoMapper.deleteAppInfoByIds(ids);
    }

    @Override
    public AppInfo getAppInfoById(Long id) {
        return appInfoMapper.getAppInfoById(id);
    }

    @Override
    public List<AppInfo> getAppInfos() {
        return appInfoMapper.getAllAppinfo();
    }

    @Override
    public PageInfo<AppInfo> getAppInfoByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<AppInfo> appInfoList = appInfoMapper.getAllAppinfo();
        return new PageInfo<>(appInfoList);
    }
}
