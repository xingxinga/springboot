package com.chsoft.fabric.manage.fabricChaincode.server;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import org.hyperledger.fabric.protos.peer.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixing on 2018/11/12.
 */
@Service
public class ChaincodeServer {

    public List<FabricChaincode> createFabricChaincode (List<Query.ChaincodeInfo> list){
        List<FabricChaincode> chaincodeList = new ArrayList<FabricChaincode>();
        for(Query.ChaincodeInfo chaincodeInfo:list){
            FabricChaincode fabricChaincode = new FabricChaincode();
            fabricChaincode.setChaincodePath(chaincodeInfo.getPath());
            fabricChaincode.setChaincodeName(chaincodeInfo.getName());
            fabricChaincode.setChaincodeVersion(chaincodeInfo.getVersion());
            chaincodeList.add(fabricChaincode);
        }
        return chaincodeList;
    }

}
