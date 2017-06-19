package com.udacity.kishore.popularmovies.dashboard.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kishorea on 19/05/17.
 */

public class MovieItem {
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
}
