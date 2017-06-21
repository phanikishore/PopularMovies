package com.udacity.kishore.popularmovies.base;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

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

    protected void replaceChildFragment(int containerId, BaseFragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragment);
        ft.commit();
    }

    protected void replace(int containerId, BaseFragment fragment) {
        ((BaseActivity) getActivity()).replace(containerId, fragment);
    }

    protected boolean isDialog() {
        return getDialog() != null;
    }

    protected void pop() {
        if (getActivity() != null) {
            if (isDialog()) {
                dismiss();
            } else {
                ((BaseActivity) getActivity()).pop();
            }
        }
    }

    protected void popFragmentsUpto(int index) {
        if (getActivity() != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count - index; ++i) {
                fm.popBackStack();
            }
        }
    }

    protected void onBacPressed() {
        if (isDialog()) {
            dismiss();
        } else {
            pop();
        }
    }
}
