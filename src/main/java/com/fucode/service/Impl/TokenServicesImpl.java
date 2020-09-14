package com.fucode.service.Impl;

import com.fucode.mapper.UserTokenMapper;
import com.fucode.pojo.UserToken;
import com.fucode.service.TokenServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TokenServicesImpl
 * @Description: TODO
 * @Author user
 * @Date 2020/9/11
 * @Version V1.0
 **/
@Service
public class TokenServicesImpl implements TokenServices {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public PageInfo<UserToken> getAllUserToken(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<UserToken> userTokenList = userTokenMapper.getAll();
        return new PageInfo<>(userTokenList);
    }

    @Override
    public void addNewUserToken(UserToken userToken) {
        userTokenMapper.add(userToken);
    }

    @Override
    public void updateUserToken(UserToken userToken) {
        userTokenMapper.update(userToken);
    }

    @Override
    public void deleteUserTokens(Long[] ids) {
        userTokenMapper.delete(ids);
    }

    @Override
    public PageInfo<UserToken> searchUserToken(Integer page, Integer limit, String name, Integer state) {
        PageHelper.startPage(page,limit);
        List<UserToken> userTokenList = userTokenMapper.search(name,state);
        return new PageInfo<>(userTokenList);
    }
}
