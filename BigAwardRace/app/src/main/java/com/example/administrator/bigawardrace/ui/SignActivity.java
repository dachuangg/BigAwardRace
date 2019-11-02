package com.example.administrator.bigawardrace.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bigawardrace.R;
import com.example.administrator.bigawardrace.entity.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//实现注册功能
public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText sign_account;
    private EditText sign_password;
    private EditText sign_phone;
    private Button sign_up_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        initView();
    }

    private void initView() {
        sign_account = findViewById(R.id.sign_account);
        sign_password = findViewById(R.id.sign_password);
        sign_up_button = findViewById(R.id.sign_up_button);
        sign_phone = findViewById(R.id.sign_phone);
        sign_up_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                String userAccount = sign_account.getText().toString().trim();
                String userPassword = sign_password.getText().toString().trim();
                String userPhone = sign_phone.getText().toString().trim();
                UserData userData = new UserData();
                userData.setAccount(userAccount);
                userData.setPassword(userPassword);
                userData.setPhone(userPhone);
                LoginRequest(userData.getAccount(),userData.getPassword(),userData.getPhone());
                break;
        }
    }

    public void LoginRequest(final String accountNumber, final String password, final String phone) {
        //请求地址
        String url = "http://101.132.135.204:8080/MyFirstWebApp/RegisterServlet";
        String tag = "Login";

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);

        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("register_params");  //注③
                            String result = jsonObject.getString("Result");  //注④
                            if (result.equals("register_success")) {  //注⑤
                                //做自己的登录成功操作，如页面跳转
                                Toast.makeText(SignActivity.this,"注册成功！！",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignActivity.this,LogonActivity.class));
                            } else if (result.equals("repeat")){
                                Toast.makeText(SignActivity.this,"用户名已经注册请登录！！！",Toast.LENGTH_LONG).show();
                            }else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(SignActivity.this,"操作失败！！！",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(SignActivity.this,result,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AccountNumber", accountNumber);  //注⑥
                params.put("Password", password);
                params.put("PhoneNumber",phone);
                return params;
            }
        };
        //设置Tag标签
        request.setTag(tag);
        //将请求添加到队列中
        requestQueue.add(request);
    }
}
