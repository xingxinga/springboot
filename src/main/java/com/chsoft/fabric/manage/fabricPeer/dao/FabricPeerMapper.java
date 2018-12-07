package com.chsoft.fabric.manage.fabricPeer.dao;

import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;

import java.util.List;

public interface FabricPeerMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricPeer record);

    int insertSelective(FabricPeer record);

    FabricPeer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricPeer record);

    int updateByPrimaryKey(FabricPeer record);

    List<FabricPeer> findAll();
}