package com.codangcoding.popularmovies.stage1.internal.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by eko on 7/2/17.
 */

public interface MovieService {

    @GET("movie/popular")
    public Observable<List<Movie>> getPopularMovies();

    @GET("movie/top_rated")
    public Observable<List<Movie>> getTopRatedMovies();
}
