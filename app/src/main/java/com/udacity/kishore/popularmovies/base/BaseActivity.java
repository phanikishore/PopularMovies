package com.udacity.kishore.popularmovies.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.model.ImageConfiguration;

/**
 * Created by kishorea on 22/05/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected ImageConfiguration mImageConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void loadImage(String uri, ImageView view) {
        Picasso.with(this).load(uri).placeholder(R.mipmap.ic_launcher_round).into(view);
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
        fragmentTransaction.replace(containerId, fragment,String.valueOf(fragmentManager.getBackStackEntryCount()));
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
            Fragment fragment = getSupportFragmentManager().findFragmentByTag((count-1)+"");
            if(fragment!=null){
                ((BaseFragment)fragment).onBacPressed();
            }
        }
    }

    protected void pop(){
        getSupportFragmentManager().popBackStackImmediate();
    }
}
