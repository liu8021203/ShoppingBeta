package com.nan.shopping.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nan.shopping.R;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.util.UtilAnimation;
import com.nan.shopping.util.UtilPixelTransfrom;

/**
 * Created by liu on 15/11/14.
 */
public class TypeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private BaseActivity activity;
    private LinearLayout type_layout;
    private TypeAdaper adaper;
    private ImageView dan_type;
    private ImageView shuican_type;
    private ImageView banchen_type;
    private ImageView children_type;
    private ImageView liangyou_type;
    private ImageView xiuxian_type;
    private ImageView jiushui_type;
    private ImageView sucai_type;


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
        sucai_type = (ImageView) view.findViewById(R.id.sucai_type);
        dan_type = (ImageView) view.findViewById(R.id.dan_type);
        shuican_type = (ImageView) view.findViewById(R.id.shuican_type);
        banchen_type = (ImageView) view.findViewById(R.id.banchen_type);
        children_type = (ImageView) view.findViewById(R.id.children_type);
        liangyou_type = (ImageView) view.findViewById(R.id.liangyou_type);
        xiuxian_type = (ImageView) view.findViewById(R.id.xiuxian_type);
        jiushui_type = (ImageView) view.findViewById(R.id.jiushui_type);

        sucai_type.setOnClickListener(this);
        dan_type.setOnClickListener(this);
        shuican_type.setOnClickListener(this);
        banchen_type.setOnClickListener(this);
        children_type.setOnClickListener(this);
        liangyou_type.setOnClickListener(this);
        xiuxian_type.setOnClickListener(this);
        jiushui_type.setOnClickListener(this);
        setMess();
    }


    /**
     * 加载数据
     */
    private void setMess() {
        adaper = new TypeAdaper();
        LinearLayout.LayoutParams paramsone = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        paramsone.topMargin = UtilPixelTransfrom.px2dip(activity, 20);
        LinearLayout.LayoutParams tempLayoutParams = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        tempLayoutParams.rightMargin = UtilPixelTransfrom.px2dip(activity, 4);
        LinearLayout.LayoutParams paramstwo = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        paramstwo.leftMargin = UtilPixelTransfrom.px2dip(activity, 4);


        for (int i = 0; i < 18 / 2; i++) {
            LinearLayout tempLayouttwo = new LinearLayout(activity);
            tempLayouttwo.setOrientation(LinearLayout.HORIZONTAL);
            tempLayouttwo.addView(adaper.getView(i * 2, null, null), tempLayoutParams);

            tempLayouttwo.addView(adaper.getView(i * 2 + 1, null, null), paramstwo);
            type_layout.addView(tempLayouttwo, paramsone);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sucai_type:
                UtilAnimation.scaleView(sucai_type, R.mipmap.sucai_type);
                break;
        }
    }


    private class TypeAdaper extends BaseAdapter {


        @Override
        public int getCount() {
            return 0;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.type_item, null);
            return convertView;
        }
    }
}
