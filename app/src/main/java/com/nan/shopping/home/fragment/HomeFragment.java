package com.nan.shopping.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nan.shopping.R;
import com.nan.shopping.TestActivity;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.view.ObservableScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by liu on 15/11/14.
 */
public class HomeFragment extends Fragment implements ObservableScrollView.Callbacks {
    @Bind(R.id.rl_ranking_one)
    RelativeLayout rlRankingOne;
    @Bind(R.id.rl_vfood_one)
    RelativeLayout rlVfoodOne;
    @Bind(R.id.rl_semi_one)
    RelativeLayout rlSemiOne;
    @Bind(R.id.rl_vip_one)
    RelativeLayout rlVipOne;
    @Bind(R.id.ll_tab_one)
    LinearLayout llTabOne;
    @Bind(R.id.rl_ranking_two)
    RelativeLayout rlRankingTwo;
    @Bind(R.id.rl_vfood_two)
    RelativeLayout rlVfoodTwo;
    @Bind(R.id.rl_semi_two)
    RelativeLayout rlSemiTwo;
    @Bind(R.id.rl_vip_two)
    RelativeLayout rlVipTwo;
    @Bind(R.id.ll_tab_two)
    LinearLayout llTabTwo;
    @Bind(R.id.scroll)
    ObservableScrollView scroll;
    @Bind(R.id.ptr_fragment)
    PtrClassicFrameLayout ptrFragment;

    private BaseActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        activity = (BaseActivity) getActivity();
    }

    private void initView() {
        scroll.setCallbacks(this);
        scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScrollChanged(scroll.getScrollY());
            }
        });
        ptrFragment.setLastUpdateTimeRelateObject(this);
//        ptrFragment.setPullToRefresh(false);
        ptrFragment.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, scroll, header);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }


    @Override
    public void onScrollChanged(int scrollY) {
        llTabTwo.setTranslationY(Math.max(llTabOne.getTop(), scrollY));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.rl_ranking_one, R.id.rl_ranking_two})
    public void clickRanking(View view)
    {
        activity.intentLeftToRight(TestActivity.class);
    }

}
