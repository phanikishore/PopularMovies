package com.udacity.kishore.popularmovies.dashboard.manager;

import com.udacity.kishore.popularmovies.dashboard.manager.operation.FetchMovieDetailsServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.manager.operation.FetchMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.manager.operation.PopularMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.manager.operation.TopRatedMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.dashboard.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;

/**
 * Created by kishorea on 19/05/17.
 */

public class DashBoardManager {

    public interface DashBoardManagerListener {
        void onSuccess(DashBoardResponse response);

        void onError(PopularMovieException exception);
    }

    public interface OnMovieDetailsListener {
        void onSuccess(MovieDetailResponse response);

        void onError(PopularMovieException exception);
    }

    public void getPopularMovies(int pageNo, final DashBoardManagerListener listener) {
        new PopularMoviesServiceOperation(pageNo, new FetchMoviesServiceOperation.FetchMoviesServiceListener() {
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

    public void getTopRatedMovies(int pageNo, final DashBoardManagerListener listener) {
        new TopRatedMoviesServiceOperation(pageNo, new FetchMoviesServiceOperation.FetchMoviesServiceListener() {
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

    public void getMovieDetails(int movieId, final OnMovieDetailsListener listener) {
        new FetchMovieDetailsServiceOperation(movieId, new FetchMovieDetailsServiceOperation.OnMovieDetailsServiceListener() {
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
        });
    }
}
