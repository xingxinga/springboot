package com.chsoft.fabric.manage.fabricChannel.dao;

import com.chsoft.fabric.manage.fabricChannel.entity.FabricChannel;

import java.util.List;

public interface FabricChannelMapper {
    int deleteByPrimaryKey(String id);

    int insert(FabricChannel record);

    int insertSelective(FabricChannel record);

    FabricChannel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FabricChannel record);

    int updateByPrimaryKey(FabricChannel record);

    List<FabricChannel> findAll();
}