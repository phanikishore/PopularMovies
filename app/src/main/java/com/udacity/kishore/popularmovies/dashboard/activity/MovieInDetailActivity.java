package com.udacity.kishore.popularmovies.dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.utils.IntentUtils;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

import java.util.Locale;

public class MovieInDetailActivity extends BaseActivity {

    private MovieDetailResponse mMovieDetailResponse;
    private ProgressBar mProgressBar;
    private ScrollView mScrollView;
    private ImageView mImageViewPoster;
    private TextView mTextViewTitle;
    private TextView mTextViewOverview;
    private TextView mTextViewReleasedOn;
    private TextView mTextViewRatings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_in_detail);
        setTitle(getString(R.string.string_movie_details));
        Intent intent = getIntent();
        if (!intent.hasExtra(IntentUtils.INTENT_MOVIE_ID)) {
            return;
        }
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_loading_indicator);
        mScrollView = (ScrollView) findViewById(R.id.scrollview_container);
        mImageViewPoster = (ImageView) findViewById(R.id.imageview_poster);
        mTextViewTitle = (TextView) findViewById(R.id.textview_movie_title);
        mTextViewOverview = (TextView) findViewById(R.id.textview_movie_overview);
        mTextViewReleasedOn = (TextView) findViewById(R.id.textview_movie_release_date);
        mTextViewRatings = (TextView) findViewById(R.id.textview_movie_rating);
        mImageConfig = PopularMoviesPreference.getInstance().getImageConfiguration().imageConfiguration;
        int movieId = intent.getIntExtra(IntentUtils.INTENT_MOVIE_ID, -1);
        new DashBoardManager().getMovieDetails(movieId, new DashBoardManager.OnMovieDetailsListener() {
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
