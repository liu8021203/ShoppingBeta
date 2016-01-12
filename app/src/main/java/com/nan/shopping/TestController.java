package com.nan.shopping;

import com.nan.shopping.base.ActionController;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.base.BaseResultListener;
import com.nan.shopping.bean.TestResult;

/**
 * Created by liu on 15/11/21.
 */
public class TestController {

    private TestActivity activity;

    public TestController(TestActivity activity)
    {
        this.activity = activity;
    }


    public void getData()
    {
        ActionController.postDate(activity, TestManager.class, null, new CallBackListener(activity));
    }

    private class CallBackListener extends BaseResultListener
    {

        public CallBackListener(BaseActivity act) {
            super(act);
        }

        @Override
        public void onSuccess(Object result) {
            super.onSuccess(result);
            TestResult result1 = (TestResult) result;
            activity.showToast(result1.toString());
            activity.success(result1);
        }


    }
}
