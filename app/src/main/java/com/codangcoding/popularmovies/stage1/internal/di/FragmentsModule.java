package com.codangcoding.popularmovies.stage1.internal.di;

import com.codangcoding.popularmovies.stage1.detail.DetailFragment;
import com.codangcoding.popularmovies.stage1.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by eko on 7/3/17.
 */
@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

    @ContributesAndroidInjector
    abstract DetailFragment contributeDetailFragment();
}
