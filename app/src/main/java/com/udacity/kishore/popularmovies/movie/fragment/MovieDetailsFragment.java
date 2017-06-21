package com.udacity.kishore.popularmovies.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.adapter.ReviewViewPagerAdapter;
import com.udacity.kishore.popularmovies.movie.manager.MovieDetailsManager;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;
import com.udacity.kishore.popularmovies.widget.ViewPagerWithPageIndicator;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 6/21/2017.
 */

public class MovieDetailsFragment extends BaseFragment {

    private int mSelectedMovieId;
    private MovieDetailResponse mMovieDetailResponse;
    private ReviewResponse mReviewResponse;
    @BindView(R.id.imagebutton_favorite)
    ImageButton mImageButtonFavorite;
    @BindView(R.id.progressbar_loading_indicator)
    ProgressBar mProgressBar;
    @BindView(R.id.scrollview_container)
    ScrollView mScrollView;
    @BindView(R.id.imageview_poster)
    ImageView mImageViewPoster;
    @BindView(R.id.textview_movie_title)
    TextView mTextViewTitle;
    @BindView(R.id.textview_movie_overview)
    TextView mTextViewOverview;
    @BindView(R.id.textview_movie_release_date)
    TextView mTextViewReleasedOn;
    @BindView(R.id.textview_movie_rating)
    TextView mTextViewRatings;
    @BindView(R.id.textview_reviews_viewmore)
    TextView mTextViewMoreReviews;
    @BindView(R.id.viewpager_reviews)
    ViewPagerWithPageIndicator mViewPager;

    private ReviewViewPagerAdapter mReviewViewPagerAdapter;

    public static MovieDetailsFragment newInstance(int movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.mSelectedMovieId = movieId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_in_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(this, view);
        mImageConfig = PopularMoviesPreference.getInstance().getImageConfiguration().imageConfiguration;
        setTitle(R.string.string_movie_details);
        setSubtitle("");
        mTextViewMoreReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(R.id.layout_container, ReviewsFragment.newInstance(mReviewResponse));
            }
        });
        replaceChildFragment(R.id.framelayout_movie_trailer, TrailersFragment.newInstance(mSelectedMovieId));
        mImageButtonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageButtonFavorite.setImageResource(R.drawable.ic_favorite_active);
            }
        });
        MovieDetailsManager mMovieManager = new MovieDetailsManager();
        mMovieManager.getMovieDetails(mSelectedMovieId, new MovieDetailsManager.OnMovieDetailsListener() {
            @Override
            public void onSuccess(MovieDetailResponse response) {
                mProgressBar.setVisibility(View.GONE);
                mScrollView.setVisibility(View.VISIBLE);
                mMovieDetailResponse = response;
                loadImage(TextUtils.join("", new String[]{
                        mImageConfig.baseUrl,
                        mImageConfig.posterSizeList.get(3),
                        mMovieDetailResponse.posterPath}), mImageViewPoster);
                mTextViewTitle.setText(mMovieDetailResponse.originalTitle);
                mTextViewOverview.setText(mMovieDetailResponse.overview);
                mTextViewReleasedOn.setText(String.format(Locale.getDefault(), "Released on %s", mMovieDetailResponse.releaseDate));
                mTextViewRatings.setText(String.format(Locale.getDefault(), "Rating: %1$,.1f", mMovieDetailResponse.voteAverage));
            }

            @Override
            public void onError(PopularMovieException exception) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
        mMovieManager.getMovieReviews(mSelectedMovieId, new MovieDetailsManager.OnMovieReviewsListener() {
            @Override
            public void onSuccess(ReviewResponse response) {
                mReviewResponse = response;
                mReviewViewPagerAdapter = new ReviewViewPagerAdapter(((BaseActivity) getActivity()).getSupportFragmentManager(), response);
                mViewPager.setAdapter(mReviewViewPagerAdapter);
                if (response.reviewsList.size() > 0) {
                    mTextViewMoreReviews.setText(String.format(Locale.getDefault(), "See All(%d)", response.reviewsList.size()));
                } else {
                    mTextViewMoreReviews.setText("No Reviews");
                }
            }

            @Override
            public void onError(PopularMovieException exception) {

            }
        });
    }
}
