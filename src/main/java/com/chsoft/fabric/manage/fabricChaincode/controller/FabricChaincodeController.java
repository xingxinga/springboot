package com.chsoft.fabric.manage.fabricChaincode.controller;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.dao.FabricChaincodeMapper;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * fabric系统链码模块Controller
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

    /**
     * 查询本地链码列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<FabricChaincode> list = fabricChaincodeMapper.findAll();
        model.addAttribute("chaincodeList",list);
        return "com/chsoft/fabric/manage/fabricChaincode/list";
    }

    /**
     * 编辑链码信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(String id,Model model) {
        FabricChaincode fabricChaincode = fabricChaincodeMapper.selectByPrimaryKey(id);
        model.addAttribute("chaincode",fabricChaincode);
        return "com/chsoft/fabric/manage/fabricChaincode/edit";
    }

    /**
     * 保存链码
     * @param fabricChaincode
     * @return
     */
    @RequestMapping("/save")
    public String save(FabricChaincode fabricChaincode) {
        if(fabricChaincode.getId().isEmpty()){
            fabricChaincodeMapper.insert(fabricChaincode);
        }else{
            fabricChaincodeMapper.updateByPrimaryKey(fabricChaincode);
        }
        return "redirect:/fabricChaincode/list";
    }

    /**
     * 系统节点安装链码操作
     * @param id
     * @param model
     * @return
     */
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

    /**
     * 系统节点实例化链码操作
     * @param channelName
     * @param arg
     * @param xmlPath
     * @param fabricChaincode
     * @param model
     * @return
     */
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

    public List<FabricPeer> getFabricPeerList(){
        List<FabricPeer> fabricPeerList = new ArrayList<FabricPeer>();
        fabricPeerList.add(fabricLocal.getLocalFabricPeer());
        FabricPeer fabricPeer2 = new FabricPeer();
        fabricPeer2.setPeerLocation("grpc://192.168.15.129:9051");
        fabricPeer2.setPeerName("peer0.org2.example.com");
        fabricPeerList.add(fabricPeer2);
        return fabricPeerList;
    }
}