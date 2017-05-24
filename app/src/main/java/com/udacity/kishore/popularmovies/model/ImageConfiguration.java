package com.udacity.kishore.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kishorea on 22/05/17.
 */

public class ImageConfiguration {

    @SerializedName("base_url")
    public String baseUrl;
    @SerializedName("secure_base_url")
    public String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    public List<String> backdropSizeList = null;
    @SerializedName("logo_sizes")
    public List<String> logoSizeList = null;
    @SerializedName("poster_sizes")
    public List<String> posterSizeList = null;
    @SerializedName("profile_sizes")
    public List<String> profileSizeList = null;
    @SerializedName("still_sizes")
    public List<String> stillSizeList = null;
}
