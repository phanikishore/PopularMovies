package com.udacity.kishore.popularmovies;

import android.app.Application;

import com.udacity.kishore.popularmovies.model.Configuration;
import com.udacity.kishore.popularmovies.network.PopularMoviesAPIServices;
import com.udacity.kishore.popularmovies.utils.AppUtils;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kishorea on 19/05/17.
 */

public class PopularMoviesApplication extends Application {

    private static PopularMoviesApplication mInstance;
    private PopularMoviesAPIServices mMoviesAPIServices;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setConfiguration();
    }

    public static PopularMoviesApplication getInstance() {
        return mInstance;
    }

    public PopularMoviesAPIServices getServiceInstance() {
        if (mMoviesAPIServices == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("contentType", "application/json").build();
                    return chain.proceed(request);
                }
            }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();

            mMoviesAPIServices = retrofit.create(PopularMoviesAPIServices.class);
        }
        return mMoviesAPIServices;
    }

    private void setConfiguration() {
        getServiceInstance().getConfiguration(AppUtils.API_KEY).enqueue(new Callback<Configuration>() {
            @Override
            public void onResponse(Call<Configuration> call, retrofit2.Response<Configuration> response) {
                if (response.isSuccessful()) {
                    PopularMoviesPreference.getInstance().setImageConfiguration(response.body());
                }
            }

            @Override
            public void onFailure(Call<Configuration> call, Throwable t) {

            }
        });
    }
}
