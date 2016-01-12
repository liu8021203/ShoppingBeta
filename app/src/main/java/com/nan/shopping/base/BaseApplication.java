package com.nan.shopping.base;

import android.app.Application;
import android.content.Context;

import com.nan.shopping.common.AppData;
import com.nan.shopping.util.UtilSPutil;


/**
 * Created by liu on 15/6/18.
 */
public class BaseApplication extends Application{
    private static BaseApplication application = null;
    public static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        AppData.uid = UtilSPutil.getInstance().getString("uid");
    }


    public static BaseApplication getInstance()
    {
        return application;
    }



}
