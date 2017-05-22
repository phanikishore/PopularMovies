package com.udacity.kishore.popularmovies.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.model.Configuration;

/**
 * Created by kishorea on 22/05/17.
 */

public class PopularMoviesPreference {

    private static final Object _object = new Object();

    private static SharedPreferences mPrefernce;

    private static PopularMoviesPreference mInstance;

    private static final String PREFERENCE_NAME = "PopularMovies";

    private PopularMoviesPreference() {
    }

    public static PopularMoviesPreference getInstance() {
        synchronized (_object) {
            if (mInstance == null) {
                mPrefernce = PopularMoviesApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
                mInstance = new PopularMoviesPreference();
            }
        }
        return mInstance;
    }

    /**
     * Configuration Preference Start
     */
    private static final String PREFERENCE_CONFIGURATION = "PREFERENCE_CONFIGURATION";

    /**
     * Configuratoin model for Images to be loaded in the project.
     */
    public void setImageConfiguration(Configuration configuration) {
        SharedPreferences.Editor editor = mPrefernce.edit();
        editor.putString(PREFERENCE_CONFIGURATION, new Gson().toJson(configuration));
        editor.apply();
    }

    public Configuration getImageConfiguration() {
        return new Gson().fromJson(mPrefernce.getString(PREFERENCE_CONFIGURATION, ""), Configuration.class);
    }
    /*
     * Configuration Preference Ends
     * */

    /**
     * Save sort by preference Start
     */
    private static final String PREFERENCE_SORT_BY = "PREFERENCE_SORT_BY";

    /**
     * Preserve the state of sort by information on every launch of application
     */
    public void setSortBy(String sortBy) {
        SharedPreferences.Editor editor = mPrefernce.edit();
        editor.putString(PREFERENCE_SORT_BY, sortBy);
        editor.apply();
    }

    public String getSortBy() {
        return mPrefernce.getString(PREFERENCE_SORT_BY, PopularMoviesApplication.getInstance().getString(R.string.string_popular));
    }
    /**
     * Save sort by preference Ends
     * */
}
