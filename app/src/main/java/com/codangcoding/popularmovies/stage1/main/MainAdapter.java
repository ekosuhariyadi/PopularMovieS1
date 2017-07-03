package com.codangcoding.popularmovies.stage1.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;
import com.codangcoding.popularmovies.stage1.internal.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Created by eko on 7/2/17.
 */

public class MainAdapter extends BaseAdapter<MainViewHolder, Movie> {

    private List<Movie> movies;

    private final MovieClickListener listener;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public MainAdapter(Context context, MovieClickListener listener) {
        this.listener = listener;
        this.context = context;

        movies = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override public void setData(List<Movie> data) {
        movies = data;
        notifyDataSetChanged();
    }

    @Override public int getItemViewType(int position) {
        return R.layout.item_movie_list;
    }

    @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final MainViewHolder viewHolder = new MainViewHolder(layoutInflater.inflate(viewType, parent, false));
        viewHolder.itemView.setOnClickListener(view -> {
            int position = viewHolder.getAdapterPosition();
            if (position != NO_POSITION) {
                listener.onMovieClicked(movies.get(position), view);
            }
        });

        return viewHolder;
    }

    @Override public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }
}
