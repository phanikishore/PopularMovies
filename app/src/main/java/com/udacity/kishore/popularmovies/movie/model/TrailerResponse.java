package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishorea on 19/06/17.
 */

public class TrailerResponse extends MovieResponse {

    @SerializedName("results")
    public List<Trailer> trailerList = new ArrayList<>();

}
