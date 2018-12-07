package com.chsoft.fabric.manage.fabricOrderer.dao;

import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;

public interface FabricOrdererMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricOrderer record);

    int insertSelective(FabricOrderer record);

    FabricOrderer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricOrderer record);

    int updateByPrimaryKey(FabricOrderer record);
}