package com.udacity.kishore.popularmovies.dashboard.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by kishorea on 22/05/17.
 */

public class TopRatedMoviesServiceOperation extends FetchMoviesServiceOperation {

    public TopRatedMoviesServiceOperation(int pageNo, FetchMoviesServiceListener listener) {
        super(listener);
        mCall = PopularMoviesApplication.getInstance()
                .getServiceInstance().getTopRatedMovies(AppUtils.API_KEY, pageNo);
        enqueue();
    }
}
