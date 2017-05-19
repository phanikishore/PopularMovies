package com.udacity.kishore.popularmovies.dashboard.manager;

import com.udacity.kishore.popularmovies.dashboard.manager.operation.DashBoardServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;

/**
 * Created by kishorea on 19/05/17.
 */

public class DashBoardManager {

    public interface DashBoardManagerListener {
        void onSuccess(DashBoardResponse response);

        void onError(PopularMovieException exception);
    }

    public void getDashBoardList(int pageNo, final DashBoardManagerListener listener) {
        new DashBoardServiceOperation(pageNo, new DashBoardServiceOperation.DashBoardServiceListener() {
            @Override
            public void onSuccess(DashBoardResponse response) {
                if (listener != null)
                    listener.onSuccess(response);
            }

            @Override
            public void onError(PopularMovieException exception) {
                if (listener != null)
                    listener.onError(exception);
            }
        });
    }
}
