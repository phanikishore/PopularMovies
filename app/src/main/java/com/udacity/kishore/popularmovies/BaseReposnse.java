package com.udacity.kishore.popularmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kishorea on 19/05/17.
 */

public class BaseReposnse {
    @SerializedName("status_code")
    public int code;
    @SerializedName("status_message")
    public int statusMessage;

}
