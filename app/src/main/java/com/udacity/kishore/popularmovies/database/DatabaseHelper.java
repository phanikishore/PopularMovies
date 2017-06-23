package com.udacity.kishore.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kishore on 6/22/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "PopularMovies.db";

    protected static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FavoriteMovieContract.CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ FavoriteMovieContract.FavoriteMovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
