package com.codangcoding.popularmovies.stage1.internal.di;

import com.codangcoding.popularmovies.stage1.MovieApp;
import com.codangcoding.popularmovies.stage1.detail.DetailActivity;
import com.codangcoding.popularmovies.stage1.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by eko on 7/2/17.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        FragmentsModule.class,
        ActivitiesModule.class
})
public interface AppComponent {

    void inject(MovieApp movieApp);
}
