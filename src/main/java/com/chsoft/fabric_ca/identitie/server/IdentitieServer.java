package com.chsoft.fabric_ca.identitie.server;

import com.chsoft.fabric_ca.conf.Config;
import com.chsoft.fabric_ca.sdk.CAClient;
import com.chsoft.webapp.util.HttpFileDownload;
import com.chsoft.webapp.util.ZipCompress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by lixing on 2018/11/12.
 */
@Service
public class IdentitieServer {
    @Resource
    private CAClient caClient;

    @Resource
    private Config config;

    public void downloadMSP(HttpServletResponse response,String identitieName,String identitiePassword) throws Exception{
        String mspPath = caClient.generateUserMSP(identitieName,identitiePassword,config.get_root_directory());
        ZipCompress zipCompress = new ZipCompress(config.get_root_directory() + identitieName+".zip",mspPath);
        zipCompress.zip();
        File file = new File(config.get_root_directory() + identitieName+".zip");
        HttpFileDownload.downloadByFile(response,file);
    }

}
