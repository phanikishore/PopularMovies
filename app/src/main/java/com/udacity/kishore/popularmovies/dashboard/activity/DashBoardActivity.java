package com.udacity.kishore.popularmovies.dashboard.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.adapter.MoviesRecyclerViewAdapter;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

public class DashBoardActivity extends BaseActivity implements DashBoardManager.DashBoardManagerListener {

    private int PAGE_NO = 1;

    private ProgressBar mLoadingIndicator;

    private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressbar_loading_indicator);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_movieslist);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mMoviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter();
        recyclerView.setAdapter(mMoviesRecyclerViewAdapter);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        loadData(PopularMoviesPreference.getInstance().getSortBy());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort_by, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PopularMoviesPreference.getInstance().setSortBy(item.getTitle().toString());
        loadData(item.getTitle().toString());
        return true;
    }

    private void loadData(String sortBy) {
        if(sortBy.equals(getString(R.string.string_popular))){
            new DashBoardManager().getPopularMovies(PAGE_NO, DashBoardActivity.this);
        }else if(sortBy.equals(getString(R.string.string_top_rated))){
            new DashBoardManager().getTopRatedMovies(PAGE_NO, DashBoardActivity.this);
        }
    }

    @Override
    public void onSuccess(DashBoardResponse response) {
        mLoadingIndicator.setVisibility(View.GONE);
        mMoviesRecyclerViewAdapter.addData(response.moviesList);
    }

    @Override
    public void onError(PopularMovieException exception) {
        mLoadingIndicator.setVisibility(View.GONE);
    }
}
