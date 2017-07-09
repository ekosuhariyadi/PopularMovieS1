package com.codangcoding.popularmovies.stage1.main;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.detail.DetailActivity;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;
import com.codangcoding.popularmovies.stage1.internal.di.Injectable;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eko on 7/3/17.
 */

public class MainFragment extends LifecycleFragment implements Injectable, MovieClickListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static MainFragment newInstanceWithErrorHandler(Consumer<? super Throwable> errorHandler) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setErrorHandler(errorHandler);

        return mainFragment;
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.movieList)
    RecyclerView rvMovie;

    private MainAdapter adapter;
    private MainViewModel viewModel;

    private Consumer<? super Throwable> errorHandler;
    private final Consumer<? super Throwable> DEFAULT_ERROR_HANDLER = (Consumer<Throwable>) throwable -> Toast.makeText(
            getActivity(),
            getString(R.string.network_request_error),
            Toast.LENGTH_SHORT
    ).show();

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new MainAdapter(getActivity(), this);
        rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.grid_column)));
        rvMovie.setAdapter(adapter);

        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        executeObservable(viewModel.getPopularMovies());
    }

    @Override public void onMovieClicked(Movie movie, View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.shared_image));
        startActivity(DetailActivity.newIntent(getActivity(), movie), options.toBundle());
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular:
                executeObservable(viewModel.getPopularMovies());
                return true;
            case R.id.menu_toprated:
                executeObservable(viewModel.getTopRatedMovies());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setErrorHandler(Consumer<? super Throwable> errorHandler) {
        this.errorHandler = errorHandler;
    }

    private void executeObservable(Observable<List<Movie>> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> adapter.setData(movies),
                        null == errorHandler ? DEFAULT_ERROR_HANDLER : errorHandler
                );
    }
}
