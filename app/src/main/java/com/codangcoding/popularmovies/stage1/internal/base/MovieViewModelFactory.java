package com.codangcoding.popularmovies.stage1.internal.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by eko on 7/3/17.
 */

public class MovieViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<? extends ViewModel>> factory;

    @Inject
    public MovieViewModelFactory(Map<Class<? extends ViewModel>, Provider<? extends ViewModel>> factory) {
        this.factory = factory;
    }

    @Override public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> provider = factory.get(modelClass);

        if (null == provider) {
            Set<Map.Entry<Class<? extends ViewModel>, Provider<? extends ViewModel>>> entrySet = factory.entrySet();
            for (Map.Entry<Class<? extends ViewModel>, Provider<? extends ViewModel>> entry : entrySet) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    provider = entry.getValue();
                    break;
                }
            }
        }

        if (null == provider) {
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass);
        }

        return (T) provider.get();
    }
}
