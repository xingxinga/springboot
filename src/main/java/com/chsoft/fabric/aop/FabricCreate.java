package com.chsoft.fabric.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据用户输入的信息组装fabric的数据
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface FabricCreate {
    
    int peer() default -1;

    int peerList() default -1;

    int orderer() default -1;

    int ordererList() default -1;

    int chaincode() default -1;

    int channel() default -1;
}