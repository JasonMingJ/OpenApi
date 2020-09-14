package com.fucode.mapper;

import com.fucode.pojo.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> getFatherMenuByUserId(long id);
}
