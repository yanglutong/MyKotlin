package com.yang.mykotlin.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
