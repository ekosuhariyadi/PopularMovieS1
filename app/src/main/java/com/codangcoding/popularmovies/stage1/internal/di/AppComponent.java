package com.codangcoding.popularmovies.stage1.internal.di;

import android.app.Application;

import com.codangcoding.popularmovies.stage1.MovieApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by eko on 7/2/17.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        NetworkModule.class,
        ActivitiesModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance Builder application(Application application);

        Builder network(NetworkModule networkModule);

        AppComponent build();
    }

    void inject(MovieApp movieApp);
}
