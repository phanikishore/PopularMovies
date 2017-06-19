package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kishorea on 23/05/17.
 */

public class MovieDetailResponse extends MovieResponse {

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("overview")
    public String overview;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("vote_average")
    public double voteAverage;

}
