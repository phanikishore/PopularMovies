package com.udacity.kishore.popularmovies.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeIntents;
import com.squareup.picasso.Picasso;
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
        final Trailer trailer = getItem(position);
        final TrailerViewHolder trailerViewHolder = holder;

        ((BaseActivity) mContext).loadImage(
                String.format(Locale.getDefault(), AppUtils.THUMBNAIL_YOUTUBE_URL, trailer.key),
                trailerViewHolder.imageViewTrailer, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        trailerViewHolder.imageViewPlay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });

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
        @BindView(R.id.imageview_play_button)
        ImageView imageViewPlay;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageViewPlay.setVisibility(View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Trailer trailer = mTrailerList.get(getAdapterPosition());
                    Intent intent = null;
                    if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(mContext)) {
                        intent = YouTubeIntents.createPlayVideoIntentWithOptions(mContext, trailer.key,true,false);
                    } else {
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.format(Locale.getDefault(), AppUtils.PLAY_YOUTUBE_URL, trailer.key)));
                    }
                    mContext.startActivity(intent);
                }
            });
        }


    }
}
