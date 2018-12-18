package com.chsoft.fabric_ca.sdk.conf;

import com.chsoft.fabric_ca.sdk.user.CaUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Setter
@Getter
public class Config {

	@Value("${system.fabric_ca.local.adminUser.name}")
	private String adminUserName;
	@Value("${system.fabric_ca.local.adminUser.password}")
	private String adminUserPassword;

	@Value("${system.fabric_ca.local.serverInfo.ip}")
	private String serverIp;
	@Value("${system.fabric_ca.local.serverInfo.caName}")
	private String caName;

	private static String osName = System.getProperties().getProperty("os.name");
	
	public static String wrap = Config.getWrap();
	
	public static String separator = Config.getSeparator();

	public static String getWrap(){
		if(osName.equals("Linux")){
            return "\r\n";
        }
        else{
        	return "\n";
        }
	}
	
	public static String getSeparator(){
		if(osName.equals("Linux")){
            return "/";
        }
        else{
        	return "\\";
        }
	}
	
	
}
