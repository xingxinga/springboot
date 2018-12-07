package com.chsoft.fabric.manage.fabricOrg.controller;

import com.chsoft.fabric.manage.fabricOrg.dao.FabricOrgMapper;
import com.chsoft.fabric.manage.fabricOrg.entity.FabricOrg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
@Controller
@RequestMapping("/fabricOrg")
public class FabricOrgController {

    @Resource
    private FabricOrgMapper fabricOrgMapper;

    @RequestMapping("/list")
    public String list(Model model) {
        List<FabricOrg> list = fabricOrgMapper.findAll();
        model.addAttribute("orgList",list);
        return "com/chsoft/fabric/manage/fabricOrg/list";
    }

    @RequestMapping("/edit")
    public String edit(String id,Model model) {
        FabricOrg fabricOrg = fabricOrgMapper.selectByPrimaryKey(id);
        model.addAttribute("fabricOrg",fabricOrg);
        return "com/chsoft/fabric/manage/fabricOrg/edit";
    }

    @RequestMapping("/save")
    public String save(FabricOrg fabricOrg) {
        if(fabricOrg.getId().isEmpty()){
            fabricOrgMapper.insert(fabricOrg);
        }else{
            fabricOrgMapper.updateByPrimaryKey(fabricOrg);
        }
        return "redirect:/fabricOrg/list";
    }

}