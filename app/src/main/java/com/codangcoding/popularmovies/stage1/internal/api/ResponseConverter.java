package com.codangcoding.popularmovies.stage1.internal.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by eko on 7/2/17.
 */

public class ResponseConverter<Data> implements Converter<ResponseBody, Data> {

    private final Converter<ResponseBody, Response<Data>> delegate;

    public ResponseConverter(Converter<ResponseBody, Response<Data>> delegate) {
        this.delegate = delegate;
    }

    @Override public Data convert(ResponseBody value) throws IOException {
        Response<Data> convert = delegate.convert(value);
        return convert.movies;
    }
}
