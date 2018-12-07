package com.chsoft.fabric.manage.fabricOrg.dao;

import com.chsoft.fabric.manage.fabricOrg.entity.FabricOrg;

import java.util.List;

public interface FabricOrgMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricOrg record);

    int insertSelective(FabricOrg record);

    FabricOrg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricOrg record);

    int updateByPrimaryKey(FabricOrg record);

    List<FabricOrg> findAll();
}