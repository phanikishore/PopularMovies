package com.udacity.kishore.popularmovies.dashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kishorea on 19/05/17.
 */

public class MovieItem {
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = null;
    @SerializedName("id")
    public int id;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("title")
    public String title;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double voteAverage;

}
