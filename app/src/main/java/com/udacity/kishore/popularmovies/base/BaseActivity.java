package com.udacity.kishore.popularmovies.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.kishore.popularmovies.model.ImageConfiguration;

/**
 * Created by kishorea on 22/05/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected ImageConfiguration mImageConfig;

    public void loadImage(String uri, ImageView view) {
        Picasso.with(this).load(uri).into(view);
    }

    public void setTitle(int resId) {
        setTitle(getString(resId));
    }

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void setSubtitle(int resId) {
        setSubtitle(getString(resId));
    }

    protected void setSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

}
