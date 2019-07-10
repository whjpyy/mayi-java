package com.mayi.jiagousi.ch14_http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HttpClientDemo {

    public void get(){
        // 创建默认链接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求
        HttpGet httpGet = new HttpGet("http://www.itmayiedu.com");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("http请求结果：" + statusCode);
            if(statusCode == 200){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void post(){
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/httpPost");
        // 创建参数
        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", "chen"));
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);
            System.out.println("request: " + httpPost.getURI());
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                System.out.println("------------------------------------");
                System.out.println("response content: " + EntityUtils.toString(entity, "utf-8"));
                System.out.println("------------------------------------");
            }
            response.close();
            httpClient.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        HttpClientDemo httpClientDemo = new HttpClientDemo();
//        httpClientDemo.get();
        httpClientDemo.post();
    }
}
