package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishorea on 19/06/17.
 */

public class ReviewResponse extends MovieResponse {
    @SerializedName("page")
    public int page;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("results")
    public List<Review> reviewsList = new ArrayList<>();

}
