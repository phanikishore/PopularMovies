package com.udacity.kishore.popularmovies.movie.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by kishorea on 23/05/17.
 */

public class FetchMovieDetailsServiceOperation extends WebServiceOperation<MovieDetailResponse> {

    public interface OnMovieDetailsServiceListener {
        void onSuccess(MovieDetailResponse response);

        void onError(PopularMovieException exception);
    }

    private OnMovieDetailsServiceListener listener;

    public FetchMovieDetailsServiceOperation(int movieId, OnMovieDetailsServiceListener listener) {
        this.listener = listener;
        mCall = PopularMoviesApplication.getInstance().getServiceInstance().getMovieDetails(movieId, AppUtils.API_KEY);
        enqueue();
    }

    @Override
    public void onSuccess(MovieDetailResponse response) {
        if (listener != null)
            listener.onSuccess(response);
    }

    @Override
    public void onError(PopularMovieException exception) {
        if (listener != null)
            listener.onError(exception);
    }
}
