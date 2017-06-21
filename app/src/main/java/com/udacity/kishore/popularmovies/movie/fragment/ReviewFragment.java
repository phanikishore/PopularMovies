package com.udacity.kishore.popularmovies.movie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.movie.model.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends BaseFragment {

    private Review mReview;
    @BindView(R.id.textview_review)
    TextView mTextViewReview;
    public static ReviewFragment getInstance(Review review) {
        ReviewFragment fragment = new ReviewFragment();
        fragment.mReview = review;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this,view);
        mTextViewReview.setText(mReview.content);
        return view;
    }

}
