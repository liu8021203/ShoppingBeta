package com.nan.shopping.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nan.shopping.R;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.home.fragment.FoodFragment;
import com.nan.shopping.home.fragment.HomeFragment;
import com.nan.shopping.home.fragment.MineFragment;
import com.nan.shopping.home.fragment.TypeFragment;
import com.nan.shopping.util.UtilAnimation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liu on 15/11/13.
 */
public class HomeActivtiy extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_food)
    TextView tvFood;
    @Bind(R.id.tv_mine)
    TextView tvMine;
    @Bind(R.id.iv_v)
    ImageView ivV;
    @Bind(R.id.iv_type)
    ImageView ivType;
    @Bind(R.id.rl_type)
    RelativeLayout rlType;
    @Bind(R.id.iv_food)
    ImageView ivFood;
    @Bind(R.id.rl_food)
    RelativeLayout rlFood;
    @Bind(R.id.iv_mine)
    ImageView ivMine;
    @Bind(R.id.rl_mine)
    RelativeLayout rlMine;
    @Bind(R.id.rl_home)
    RelativeLayout rlHome;
    @Bind(R.id.iv_home)
    ImageView ivHome;

    private HomeFragment homeFragment;
    private TypeFragment typeFragment;
    private FoodFragment foodFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isHome = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        init();
        setTabSelection(0);
    }

    private void init() {
        rlHome.setOnClickListener(this);
        rlType.setOnClickListener(this);
        rlFood.setOnClickListener(this);
        rlMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_v:

                break;

            case R.id.rl_home:
                clickfailure(0);
                setTabSelection(0);
                break;

            case R.id.rl_type:
                clickfailure(1);
                setTabSelection(1);
                break;
            case R.id.rl_food:
                clickfailure(2);
                setTabSelection(2);
                break;

            case R.id.rl_mine:
                clickfailure(3);
                setTabSelection(3);
                break;
        }
    }


    /**
     * 将所有的Fragment设置为隐藏状态
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (typeFragment != null) {
            transaction.hide(typeFragment);
        }
        if (foodFragment != null) {
            transaction.hide(foodFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    /**
     * 点击失效
     *
     * @param index
     */
    private void clickfailure(int index) {
        switch (index) {
            case 0:
                rlHome.setEnabled(false);
                rlType.setEnabled(true);
                rlFood.setEnabled(true);
                rlMine.setEnabled(true);
                break;
            case 1:
                rlHome.setEnabled(true);
                rlType.setEnabled(false);
                rlFood.setEnabled(true);
                rlMine.setEnabled(true);
                break;
            case 2:
                rlHome.setEnabled(true);
                rlType.setEnabled(true);
                rlFood.setEnabled(false);
                rlMine.setEnabled(true);
                break;
            case 3:
                rlHome.setEnabled(true);
                rlType.setEnabled(true);
                rlFood.setEnabled(true);
                rlMine.setEnabled(false);
                break;
            default:
                break;
        }
    }

    /**
     * 清楚所有状态
     */
    private void clearState() {
        tvHome.setTextColor(0xff898989);
        ivHome.setImageResource(R.mipmap.home_unselect);
        tvType.setTextColor(0xff898989);
        ivType.setImageResource(R.mipmap.type_unselect);
        tvFood.setTextColor(0xff898989);
        ivFood.setImageResource(R.mipmap.food_unselect);
        tvMine.setTextColor(0xff898989);
        ivMine.setImageResource(R.mipmap.mine_unselect);
    }


    private void setTabSelection(int index) {
        //开启一个Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        clearState();
        hideFragments(transaction);
        switch (index) {
            case 0:
//                ivHome.setImageResource(R.mipmap.home_select);
                UtilAnimation.scaleView(ivHome, R.mipmap.home_select);
                tvHome.setTextColor(0xffffffff);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.activity_layout, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;

            case 1:
//                ivType.setImageResource(R.mipmap.type_select);
                UtilAnimation.scaleView(ivType, R.mipmap.type_select);
                tvType.setTextColor(0xffffffff);
                if (typeFragment == null) {
                    typeFragment = new TypeFragment();
                    transaction.add(R.id.activity_layout, typeFragment);
                } else {
                    transaction.show(typeFragment);
                }
                break;
            case 2:
//                ivFood.setImageResource(R.mipmap.food_select);
                UtilAnimation.scaleView(ivFood, R.mipmap.food_select);
                tvFood.setTextColor(0xffffffff);
                if (foodFragment == null) {
                    foodFragment = new FoodFragment();
                    transaction.add(R.id.activity_layout, foodFragment);
                } else {
                    transaction.show(foodFragment);
                }
                break;
            case 3:
//                ivMine.setImageResource(R.mipmap.mine_select);
                tvMine.setTextColor(0xffffffff);
                UtilAnimation.scaleView(ivMine, R.mipmap.mine_select);
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.activity_layout, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;


            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!isExit) {
            showToast("再点击一次返回键退出应用");
            isExit = true;
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
