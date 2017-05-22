package com.udacity.kishore.popularmovies.dashboard.manager.operation;

import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;

/**
 * Created by kishorea on 22/05/17.
 */

public abstract class FetchMoviesServiceOperation extends WebServiceOperation<DashBoardResponse> {

    public FetchMoviesServiceOperation(FetchMoviesServiceListener listener) {
        this.mListener = listener;
    }

    public interface FetchMoviesServiceListener {
        void onSuccess(DashBoardResponse response);

        void onError(PopularMovieException exception);
    }

    private FetchMoviesServiceListener mListener;

    @Override
    public void onSuccess(DashBoardResponse response) {
        if (mListener != null)
            mListener.onSuccess(response);
    }

    @Override
    public void onError(PopularMovieException exception) {
        if (mListener != null)
            mListener.onError(exception);
    }
}
