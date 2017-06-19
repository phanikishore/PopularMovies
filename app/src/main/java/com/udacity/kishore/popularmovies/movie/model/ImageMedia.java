package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kishorea on 19/06/17.
 */

public class ImageMedia {
    @SerializedName("aspect_ratio")
    public double aspectRatio;
    @SerializedName("file_path")
    public String filePath;
    @SerializedName("height")
    public long height;
    @SerializedName("iso_639_1")
    public Object iso6391;
    @SerializedName("vote_average")
    public long voteAverage;
    @SerializedName("vote_count")
    public long voteCount;
    @SerializedName("width")
    public long width;
}
