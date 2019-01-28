package com.chsoft.fabric_ca.sdk.msp;

import java.io.FileWriter;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.chsoft.fabric_ca.sdk.conf.Config;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ExportCert {
	
	 /**
     * 得到密钥字符串（经过base64编码）
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
          byte[] keyBytes = key.getEncoded();
          String s = (new BASE64Encoder()).encode(keyBytes);
          return s;
    }

	
	//导出私钥  
    public static void exportPrivateKey(PrivateKey privateKey,String exportFile) throws Exception {  
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(privateKey.getEncoded());
        FileWriter fw = new FileWriter(exportFile);  
        fw.write("-----BEGIN PRIVATE KEY-----"+Config.wrap);  //非必须  
        fw.write(encoded);  
        fw.write(Config.wrap+"-----END PRIVATE KEY-----");        //非必须
        fw.close();   
    	/*BASE64Encoder encoder = new BASE64Encoder();  
        String encoded = encoder.encode(privateKey.getEncoded()); 
        FileOutputStream fs = new FileOutputStream(exportFile);
    	fs.write(privateKey.getEncoded());
    	fs.close();*/
    }  
      
    //导出公钥  
    public static void exportPublicKey(PublicKey publicKey,String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();  
        String encoded = encoder.encode(publicKey.getEncoded());  
        FileWriter fw = new FileWriter(exportFile);  
        fw.write("-----BEGIN PUBLIC KEY-----"+Config.wrap);       //非必须
        fw.write(encoded);  
        fw.write(Config.wrap+"-----END PUBLIC KEY-----");     //非必须
        fw.close();  
    }

    //导出证书，解密base64字符串
    public static void exportCertByBase64String(String certs,String exportFile) throws Exception {
        FileWriter fw = new FileWriter(exportFile);
        fw.write(decoderBase64String(certs));
        fw.close();
    }

    public static String decoderBase64String(String certs) throws Exception{
        byte[] bt;
        BASE64Decoder decoder = new BASE64Decoder();
        bt = decoder.decodeBuffer(certs);
        return new String(bt);
    }


    public static void exportCertByString(String certs,String exportFile) throws Exception {
    	FileWriter fw = new FileWriter(exportFile);
        fw.write(certs);  
        fw.close();  
    }
}
