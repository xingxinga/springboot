package com.chsoft.fabric_ca.sdk.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class Fabric_ca_config {

	private static String osName = System.getProperties().getProperty("os.name");
	
	public static String wrap = Fabric_ca_config.getWrap();
	
	public static String separator = Fabric_ca_config.getSeparator();

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
