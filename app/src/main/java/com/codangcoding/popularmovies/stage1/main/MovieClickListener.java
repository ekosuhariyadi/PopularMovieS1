package com.codangcoding.popularmovies.stage1.main;

import android.view.View;

import com.codangcoding.popularmovies.stage1.internal.api.Movie;

/**
 * Created by eko on 7/3/17.
 */

public interface MovieClickListener {

    void onMovieClicked(Movie movie, View view);
}
