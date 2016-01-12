package com.nan.shopping.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.volley.VolleyUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.nan.shopping.R;
import com.nan.shopping.util.UtilIntent;
import com.nan.shopping.util.UtilPixelTransfrom;
import com.nan.shopping.util.UtilToast;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.InputStream;

/**
 * Created by liu on 15/5/29.
 */
public class BaseActivity extends FragmentActivity {

    public static RequestQueue queue = null;
    private LoadDialog loadDialog;
    //是否是上传文件请求
    public boolean isUpload = false;
    protected boolean isHome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.get(this).register(GlideUrl.class, InputStream.class,
                new VolleyUrlLoader.Factory(queue));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(this, true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.black);

        }
        if(!isHome) {
            SlidrConfig config = new SlidrConfig.Builder()
                    .velocityThreshold(2400)
                    .distanceThreshold(.25f)
                    .edge(true)
                    .touchSize(UtilPixelTransfrom.dip2px(this, 32))
                    .velocityThreshold(2000)
                    .build();
            // Attach the Slidr Mechanism to this activity
            Slidr.attach(this, config);
        }
    }

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }


    public static RequestQueue getRequestQueueinstance() {
        if (queue == null) {
            queue = Volley.newRequestQueue(BaseApplication.getInstance());
            System.out.println("哈哈哈");
        }
        return queue;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 显示标题
     *
     * @param title
     */
    protected void showTitle(String title) {
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
        TextView tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setVisibility(View.GONE);
        RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_layout_right);
        rl_right.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (queue != null) {
            System.out.println(this.getClass().getName());
            queue.cancelAll(this.getClass().getName());
        }
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public void showToast(String text) {
        UtilToast.showToast(text);
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param resId 文本的资源ID
     */
    public void showToast(int resId) {
        UtilToast.showToast(getString(resId));
    }

    /**
     * 加载框
     */
    public void showLoadDialog() {
        if (loadDialog != null) {
            loadDialog.show();
        } else {
            loadDialog = new LoadDialog(this);
            loadDialog.show();
        }
    }

    /**
     *
     */
    public void removeLoadDialog() {
        if (loadDialog != null) {
            if (loadDialog.isShowing()) {
                loadDialog.dismiss();
            }
        }
    }

    /**
     * 页面跳转
     *
     * @param classes c
     */
    public void intentLeftToRight(final Class<?> classes) {
        UtilIntent.intentDIYLeftToRight(this, classes);
    }

    /**
     * 自定义页面跳转动画
     *
     * @param classes   目标
     * @param enterAnim 进入动画文件ID
     * @param exitAnim  退出动画文件ID
     */
    public void intent(final Class<?> classes, final int enterAnim, final int exitAnim) {
        UtilIntent.intentDIYLeftToRight(this, classes, enterAnim, exitAnim);
    }

    /**
     * 页面跳转
     *
     * @param classes 目标
     * @param map     参数
     */
    public void intentLeftToRight(final Class<?> classes, final Bundle bundle) {
        UtilIntent.intentDIYLeftToRight(this, classes, bundle);
    }

    public void intentBottomToTop(final Class<?> classes) {
        UtilIntent.intentDIYBottomToTop(this, classes);
    }

    public void intentBottomToTop(final Class<?> classes, final Bundle bundle) {
        UtilIntent.intentDIYBottomToTop(this, classes, bundle);
    }

    public void intentResultLeftToRight(final Class<?> classes, final int requestCode) {
        UtilIntent.intentResultDIY(this, classes, requestCode);
    }

    public void intentResultLeftToRight(final Class<?> classes, final int requestCode, final Bundle bundle) {
        UtilIntent.intentResultDIY(this, classes, bundle, requestCode);
    }


    /**
     * 自定义页面跳转
     *
     * @param classes   目标
     * @param map       参数
     * @param enterAnim 进入动画文件ID
     * @param exitAnim  退出动画文件ID
     */
    protected void intent(final Class<?> classes, final Bundle bundle, final int enterAnim, final int exitAnim) {
        UtilIntent.intentDIYLeftToRight(BaseActivity.this, classes, bundle, enterAnim, exitAnim);
    }


    public void finishAnimLeftToRight() {
        UtilIntent.finishDIYLeftToRight(this);
    }


    public void finishAnimBottomToTop() {
        UtilIntent.finishDIYBottomToTop(this);
    }

    @Override
    public void onBackPressed() {
        finishAnimLeftToRight();
    }

    public void onBack(View v) {
        finishAnimLeftToRight();
    }

    /**
     * 左边图片点击事件
     *
     * @param v
     */
    public void leftText(View v) {

    }

    /**
     * 右边图片点击事件
     *
     * @param v
     */
    public void rightImage(View v) {

    }

    /**
     * 右边文字点击事件
     *
     * @param v
     */
    public void rightText(View v) {

    }
}
