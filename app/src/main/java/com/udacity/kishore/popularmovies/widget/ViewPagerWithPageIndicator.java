package com.udacity.kishore.popularmovies.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
    private DotRecyclerView mDotRecyclerViewAdapter;

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
        System.out.println("Custom View is Creating");
        mDotRecyclerViewAdapter = new DotRecyclerView();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerview.setAdapter(mDotRecyclerViewAdapter);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mViewPager.setAdapter(adapter);
        //mDotRecyclerViewAdapter.setDots(adapter.getCount());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        mDotRecyclerViewAdapter.setIndex(position);
    }

    @Override
    public void onPageSelected(int position) {
        mDotRecyclerViewAdapter.setIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

     class DotRecyclerView extends RecyclerView.Adapter<DotRecyclerView.ViewHolder> {

        private int selectedIndex = 0;
        private int count = 3;

        private void setIndex(int index) {
            selectedIndex = index;
            notifyDataSetChanged();
        }

        private void setDots(int count) {
            System.out.println("Count: "+count);
            this.count = count;
            selectedIndex = 0;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            System.out.println("Creating ViewHolder Object");
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dot_indicator,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            System.out.println("Recyclerview Count: "+count+" Position: "+position);
            if (position == selectedIndex) {
                holder.imageView.setImageResource(R.drawable.drawable_dot_active);
            } else {
                holder.imageView.setImageResource(R.drawable.drawable_dot_inactive);
            }
        }

        @Override
        public int getItemCount() {
            System.out.println("Recyclerview Count: "+count);
            return count;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.imageview_dot)
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
