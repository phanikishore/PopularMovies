package com.udacity.kishore.popularmovies.movie.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.adapter.TrailerAdapter;
import com.udacity.kishore.popularmovies.movie.manager.MovieDetailsManager;
import com.udacity.kishore.popularmovies.movie.model.TrailerResponse;

/**
 * Created by kishorea on 21/06/17.
 */

public class TrailersFragment extends BaseMovieFragment {
    private int mSelectedMovieId;

    public static TrailersFragment newInstance(int movieId) {
        TrailersFragment fragment = new TrailersFragment();
        fragment.mSelectedMovieId = movieId;
        return fragment;
    }

    @Override
    public void setAdapter() {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        final TrailerAdapter trailerAdapter = new TrailerAdapter();
        mRecyclerview.setAdapter(trailerAdapter);
        new MovieDetailsManager().getMovieTrailers(mSelectedMovieId, new MovieDetailsManager.OnMovieTrailersListener() {
            @Override
            public void onSuccess(TrailerResponse response) {
                if (isAdded()) {
                    trailerAdapter.setData(response.trailerList);
                }
            }

            @Override
            public void onError(PopularMovieException exception) {

            }
        });
    }

}
