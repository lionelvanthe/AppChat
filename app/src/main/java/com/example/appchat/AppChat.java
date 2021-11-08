package com.example.appchat;

import android.app.Application;

public class AppChat extends Application {
    private static final String TAG = AppChat.class.getName();
    private static AppChat instance;

    public static AppChat getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
