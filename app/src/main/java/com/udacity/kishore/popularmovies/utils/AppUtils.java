package com.udacity.kishore.popularmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;

/**
 * Created by kishorea on 19/05/17.
 */

public class AppUtils {

    //TODO: PROVIDE YOUR API KEY :)
    public static final String API_KEY = "a3d203c426fb20f97a4ce8f3fd601e32";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) PopularMoviesApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
