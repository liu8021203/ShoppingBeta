package com.nan.shopping.welcome.controller;

import android.util.ArrayMap;

import com.nan.shopping.base.ActionController;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.base.BaseManager;
import com.nan.shopping.base.BaseResultListener;
import com.nan.shopping.welcome.WelcomeActivity;

/**
 * Created by liu on 15/11/6.
 */
public class WelcomeController {

    public WelcomeActivity activity;

    public WelcomeController(WelcomeActivity activity)
    {
        this.activity = activity;
    }


    public void getData(ArrayMap<String, String> map)
    {
        ActionController.postDate(activity, BaseManager.class, map, new DataCallBackListener(activity));
    }

    private class DataCallBackListener extends BaseResultListener
    {

        public DataCallBackListener(BaseActivity act) {
            super(act);
        }
    }
}
