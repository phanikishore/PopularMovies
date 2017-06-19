package com.udacity.kishore.popularmovies.movie.model;

import android.support.annotation.StringDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kishorea on 19/06/17.
 */
public class Trailer {
    private static final String TRAILER = "Trailer";
    private static final String TEASER = "Teaser";
    private static final String CLIP = "Clip";
    private static final String FEATURETTE = "Featurette";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TRAILER, TEASER, CLIP, FEATURETTE})
    public @interface Type {
    }

    @Type
    public String getType() {
        return type;
    }

    @SerializedName("id")
    public String id;
    @SerializedName("iso_639_1")
    public String iso_639_1;
    @SerializedName("iso_3166_1")
    public String iso_3166_1;
    @SerializedName("key")
    public String key;
    @SerializedName("name")
    public String name;
    @SerializedName("site")
    public String site;
    @SerializedName("size")
    public int size;
    @SerializedName("type")
    @Type
    public String type;
}
