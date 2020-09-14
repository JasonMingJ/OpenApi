package com.fucode.service;

import com.fucode.pojo.Recharge;
import com.github.pagehelper.PageInfo;

public interface RechargeServices {
    PageInfo<Recharge> getAllRecharge(Integer page, Integer limit);

    void addNewRecharge(Recharge recharge);

    void updateRecharge(Recharge recharge);

    void deleteRecharges(Long[] ids);

    PageInfo<Recharge> searchRecharge(Integer page, Integer limit, String name, Integer state);
}
