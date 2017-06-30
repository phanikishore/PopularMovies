package com.udacity.kishore.popularmovies.movie.fragment;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseFragment;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.database.FavoriteMovieContract;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.movie.adapter.ReviewViewPagerAdapter;
import com.udacity.kishore.popularmovies.movie.manager.MovieDetailsManager;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;
import com.udacity.kishore.popularmovies.utils.IntentUtils;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;
import com.udacity.kishore.popularmovies.widget.ViewPagerWithPageIndicator;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * Created by Kishore on 6/21/2017.
 */

public class MovieDetailsFragment extends BaseFragment {

    private int mSelectedMovieId;
    private MovieDetailResponse mMovieDetailResponse;
    private ReviewResponse mReviewResponse;
    @BindView(R.id.fab)
    FloatingActionButton mFABFavorite;
    @BindView(R.id.imageview_poster)
    ImageView mImageViewPoster;
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
    private Toast mToast;

    public static MovieDetailsFragment newInstance(int movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.mSelectedMovieId = movieId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_info, container, false);
    }

    @Optional
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setToolbar((Toolbar) view.findViewById(R.id.toolbar));
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageConfig = PopularMoviesPreference.getInstance().getImageConfiguration().imageConfiguration;
        mSelectedMovieId = savedInstanceState != null ? savedInstanceState.getInt(IntentUtils.INTENT_MOVIE_ID) : mSelectedMovieId;
        mTextViewMoreReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(R.id.layout_container, ReviewsFragment.newInstance(mReviewResponse));
            }
        });
        replaceChildFragment(R.id.framelayout_movie_trailer, TrailersFragment.newInstance(mSelectedMovieId));
        mFABFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mToast != null) {
                    mToast.cancel();
                }
                if (!new DashBoardManager().isMovieListed(getActivity(), mMovieDetailResponse.id)) {
                    ContentValues values = new ContentValues();
                    values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID, mMovieDetailResponse.id);
                    values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_NAME, mMovieDetailResponse.originalTitle);
                    values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_POSTER, mMovieDetailResponse.posterPath);

                    Uri uri = getActivity().getContentResolver().insert(FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI, values);
                    if (uri != null) {
                        mFABFavorite.setImageResource(R.drawable.ic_favorite_active);
                        mToast = Toast.makeText(getActivity(), R.string.lbl_favorite_added, Toast.LENGTH_SHORT);
                    }
                } else {
                    int deletedId;
                    Uri uri = FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(mMovieDetailResponse.id)).build();
                    deletedId = getActivity().getContentResolver().delete(uri, null, null);
                    if (deletedId != 0) {
                        mFABFavorite.setImageResource(R.drawable.ic_favorite_inactive);
                        mToast = Toast.makeText(getActivity(), R.string.lbl_favorite_removed, Toast.LENGTH_SHORT);
                    }
                }
                mToast.show();
            }
        });
        MovieDetailsManager mMovieManager = new MovieDetailsManager();
        mMovieManager.getMovieDetails(mSelectedMovieId, new MovieDetailsManager.OnMovieDetailsListener() {
            @Override
            public void onSuccess(MovieDetailResponse response) {
                if (isAdded()) {
                    mMovieDetailResponse = response;
                    loadImage(TextUtils.join("", new String[]{
                            mImageConfig.baseUrl,
                            mImageConfig.posterSizeList.get(3),
                            mMovieDetailResponse.posterPath}), mImageViewPoster);
                    setTitle(mMovieDetailResponse.originalTitle);
                    setSubtitle("");
                    mTextViewOverview.setText(mMovieDetailResponse.overview);
                    mTextViewReleasedOn.setText(String.format(Locale.getDefault(), "Released on %s", mMovieDetailResponse.releaseDate));
                    mTextViewRatings.setText(String.format(Locale.getDefault(), "Rating: %1$,.1f", mMovieDetailResponse.voteAverage));
                    mFABFavorite.setImageResource(new DashBoardManager().isMovieListed(getActivity(), mMovieDetailResponse.id) ? R.drawable.ic_favorite_active : R.drawable.ic_favorite_inactive);
                }
            }

            @Override
            public void onError(PopularMovieException exception) {
                if (isAdded()) {
                    if (mToast != null) {
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
        });
        mMovieManager.getMovieReviews(mSelectedMovieId, new MovieDetailsManager.OnMovieReviewsListener() {
            @Override
            public void onSuccess(ReviewResponse response) {
                if (isAdded()) {
                    mReviewResponse = response;
                    mReviewViewPagerAdapter = new ReviewViewPagerAdapter(getActivity().getSupportFragmentManager(), response);
                    mViewPager.setAdapter(mReviewViewPagerAdapter);
                    if (response.reviewsList.size() > 0) {
                        mTextViewMoreReviews.setText(String.format(Locale.getDefault(), getString(R.string.lbl_reviews_count), response.reviewsList.size()));
                    } else {
                        mTextViewMoreReviews.setText(R.string.lbl_no_reviews);
                    }
                }
            }

            @Override
            public void onError(PopularMovieException exception) {
                if (isAdded()) {
                    if (mToast != null) {
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(IntentUtils.INTENT_MOVIE_ID, mSelectedMovieId);
        super.onSaveInstanceState(outState);
    }
}
