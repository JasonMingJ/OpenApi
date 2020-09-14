package com.fucode.service;

import com.fucode.pojo.UserToken;
import com.github.pagehelper.PageInfo;

public interface TokenServices {
    PageInfo<UserToken> getAllUserToken(Integer page, Integer limit);

    void addNewUserToken(UserToken userToken);

    void updateUserToken(UserToken userToken);

    void deleteUserTokens(Long[] ids);

    PageInfo<UserToken> searchUserToken(Integer page, Integer limit, String name, Integer state);
}
