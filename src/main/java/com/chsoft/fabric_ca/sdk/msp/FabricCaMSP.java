package com.chsoft.fabric_ca.sdk.msp;

import com.chsoft.fabric_ca.sdk.conf.Fabric_ca_config;

import java.io.File;

public class FabricCaMSP extends MSP{
	
	public static final String cacertsName = "localhost-7054.pem";
	
	public static final String intermediatecertsName = "localhost-7054.pem";
	
	public static final String keystoreName = "abc_sk";
	
	public static final String signcertsName = "";
	
	public static final String tlscacertsName = "cert.pem";
	
	public FabricCaMSP(String mspDirectory) {
		super(mspDirectory);

		cacerts = mspDirectory+"cacerts"+ Fabric_ca_config.separator;
		
		intermediatecerts = mspDirectory+"intermediatecerts"+ Fabric_ca_config.separator;
		
		keystore = mspDirectory+"keystore"+ Fabric_ca_config.separator;
		
		signcerts = mspDirectory+"signcerts"+ Fabric_ca_config.separator;
		
		tlscacerts = mspDirectory+"tlscacerts"+ Fabric_ca_config.separator;
		
		File file1=new File(cacerts);
		file1.mkdirs();
		
		File file2=new File(intermediatecerts);
		file2.mkdirs();
		
		File file3=new File(keystore);
		file3.mkdirs();
		
		File file4=new File(signcerts);
		file4.mkdirs();
		
		File file5=new File(tlscacerts);
		file5.mkdirs();
	}
	
}
