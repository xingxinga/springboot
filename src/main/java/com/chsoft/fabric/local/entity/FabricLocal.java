package com.chsoft.fabric.local.entity;


import com.chsoft.fabric.aop.UtilCer;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixing on 2018/11/27.
 */
@Component
public class FabricLocal {

    @Value("${system.fabric.local.peer.peerName}")
    private String peerName;
    @Value("${system.fabric.local.peer.peerLocation}")
    private String peerLocation;




    @Value("${system.fabric.local.adminUser.name}")
    private String adminUserName;
    @Value("${system.fabric.local.adminUser.affiliation}")
    private String adminUserAffiliation;
    @Value("${system.fabric.local.adminUser.mspId}")
    private String adminUserMspId;
    @Value("${system.fabric.local.adminUser.privateKeyFilePath}")
    private String adminUserPrivateKeyFilePath;
    @Value("${system.fabric.local.adminUser.certificateFile}")
    private String adminUserCertificateFile;

    @Value("${system.fabric.local.ordererList.orderer1.domainName}")
    private String orderer1DomainName;
    @Value("${system.fabric.local.ordererList.orderer1.location}")
    private String orderer1Location;
    @Value("${system.fabric.local.ordererList.orderer1.ordererName}")
    private String orderer1OrdererName;

    @Value("${system.fabric.local.ordererList.orderer2.domainName}")
    private String orderer2DomainName;
    @Value("${system.fabric.local.ordererList.orderer2.location}")
    private String orderer2Location;
    @Value("${system.fabric.local.ordererList.orderer2.ordererName}")
    private String orderer2OrdererName;

    @Value("${system.fabric.local.ordererList.orderer3.domainName}")
    private String orderer3DomainName;
    @Value("${system.fabric.local.ordererList.orderer3.location}")
    private String orderer3Location;
    @Value("${system.fabric.local.ordererList.orderer3.ordererName}")
    private String orderer3OrdererName;

    @Resource
    private FabricPeer fabricPeer;

    @Resource
    private FabricOrderer fabricOrderer;

    public FabricPeer getLocalFabricPeer(){
        fabricPeer.setPeerName(peerName);
        fabricPeer.setPeerLocation(peerLocation);
        return fabricPeer;
    }

    public FabricUser getLocalFabricUserAdmin(){
        File privatePath = new File(adminUserPrivateKeyFilePath);
        File privateKeyFile =  UtilCer.findFileSk(privatePath);
        File certificateFile = new File(adminUserCertificateFile);
        FabricUser fabricUser =  UtilCer.getMember(adminUserName,adminUserAffiliation,adminUserMspId,privateKeyFile,certificateFile);
        fabricUser.setCertificatefile(adminUserCertificateFile);
        fabricUser.setPrivatekeyfilepath(adminUserPrivateKeyFilePath);
        return fabricUser;
    }

    public FabricOrderer getLocalFabricOrderer(){
        List<FabricOrderer> list = getLocalFabricOrdererList();
        int index = (int)(Math.random()*list.size());
        return list.get(index);
    }

    public List<FabricOrderer> getLocalFabricOrdererList(){
        List<FabricOrderer> list = new ArrayList<FabricOrderer>();
        FabricOrderer fabricOrderer1 = new FabricOrderer();
        fabricOrderer1.setOrdererName(orderer1OrdererName);
        fabricOrderer1.setOrdererDomainName(orderer1DomainName);
        fabricOrderer1.setOrdererLocation(orderer1Location);
        list.add(fabricOrderer1);
        if(!orderer2OrdererName.isEmpty()&&!orderer2DomainName.isEmpty()&&!orderer2Location.isEmpty()){
            FabricOrderer fabricOrderer2 = new FabricOrderer();
            fabricOrderer2.setOrdererName(orderer2OrdererName);
            fabricOrderer2.setOrdererDomainName(orderer2DomainName);
            fabricOrderer2.setOrdererLocation(orderer2Location);
            list.add(fabricOrderer2);
        }
        if(!orderer3OrdererName.isEmpty()&&!orderer3DomainName.isEmpty()&&!orderer3Location.isEmpty()){
            FabricOrderer fabricOrderer3 = new FabricOrderer();
            fabricOrderer3.setOrdererName(orderer3OrdererName);
            fabricOrderer3.setOrdererDomainName(orderer3DomainName);
            fabricOrderer3.setOrdererLocation(orderer3Location);
            list.add(fabricOrderer3);
        }
        return list;
    }
}
