package com.lxq.httpjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * JAVA GET请求HTTP协议返回的JSON数据
 * @author lxq
 *
 */
public class httpjson {
	public static void main(String[] args) {
		String url = "http://127.0.0.1/SSH_Object/framework/user/add.action?userName=2222d&age=1111";
        String json = loadJSON(url);
        System.out.println(json);
	}
	
	public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); //防止乱码
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
}
