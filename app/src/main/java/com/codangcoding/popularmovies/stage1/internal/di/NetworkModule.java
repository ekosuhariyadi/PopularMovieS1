package com.codangcoding.popularmovies.stage1.internal.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.codangcoding.popularmovies.stage1.BuildConfig;
import com.codangcoding.popularmovies.stage1.internal.api.MovieService;
import com.codangcoding.popularmovies.stage1.internal.api.ResponseConverterFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by eko on 7/2/17.
 */

@Module
public class NetworkModule {

    private final static String MOVIEDB_API_KEY = BuildConfig.MOVIEDB_API_KEY;

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Provides @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originRequest = chain.request();
                    HttpUrl newUrl = originRequest.url().newBuilder()
                            .addQueryParameter("api_key", MOVIEDB_API_KEY)
                            .build();
                    Request newRequest = originRequest.newBuilder()
                            .url(newUrl)
                            .build();
                    return chain.proceed(newRequest);
                })
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides @Singleton
    Retrofit provideRetrofit(ObjectMapper objectMapper, OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new ResponseConverterFactory())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(httpClient)
                .build();
    }

    @Provides @Singleton
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }
}
