package com.codangcoding.popularmovies.stage1.main;

import android.arch.lifecycle.ViewModel;

import com.codangcoding.popularmovies.stage1.internal.api.Movie;
import com.codangcoding.popularmovies.stage1.internal.api.MovieService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by eko on 7/2/17.
 */

public class MainViewModel extends ViewModel {

    private final MovieService movieService;

    @Inject
    public MainViewModel(MovieService movieService) {
        this.movieService = movieService;
    }

    public Observable<List<Movie>> getPopularMovies() {
        return movieService.getPopularMovies();
    }

    public Observable<List<Movie>> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }
}
