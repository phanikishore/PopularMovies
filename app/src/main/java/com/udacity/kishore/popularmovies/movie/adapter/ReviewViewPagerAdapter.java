package com.udacity.kishore.popularmovies.movie.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by kishorea on 19/06/17.
 */

public class ReviewViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
