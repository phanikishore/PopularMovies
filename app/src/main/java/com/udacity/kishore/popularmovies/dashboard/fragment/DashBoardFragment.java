package com.udacity.kishore.popularmovies.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.udacity.kishore.popularmovies.utils.IntentUtils;

import java.util.List;

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
    private String mCategoryType;

    public static DashBoardFragment newInstance() {
        return new DashBoardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this, view);
        System.out.println("onCreateView, savedInstanceState is NULL? " + (savedInstanceState == null));
        setTitle(R.string.app_name);
        mMoviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(new MoviesRecyclerViewAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClicked(MovieItem movie) {
                replace(R.id.layout_container, MovieDetailsFragment.newInstance(movie.id));
            }
        });
        mRecyclerView.setAdapter(mMoviesRecyclerViewAdapter);
        mCategoryType = getString(R.string.string_popular);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        System.out.println(DashBoardFragment.class.getSimpleName() + " onSaveInstanceState Method called");
        outState.putString(IntentUtils.INTENT_MOVIE_TYPE, mCategoryType);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println(DashBoardFragment.class.getSimpleName() + " onActivityCreated Method called.");
        System.out.println("savedInstanceState is NULL? " + (savedInstanceState == null));
        if (savedInstanceState != null) {
            mCategoryType = savedInstanceState.getString(IntentUtils.INTENT_MOVIE_TYPE);
        }
        setSubtitle(mCategoryType);
        loadData(mCategoryType);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort_by, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        setSubtitle(item.getTitle().toString());
        loadData(item.getTitle().toString());
        return super.onOptionsItemSelected(item);
    }

    private void loadData(String sortBy) {
        mCategoryType = sortBy;
        mLoadingIndicator.setVisibility(View.VISIBLE);
        //showLoadingIndicator();
        mRecyclerView.setVisibility(View.INVISIBLE);
        //String sortBy = PopularMoviesPreference.getInstance().getSortBy();
        int PAGE_NO = 1;
        if (sortBy.equals(getString(R.string.string_popular))) {
            new DashBoardManager().getPopularMovies(PAGE_NO, this);
        } else if (sortBy.equals(getString(R.string.string_top_rated))) {
            new DashBoardManager().getTopRatedMovies(PAGE_NO, this);
        } else if (sortBy.equals(getString(R.string.string_favorite))) {
            new DashBoardManager().getMyFavoriteMovies(getActivity(), this);
        }
    }

    private void markFavoriteItems(List<MovieItem> moviesList) {
        DashBoardManager manager = new DashBoardManager();
        for (MovieItem item : moviesList) {
            item.isFavorite = manager.isMovieListed(getActivity(), item.id);
        }
    }


    @Override
    public void onSuccess(DashBoardResponse response) {
        if (isAdded()) {
            mLoadingIndicator.setVisibility(View.GONE);
            //hideProgressBar();
            mRecyclerView.setVisibility(View.VISIBLE);
            markFavoriteItems(response.moviesList);
            mMoviesRecyclerViewAdapter.addData(response.moviesList);
        }
    }

    @Override
    public void onError(PopularMovieException exception) {
        if (isAdded()) {
            mLoadingIndicator.setVisibility(View.GONE);
            //hideProgressBar();
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
