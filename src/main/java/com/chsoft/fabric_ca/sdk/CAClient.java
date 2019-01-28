package com.chsoft.fabric_ca.sdk;

import com.chsoft.fabric_ca.sdk.conf.Config;
import com.chsoft.fabric_ca.sdk.msp.ExportCert;
import com.chsoft.fabric_ca.sdk.user.CaUser;
import com.chsoft.webapp.util.HttpFileDownload;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAAffiliation;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lixing on 2018/12/18.
 */
@Component
public class CAClient {

    @Resource
    private CaUser caUser;
    @Resource
    private Config config;

    private HFCAClient client;

    public void init(CaUser caUser) throws Exception{
        initHFCAClient(config.getCaName(), "http://"+config.getServerIp());
        this.caUser = caUser;
    }

    @PostConstruct
    public void init() throws Exception{
        initHFCAClient(config.getCaName(), config.getServerIp());
        caUser.setName(config.getAdminUserName());
        caUser.setPassword(config.getAdminUserPassword());
        caUser.setEnrollment(getEnrollment(caUser.getName(),caUser.getPassword()));
    }

    public List<HFCAAffiliation> getAllAffiliation() throws Exception{
        HFCAAffiliation hFCAAffiliation = client.getHFCAAffiliations(caUser);
        List<HFCAAffiliation> list = new ArrayList<HFCAAffiliation>();
        return buildAffiliations(hFCAAffiliation,list);
    }

    public boolean createAffiliation(String affiliationName) throws Exception{
        return client.createAffiliation(affiliationName,caUser);
    }

    public boolean remvoeAffiliation(String affiliationName,boolean force)throws Exception{
        return client.removeAffiliation(affiliationName,caUser,force);
    }

    public boolean updateAffiliation(String affiliationName,String affiliationNameOld)throws Exception{
        return client.updateAffiliation(affiliationName,affiliationNameOld,caUser);
    }

    public void registerCommon(String userName,String password ){

    }

    public void registerManage(String userName,String password ){

    }

    public Enrollment getEnrollment(String userName, String password) throws Exception{
        return client.enroll(userName, password);
    }

    public void downloadCertificate(HttpServletResponse response, String fileName) throws Exception{
        HFCAInfo CAInfo = client.info();
        HttpFileDownload.downloadByString(response,ExportCert.decoderBase64String(CAInfo.getCACertificateChain()),fileName);
    }

    private void initHFCAClient(String caName,String serverIp) throws Exception {
        client = HFCAClient.createNewInstance(caName, serverIp, null);
        //调用client的登录方法，参数为登录用户的用户名和用户密码
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        client.setCryptoSuite(cryptoSuite);
    }

    private List<HFCAAffiliation> buildAffiliations(HFCAAffiliation hFCAAffiliation,List<HFCAAffiliation> list) throws Exception{
        if(hFCAAffiliation==null){
            return null;
        }
        if(!hFCAAffiliation.getName().isEmpty()){
            list.add(hFCAAffiliation);
        }
        Collection<HFCAAffiliation> listChildren = hFCAAffiliation.getChildren();
        if(listChildren!=null&&listChildren.size()!=0){
            for(HFCAAffiliation hFCAAffiliation_:listChildren){
                buildAffiliations(hFCAAffiliation_,list);
            }
        }
        return list;
    }
}
