package com.codangcoding.popularmovies.stage1.detail;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.internal.api.ApiConstants;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by eko on 7/3/17.
 */

public class DetailFragment extends LifecycleFragment {

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @BindView(R.id.moviePoster)
    ImageView moviePoster;

    @BindView(R.id.movieTitle)
    TextView movieTitle;

    @BindView(R.id.movieRelease)
    TextView movieRelease;

    @BindView(R.id.movieRating)
    TextView movieRating;

    @BindView(R.id.movieOverview)
    TextView movieOverview;

    @Override public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Movie movie = getActivity().getIntent().getParcelableExtra(DetailActivity.EXTRA_MOVIE);
        movieTitle.setText(movie.originalTitle);
        movieRelease.setText(movie.releaseDate);
        movieRating.setText(getString(R.string.movie_details_rating, movie.userRating));
        movieOverview.setText(movie.overview);

        Glide.with(getActivity())
                .load(ApiConstants.POSTER_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(moviePoster);
    }
}
