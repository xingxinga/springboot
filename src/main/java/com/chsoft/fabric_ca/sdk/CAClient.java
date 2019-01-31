package com.chsoft.fabric_ca.sdk;

import com.chsoft.fabric_ca.conf.Config;
import com.chsoft.fabric_ca.sdk.conf.Fabric_ca_config;
import com.chsoft.fabric_ca.sdk.msp.Base64MSPBuild;
import com.chsoft.fabric_ca.sdk.msp.ExportCert;
import com.chsoft.fabric_ca.sdk.user.CaUser;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAAffiliation;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.HFCAInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

    public Collection<HFCAIdentity> getIdentities() throws Exception {
        return client.getHFCAIdentities(caUser);
    }

    public Enrollment getEnrollment(String userName, String password) throws Exception{
        return client.enroll(userName, password);
    }

    public String getCertificateString() throws Exception{
        HFCAInfo CAInfo = client.info();
        return ExportCert.decoderBase64String(CAInfo.getCACertificateChain());
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

    public String generateUserMSP(String identitieName,String identitiePassword,String root_directory) throws Exception{
        CaUser user = new CaUser();
        user.setEnrollment(getEnrollment(identitieName,identitiePassword));
        user.setName(identitieName);
        String path = root_directory;
        HFCAAffiliation affiliation= client.getHFCAAffiliations(user);
        while(affiliation.getChildren()!=null&&affiliation.getChildren().size()==1){
            ArrayList<HFCAAffiliation> list = (ArrayList) affiliation.getChildren();
            affiliation = list.get(0);
        }
        path = path + affiliation.getName() + Fabric_ca_config.getSeparator() + user.getName() + Fabric_ca_config.getSeparator() + "msp" + Fabric_ca_config.getSeparator();
        buildMSP(path,user);
        return path;
    }

    public void buildMSP(String path ,CaUser user){
        Base64MSPBuild build = new Base64MSPBuild(path);
        build.BuildKeystore(user.getEnrollment().getKey());
        build.BuildSigncerts(user.getEnrollment().getCert());
    }

}
