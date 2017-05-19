package com.udacity.kishore.popularmovies.dashboard.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.dashboard.adapter.MoviesRecyclerViewAdapter;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;

public class DashBoardActivity extends AppCompatActivity implements DashBoardManager.DashBoardManagerListener {

    private int PAGE_NO = 1;

    private ProgressBar mLoadingIndicator;

    private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressbar_loading_indicator);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_movieslist);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        new DashBoardManager().getDashBoardList(PAGE_NO,DashBoardActivity.this);
    }

    @Override
    public void onSuccess(DashBoardResponse response) {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onError(PopularMovieException exception) {
        mLoadingIndicator.setVisibility(View.GONE);
    }
}
