package com.udacity.kishore.popularmovies.movie.manager;

import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.manager.operation.FetchMovieDetailsServiceOperation;

/**
 * Created by kishorea on 19/06/17.
 */

public class MovieDetailsManager {

    public interface OnMovieDetailsListener {
        void onSuccess(MovieDetailResponse response);

        void onError(PopularMovieException exception);
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
