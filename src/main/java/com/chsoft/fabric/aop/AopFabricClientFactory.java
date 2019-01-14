package com.chsoft.fabric.aop;

import com.chsoft.fabric.local.entity.FabricLocal;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lixing on 2018/11/30.
 */
@Component
public class AopFabricClientFactory {

    @Resource
    private FabricLocal fabricLocal;

    /**
     * g根据系统默认用户信息生成AopFabric客户端
     * @return
     */
    public AopFabricClient createAopFabricClient(){
        AopFabricClient aopFabricClient = new AopFabricClient();
        aopFabricClient.init(fabricLocal.getLocalFabricUserAdmin());
        return aopFabricClient;
    }

}
