package com.chsoft.fabric_ca.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

	@Value("${system.fabric_ca.local.filePath.windows.root_directory}")
	private String root_directory_windows;

	@Value("${system.fabric_ca.local.filePath.windows.ca_certificate.caCertificateFilePath}")
	private String caCertificateFilePath_windows;

	@Value("${system.fabric_ca.local.filePath.windows.ca_certificate.caCertificateFileName}")
	private String caCertificateFileName_windows;

	@Value("${system.fabric_ca.local.filePath.windows.identitie.cacerts}")
	private String cacerts_windows;

	@Value("${system.fabric_ca.local.filePath.windows.identitie.keystore}")
	private String keystore_windows;

	@Value("${system.fabric_ca.local.filePath.windows.identitie.signcerts}")
	private String signcerts_windows;

	@Value("${system.fabric_ca.local.filePath.windows.identitie.tlscacerts}")
	private String tlscacerts_windows;

	@Value("${system.fabric_ca.local.filePath.windows.identitie.admincerts}")
	private String admincerts_windows;


	@Value("${system.fabric_ca.local.filePath.liunx.root_directory}")
	private String root_directory_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.ca_certificate.caCertificateFilePath}")
	private String caCertificateFilePath_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.ca_certificate.caCertificateFileName}")
	private String caCertificateFileName_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.identitie.cacerts}")
	private String cacerts_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.identitie.keystore}")
	private String keystore_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.identitie.signcerts}")
	private String signcerts_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.identitie.tlscacerts}")
	private String tlscacerts_liunx;

	@Value("${system.fabric_ca.local.filePath.liunx.identitie.admincerts}")
	private String admincerts_liunx;

	@Value("${system.fabric_ca.server.crtificateFile.isRetrieve}")
	private String isRetrieve;


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


	public String get_root_directory(){
		return osName.equals("Linux")? root_directory_liunx:root_directory_windows;
	}

	public String get_caCertificateFilePath(){
		return osName.equals("Linux")? caCertificateFilePath_liunx:caCertificateFilePath_windows;
	}

	public String get_caCertificateFileName(){
		return osName.equals("Linux")? caCertificateFileName_liunx:caCertificateFileName_windows;
	}

	public String get_cacerts(){
		return osName.equals("Linux")? cacerts_liunx:cacerts_windows;
	}

	public String get_keystore(){
		return osName.equals("Linux")? keystore_liunx:keystore_windows;
	}

	public String get_signcerts(){
		return osName.equals("Linux")? signcerts_liunx:signcerts_windows;
	}

	public String get_tlscacerts(){
		return osName.equals("Linux")? tlscacerts_liunx:tlscacerts_windows;
	}

	public String get_admincerts(){
		return osName.equals("Linux")? admincerts_liunx:admincerts_windows;
	}

}
