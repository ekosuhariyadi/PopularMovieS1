package com.codangcoding.popularmovies.stage1;

import android.app.Activity;
import android.app.Application;

import com.codangcoding.popularmovies.stage1.internal.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by eko on 7/2/17.
 */

public class MovieApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

    @Override public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
