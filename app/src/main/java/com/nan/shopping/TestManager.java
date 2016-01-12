package com.nan.shopping;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.nan.shopping.base.BaseManager;
import com.nan.shopping.bean.TestResult;

import java.lang.reflect.Type;

/**
 * Created by liu on 15/11/21.
 */
public class TestManager extends BaseManager{
    @Override
    public void onExecute() {
        String url  = "http://www.tingshijie.com/index.php?s=Home/App/getHotBroadcaster";
        Type type = new TypeToken<TestResult>(){}.getType();
        getWebData(url, type, Request.Method.GET);
    }
}
