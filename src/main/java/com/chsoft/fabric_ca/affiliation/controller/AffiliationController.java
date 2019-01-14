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
 * 证书管理平台，组织管理Controller
 */
@Controller
@RequestMapping("/affiliation")
public class AffiliationController {

    @Resource
    private CAClient caClient;

    /**
     * 获取CA服务器组织列表
     * @param model
     * @return
     * @throws Exception
     */
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
    public String create(String affiliationName,Model model) throws Exception{
        if(caClient.createAffiliation(affiliationName)){
            model.addAttribute("message","组织添加成功："+affiliationName);
        }
        List<HFCAAffiliation> list = caClient.getAllAffiliation();
        model.addAttribute("affiliationList",list);
        return "redirect:/affiliation/list";
    }

    @RequestMapping("/delete")
    public String delete(String affiliationName,boolean force,Model model) throws Exception{
        if(caClient.remvoeAffiliation(affiliationName,force)){
            model.addAttribute("message","组织删除成功："+affiliationName);
        }
        List<HFCAAffiliation> list = caClient.getAllAffiliation();
        model.addAttribute("affiliationList",list);
        return "redirect:/affiliation/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String affiliationName,Model model) throws Exception{
        model.addAttribute("affiliationName",affiliationName);
        return "com/chsoft/fabric_ca/affiliation/update";
    }

    @RequestMapping("/update")
    public String update(String affiliationName,String affiliationNameOld,Model model) throws Exception{
        if(caClient.updateAffiliation(affiliationName,affiliationNameOld)){
            model.addAttribute("message","组织修改成功："+affiliationName);
        }
        List<HFCAAffiliation> list = caClient.getAllAffiliation();
        model.addAttribute("affiliationList",list);
        return "redirect:/affiliation/list";
    }
}