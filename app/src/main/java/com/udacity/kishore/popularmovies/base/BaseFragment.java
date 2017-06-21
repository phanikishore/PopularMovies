package com.udacity.kishore.popularmovies.base;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.kishore.popularmovies.model.ImageConfiguration;

/**
 * Created by Kishore on 6/20/2017.
 */

public class BaseFragment extends DialogFragment {

    protected ImageConfiguration mImageConfig;

    public void loadImage(String uri, ImageView view) {
        ((BaseActivity) getActivity()).loadImage(uri, view);
    }

    public void setTitle(int resId) {
        setTitle(getString(resId));
    }

    protected void setTitle(String title) {
        ((BaseActivity) getActivity()).setTitle(title);
    }

    protected void setSubtitle(int resId) {
        setSubtitle(getString(resId));
    }

    protected void setSubtitle(String subtitle) {
        ((BaseActivity) getActivity()).setSubtitle(subtitle);
    }

    protected void replace(int containerId, BaseFragment fragment) {
        ((BaseActivity) getActivity()).replace(containerId, fragment);
    }
}
