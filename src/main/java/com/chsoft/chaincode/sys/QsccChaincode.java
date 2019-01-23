package com.chsoft.chaincode.sys;

import com.chsoft.fabric.aop.AopFabricClient;
import com.chsoft.fabric.local.entity.FabricLocal;
import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.chaincode.aop.ChaincodeCreate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统查询chaincode逻辑
 */
@Service
public class QsccChaincode implements ChaincodeCreate {

    //chaincode名称
    private final String chaincodeName = "qscc";

    private final String chaincodePath = "github.com/hyperledger/fabric/core/scc/qscc";

    private final String chaincodeVersion ="1.0";


    @Resource
    private AopFabricClient aopFabricClient;

    @Resource
    private FabricChaincode fabricChaincode;

    @Resource
    private FabricLocal fabricLocal;

    /**
     * 根据交易ID获取交易的数据
     * @param channelName
     * @param TxID
     * @return
     * @throws Exception
     */
    public String getTransactionByID(String channelName ,String TxID) throws Exception{
        String fcn = "GetTransactionByID";
        String[] args = new String[2];
        args[0] = channelName;
        args[1] = TxID;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return result;
    }

    /**
     * 根据交易ID获取区块数据
     * @param channelName
     * @param TxID
     * @return
     * @throws Exception
     */
    public String getBlockByTxID(String channelName ,String TxID) throws Exception{
        String fcn = "GetBlockByTxID";
        String[] args = new String[3];
        args[0] = channelName;
        args[1] = TxID;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return result;
    }

    /**
     * 根据hash值获取区块数据
     * @param channelName
     * @param hash
     * @return
     * @throws Exception
     */
    public String getBlockByHash(String channelName ,String hash) throws Exception{
        String fcn = "GetBlockByHash";
        String[] args = new String[3];
        args[0] = channelName;
        args[1] = hash;
        String result = aopFabricClient.queryChaincode(channelName,fabricLocal.getLocalFabricPeer(),fabricChaincode,fcn,args);
        return result;
    }

    @Override
    public void creatChaincode(FabricChaincode fabricChaincode) {
        fabricChaincode.setChaincodeVersion(chaincodeVersion);
        fabricChaincode.setChaincodeName(chaincodeName);
        fabricChaincode.setChaincodePath(chaincodePath);
    }

    @Override
    public FabricChaincode getChaincode() {
        return this.fabricChaincode;
    }


}
