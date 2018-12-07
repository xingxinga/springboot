package com.chsoft.webapp.chaincode.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;

/**
 * Created by lixing on 2018/12/7.
 */
public interface ChaincodeCreate {

    public void creatChaincode(FabricChaincode fabricChaincode);

    public FabricChaincode getChaincode();
}
