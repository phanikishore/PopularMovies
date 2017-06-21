package com.udacity.kishore.popularmovies.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.movie.model.Trailer;
import com.udacity.kishore.popularmovies.utils.AppUtils;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kishorea on 21/06/17.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> mTrailerList;
    private Context mContext;

    public void setData(List<Trailer> trailerList) {
        mTrailerList = trailerList;
        notifyDataSetChanged();
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        Trailer trailer = getItem(position);
        ((BaseActivity) mContext).loadImage(
                String.format(Locale.getDefault(), AppUtils.THUMBNAIL_YOUTUBE_URL, trailer.key),
                holder.imageViewTrailer);
    }

    private Trailer getItem(int position) {
        return mTrailerList.get(position);
    }

    @Override
    public int getItemCount() {
        return mTrailerList != null ? mTrailerList.size() : 0;
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_trailer)
        ImageView imageViewTrailer;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
