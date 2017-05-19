package com.udacity.kishore.popularmovies.exception;

import com.udacity.kishore.popularmovies.PopularMoviesApplication;
import com.udacity.kishore.popularmovies.R;

import java.util.Locale;

/**
 * Created by kishorea on 19/05/17.
 */

public class PopularMovieException extends Exception {

    private int code;
    private String message;

    public PopularMovieException(int code) {
        this(code, String.format(Locale.getDefault(),
                PopularMoviesApplication.getInstance().getString(R.string.string_exception), code,
                PopularMoviesApplication.getInstance().getString(R.string.string_no_message)));
    }

    public PopularMovieException(String message) {
        this(-1, String.format(Locale.getDefault(),
                PopularMoviesApplication.getInstance().getString(R.string.string_exception), -1, message));
    }

    public PopularMovieException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
