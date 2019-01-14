package com.chsoft.fabric.local.controller;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricChaincode.server.ChaincodeServer;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import com.chsoft.fabric.local.entity.FabricLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hyperledger.fabric.protos.peer.Query.ChaincodeInfo;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/local")
public class LocalController {

    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private ChaincodeServer chaincodeServer;

    @Resource
    private FabricLocal fabricLocal;

    /**
     * 查询系统节点信息
     * @param model
     * @return
     */
    @RequestMapping("/peer")
    public String peer(Model model) {
        FabricPeer fabricPeer = fabricLocal.getLocalFabricPeer();
        FabricUser fabricUser = fabricLocal.getLocalFabricUserAdmin();
        model.addAttribute("fabricUser",fabricUser);
        model.addAttribute("fabricPeer",fabricPeer);
        return "com/chsoft/fabric/local/peer";
    }

    /**
     * 查询系统orderer节点信息
     * @param model
     * @return
     */
    @RequestMapping("/orderer")
    public String orderer(Model model) {
        List<FabricOrderer> list = fabricLocal.getLocalFabricOrdererList();
        model.addAttribute("fabricOrdererList",list);
        return "com/chsoft/fabric/local/orderer";
    }

    /**
     * 查询系统节点上加入的通道
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/channel")
    public String channel(Model model) throws Exception{
        Set<String> list =  aopFabricClient.queryChannels(fabricLocal.getLocalFabricPeer());
        model.addAttribute("channelList",list);
        return "com/chsoft/fabric/local/channel";
    }

    /**
     * 查询系统节点上已安装的链码
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/chaincodeInstalled")
    public String installedChaincodes(Model model) throws Exception{
        List<ChaincodeInfo> list = aopFabricClient.queryInstalledChaincodes(fabricLocal.getLocalFabricPeer());
        List<FabricChaincode> chaincodeList = chaincodeServer.createFabricChaincode(list);
        model.addAttribute("chaincodeList",chaincodeList);
        return "com/chsoft/fabric/local/chaincode";
    }

    /**
     * 查询通道上已实例化的链码
     * @param channelName
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/channelInstantiate")
    public String channelInstantiate(String channelName,Model model) throws Exception{
        List<ChaincodeInfo>  list = aopFabricClient.queryInstantiateChaincodes(channelName,fabricLocal.getLocalFabricPeer());
        List<FabricChaincode> chaincodeList = chaincodeServer.createFabricChaincode(list);
        model.addAttribute("channelName",channelName);
        model.addAttribute("chaincodeList",chaincodeList);
        return "com/chsoft/fabric/local/chaincode";
    }

    /**
     * 实例化链码跳转页面
     * @param chaincodeName
     * @param chaincodeVersion
     * @param chaincodePath
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toInstantiateChaincode")
    public String toInstantiateChaincode(String chaincodeName,String chaincodeVersion,String chaincodePath,Model model) throws Exception{
        Set<String> list =  aopFabricClient.queryChannels(fabricLocal.getLocalFabricPeer());
        model.addAttribute("channelList",list);
        model.addAttribute("chaincodeName",chaincodeName);
        model.addAttribute("chaincodeVersion",chaincodeVersion );
        model.addAttribute("chaincodePath",chaincodePath);
        return "com/chsoft/fabric/local/instantiateChaincode";
    }


}