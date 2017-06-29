package com.udacity.kishore.popularmovies.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.model.ImageConfiguration;

/**
 * Created by kishorea on 22/05/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected ImageConfiguration mImageConfig;
    protected ProgressDialog mProgressDialog;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void setActionBar(int resId) {
        mToolbar = (Toolbar) findViewById(resId);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void showToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    protected void hideToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int count = getSupportFragmentManager().getBackStackEntryCount();
        getSupportFragmentManager().putFragment(outState,
                Integer.toString(count - 1),
                getSupportFragmentManager().findFragmentByTag(Integer.toString(count - 1)));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void loadImage(String uri, ImageView view) {
        loadImage(uri, view, new Callback.EmptyCallback());
    }

    public void loadImage(String uri, ImageView view, Callback listener) {
        Callback mListener = listener != null ? listener : new Callback.EmptyCallback();
        Picasso.with(this).load(uri).into(view, mListener);
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

    protected void replace(int containerId, BaseFragment fragment) {
        replace(containerId, fragment, true);
    }

    protected void replace(int containerId, BaseFragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, Integer.toString(fragmentManager.getBackStackEntryCount()));
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag((count - 1) + "");
            if (fragment != null) {
                ((BaseFragment) fragment).onBacPressed();
            }
        }
    }

    protected void pop() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    protected void showLoadingIndicator() {
        showProgressBar(getString(R.string.label_loading), false);
    }

    protected void showProgressBar(int resId, boolean isCancelable) {
        showProgressBar(getString(resId), isCancelable);
    }

    protected void showProgressBar(String message, boolean isCancelable) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.show();
    }

    protected void hideProgressBar() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
