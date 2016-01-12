package com.nan.shopping.welcome;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nan.shopping.R;
import com.nan.shopping.base.BaseActivity;
import com.nan.shopping.home.HomeActivtiy;
import com.nan.shopping.util.UtilSPutil;
import com.nan.shopping.util.UtilSystem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by liu on 15/11/6.
 */
public class GuiActivity extends BaseActivity {


    @Bind(R.id.gui_pager)
    ViewPager guiPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        GuiPagerAdapter adapter = new GuiPagerAdapter();
        guiPager.setAdapter(adapter);
        indicator.setViewPager(guiPager);
        UtilSPutil.getInstance().setInt("code", UtilSystem.getVersionCode());
    }


    private class GuiPagerAdapter extends PagerAdapter {

        private int[] imgs = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        private List<ImageView> views = new ArrayList<ImageView>();

        public GuiPagerAdapter() {
            for (int i = 0; i < imgs.length; i++) {
                ImageView view = new ImageView(GuiActivity.this);
                view.setImageResource(imgs[i]);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                if (i == 3) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intentLeftToRight(HomeActivtiy.class);
                            finish();
                        }
                    });
                }
                views.add(view);
            }
        }

        @Override
        public int getCount() {
            return imgs == null ? 0 : imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }
}
