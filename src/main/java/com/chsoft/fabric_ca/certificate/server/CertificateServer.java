package com.chsoft.fabric_ca.certificate.server;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric_ca.conf.Config;
import com.chsoft.fabric_ca.sdk.CAClient;
import com.chsoft.fabric_ca.sdk.msp.ExportCert;
import com.chsoft.webapp.util.HttpFileDownload;
import org.hyperledger.fabric.protos.peer.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixing on 2018/11/12.
 */
@Service
public class CertificateServer {
    @Resource
    private CAClient caClient;

    @Resource
    private Config config;

    public void downloadCertificate(HttpServletResponse response) throws Exception{
        String certificate = null;
        if("false".equals(config.getIsRetrieve())){
            certificate  = caClient.getCertificateString();
            HttpFileDownload.downloadByString(response, certificate,config.get_caCertificateFileName());
        }else if("true".equals(config.getIsRetrieve())){
            File certificateFile = new File(config.get_caCertificateFilePath()+config.get_caCertificateFileName());
            if(certificateFile.exists()){
                HttpFileDownload.downloadByFile(response,certificateFile);
            }else{
                certificate  = caClient.getCertificateString();
                HttpFileDownload.downloadByString(response, certificate,config.get_caCertificateFileName());
            }
        }else{
            System.out.print("类型错误");
        }

    }

}
