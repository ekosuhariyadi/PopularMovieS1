package com.codangcoding.popularmovies.stage1.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.internal.api.ApiConstants;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by eko on 7/2/17.
 */

public class DetailActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    public static final String EXTRA_MOVIE = "movie";

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        return intent;
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.backdropImage)
    ImageView backdropImage;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Movie movie = getIntent().getParcelableExtra(DetailActivity.EXTRA_MOVIE);
        collapsingToolbarLayout.setTitle(movie.title);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
        setTitle(null);
        Glide.with(this)
                .load(ApiConstants.POSTER_BASE_URL + movie.backdropPath)
                .placeholder(R.drawable.placeholder)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(backdropImage);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, DetailFragment.newInstance())
                    .commitAllowingStateLoss();
        }
    }

    @Override public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }
}
