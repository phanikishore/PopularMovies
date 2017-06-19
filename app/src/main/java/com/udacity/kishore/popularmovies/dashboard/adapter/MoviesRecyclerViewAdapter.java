package com.udacity.kishore.popularmovies.dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.model.MovieItem;
import com.udacity.kishore.popularmovies.model.Configuration;
import com.udacity.kishore.popularmovies.utils.PopularMoviesPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kishorea on 19/05/17.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder> {

    private List<MovieItem> mList;
    private Toast mToast;
    private OnMovieClickListener mClickListener;

    public interface OnMovieClickListener {
        void OnMovieClicked(MovieItem movie);
    }

    public MoviesRecyclerViewAdapter(OnMovieClickListener listener) {
        this.mClickListener = listener;
    }

    public void addData(List<MovieItem> movieItemList) {
        mList = movieItemList;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieItem item = getMovieItem(position);
        holder.textViewTitle.setText(item.title);
        Configuration configuration = PopularMoviesPreference.getInstance().getImageConfiguration();
        String uri = TextUtils.join("", new String[]{
                configuration.imageConfiguration.baseUrl,
                configuration.imageConfiguration.posterSizeList.get(4),
                item.posterPath
        });
        ((BaseActivity) holder.context).loadImage(uri, holder.imageViewMoviePoster);
    }

    private MovieItem getMovieItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_movie_title)
        TextView textViewTitle;
        @BindView(R.id.imageview_movie_image)
        ImageView imageViewMoviePoster;
        Context context;

        MovieViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.OnMovieClicked(getMovieItem(getAdapterPosition()));
            }
        }
    }
}
