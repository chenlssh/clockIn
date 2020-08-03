package com.cls.clock.util;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

/**
 * @version 1.0
 * @Description http工具类
 * @Author chenLongshun
 * @DATE 2020/7/22
 */
public class HttpUtil {
    /**
     * 发送HTTPS请求
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer = null;
        try {
            // 创建SSLContext对象
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            // 初始化
            sslContext.init(null,tm,new SecureRandom());
            // 获取SSLSocketFactory对象
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            // 设置当前实例使用工厂
            conn.setSSLSocketFactory(sslSocketFactory);
            conn.connect();
            if(outputStr != null){
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(is,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(isReader);
            buffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
}

