package com.chsoft.fabric.manage.fabricChaincode.controller;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.dao.FabricChaincodeMapper;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
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
@RequestMapping("/fabricChaincode")
public class FabricChaincodeController {

    @Resource
    private FabricChaincodeMapper fabricChaincodeMapper;

    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private FabricLocal fabricLocal;

    @RequestMapping("/list")
    public String list(Model model) {
        List<FabricChaincode> list = fabricChaincodeMapper.findAll();
        model.addAttribute("chaincodeList",list);
        return "com/chsoft/fabric/manage/fabricChaincode/list";
    }

    @RequestMapping("/edit")
    public String edit(String id,Model model) {
        FabricChaincode fabricChaincode = fabricChaincodeMapper.selectByPrimaryKey(id);
        model.addAttribute("chaincode",fabricChaincode);
        return "com/chsoft/fabric/manage/fabricChaincode/edit";
    }

    @RequestMapping("/save")
    public String save(FabricChaincode fabricChaincode) {
        if(fabricChaincode.getId().isEmpty()){
            fabricChaincodeMapper.insert(fabricChaincode);
        }else{
            fabricChaincodeMapper.updateByPrimaryKey(fabricChaincode);
        }
        return "redirect:/fabricChaincode/list";
    }

    @RequestMapping("/fabricInstallChaincode")
    public String installChaincode(String id,Model model) {
        FabricChaincode fabricChaincode = fabricChaincodeMapper.selectByPrimaryKey(id);
        try{
            aopFabricClient.peerInstallChaincode(fabricChaincode,fabricLocal.getLocalFabricPeer());
            model.addAttribute("message","系统节点成功安装链码："+fabricChaincode.getChaincodeName());
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }
        finally {
            List<FabricChaincode> list = fabricChaincodeMapper.findAll();
            model.addAttribute("chaincodeList",list);
            return "com/chsoft/fabric/manage/fabricChaincode/list";
        }
    }

    @RequestMapping("/fabricInstantiateChaincode")
    public String instantiateChaincode(String channelName,String arg,String xmlPath,FabricChaincode fabricChaincode,Model model) {
        try{
            String[] args = new String[0];
            if(!arg.isEmpty()){
                args = arg.split(",");
            }
            aopFabricClient.peerInstantiateChainCodeXmlPath(channelName,fabricLocal.getLocalFabricOrderer(),fabricLocal.getLocalFabricPeer(),fabricChaincode,args,xmlPath);
            model.addAttribute("message","系统节点  "+"通道:"+channelName+" 成功实例化链码："+fabricChaincode.getChaincodeName());
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }
        finally {
            List<FabricChaincode> list = fabricChaincodeMapper.findAll();
            model.addAttribute("chaincodeList",list);
            return "redirect:/local/channelInstantiate?channelName="+channelName;
        }
    }
}