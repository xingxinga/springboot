package com.chsoft.fabric.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;

import java.util.List;

/**
 * Created by lixing on 2018/11/13.
 *
 */
public interface FabricCreateClient {

    //设置客户端当前的order节点
    public void createOrderer(FabricOrderer orderer) throws Exception;
    //设置客户端当前的order节点列表
    public void createOrdererList(List<FabricOrderer> orderers) throws Exception ;

    //设置客户端当前的peer节点
    public void createPeer(FabricPeer peer) throws Exception ;
    //设置客户端当前的peer节点列表
    public void createPeerList(List<FabricPeer> peers) throws Exception ;

    //设置客户端当前的channel
    public void createChannel(String channelName) throws Exception ;

    //
    public void createChaincodeID(FabricChaincode fabricChaincode) throws Exception ;
    //获取客户端操作对象
    /*public HFClient getHFClient();*/
}
