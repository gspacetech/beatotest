package com.test.beatoapp;

import android.app.Application;
import android.content.Context;


public class ApplicationClass extends Application {

    private static ApplicationClass applicationClass;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static ApplicationClass getInstance() {
        return applicationClass;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationClass = this;

    }


}
