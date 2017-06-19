package com.udacity.kishore.popularmovies.movie.model;

import com.google.gson.annotations.SerializedName;
import com.udacity.kishore.popularmovies.model.BaseReposnse;

/**
 * Created by kishorea on 19/06/17.
 */

public class MovieResponse extends BaseReposnse {
    @SerializedName("id")
    public int id;
}
