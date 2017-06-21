package com.udacity.kishore.popularmovies.movie.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;
import com.udacity.kishore.popularmovies.movie.model.ImageMediaResponse;

/**
 * Created by Kishore on 6/20/2017.
 */

public class MoviePostersOperation extends WebServiceOperation<ImageMediaResponse> {
    public interface OnMoviePostersOperationListener {
        public void onSuccess(ImageMediaResponse response);

        public void onError(PopularMovieException execption);
    }

    private OnMoviePostersOperationListener mListener;

    public MoviePostersOperation(int movieId,OnMoviePostersOperationListener listener) {
        mListener = listener;
        //mCall = PopularMoviesApplication.getInstance().getServiceInstance()
    }

    @Override
    public void enqueue() {
        super.enqueue();
    }

    @Override
    public void onSuccess(ImageMediaResponse response) {

    }

    @Override
    public void onError(PopularMovieException exception) {

    }
}
