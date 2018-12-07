package com.chsoft.fabric.manage.fabricUser.dao;

import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;

public interface FabricUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricUser record);

    int insertSelective(FabricUser record);

    FabricUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricUser record);

    int updateByPrimaryKey(FabricUser record);
}