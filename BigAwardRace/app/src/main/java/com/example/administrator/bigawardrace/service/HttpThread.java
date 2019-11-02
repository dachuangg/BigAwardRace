package com.example.administrator.bigawardrace.service;

import com.example.administrator.bigawardrace.utils.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//使用HttpURLConnection实现网络请求
public class HttpThread extends Thread {

    String url;
    String name;
    String age;

    public HttpThread(String url, String name, String age) {
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doget() {
        try {
            //拼接一个url，相当于用户名以及密码
            //用户名可以中文，使用URLEncoder.encode（，"utf-8"）可转码是得浏览器不会出现乱码
            url = url + "?name=" + URLEncoder.encode(name, "utf-8") + "&age=" + age;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL HttpUrl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) HttpUrl.openConnection();
                //网络的方式
                connection.setRequestMethod("GET");
                //上传的时间
                connection.setReadTimeout(5000);
                //网络请求返回的数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String str;
                StringBuffer sb = new StringBuffer();
                //读取返回数据的每一行
                while ((str = reader.readLine()) != null) {
                    //并且把每一行数据装载进StringBuffer中
                    //如果为空就跳出while循环
                    sb.append(str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void doPost(){
        try {
            URL url1 = new URL(url);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setReadTimeout(5000);
                OutputStream out = httpURLConnection.getOutputStream();
                String content = "?name=" + name + "&age=" +age;
                out.write(content.getBytes());
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String str;
                while ((str=reader.readLine())!=null){
                    sb.append(str);
                }
                L.i("hah:" + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //doget();
        doPost();
    }
}
