package com.chsoft.webapp.chaincode.aop;

import com.chsoft.fabric.manage.fabricChaincode.entity.FabricChaincode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect
/**
 * 组装具体实现类
 */
public class ChaincodeCreateAction {

	private ChaincodeCreate chaincodeCreate;

	@Pointcut("execution(public * com.chsoft.webapp.chaincode.*.*(..))")
	public void pointCut(){

	}

	//指定在具体注释后执行该方法
	@Before( "pointCut()")
    public void beforeMethodXML(JoinPoint joinPoint) throws Exception{
		//获取目标对象信息
		Object obj = joinPoint.getTarget();
		if(obj instanceof ChaincodeCreate){
			chaincodeCreate = (ChaincodeCreate) obj;
		}else{
			return;
		}
		chaincodeCreate.creatChaincode(chaincodeCreate.getChaincode());
    }
	
	//指定在具体注释前执行该方法
	
	/*@After( value = "@annotation(com.chsoft.fabric.aop.FabricCreate)")
    public void afterMethodXML(JoinPoint joinPoint) {
        //String opreate = joinPoint.getSignature().getName();  
    }*/


}