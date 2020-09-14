package com.fucode.mapper;

import com.fucode.pojo.ApiMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMappingMapper {
    List<ApiMapping> getAllApiMapping();

    int insertNewApiMapping(ApiMapping apiMapping);

    int updateApiMapping(ApiMapping apiMapping);

    int deleteApiMapping(Long[] id);

    List<ApiMapping> searchApiMapping(@Param("name") String name, @Param("state") Integer state);
}
