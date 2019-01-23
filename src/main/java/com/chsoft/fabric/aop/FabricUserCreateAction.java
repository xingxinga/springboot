package com.chsoft.fabric.aop;

import com.chsoft.fabric.manage.fabricUser.entity.FabricUser;
import com.chsoft.sys.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

//import com.hyperledger.fabric.FabricOrderer;
//import com.hyperledger.fabric.FabricPeer;
@Order(1)
@Component  
@Aspect
/**
 * 组装具体实现类，根据登录用户初始化aopFabric客户端
 */
public class FabricUserCreateAction {

	private AopFabricClient aopFabricClient;

	@Pointcut("execution(public * com.chsoft.fabric.aop.AopFabricClient.*(..))")
	public void pointCut(){

	}

	//指定在具体注释后执行该方法
	@Before( "pointCut()")
	public void beforeMethodXML(JoinPoint joinPoint) throws Exception{
		//获取目标对象信息
		Object obj = joinPoint.getTarget();
		if(obj instanceof AopFabricClient){
			aopFabricClient = (AopFabricClient) obj;
		}else{
			return;
		}
		//获取登录用户信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//获取登录用户在fabric中的用户证书密钥信息
		File privatePath = new File(user.getPrivatekeyfilepath());
		File privateKeyFile =  UtilCer.findFileSk(privatePath);
		File certificateFile = new File(user.getCertificatefile());
		FabricUser fabricUser =  UtilCer.getMember(user.getFabricusername(),user.getFabricaffiliation(),user.getFabricmspid(),privateKeyFile,certificateFile);
		aopFabricClient.init(fabricUser);
	}
}