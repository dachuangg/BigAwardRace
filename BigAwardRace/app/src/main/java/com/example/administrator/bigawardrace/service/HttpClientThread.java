package com.example.administrator.bigawardrace.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
//使用HttpClient实现网络请求
public class HttpClientThread extends Thread {

    private String url;

    public HttpClientThread(String url) {
        this.url = url;
    }

    //使用get方式进行网络请求
    private void doHttpClientGet() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                //如果返回值正确，即使用content读取收到的数据
                String content = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doHttpClientGet();
    }
}
