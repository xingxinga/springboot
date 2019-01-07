package com.chsoft.sys.user.dao;

import com.chsoft.sys.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String getPassword(String userName);

    String getRole(String userName);

    User getUserByName(String userName);

}