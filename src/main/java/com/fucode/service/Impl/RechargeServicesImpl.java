package com.fucode.service.Impl;

import com.fucode.mapper.RechargeMapper;
import com.fucode.pojo.Recharge;
import com.fucode.service.RechargeServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RechargeServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/10
 * @Version V1.0
 **/
@Service
public class RechargeServicesImpl implements RechargeServices {

    @Autowired
    RechargeMapper rechargeMapper;

    @Override
    public PageInfo<Recharge> getAllRecharge(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Recharge> rechargeList = rechargeMapper.getAll();
        return new PageInfo<>(rechargeList);
    }

    @Override
    public void addNewRecharge(Recharge recharge) {
        rechargeMapper.add(recharge);
    }

    @Override
    public void updateRecharge(Recharge recharge) {
        rechargeMapper.update(recharge);
    }

    @Override
    public void deleteRecharges(Long[] ids) {
        rechargeMapper.delete(ids);
    }

    @Override
    public PageInfo<Recharge> searchRecharge(Integer page, Integer limit, String name, Integer state) {
        PageHelper.startPage(page,limit);
        List<Recharge> rechargeList = rechargeMapper.search(name,state);
        return new PageInfo<>(rechargeList);
    }
}
