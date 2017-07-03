package com.codangcoding.popularmovies.stage1.main;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codangcoding.popularmovies.stage1.R;
import com.codangcoding.popularmovies.stage1.detail.DetailActivity;
import com.codangcoding.popularmovies.stage1.internal.api.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eko on 7/3/17.
 */

public class MainFragment extends LifecycleFragment implements MovieClickListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Inject
    MainViewModel viewModel;

    @BindView(R.id.movieList)
    RecyclerView rvMovie;

    private MainAdapter adapter;

    @Override public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new MainAdapter(getActivity(), this);
        ButterKnife.bind(this, view);
        rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvMovie.setAdapter(adapter);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    private void executeObservable(Observable<List<Movie>> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> adapter.setData(movies),
                        throwable -> Log.e("MainFragment", throwable.getMessage())
                );
    }
}
