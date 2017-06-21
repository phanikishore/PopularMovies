package com.udacity.kishore.popularmovies.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kishorea on 21/06/17.
 */

public abstract class BaseMovieFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    protected RecyclerView mRecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        setAdapter();
        return view;
    }

    public abstract void setAdapter();
}
