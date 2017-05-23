package com.udacity.kishore.popularmovies.manager;

import com.google.gson.Gson;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;
import com.udacity.kishore.popularmovies.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kishorea on 19/05/17.
 */

public abstract class WebServiceOperation<T> implements Callback<T> {

    protected Call<T> mCall;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            System.out.println("Response Body: " + new Gson().toJson(response.body()));
            onSuccess(response.body());
        } else {
            System.out.println("Response Error Code: " + response.code() + "\nResponse Error Body: " + response.message());
            onError(new PopularMovieException(response.code(), response.message()));
        }
    }

    public void enqueue() {
        System.out.println("Is Network Available? " + AppUtils.isNetworkAvailable());
        System.out.println("Url: " + mCall.request().url());
        if (AppUtils.isNetworkAvailable()) {
            mCall.enqueue(this);
        } else {
            onError(new PopularMovieException("Network Not Available!!! Try after sometime."));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(new PopularMovieException(t.getMessage()));
    }

    public abstract void onSuccess(T response);

    public abstract void onError(PopularMovieException exception);
}
