package com.nan.shopping.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nan.shopping.R;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.base.LoadDialog;
import com.nan.shopping.util.UtilSPutil;
import com.nan.shopping.util.UtilSystem;
import com.nan.shopping.util.UtilToast;

public class WelcomeActivity extends BaseActivity {
    private long startTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int currVersionCode = UtilSystem.getVersionCode();
        int spVersionCode = UtilSPutil.getInstance().getInt("code", -1);
        startTime = System.currentTimeMillis();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intentLeftToRight(GuiActivity.class);
            finish();
        }
    };

}
