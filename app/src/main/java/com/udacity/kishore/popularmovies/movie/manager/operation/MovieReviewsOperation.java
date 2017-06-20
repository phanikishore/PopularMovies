package com.udacity.kishore.popularmovies.movie.manager.operation;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.manager.WebServiceOperation;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;
import com.udacity.kishore.popularmovies.utils.AppUtils;

/**
 * Created by Kishore on 6/20/2017.
 */

public class MovieReviewsOperation extends WebServiceOperation<ReviewResponse> {
    public interface OnMovieReviewsOperationListener {
        public void onSuccess(ReviewResponse response);

        public void onError(PopularMovieException execption);
    }

    private OnMovieReviewsOperationListener mListener;

    public MovieReviewsOperation(int movieId,OnMovieReviewsOperationListener listener) {
        mListener = listener;
        mCall = PopularMoviesApplication.getInstance().getServiceInstance().getMovieReviews(movieId, AppUtils.API_KEY);
    }

    @Override
    public void enqueue() {
        super.enqueue();
    }

    @Override
    public void onSuccess(ReviewResponse response) {
        if(mListener!=null){
            mListener.onSuccess(response);
        }
    }

    @Override
    public void onError(PopularMovieException exception) {
        if(mListener!=null){
            mListener.onError(exception);
        }
    }
}
