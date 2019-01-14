package com.chsoft.fabric.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import org.hyperledger.fabric.sdk.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixing on 2018/12/7
 * fabric客户端创建器的简单实现类
 */
public class SampleFabricCreateClient implements FabricCreateClient{

    //fabric客户端
    public HFClient client;

    //组装的通道
    public Channel channel;

    //组装的ordere节点
    public Orderer orderer;

    //组装的ordere集合
    public List<Orderer> orderers;

    //组装的peer节点
    public Peer peer;

    //组装的peer集合
    public List<Peer> peers;

    //组装的chaincode
    public FabricChaincode fabricChaincode;

    public SampleFabricCreateClient(){

    }

    /**
     * @Title:用户的客户端，用于操作fabric网络
     * @Description: TODO
     * @param @param fabricUser 入参：指定用户
     */
    public SampleFabricCreateClient(FabricUser fabricUser){
        try {
            client = FabricClientFactory.getPeerUserClient(fabricUser);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void init(FabricUser fabricUser){
        try {
            client = FabricClientFactory.getPeerUserClient(fabricUser);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 根据传入的ordere节点信息，使用当前的fabric客户端组装orderer节点
     * @param fabricOrderer orderer节点信息
     * @throws Exception
     */
    @Override
    public void createOrderer(FabricOrderer fabricOrderer) throws Exception{
        //创建orderer节点对象
        this.orderer = client.newOrderer(fabricOrderer.getOrdererName(), fabricOrderer.getOrdererLocation());
    }

    /**
     * 组装orderer集合
     * @param fabricOrderers orderer集合信息
     * @throws Exception
     */
    @Override
    public void createOrdererList(List<FabricOrderer> fabricOrderers) throws Exception {
        List<Orderer> ordererList = new ArrayList<>();
        for(FabricOrderer fabricOrderer:fabricOrderers){
            Orderer orderer = client.newOrderer(fabricOrderer.getOrdererName(), fabricOrderer.getOrdererLocation());
            ordererList.add(orderer);
        }
        this.orderers = ordererList;
    }

    /**
     * 根据传入的peer节点信息，使用当前的fabric客户端组装peer节点
     * @param fabricPeer peer节点信息
     * @throws Exception
     */
    @Override
    public void createPeer(FabricPeer fabricPeer) throws Exception {
        this.peer = client.newPeer(fabricPeer.getPeerName(), fabricPeer.getPeerLocation());
    }

    /**
     * 组装peer节点集合
     * @param fabricPeers peer集合
     * @throws Exception
     */
    @Override
    public void createPeerList(List<FabricPeer> fabricPeers) throws Exception{
        List<Peer> peersList = new ArrayList<>();
        for(FabricPeer fabricPeer:fabricPeers){
            Peer peer = client.newPeer(fabricPeer.getPeerName(), fabricPeer.getPeerLocation());
            peersList.add(peer);
        }
        this.peers = peersList;
    }

    /**
     * 根据通道名组装通道
     * @param channelName 通道名称
     * @throws Exception
     */
    @Override
    public void createChannel(String channelName) throws Exception{
        if(client.getChannel(channelName)!=null){
            client.removeChannel(client.getChannel(channelName));
        }
        Channel channelNow = client.newChannel(channelName);
        if(peer!= null){
            peer.unsetChannel();
            channelNow.addPeer(peer);
        }
        if(peers != null&&peers.size() != 0){
            for(Peer peerNow:peers){
                peerNow.unsetChannel();
                channelNow.addPeer(peerNow);
            }
        }
        if(orderer != null){
            orderer.unsetChannel();
            channelNow.addOrderer(orderer);
        }
        if(orderers != null){
            for(Orderer ordererNow:orderers){
                ordererNow.unsetChannel();
                channelNow.addOrderer(ordererNow);
            }
        }
        AopFabricClient.setField(channelNow, "initialized", true);
        this.channel = channelNow;
    }

    @Override
    public void createChaincodeID(FabricChaincode fabricChaincode) {
        ChaincodeID.Builder chaincodeIDBuilder = ChaincodeID.newBuilder().setName(fabricChaincode.getChaincodeName())
                .setVersion(fabricChaincode.getChaincodeVersion());
        chaincodeIDBuilder.setPath(fabricChaincode.getChaincodePath());
        ChaincodeID chaincodeID = chaincodeIDBuilder.build();
        fabricChaincode.setChaincodeID(chaincodeID);
        this.fabricChaincode = fabricChaincode;
    }


}
