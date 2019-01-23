package com.chsoft.chaincode.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;

/**
 * Created by lixing on 2018/12/7.
 * chaincode创建
 */
public interface ChaincodeCreate {

    //创建chaincode基本信息
    public void creatChaincode(FabricChaincode fabricChaincode);

    //获取chaincode
    public FabricChaincode getChaincode();
}
