package com.udacity.kishore.popularmovies.database;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Kishore on 6/22/2017.
 */

public final class FavoriteMovieContract {

    public static final String AUTHORITY = "com.udacity.kishore.popularmovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITE_MOVIES = "favoritemovieslist";

    public static class FavoriteMovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_MOVIES).build();

        public static final String TABLE_NAME = "favoriteMovies";
        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String COLUMN_MOVIE_NAME = "movieName";
        public static final String COLUMN_MOVIE_POSTER = "poster";
    }

    public static final String CREATE_FAVORITE_TABLE = "Create table " + FavoriteMovieEntry.TABLE_NAME + " (" +
            FavoriteMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavoriteMovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL UNIQUE, " +
            FavoriteMovieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, " +
            FavoriteMovieEntry.COLUMN_MOVIE_POSTER + " TEXT NOT NULL);";
}
