package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishorea on 19/06/17.
 */

public class ImageMediaResponse extends MovieResponse {
    @SerializedName("backdrops")
    public List<ImageMedia> backdropList = new ArrayList<>();
    @SerializedName("posters")
    public List<ImageMedia> posterList = new ArrayList<>();
}
