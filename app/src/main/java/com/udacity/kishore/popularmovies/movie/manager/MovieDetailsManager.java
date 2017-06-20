package com.udacity.kishore.popularmovies.movie.manager;

import com.udacity.kishore.popularmovies.movie.manager.operation.MovieReviewsOperation;
import com.udacity.kishore.popularmovies.movie.manager.operation.MovieTrailersOperation;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.manager.operation.FetchMovieDetailsServiceOperation;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;
import com.udacity.kishore.popularmovies.movie.model.TrailerResponse;

/**
 * Created by kishorea on 19/06/17.
 */

public class MovieDetailsManager {

    public interface OnMovieDetailsListener {
        void onSuccess(MovieDetailResponse response);

        void onError(PopularMovieException exception);
    }

    public interface OnMovieReviewsListener {
        void onSuccess(ReviewResponse response);

        void onError(PopularMovieException exception);
    }

    public interface OnMovieTrailersListener {
        void onSuccess(TrailerResponse response);

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
        }).enqueue();
    }

    public void getMovieReviews(int movieId, final OnMovieReviewsListener listener) {
        MovieReviewsOperation operation = new MovieReviewsOperation(movieId, new MovieReviewsOperation.OnMovieReviewsOperationListener() {
            @Override
            public void onSuccess(ReviewResponse response) {
                if (listener != null) {
                    listener.onSuccess(response);
                }
            }

            @Override
            public void onError(PopularMovieException execption) {
                if (listener != null) {
                    listener.onError(execption);
                }
            }
        });
        operation.enqueue();
    }

    public void getMovieTrailers(int movieId, final OnMovieTrailersListener listener) {
        new MovieTrailersOperation(movieId, new MovieTrailersOperation.OnMovieVideosOperationListener() {
            @Override
            public void onSuccess(TrailerResponse response) {
                if (listener != null)
                    listener.onSuccess(response);
            }

            @Override
            public void onError(PopularMovieException execption) {
                if (listener != null)
                    listener.onError(execption);
            }
        }).enqueue();
    }
}
