package com.codangcoding.popularmovies.stage1.detail;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

/**
 * Created by eko on 7/3/17.
 */

public class DetailFragment extends LifecycleFragment {

    private static final double RATE_PERFECT = 9.0;
    private static final double RATE_GOOD = 7.0;
    private static final double RATE_NORMAL = 5.0;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @BindView(R.id.moviePoster)
    ImageView moviePoster;

    @BindView(R.id.movieOriginalTitle)
    TextView movieTitle;

    @BindView(R.id.movieReleaseDate)
    TextView movieReleaseDate;

    @BindView(R.id.movieRating)
    TextView movieRating;

    @BindView(R.id.movieOverview)
    TextView movieOverview;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Movie movie = getActivity().getIntent().getParcelableExtra(DetailActivity.EXTRA_MOVIE);
        movieTitle.setText(movie.originalTitle);
        movieReleaseDate.setText(getString(R.string.movie_details_release_date, movie.releaseDate));
        movieRating.setText(getString(R.string.movie_details_rating, movie.userRating, getRatingText(movie.userRating)));
        movieRating.setTextColor(getRatingColor(movie.userRating));
        movieOverview.setText(movie.overview);

        Glide.with(getActivity())
                .load(ApiConstants.POSTER_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(moviePoster);
    }

    public int getRatingColor(double rate) {
        if (rate >= RATE_PERFECT) {
            return ContextCompat.getColor(getContext(), R.color.rate_perfect);
        } else if (rate >= RATE_GOOD) {
            return ContextCompat.getColor(getContext(), R.color.rate_good);
        } else if (rate >= RATE_NORMAL) {
            return ContextCompat.getColor(getContext(), R.color.rate_normal);
        } else {
            return ContextCompat.getColor(getContext(), R.color.rate_bad);
        }
    }

    public String getRatingText(double rate) {
        if (rate >= RATE_PERFECT) {
            return getString(R.string.rate_perfect);
        } else if (rate >= RATE_GOOD) {
            return getString(R.string.rate_good);
        } else if (rate >= RATE_NORMAL) {
            return getString(R.string.rate_normal);
        } else {
            return getString(R.string.rate_bad);
        }
    }
}
