package com.chsoft.fabric.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import com.chsoft.fabric.manage.fabricOrderer.entity.FabricOrderer;
import com.chsoft.fabric.manage.fabricPeer.entity.FabricPeer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//import com.hyperledger.fabric.FabricOrderer;
//import com.hyperledger.fabric.FabricPeer;

@Component  
@Aspect
/**
 * 组装具体实现类
 */
public class FabricCreateAction {

	private Object[] arguments;

	private FabricCreateClient fabricCreateClient;

	private FabricCreate fabricCreate;

	//指定在具体注释后执行该方法
	@Before( value = "@annotation(com.chsoft.fabric.aop.FabricCreate)")
    public void beforeMethodXML(JoinPoint joinPoint) throws Exception{
		//获取目标对象信息
		Object obj = joinPoint.getTarget();
		if(obj instanceof FabricCreateClient){
			fabricCreateClient = (FabricCreateClient) obj;
		}else{
			return;
		}
		//获取注解上信息
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		//获取注解的具体对象
        fabricCreate = ms.getMethod().getAnnotation(FabricCreate.class);
        //获取带参方法的参数
        arguments = joinPoint.getArgs();
		//具体赋值操作
		setValue();
    }
	
	//指定在具体注释前执行该方法
	
	@After( value = "@annotation(com.chsoft.fabric.aop.FabricCreate)")
    public void afterMethodXML(JoinPoint joinPoint) {
        //String opreate = joinPoint.getSignature().getName();  
    }

	/**
	 * 根据注解传入的参数，找到对应位置的peer和order信息，并赋值
	 * @throws Exception
	 */
    public void setValue() throws Exception{
    	//赋值peer
		setPeer();
		//赋值order
		setOrderer();
		//赋值channel
		setChannel();
		//赋值chaincodeID
		setChaincode();
	}

	/**
	 * 设置peer部分的值
	 * @throws Exception
	 */
	public void setPeer() throws Exception {
		if(fabricCreate.peer()!=-1){
			FabricPeer argumentPeer = (FabricPeer) arguments[fabricCreate.peer()-1];
			fabricCreateClient.createPeer(argumentPeer);
		}
		//组装peer节点列表信息
		if(fabricCreate.peerList()!=-1){
			List<FabricPeer> argumentPeerList = (List<FabricPeer>) arguments[fabricCreate.peerList()-1];
			fabricCreateClient.createPeerList(argumentPeerList);
		}
	}

	/**
	 * 设置orderer部分的值
	 * @throws Exception
	 */
	public void setOrderer() throws Exception {
		if(fabricCreate.orderer()!=-1){
			FabricOrderer argumentOrderer = (FabricOrderer) arguments[fabricCreate.orderer()-1];
			fabricCreateClient.createOrderer(argumentOrderer);
		}
		//组装orderer节点列表信息
		if(fabricCreate.ordererList()!=-1){
			List<FabricOrderer> argumentOrdererList = (List<FabricOrderer>) arguments[fabricCreate.ordererList()-1];
			fabricCreateClient.createOrdererList(argumentOrdererList);
		}
	}

	public void setChannel() throws Exception {
		//组装channel信息
		if(fabricCreate.channel()!= -1){
			String channelName = (String) arguments[fabricCreate.channel()-1];
			fabricCreateClient.createChannel(channelName);
		}
	}
	public void setChaincode() throws Exception {
		if(fabricCreate.chaincode()!= -1){
			FabricChaincode argumentChaincode = (FabricChaincode) arguments[fabricCreate.chaincode()-1];
			fabricCreateClient.createChaincodeID(argumentChaincode);
		}
	}
}