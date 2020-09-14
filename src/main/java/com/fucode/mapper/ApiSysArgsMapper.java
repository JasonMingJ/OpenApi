package com.fucode.mapper;

import com.fucode.pojo.ApiSystemparam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiSysArgsMapper {
    List<ApiSystemparam> getArgs();

    void addNew(ApiSystemparam apiSysArgs);

    void updateArgs(ApiSystemparam apiSysArgs);

    void deleteArgs(Long[] id);

    List<ApiSystemparam> searchArgs(@Param("name") String name, @Param("state") Integer state);
}
