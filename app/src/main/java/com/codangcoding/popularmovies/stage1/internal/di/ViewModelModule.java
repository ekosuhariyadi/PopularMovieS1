package com.codangcoding.popularmovies.stage1.internal.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.codangcoding.popularmovies.stage1.internal.base.MovieViewModelFactory;
import com.codangcoding.popularmovies.stage1.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by eko on 7/3/17.
 */
@Module
abstract class ViewModelModule {

    @Binds @IntoMap @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(MovieViewModelFactory viewModelFactory);

}