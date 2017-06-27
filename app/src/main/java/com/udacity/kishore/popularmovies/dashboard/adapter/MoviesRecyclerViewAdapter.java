package com.udacity.kishore.popularmovies.dashboard.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.udacity.kishore.popularmovies.R;
import com.udacity.kishore.popularmovies.base.BaseActivity;
import com.udacity.kishore.popularmovies.dashboard.manager.DashBoardManager;
import com.udacity.kishore.popularmovies.dashboard.model.MovieItem;
import com.udacity.kishore.popularmovies.database.FavoriteMovieContract;
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
    private String contentType;

    public interface OnMovieClickListener {
        void OnMovieClicked(MovieItem movie);
    }

    public MoviesRecyclerViewAdapter(OnMovieClickListener listener) {
        this.mClickListener = listener;
    }

    public void addData(String content, List<MovieItem> movieItemList) {
        this.contentType = content;
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
        Configuration configuration = PopularMoviesPreference.getInstance().getImageConfiguration();
        holder.imageViewFavorite.setImageResource(item.isFavorite ? R.drawable.ic_favorite_active : R.drawable.ic_favorite_inactive);
        String uri = TextUtils.join("", new String[]{
                configuration.imageConfiguration.baseUrl,
                configuration.imageConfiguration.posterSizeList.get(3),
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
        @BindView(R.id.imageview_favorite)
        ImageView imageViewFavorite;
        @BindView(R.id.imageview_movie_image)
        ImageView imageViewMoviePoster;
        Context context;
        Toast mToast;

        MovieViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_inactive);
            itemView.setOnClickListener(this);
            imageViewFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_favorite:
                    MovieItem movie = mList.get(getAdapterPosition());
                    if (mToast != null) {
                        mToast.cancel();
                    }
                    DashBoardManager manager = new DashBoardManager();
                    if (!manager.isMovieListed(context, movie.id)) {
                        ContentValues values = new ContentValues();
                        values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_ID, movie.id);
                        values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_NAME, movie.title);
                        values.put(FavoriteMovieContract.FavoriteMovieEntry.COLUMN_MOVIE_POSTER, movie.posterPath);
                        Uri uri = context.getContentResolver().insert(FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI, values);
                        if (uri != null) {
                            movie.isFavorite = true;
                            imageViewFavorite.setImageResource(R.drawable.ic_favorite_active);
                            mToast = Toast.makeText(context, R.string.lbl_favorite_added, Toast.LENGTH_SHORT);
                        }
                    } else {
                        int deletedId = 0;
                        Uri uri = FavoriteMovieContract.FavoriteMovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(movie.id)).build();
                        deletedId = context.getContentResolver().delete(uri, null, null);
                        if (deletedId != 0) {
                            movie.isFavorite = false;
                            imageViewFavorite.setImageResource(R.drawable.ic_favorite_inactive);
                            if (contentType.equalsIgnoreCase(context.getString(R.string.string_favorite))) {
                                mList.remove(movie);
                                notifyDataSetChanged();
                            }
                            mToast = Toast.makeText(context, R.string.lbl_favorite_removed, Toast.LENGTH_SHORT);
                        }
                    }
                    mToast.show();
                    break;
                default:
                    if (mClickListener != null) {
                        mClickListener.OnMovieClicked(getMovieItem(getAdapterPosition()));
                    }
                    break;
            }
        }
    }
}
