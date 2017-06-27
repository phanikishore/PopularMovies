package com.udacity.kishore.popularmovies.dashboard.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.udacity.kishore.popularmovies.dashboard.manager.operation.FetchMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.manager.operation.PopularMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.manager.operation.TopRatedMoviesServiceOperation;
import com.udacity.kishore.popularmovies.dashboard.model.DashBoardResponse;
import com.udacity.kishore.popularmovies.dashboard.model.MovieItem;
import com.udacity.kishore.popularmovies.database.FavoriteMovieContract;
import com.udacity.kishore.popularmovies.exception.PopularMovieException;

import java.util.ArrayList;

/**
 * Created by kishorea on 19/05/17.
 */

public class DashBoardManager {

    public interface DashBoardManagerListener {
        void onSuccess(DashBoardResponse response);

        void onError(PopularMovieException exception);
    }


    public void getPopularMovies(int pageNo, final DashBoardManagerListener listener) {
        new PopularMoviesServiceOperation(pageNo, new FetchMoviesServiceOperation.FetchMoviesServiceListener() {
            @Override
            public void onSuccess(DashBoardResponse response) {
                if (listener != null)
                    listener.onSuccess(response);
            }

            @Override
            public void onError(PopularMovieException exception) {
                if (listener != null)
                    listener.onError(exception);
            }
        });
    }

    public void getTopRatedMovies(int pageNo, final DashBoardManagerListener listener) {
        new TopRatedMoviesServiceOperation(pageNo, new FetchMoviesServiceOperation.FetchMoviesServiceListener() {
            @Override
            public void onSuccess(DashBoardResponse response) {
                if (listener != null)
                    listener.onSuccess(response);
            }

            @Override
            public void onError(PopularMovieException exception) {
                if (listener != null)
                    listener.onError(exception);
            }
        });
    }

    public void getMyFavoriteMovies(Context context, final DashBoardManagerListener listener) {
        DashBoardResponse response = getFavoriteMovies(context);
        if (listener != null) {
            if (response != null) {
                listener.onSuccess(response);
            } else {
                listener.onError(new PopularMovieException("No Movies Listed"));
            }
        }
    }

    public boolean isMovieListed(Context context, int movieId) {
        Cursor cursor = null;
        String[] projection = new String[]{FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID, FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_NAME, FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_POSTER};
        Uri uri = FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(movieId)).build();
        cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private DashBoardResponse getFavoriteMovies(Context context) {
        Cursor resultedCursor = null;
        String[] projection = new String[]{FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID, FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_NAME, FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_POSTER};
        resultedCursor = context.getContentResolver().query(FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI, projection, null, null, null);
        if (resultedCursor != null) {
            resultedCursor.moveToFirst();
            DashBoardResponse response = new DashBoardResponse();
            response.moviesList = new ArrayList<>();
            try {
                do{
                    MovieItem item = new MovieItem();
                    item.id = resultedCursor.getInt(resultedCursor.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID));
                    item.title = resultedCursor.getString(resultedCursor.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_NAME));
                    item.posterPath = resultedCursor.getString(resultedCursor.getColumnIndexOrThrow(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_POSTER));
                    item.isFavorite = !item.isFavorite;
                    response.moviesList.add(item);
                }while (resultedCursor.moveToNext());
            } finally {
                resultedCursor.close();
                return response;
            }
        }
        return null;
    }
}
