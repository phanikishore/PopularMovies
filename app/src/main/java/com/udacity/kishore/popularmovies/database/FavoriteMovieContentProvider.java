package com.udacity.kishore.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kishore on 6/22/2017.
 */

public class FavoriteMovieContentProvider extends ContentProvider {

    public static final int FAVORITE_MOVIES_LIST = 100;
    public static final int FAVORITE_MOVIE_WITH_ID = 101;

    private DatabaseHelper databaseHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FavoriteMovieContract.AUTHORITY, FavoriteMovieContract.PATH_FAVORITE_MOVIES, FAVORITE_MOVIES_LIST);
        uriMatcher.addURI(FavoriteMovieContract.AUTHORITY, FavoriteMovieContract.PATH_FAVORITE_MOVIES + "/#", FAVORITE_MOVIE_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sb = databaseHelper.getReadableDatabase();
        Cursor resultedCursor = null;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_MOVIES_LIST:
                resultedCursor = sb.query(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case FAVORITE_MOVIE_WITH_ID:
                String mSelection = FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID + " = ?";
                String[] mSelectionArgs = new String[]{uri.getPathSegments().get(1)};
                resultedCursor = sb.query(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME, projection, mSelection, mSelectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }

        resultedCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return resultedCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase sb = databaseHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_MOVIES_LIST:
                long id = sb.insertOrThrow(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into Uri: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deletedId = 0;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_MOVIE_WITH_ID:
                String mSelection = FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID + " = ?";
                String[] mSelectionArgs = new String[]{uri.getPathSegments().get(1)};
                deletedId = db.delete(FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME, mSelection, mSelectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        if (deletedId != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedId;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
