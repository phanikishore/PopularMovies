package com.udacity.kishore.popularmovies.dashboard.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by kishorea on 19/05/17.
 */

public class PopularMoviesServiceOperation extends FetchMoviesServiceOperation {

    public PopularMoviesServiceOperation(int pageNo, FetchMoviesServiceListener listener) {
        super(listener);
        mCall = PopularMoviesApplication.getInstance().getServiceInstance()
                .getPopularMovies(AppUtils.API_KEY, pageNo);
        enqueue();
    }


}
