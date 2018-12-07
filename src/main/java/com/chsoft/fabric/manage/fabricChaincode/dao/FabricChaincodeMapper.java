package com.chsoft.fabric.manage.fabricChaincode.dao;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;

import java.util.List;

public interface FabricChaincodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricChaincode record);

    int insertSelective(FabricChaincode record);

    FabricChaincode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricChaincode record);

    int updateByPrimaryKey(FabricChaincode record);

    List<FabricChaincode> findAll();
}