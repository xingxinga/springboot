package com.chsoft.fabric_ca.affiliation.controller;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricChaincode.server.ChaincodeServer;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import com.chsoft.fabric_ca.sdk.CAClient;
import org.hyperledger.fabric.protos.peer.Query.ChaincodeInfo;
import org.hyperledger.fabric_ca.sdk.HFCAAffiliation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 测试控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
@Controller
@RequestMapping("/affiliation")
public class AffiliationController {

    @Resource
    private CAClient caClient;

    @RequestMapping("/list")
    public String list(Model model) throws Exception{
        List<HFCAAffiliation> list = caClient.getAllAffiliation();
        model.addAttribute("affiliationList",list);
        return "com/chsoft/fabric_ca/affiliation/list";
    }

    @RequestMapping("/toCreate")
    public String toCreate(){
        return "com/chsoft/fabric_ca/affiliation/edit";
    }

    @RequestMapping("/create")
    public String create(String affiliationName) throws Exception{
        caClient.createAffiliation(affiliationName);
        return "redirect:/affiliation/list";
    }

}