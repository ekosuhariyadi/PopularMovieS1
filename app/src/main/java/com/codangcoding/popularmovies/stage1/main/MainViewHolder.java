package com.codangcoding.popularmovies.stage1.main;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.internal.api.ApiConstants;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eko on 7/2/17.
 */

public class MainViewHolder extends ViewHolder {

    @BindView(R.id.moviePoster)
    ImageView moviePoster;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        Glide.with(itemView.getContext())
                .load(ApiConstants.POSTER_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(moviePoster);
    }
}
