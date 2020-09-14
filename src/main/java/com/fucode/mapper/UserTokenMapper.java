package com.fucode.mapper;

import com.fucode.pojo.UserToken;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTokenMapper {

    List<UserToken> getAll();

    void add(UserToken userToken);

    void update(UserToken userToken);

    void delete(Long[] ids);

    List<UserToken> search(@Param("name") String name, @Param("state") Integer state);
}
