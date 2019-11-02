package com.example.administrator.bigawardrace.application;

import android.app.Application;

import com.example.administrator.bigawardrace.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APPID, true);
    }
}
