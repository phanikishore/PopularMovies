package com.udacity.kishore.popularmovies.model;

/**
 * Created by kishorea on 19/05/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Configuration class used for Image configuration of Movies postors on various dimens
 */
public class Configuration {
    @SerializedName("images")
    public ImageConfiguration imageConfiguration;
    @SerializedName("change_keys")
    public List<String> changeKeyList = null;


    class ImageConfiguration {
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
}
