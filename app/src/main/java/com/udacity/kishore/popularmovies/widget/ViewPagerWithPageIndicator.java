package com.udacity.kishore.popularmovies.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.kishore.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kishorea on 19/06/17.
 */

public class ViewPagerWithPageIndicator extends ViewPager implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.recyclerview_dots)
    RecyclerView mRecyclerview;
    private DotRecyclerView mDotRecyclerView;

    public ViewPagerWithPageIndicator(Context context) {
        super(context);
        initView();
    }

    public ViewPagerWithPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_viewpagerwithpageindicator, this);
        ButterKnife.bind(this);
        mDotRecyclerView = new DotRecyclerView();
        mRecyclerview.setAdapter(mDotRecyclerView);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mViewPager.setAdapter(adapter);
        mDotRecyclerView.setDots(adapter.getCount());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        mDotRecyclerView.setIndex(position);
    }

    @Override
    public void onPageSelected(int position) {
        mDotRecyclerView.setIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class DotRecyclerView extends RecyclerView.Adapter<DotRecyclerView.ViewHolder> {

        private int selectedIndex = 0;
        private int count = 0;

        private void setIndex(int index) {
            selectedIndex = index;
            notifyDataSetChanged();
        }

        private void setDots(int count) {
            this.count = count;
            selectedIndex = 0;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflate(parent.getContext(), R.layout.item_dot_indicator, parent));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == selectedIndex) {
                holder.imageView.setImageResource(R.drawable.drawable_dot_active);
            } else {
                holder.imageView.setImageResource(R.drawable.drawable_dot_inactive);
            }
        }

        @Override
        public int getItemCount() {
            return count;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.imageview_dot)
            ImageView imageView;

            private ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
