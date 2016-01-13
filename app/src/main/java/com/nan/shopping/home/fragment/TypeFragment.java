package com.nan.shopping.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.nan.shopping.R;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.util.UtilPixelTransfrom;

/**
 * Created by liu on 15/11/14.
 */
public class TypeFragment extends Fragment {

    private View view;
    private BaseActivity activity;
    private LinearLayout type_layout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_type, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (BaseActivity) this.getActivity();
        initView();
    }

    private void initView() {
        type_layout = (LinearLayout) view.findViewById(R.id.type_layout);
        setMess();
    }


    /**
     * 加载数据
     */
    private void setMess() {

        LinearLayout.LayoutParams paramsone = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        paramsone.topMargin = UtilPixelTransfrom.px2dip(activity, 20);
        for (int i = 0; i < 6 / 2; i++) {
            LinearLayout tempLayouttwo = new LinearLayout(activity);
            tempLayouttwo.setOrientation(LinearLayout.HORIZONTAL);
            tempLayouttwo.addView(adapter.getView(i * 3, null, null), tempLayoutParams);
            tempLayouttwo.addView(adapter.getView(i * 3 + 1, null, null), tempLayoutParams);
            tempLayouttwo.addView(adapter.getView(i * 3 + 2, null, null), tempLayoutParams);
            hot_rank_layout.addView(tempLayouttwo, paramsone);
        }


    }


    private class TypeAdaper extends BaseAdapter {

    }
}
