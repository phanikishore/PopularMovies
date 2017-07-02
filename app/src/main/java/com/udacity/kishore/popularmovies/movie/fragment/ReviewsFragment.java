package com.udacity.kishore.popularmovies.movie.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.udacity.kishore.popularmovies.movie.adapter.ReviewsAdapter;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;

import java.util.Locale;

/**
 * Created by Kishore on 6/20/2017.
 */

public class ReviewsFragment extends BaseMovieFragment {

    private ReviewResponse mReviewResponse;

    public static ReviewsFragment newInstance(ReviewResponse response) {
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.mReviewResponse = response;
        return fragment;
    }

    @Override
    public void setAdapter() {
        showToolbar();
        setTitle("Reviews");
        setSubtitle(String.format(Locale.getDefault(), "%d Reviews", mReviewResponse.reviewsList.size()));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ReviewsAdapter adapter = new ReviewsAdapter(mReviewResponse.reviewsList);
        mRecyclerview.setAdapter(adapter);
    }
}
