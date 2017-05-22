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

}
