package com.codangcoding.popularmovies.stage1.internal.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by eko on 7/2/17.
 */

public class Response<Data> {

    public int page;

    @JsonProperty("total_results")
    public int totalResults;

    @JsonProperty("total_pages")
    public int totalPages;

    @JsonProperty("results")
    public Data movies;
}
