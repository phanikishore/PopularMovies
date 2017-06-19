package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kishorea on 19/06/17.
 */
public class Review {
    @SerializedName("id")
    public String id;
    @SerializedName("author")
    public String author;
    @SerializedName("content")
    public String content;
    @SerializedName("url")
    public String url;
}
