package com.udacity.kishore.popularmovies;


import android.os.Bundle;

import com.google.gson.Gson;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.fragment.DashBoardFragment;

public class PopularMovieActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replace(R.id.layout_container, DashBoardFragment.newInstance());
    }


}
