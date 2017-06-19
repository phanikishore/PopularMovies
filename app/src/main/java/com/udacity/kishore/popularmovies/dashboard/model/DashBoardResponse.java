package com.udacity.kishore.popularmovies.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.udacity.kishore.popularmovies.model.BaseReposnse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishorea on 19/05/17.
 */

public class DashBoardResponse extends BaseReposnse {
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<MovieItem> moviesList = new ArrayList<>();
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
}
