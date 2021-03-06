package com.chsoft.fabric.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import com.chsoft.fabric.local.entity.FabricLocal;
import org.hyperledger.fabric.protos.peer.Query.ChaincodeInfo;
import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.Channel.NOfEvents;
import org.hyperledger.fabric.sdk.Channel.TransactionOptions;
import org.hyperledger.fabric.sdk.Peer.PeerRole;
import org.hyperledger.fabric.sdk.TransactionRequest.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 * @ClassName: FabricClient
 * @Description: TODO(自己封装的fabric客户端，操作时使用自己封装的对象)
 * @author lixing
 * @date 2018年9月13日 下午5:35:57
 * @version V1.0
 */
@Component
public class AopFabricClient extends SampleFabricCreateClient{
	@Resource
	private FabricLocal fabricLocal;

	public AopFabricClient(){
		super();
	}

	/**
	 * @param fabricUser
	 * @Title:用户的客户端，用于操作fabric网络
	 * @Description: TODO
	 */
	public AopFabricClient(FabricUser fabricUser) {
		super(fabricUser);
	}

	/*@PostConstruct
	public void init(){
		try {
			client = FabricClientFactory.getPeerUserClient(fabricLocal.getLocalFabricUserAdmin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/**
	 * fabric客户端初始化操作，将对象中的属性清空
	 * @param fabricUser 根据传入的用户对象生成fabric客户端
	 */
	public void init(FabricUser fabricUser){
		try {
			client = FabricClientFactory.getPeerUserClient(fabricUser);
			channel = null;
			orderer = null;
			orderers = null;
			peer = null;
			peers = null;
			fabricChaincode = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Title: createChannel
	 * @Description: TODO(创建channel)
	 * @param @param channelName 通道的名称
	 * @param @param fabricOrderer 创建通道的orderer信息
	 * @param @param channelTxPath 储存通道的交易配置信息文件的地址
	 * @param @return
	 * @param @throws Exception    入参
	 * @return Channel    返回类型 返回创建好的channel
	 * @author lixing
	 * @throws
	 * @date 2018年9月20日 上午10:33:00
	 * @version V1.0
	 */
	@FabricCreate(orderer = 2)
	public Channel createChannel(String channelName,FabricOrderer fabricOrderer,String channelTxPath) throws Exception{

		ChannelConfiguration channelConfiguration = new ChannelConfiguration(new File(channelTxPath));

		Channel channel = client.newChannel(channelName, orderer, channelConfiguration, client.getChannelConfigurationSignature(channelConfiguration,client.getUserContext()));

		return channel;
	}

	/**
	 * @Title: queryChannels
	 * @Description: TODO(查询peer节点上加入的channel信息)
	 * @param @param fabricUser
	 * @param @param fabricPeer
	 * @param @return
	 * @param @throws InvalidArgumentException    入参
	 * @return Set<String>    返回类型
	 * @author lixing
	 * @throws
	 * @date 2018年9月20日 上午10:39:13
	 * @version V1.0
	 */
	@FabricCreate(peer = 1)
	public Set<String> queryChannels(FabricPeer fabricPeer) throws Exception{
		return client.queryChannels(peer);
	}

	/**
	 * @Title: channelJoinPeer
	 * @Description: TODO(peer加入一个指定的channel)
	 * @param @param channelName //加入的通道名称
	 * @param @param fabricOrderer //ordere对象
	 * @param @param fabricPeer //加入的通道的peer对象
	 * @param @return
	 * @param @throws Exception    入参
	 * @return Channel    返回类型
	 * @author lixing
	 * @throws
	 * @date 2018年9月20日 上午10:40:05
	 * @version V1.0
	 */
	@FabricCreate(channel = 1,orderer = 2)
	public Channel channelJoinPeer (String channelName, FabricOrderer fabricOrderer, FabricPeer fabricPeer) throws Exception {
		setField(channel, "initialized", false);
		channel.joinPeer(client.newPeer(fabricPeer.getPeerName(), fabricPeer.getPeerLocation()));
		return channel;
	}

	/**
	 *
	 * @param fabricChaincode
	 * @param fabricPeerList
	 * @return
	 * @throws Exception
	 */
	public boolean peerListInstallChaincode(FabricChaincode fabricChaincode, List<FabricPeer> fabricPeerList) throws Exception {
		ChaincodeID.Builder chaincodeIDBuilder = ChaincodeID.newBuilder().setName(fabricChaincode.getChaincodeName())
				.setVersion(fabricChaincode.getChaincodeVersion());
		chaincodeIDBuilder.setPath(fabricChaincode.getChaincodePath());
		ChaincodeID chaincodeID = chaincodeIDBuilder.build();
		InstallProposalRequest installProposalRequest = client.newInstallProposalRequest();
		installProposalRequest.setChaincodeID(chaincodeID);

		//client.sendInstallProposal(installProposalRequest, peers)
		return true;
	}

	/**
	 * 在节点上安装链码
	 * @param fabricChaincode 链码信息
	 * @param fabricPeer 节点信息
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(chaincode = 1, peer = 2)
	public boolean peerInstallChaincode(FabricChaincode fabricChaincode,FabricPeer fabricPeer) throws Exception   {
		//新建安装链码的会话
		InstallProposalRequest installProposalRequest = client.newInstallProposalRequest();
		//赋值会话中的具体链码信息
		installProposalRequest.setChaincodeID(fabricChaincode.getChaincodeID());
		if (fabricChaincode.getChaincodeLanguage().equals(Type.GO_LANG)) {
			installProposalRequest.setChaincodeInputStream(Util.generateTarGzInputStream(
					(Paths.get(fabricChaincode.getChaincodeFilepath()).toFile()),
					Paths.get("src", fabricChaincode.getChaincodePath()).toString()));
		}
		installProposalRequest.setChaincodeVersion(fabricChaincode.getChaincodeVersion());
		installProposalRequest.setChaincodeLanguage(fabricChaincode.getChaincodeLanguage());
		Collection<Peer> peerList = new ArrayList<Peer>();
		peerList.add(peer);
		Collection<ProposalResponse> list = client.sendInstallProposal(installProposalRequest,peerList);
		if(!Util.isSuccess(list)){
			return false;
		}
		return true;
	}

	/**
	 * 查询在节点上已安装的链码
	 * @param fabricPeer 查询的节点
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(peer = 1)
	public List<ChaincodeInfo> queryInstalledChaincodes(FabricPeer fabricPeer) throws Exception{
		return client.queryInstalledChaincodes(peer);
	}

	/**
	 *查询在节点上某一通道上已实例化的链码
	 * @param channelName 查询通道的名称
	 * @param fabricPeer 查询的节点
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,peer = 2)
	public List<ChaincodeInfo> queryInstantiateChaincodes(String channelName,FabricPeer fabricPeer) throws Exception{
		return channel.queryInstantiatedChaincodes(peer);
	}

	/**
	 * 节点实例化链码
	 * @param channelName 通道名称
	 * @param fabricOrderer 排序节点信息
	 * @param fabricPeer 实例化链码的节点
	 * @param fabricChaincode 链码信息
	 * @param args 实例化的参数
	 * @param chaincodeEndorsementPolicy 背书策略
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,orderer = 2,peer = 3,chaincode = 4)
	public boolean peerInstantiateChainCode(String channelName,FabricOrderer fabricOrderer,FabricPeer fabricPeer,FabricChaincode fabricChaincode,String[] args,ChaincodeEndorsementPolicy chaincodeEndorsementPolicy) throws Exception{
		return peerInstantiateChainCode(args,chaincodeEndorsementPolicy);
	}

	/**
	 * 节点实例化链码
	 * @param channelName 通道名称
	 * @param fabricOrderer 排序节点信息
	 * @param fabricPeer 实例化链码的节点
	 * @param fabricChaincode 链码信息
	 * @param args 实例化的参数
	 * @param xmlPath 背书策略
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,orderer = 2,peer = 3,chaincode = 4)
	public boolean peerInstantiateChainCodeXmlPath(String channelName,FabricOrderer fabricOrderer,FabricPeer fabricPeer,FabricChaincode fabricChaincode,String[] args,String xmlPath) throws Exception{
		ChaincodeEndorsementPolicy chaincodeEndorsementPolicy = new ChaincodeEndorsementPolicy();
		chaincodeEndorsementPolicy.fromYamlFile(new File(xmlPath));
		return peerInstantiateChainCode(args,chaincodeEndorsementPolicy);
	}

	/**
	 * 实例化链码的具体操作
	 * @param args
	 * @param chaincodeEndorsementPolicy
	 * @return
	 * @throws Exception
	 */
	private boolean peerInstantiateChainCode(String[] args,ChaincodeEndorsementPolicy chaincodeEndorsementPolicy) throws Exception{
		InstantiateProposalRequest instantiateProposalRequest = client.newInstantiationProposalRequest();
		instantiateProposalRequest.setChaincodeID(fabricChaincode.getChaincodeID());
		instantiateProposalRequest.setChaincodePath(fabricChaincode.getChaincodePath());
		instantiateProposalRequest.setChaincodeVersion(fabricChaincode.getChaincodeVersion());
		instantiateProposalRequest.setProposalWaitTime(fabricChaincode.getDeployWatiTime());
		instantiateProposalRequest.setChaincodeName(fabricChaincode.getChaincodeName());
		instantiateProposalRequest.setChaincodeLanguage(fabricChaincode.getChaincodeLanguage());
		instantiateProposalRequest.setFcn("init");
		instantiateProposalRequest.setArgs(args);
		Map<String, byte[]> tm = new HashMap<>();
		tm.put("HyperLedgerFabric", "InstantiateProposalRequest:JavaSDK".getBytes(UTF_8));
		tm.put("method", "InstantiateProposalRequest".getBytes(UTF_8));
		instantiateProposalRequest.setTransientMap(tm);
		instantiateProposalRequest.setChaincodeEndorsementPolicy(chaincodeEndorsementPolicy);
		Collection<ProposalResponse> list = channel.sendInstantiationProposal(instantiateProposalRequest);
		if(!Util.isSuccess(list)){
			return false;
		}
		NOfEvents nOfEvents = NOfEvents.createNofEvents();
		if (!channel.getPeers(EnumSet.of(PeerRole.EVENT_SOURCE)).isEmpty()) {
			nOfEvents.addPeers(channel.getPeers(EnumSet.of(PeerRole.EVENT_SOURCE)));
		}
		if (!channel.getEventHubs().isEmpty()) {
			nOfEvents.addEventHubs(channel.getEventHubs());
		}
		channel.sendTransaction(list, TransactionOptions.createTransactionOptions() //Basically the default options but shows it's usage.
				.userContext(client.getUserContext()) //could be a different user context. this is the default.
				.shuffleOrders(false) // don't shuffle any orderers the default is true.
				.orderers(channel.getOrderers()) // specify the orderers we want to try this transaction. Fails once all Orderers are tried.
				.nOfEvents(nOfEvents) // The events to signal the completion of the interest in the transaction
		);
		return true;
	}

	/**
	 * 执行链码的查询
	 * @param channelName 查询通道的名称
	 * @param fabricPeer 查询的节点信息
	 * @param fabricChaincode 查询的链码信息
	 * @param fcn 链码中的函数对应的参数
	 * @param args 查询链码函数的参数
	 * @return
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,peer = 2,chaincode = 3)
	public String queryChaincode(String channelName,FabricPeer fabricPeer,FabricChaincode fabricChaincode,String fcn, String[] args) throws Exception{
		QueryByChaincodeRequest queryByChaincodeRequest = client.newQueryProposalRequest();
		queryByChaincodeRequest.setArgs(args);
		queryByChaincodeRequest.setFcn(fcn);
		queryByChaincodeRequest.setChaincodeID(fabricChaincode.getChaincodeID());
		Map<String, byte[]> tm2 = new HashMap<>();
		tm2.put("HyperLedgerFabric", "QueryByChaincodeRequest:JavaSDK".getBytes(UTF_8));
		tm2.put("method", "QueryByChaincodeRequest".getBytes(UTF_8));
		queryByChaincodeRequest.setTransientMap(tm2);
		Collection<ProposalResponse> queryProposals = channel.queryByChaincode(queryByChaincodeRequest);
		return Util.getResult(queryProposals);
	}

	/**
	 * 执行链码交易
	 * @param channelName 通道名称
	 * @param fabricOrderer 排序节点信息
	 * @param fabricPeer 执行链码的节点信息
	 * @param fabricChaincode 链码信息
	 * @param fcn 链码中的函数对应的参数
	 * @param args 执行链码函数的参数
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,orderer = 2,peer = 3,chaincode = 4)
	public void invokeChaincodePeer(String channelName,FabricOrderer fabricOrderer,FabricPeer fabricPeer,FabricChaincode fabricChaincode,String fcn, String[] args) throws Exception{
		invokeChaincode(fcn,args);
	}

	/**
	 * 执行链码交易
	 * @param channelName 通道名称
	 * @param fabricOrderer 排序节点信息
	 * @param fabricPeerList 执行链码的节点列表
	 * @param fabricChaincode 链码信息
	 * @param fcn 链码中的函数对应的参数
	 * @param args 执行链码函数的参数
	 * @throws Exception
	 */
	@FabricCreate(channel = 1,orderer = 2,peerList = 3,chaincode = 4)
	public void invokeChaincodePeerList(String channelName,FabricOrderer fabricOrderer,List<FabricPeer> fabricPeerList,FabricChaincode fabricChaincode,String fcn, String[] args) throws Exception{
		invokeChaincode(fcn,args);
	}

	/**
	 * 执行链码的具体操作
	 * @param fcn
	 * @param args
	 * @throws Exception
	 */
	private void invokeChaincode(String fcn,String[] args) throws Exception{
		TransactionProposalRequest transactionProposalRequest = client.newTransactionProposalRequest();
		transactionProposalRequest.setChaincodeID(fabricChaincode.getChaincodeID());
		transactionProposalRequest.setChaincodeLanguage(fabricChaincode.getChaincodeLanguage());
		transactionProposalRequest.setFcn(fcn);
		transactionProposalRequest.setArgs(args);

		Map<String, byte[]> tm2 = new HashMap<>();
		tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
		tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
		tm2.put("result", ":)".getBytes(UTF_8));
		transactionProposalRequest.setTransientMap(tm2);
		Collection<ProposalResponse> transactionPropResp = channel.sendTransactionProposal(transactionProposalRequest);
		Util.getResult(transactionPropResp);
		CompletableFuture<BlockEvent.TransactionEvent> aa =  channel.sendTransaction(transactionPropResp);
		fcn = null;
	}
	/**
	 * Sets the value of a field on an object
	 *
	 * @param o         The object that contains the field
	 * @param fieldName The name of the field
	 * @param value     The new value
	 * @return The previous value of the field
	 */
	public static Object setField(Object o, String fieldName, Object value) {
		Object oldVal = null;
		try {
			final Field field = o.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			oldVal = field.get(o);
			field.set(o, value);
		} catch (Exception e) {
			throw new RuntimeException("Cannot get value of field " + fieldName, e);
		}
		return oldVal;
	}


}
