package com.nan.shopping;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.bean.TestResult;
import com.nan.shopping.util.UtilAnimation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liu on 15/11/12.
 */
public class TestActivity extends BaseActivity {

    @Bind(R.id.tv_test)
    TextView tvTest;
    @Bind(R.id.iv_test)
    ImageView ivTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        finishAnimLeftToRight();
    }

    @OnClick(R.id.btn_test)
    public void test(View v) {
//        showLoadDialog();
//        TestController controller = new TestController(this);
//        controller.getData();
        ivTest.setImageResource(R.mipmap.home_select);
    }

    public void success(TestResult result) {
        List<TestResult.DataEntity> data = result.getData();
        tvTest.setText(data.get(0).getName());
    }

    @OnClick(R.id.iv_test)
    public void aa(View view)
    {
//        UtilAnimation.aa(ivTest);
    }
}