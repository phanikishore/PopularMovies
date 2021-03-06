package com.udacity.kishore.popularmovies.network;

import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.model.BaseReposnse;
import com.udacity.kishore.popularmovies.model.Configuration;
import com.udacity.kishore.popularmovies.movie.model.MovieDetailResponse;
import com.udacity.kishore.popularmovies.movie.model.ReviewResponse;
import com.udacity.kishore.popularmovies.movie.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kishorea on 19/05/17.
 */

public interface PopularMoviesAPIServices {

    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String apikey);

    @GET("movie/popular")
    Call<DashBoardResponse> getPopularMovies(@Query("api_key") String apikey, @Query("page") int pageNo);

    @GET("movie/top_rated")
    Call<DashBoardResponse> getTopRatedMovies(@Query("api_key") String apikey, @Query("page") int pageNo);

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apikey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailers(@Path("movie_id") int movieId, @Query("api_key") String apikey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getMovieReviews(@Path("movie_id") int movieId, @Query("api_key") String apikey);
}
