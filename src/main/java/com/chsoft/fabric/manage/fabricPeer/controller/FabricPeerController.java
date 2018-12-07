package com.chsoft.fabric.manage.fabricPeer.controller;

import com.chsoft.fabric.manage.fabricPeer.dao.FabricPeerMapper;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
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
@RequestMapping("/fabricPeer")
public class FabricPeerController {

    @Resource
    private FabricPeerMapper fabricPeerMapper;

    @RequestMapping("/list")
    public String list(Model model) {
        List<FabricPeer> list = fabricPeerMapper.findAll();
        model.addAttribute("peerList",list);
        return "com/chsoft/fabric/manage/fabricPeer/list";
    }

    @RequestMapping("/edit")
    public String edit(String id,Model model) {
        FabricPeer fabricPeer = fabricPeerMapper.selectByPrimaryKey(id);
        model.addAttribute("fabricPeer",fabricPeer);
        return "com/chsoft/fabric/manage/fabricPeer/edit";
    }

    @RequestMapping("/save")
    public String save(FabricPeer fabricPeer) {
        if(fabricPeer.getId().isEmpty()){
            fabricPeerMapper.insert(fabricPeer);
        }else{
            fabricPeerMapper.updateByPrimaryKey(fabricPeer);
        }
        return "redirect:/peer/list";
    }

}