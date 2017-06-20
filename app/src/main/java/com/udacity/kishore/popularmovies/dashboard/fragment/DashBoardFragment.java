package com.udacity.kishore.popularmovies.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.dashboard.adapter.MoviesRecyclerViewAdapter;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.dashboard.model.MovieItem;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.fragment.MovieDetailsFragment;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 6/21/2017.
 */

public class DashBoardFragment extends BaseFragment implements DashBoardManager.DashBoardManagerListener {

    private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;
    @BindView(R.id.rv_movieslist)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressbar_loading_indicator)
    ProgressBar mLoadingIndicator;

    public static DashBoardFragment newInstance() {
        return new DashBoardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this, view);
        setTitle(R.string.app_name);
        mMoviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(new MoviesRecyclerViewAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClicked(MovieItem movie) {
                replace(R.id.layout_container, MovieDetailsFragment.newInstance(movie.id));
            }
        });
        mRecyclerView.setAdapter(mMoviesRecyclerViewAdapter);
        setSubtitle(PopularMoviesPreference.getInstance().getSortBy());
        loadData();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort_by, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        PopularMoviesPreference.getInstance().setSortBy(item.getTitle().toString());
        setSubtitle(item.getTitle().toString());
        loadData();
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        String sortBy = PopularMoviesPreference.getInstance().getSortBy();
        int PAGE_NO = 1;
        if (sortBy.equals(getString(R.string.string_popular))) {
            new DashBoardManager().getPopularMovies(PAGE_NO, this);
        } else if (sortBy.equals(getString(R.string.string_top_rated))) {
            new DashBoardManager().getTopRatedMovies(PAGE_NO, this);
        }
    }

    @Override
    public void onSuccess(DashBoardResponse response) {
        if (isAdded()) {
            mLoadingIndicator.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMoviesRecyclerViewAdapter.addData(response.moviesList);
        }
    }

    @Override
    public void onError(PopularMovieException exception) {
        if (isAdded()) {
            mLoadingIndicator.setVisibility(View.GONE);
            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
