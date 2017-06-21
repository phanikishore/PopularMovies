package com.udacity.kishore.popularmovies.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.movie.fragment.ReviewFragment;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kishorea on 19/06/17.
 */

public class ReviewViewPagerAdapter extends FragmentPagerAdapter {

    private Map<Integer, BaseFragment> map = new HashMap<>();
    private ReviewResponse mReviewResponse;

    public ReviewViewPagerAdapter(FragmentManager fm,ReviewResponse response) {
        super(fm);
        mReviewResponse = response;
    }

    @Override
    public BaseFragment getItem(int position) {
        BaseFragment fragment ;//= map.get(position);
        //System.out.println("Is NULL at pos: "+position+" ? "+(fragment==null));
       // if (fragment == null) {
            fragment = ReviewFragment.getInstance(mReviewResponse.reviewsList.get(position));
        //    map.put(position, fragment);
        //}
        return fragment;
    }

    @Override
    public int getCount() {
//        System.out.println("Hello: "+mReviewResponse.reviewsList.size());
        return  mReviewResponse.reviewsList.size() > 2 ? 2 : mReviewResponse.reviewsList.size() ;
    }
}
