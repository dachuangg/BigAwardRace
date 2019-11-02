package com.example.administrator.bigawardrace.service;

public class Http {
    //    public boolean userHttp(final UserData userData){
//        new Thread(){
//            @Override
//            public void run() {
//                JSONObject jsonObject = new JSONObject();
//                try {
//
////                    jsonObject.put("height","56789");
////                    jsonObject.put("weight","11165");
////                    jsonObject.put("exercise","hahah");
////                    jsonObject.put("uid","1010");
//                    jsonObject.put("AccountNumber",userData.getAccount());
//                    jsonObject.put("Password",userData.getPassword());
//
//                    String content = String.valueOf(jsonObject);
//                    //String jsonC = "{\"height\":\"163\",\"weight\":\"123\",\"exercise\":\"篮球\",\"uid\":1010}";
//
//                    //请求
//                    String url = "http://101.132.135.204:8080/MyFirstWebApp/LoginServlet";
//                    try {
//                        URL url1 = new URL(url);
//                        HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
//                        connection.setRequestMethod("POST");
//                        connection.setReadTimeout(5000);
//                        connection.setDoOutput(true);
//                        connection.setRequestProperty("User-Agent","Fiddler");
//                        connection.setRequestProperty("Content-Type","application/json");
//                        connection.setRequestProperty("Charset","UTF-8");
//                        OutputStream os = connection.getOutputStream();
//                        os.write(content.getBytes());
//                        //清除缓冲区中的数据
//                        os.flush();
//                        //关闭流操作
//                        os.close();
//
//                        //服务器返回结果
//                        /*
//                        if (connection.getResponseCode() == 200) {
//                            BufferedReader reader = new BufferedReader(
//                                    new InputStreamReader(connection.getInputStream()));
//                            str = reader.readLine();
//                        }
//                        */
//
//                        String str;
//                        StringBuffer sb;
//                        BufferedReader reader = new BufferedReader
//                                (new InputStreamReader(connection.getInputStream()));
//                        sb = new StringBuffer();
//                        while ((str=reader.readLine())!=null){
//                            sb.append(str);
//                        }
//                        Looper.prepare();
//                        Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        return true;
//    }
}
