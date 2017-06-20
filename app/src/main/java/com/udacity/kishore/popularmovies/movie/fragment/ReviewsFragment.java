package com.udacity.kishore.popularmovies.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.movie.adapter.ReviewsAdapter;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 6/20/2017.
 */

public class ReviewsFragment extends BaseFragment {
    private ReviewResponse mReviewResponse;
    @BindView(R.id.rv_reviews)
    RecyclerView mRecyclerView;


    public static ReviewsFragment newInstance(ReviewResponse response) {
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.mReviewResponse = response;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);
        setTitle("Reviews");
        setSubtitle(String.format(Locale.getDefault(), "%d Reviews", mReviewResponse.reviewsList.size()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ReviewsAdapter adapter = new ReviewsAdapter(mReviewResponse.reviewsList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
