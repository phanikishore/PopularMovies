package com.udacity.kishore.popularmovies.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by kishorea on 22/05/17.
 */

public class BaseActivity extends AppCompatActivity {
    public void loadImage(String uri, ImageView view) {
        Picasso.with(this).load(uri).into(view);
    }
}
