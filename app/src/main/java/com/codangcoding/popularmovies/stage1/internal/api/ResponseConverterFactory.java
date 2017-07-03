package com.codangcoding.popularmovies.stage1.internal.api;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by eko on 7/2/17.
 */

public class ResponseConverterFactory extends Converter.Factory {

    @Nullable @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<ResponseBody, Object> delegate = retrofit.nextResponseBodyConverter(this, new ResponseParameterizedType(Response.class, type), annotations);

        return new ResponseConverter(delegate);
    }

    private static final class ResponseParameterizedType implements ParameterizedType {

        private final Type rawType;
        private final Type typeArgument;

        public ResponseParameterizedType(Type rawType, Type typeArgument) {
            this.rawType = rawType;
            this.typeArgument = typeArgument;
        }

        @Override public Type[] getActualTypeArguments() {
            return new Type[]{typeArgument};
        }

        @Override public Type getRawType() {
            return rawType;
        }

        @Override public Type getOwnerType() {
            return null;
        }
    }
}
