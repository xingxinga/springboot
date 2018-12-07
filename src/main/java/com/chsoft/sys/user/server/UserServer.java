package com.chsoft.sys.user.server;

import com.chsoft.sys.user.dao.UserDao;
import com.chsoft.sys.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lixing on 2018/11/12.
 */
@Service
public class UserServer {

    @Autowired
    private UserDao userDao;

    public User getUser(String id){
        return userDao.selectByPrimaryKey(id);
    }

}
