package com.udacity.kishore.popularmovies.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.manager.MovieDetailsManager;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.utils.IntentUtils;
import com.udacity.kishore.popularmovies.widget.ViewPagerWithPageIndicator;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity {

    private MovieDetailResponse mMovieDetailResponse;
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
    ViewPagerWithPageIndicator mTextViewReviews;
    @BindView(R.id.recyclerview_movie_trailer)
    RecyclerView mTrailerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_in_detail);
        ButterKnife.bind(this);
        setTitle(getString(R.string.string_movie_details));
        Intent intent = getIntent();
        if (!intent.hasExtra(IntentUtils.INTENT_MOVIE_ID)) {
            return;
        }
        int movieId = intent.getIntExtra(IntentUtils.INTENT_MOVIE_ID, -1);
        new MovieDetailsManager().getMovieDetails(movieId, new MovieDetailsManager.OnMovieDetailsListener() {
            @Override
            public void onSuccess(MovieDetailResponse response) {
                mProgressBar.setVisibility(View.GONE);
                mScrollView.setVisibility(View.VISIBLE);
                mMovieDetailResponse = response;
                loadImage(TextUtils.join("", new String[]{
                        mImageConfig.baseUrl,
                        mImageConfig.posterSizeList.get(5),
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


    }
}
