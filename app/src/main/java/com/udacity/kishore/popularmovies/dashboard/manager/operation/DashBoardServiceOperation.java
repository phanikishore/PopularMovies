package com.udacity.kishore.popularmovies.dashboard.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by kishorea on 19/05/17.
 */

public class DashBoardServiceOperation extends WebServiceOperation<DashBoardResponse> {

    public interface DashBoardServiceListener {
        void onSuccess(DashBoardResponse response);

        void onError(PopularMovieException exception);
    }

    private DashBoardServiceListener mListener;

    public DashBoardServiceOperation(int pageNo, DashBoardServiceListener listener) {
        mListener = listener;
        mCall = PopularMoviesApplication.getInstance().getServiceInstance()
                .getDashBoardResponse(AppUtils.API_KEY, pageNo);
        enqueue();
    }

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
