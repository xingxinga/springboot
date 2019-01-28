package com.chsoft.webapp.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by lixing on 2019/1/28.
 */
public class HttpFileDownload {

    public static void downloadByFile(HttpServletResponse response, File file) throws Exception{
        if (file.exists()){
            String fileName = file.getName();
            FileInputStream fis = new FileInputStream(file);
            downloadByInputStream(response,fis,fileName);
        }
    }

    public static void downloadByString(HttpServletResponse response, String value,String fileName) throws Exception{
        if (!value.isEmpty()&&!fileName.isEmpty()){
            InputStream is = new ByteArrayInputStream(value.getBytes());
            downloadByInputStream(response,is,fileName);
        }
    }

    private static void downloadByInputStream(HttpServletResponse response, InputStream inputStream,String fileName) throws Exception{
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(inputStream);
        OutputStream os = response.getOutputStream();
        int i = bis.read(buffer);
        while (i != -1) {
            os.write(buffer, 0, i);
            i = bis.read(buffer);
        }
    }
}
