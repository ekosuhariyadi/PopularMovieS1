package com.codangcoding.popularmovies.stage1.main;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codangcoding.popularmovies.stage1.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.functions.Consumer;

/**
 * Created by eko on 7/2/17.
 */

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner, HasSupportFragmentInjector {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private final Consumer<? super Throwable> errorHandler = (Consumer<Throwable>) throwable -> Snackbar.make(
            coordinatorLayout,
            getString(R.string.network_request_error),
            Snackbar.LENGTH_LONG
    ).show();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, MainFragment.newInstanceWithErrorHandler(errorHandler))
                    .commitAllowingStateLoss();
        }
    }

    @Override public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
