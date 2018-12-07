package com.chsoft.fabric.manage.fabricChannel.controller;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChannel.dao.FabricChannelMapper;
import com.chsoft.fabric.manage.fabricChannel.entity.FabricChannel;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
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
@RequestMapping("/fabricChannel")
public class FabricChannelController {

    @Resource
    private FabricChannelMapper fabricChannelMapper;

    @Resource
    private FabricLocal fabricLocal;

    @Resource
    private AopFabricClient aopFabricClient;

    @RequestMapping("/list")
    public String list(Model model) {
        List<FabricChannel> list = fabricChannelMapper.findAll();
        model.addAttribute("channelList",list);
        return "com/chsoft/fabric/manage/fabricChannel/list";
    }

    @RequestMapping("/edit")
    public String edit(String id,Model model) {
        FabricChannel fabricChannel = fabricChannelMapper.selectByPrimaryKey(id);
        model.addAttribute("fabricChannel",fabricChannel);
        return "com/chsoft/fabric/manage/fabricChannel/edit";
    }

    @RequestMapping("/save")
    public String save(FabricChannel fabricChannel) {
        if(fabricChannel.getId().isEmpty()){
            fabricChannelMapper.insert(fabricChannel);
        }else{
            fabricChannelMapper.updateByPrimaryKey(fabricChannel);
        }
        return "redirect:/fabricChannel/list";
    }


    @RequestMapping("/fabricCreateChannel")
    public String createChannel(String id,Model model){
        FabricChannel fabricChannel = fabricChannelMapper.selectByPrimaryKey(id);
        FabricOrderer fabricOrderer = fabricLocal.getLocalFabricOrderer();
        try{
            aopFabricClient.createChannel(fabricChannel.getChannelName(),fabricOrderer,fabricChannel.getChannelFilePath());
            model.addAttribute("message","通道创建成功");
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }
        finally {
            List<FabricChannel> list = fabricChannelMapper.findAll();
            model.addAttribute("channelList",list);
            return "com/chsoft/fabric/manage/fabricChannel/list";
        }
    }

    @RequestMapping("/fabricJoinPeer")
    public String joinPeer(String id,Model model) throws Exception{
        FabricChannel fabricChannel = fabricChannelMapper.selectByPrimaryKey(id);
        try{
            aopFabricClient.channelJoinPeer(fabricChannel.getChannelName(),fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer());
            model.addAttribute("message","系统节点成功加入通道："+fabricChannel.getChannelName());
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }
        finally {
            List<FabricChannel> list = fabricChannelMapper.findAll();
            model.addAttribute("channelList",list);
            return "com/chsoft/fabric/manage/fabricChannel/list";
        }
    }
}