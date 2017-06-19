package com.udacity.kishore.popularmovies.dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.adapter.MoviesRecyclerViewAdapter;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.dashboard.model.MovieItem;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.activity.MovieDetailsActivity;
import com.udacity.kishore.popularmovies.utils.IntentUtils;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends BaseActivity implements DashBoardManager.DashBoardManagerListener {

    private int PAGE_NO = 1;


    private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;
    @BindView(R.id.rv_movieslist) RecyclerView mRecyclerView;
    @BindView(R.id.progressbar_loading_indicator) ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        mMoviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(new MoviesRecyclerViewAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClicked(MovieItem movie) {
                Intent intent = new Intent(DashBoardActivity.this, MovieDetailsActivity.class);
                intent.putExtra(IntentUtils.INTENT_MOVIE_ID, movie.id);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mMoviesRecyclerViewAdapter);
        setSubtitle(PopularMoviesPreference.getInstance().getSortBy());
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort_by, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        PopularMoviesPreference.getInstance().setSortBy(item.getTitle().toString());
        setSubtitle(item.getTitle().toString());
        loadData();
        return true;
    }

    private void loadData() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        String sortBy = PopularMoviesPreference.getInstance().getSortBy();
        if (sortBy.equals(getString(R.string.string_popular))) {
            new DashBoardManager().getPopularMovies(PAGE_NO, DashBoardActivity.this);
        } else if (sortBy.equals(getString(R.string.string_top_rated))) {
            new DashBoardManager().getTopRatedMovies(PAGE_NO, DashBoardActivity.this);
        }
    }

    @Override
    public void onSuccess(DashBoardResponse response) {
        mLoadingIndicator.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mMoviesRecyclerViewAdapter.addData(response.moviesList);
    }

    @Override
    public void onError(PopularMovieException exception) {
        mLoadingIndicator.setVisibility(View.GONE);
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
