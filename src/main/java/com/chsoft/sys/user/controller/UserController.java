package com.chsoft.sys.user.controller;

import com.chsoft.sys.user.dao.UserMapper;
import com.chsoft.sys.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/edit")
    public String info(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "com/chsoft/sys/user/edit";
    }

    @RequestMapping("/save")
    public String save(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return "redirect:/logout";
    }

}