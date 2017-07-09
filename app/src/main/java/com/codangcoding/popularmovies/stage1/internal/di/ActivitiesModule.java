package com.codangcoding.popularmovies.stage1.internal.di;

import com.codangcoding.popularmovies.stage1.detail.DetailActivity;
import com.codangcoding.popularmovies.stage1.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by eko on 7/3/17.
 */
@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract DetailActivity contributeDetailActivity();
}
