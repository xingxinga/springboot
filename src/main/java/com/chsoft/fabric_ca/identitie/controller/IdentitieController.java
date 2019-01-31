package com.chsoft.fabric_ca.identitie.controller;

import com.chsoft.fabric_ca.conf.Config;
import com.chsoft.fabric_ca.identitie.server.IdentitieServer;
import com.chsoft.fabric_ca.sdk.CAClient;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 证书管理平台，组织管理Controller
 */
@Controller
@RequestMapping("/identitie")
public class IdentitieController {

    @Resource
    private CAClient caClient;

    @Resource
    private IdentitieServer identitieServer;
    /**
     * 获取CA服务器组织列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(Model model) throws Exception{
        Collection<HFCAIdentity> list =  caClient.getIdentities();
        model.addAttribute("identitieList",list);
        return "com/chsoft/fabric_ca/identitie/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model,String enrollmentId) throws Exception{
        Collection<HFCAIdentity> list =  caClient.getIdentities();
        HFCAIdentity identity = null;
        for(HFCAIdentity hFCAIdentity:list){
            if(hFCAIdentity.getEnrollmentId().equals(enrollmentId)){
                identity = hFCAIdentity;
            }
        }
        model.addAttribute("identity",identity);
        return "com/chsoft/fabric_ca/identitie/edit";
    }

    @RequestMapping("/toRegister")
    public String toRegister(Model model,String enrollmentId) throws Exception{
        return "com/chsoft/fabric_ca/identitie/toRegister";
    }

    @RequestMapping("/toGetMSP")
    public String toGetCert(Model model,String enrollmentId) throws Exception{
        return "com/chsoft/fabric_ca/identitie/toGetMSP";
    }


    @RequestMapping("/register")
    public String register(Model model,String enrollmentId) throws Exception{
        return "com/chsoft/fabric_ca/identitie/edit";
    }

    @RequestMapping("/getMSP")
    @ResponseBody
    public void getCert(String identitieName,String identitiePassword,HttpServletResponse response) throws Exception{
        identitieServer.downloadMSP(response,identitieName,identitiePassword);
    }
}