package com.chsoft.fabric_ca.certificate.controller;

import com.chsoft.fabric_ca.sdk.CAClient;
import com.chsoft.fabric_ca.sdk.conf.Config;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 证书管理平台，根证书管理Controller
 */
@Controller
@RequestMapping("/certificate")
public class CertificateController {

    @Resource
    private CAClient caClient;

    @Resource
    private Config config;
    @RequestMapping("/getCertificate")
    @ResponseBody
    public void list(Model model, HttpServletResponse response) throws Exception{
        caClient.downloadCertificate(response,config.getCaCertificateFileName());
    }

}