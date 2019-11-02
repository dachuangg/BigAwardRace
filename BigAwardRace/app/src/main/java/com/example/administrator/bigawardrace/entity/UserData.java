package com.example.administrator.bigawardrace.entity;

//关于注册登录的实体信息类
public class UserData {

    private String Account;
    private String Password;
    private String Phone;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
