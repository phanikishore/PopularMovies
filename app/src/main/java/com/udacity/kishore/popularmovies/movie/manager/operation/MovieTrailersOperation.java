package com.udacity.kishore.popularmovies.movie.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;
import com.udacity.kishore.popularmovies.movie.model.TrailerResponse;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by Kishore on 6/20/2017.
 */

public class MovieTrailersOperation extends WebServiceOperation<TrailerResponse> {
    public interface OnMovieVideosOperationListener {
        public void onSuccess(TrailerResponse response);

        public void onError(PopularMovieException execption);
    }

    private OnMovieVideosOperationListener mListener;

    public MovieTrailersOperation(int movieId, OnMovieVideosOperationListener listener) {
        mListener = listener;
        mCall = PopularMoviesApplication.getInstance().getServiceInstance().getMovieTrailers(movieId, AppUtils.API_KEY);
    }

    @Override
    public void enqueue() {
        super.enqueue();
    }

    @Override
    public void onSuccess(TrailerResponse response) {

    }

    @Override
    public void onError(PopularMovieException exception) {

    }
}
