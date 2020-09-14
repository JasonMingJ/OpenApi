package com.fucode.mapper;

import com.fucode.pojo.Recharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeMapper {

    List<Recharge> getAll();

    void add(Recharge recharge);

    void update(Recharge recharge);

    void delete(Long[] ids);

    List<Recharge> search(@Param("name") String name, @Param("state") Integer state);
}
