package com.example.administrator.bigawardrace.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.bigawardrace.MainActivity;
import com.example.administrator.bigawardrace.R;
import com.example.administrator.bigawardrace.entity.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//实现登录功能
public class LogonActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView logon_sign_up;
    private EditText logon_account;
    private EditText logon_password;

    private Button login_button;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        initView();
    }

    //各种控件的初始化
    private void initView() {
        logon_account = findViewById(R.id.logon_account);
        logon_password = findViewById(R.id.logon_password);
        logon_sign_up = findViewById(R.id.logon_sign_up);
        logon_sign_up.setOnClickListener(this);
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);

    }

    public void LoginRequest(final String accountNumber, final String password) {
        //请求地址
        String url = "http://101.132.135.204:8080/MyFirstWebApp/LoginServlet";
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
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("login_params");  //注③
                            String result = jsonObject.getString("Result");  //注④
                            if (result.equals("login_success")) {  //注⑤
                                //做自己的登录成功操作，如页面跳转
                                Toast.makeText(LogonActivity.this,"登录成功！！",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LogonActivity.this, MainActivity.class));
                            } else {
                                //做自己的登录失败操作，如Toast提示
                                Toast.makeText(LogonActivity.this,"登录失败！！！",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(LogonActivity.this,result,Toast.LENGTH_LONG).show();
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
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logon_sign_up:
                startActivity(new Intent(LogonActivity.this,SignActivity.class));
                break;
            case R.id.login_button:
                String logonAccount = logon_account.getText().toString().trim();
                String logonPassword = logon_password.getText().toString().trim();

                userData = new UserData();
                userData.setAccount(logonAccount);
                userData.setPassword(logonPassword);
                LoginRequest(userData.getAccount(),userData.getPassword());
                break;
                default:
                    break;
        }
    }
}
