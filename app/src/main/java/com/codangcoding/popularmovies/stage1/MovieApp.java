package com.codangcoding.popularmovies.stage1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.codangcoding.popularmovies.stage1.internal.api.ApiConstants;
import com.codangcoding.popularmovies.stage1.internal.di.AppComponent;
import com.codangcoding.popularmovies.stage1.internal.di.AppModule;
import com.codangcoding.popularmovies.stage1.internal.di.DaggerAppComponent;
import com.codangcoding.popularmovies.stage1.internal.di.NetworkModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by eko on 7/2/17.
 */

public class MovieApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    @Override public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ApiConstants.BASE_URL))
                .build()
                .inject(this);
    }
}
