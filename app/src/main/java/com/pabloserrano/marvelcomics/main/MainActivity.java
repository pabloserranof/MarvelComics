package com.pabloserrano.marvelcomics.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pabloserrano.marvelcomics.MyApplication;
import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.baseclasses.BaseActivity;
import com.pabloserrano.marvelcomics.data.model.MarvelComics;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.main.adapter.ComicsAdapter;
import com.pabloserrano.marvelcomics.comicdetails.ComicDetailsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainPresenterImp.View {

    @Inject
    MainPresenterImp presenter;

    @BindView(R.id.recycler_view_city_list)
    RecyclerView recyclerView;

    private ComicsAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override
    public void showComics(MarvelComics marvelComics) {
        Timber.d(marvelComics.getData().getResults().toString());
        adapter.addAll(marvelComics.getData().getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openComicScreen(Result comic) {
        ComicDetailsActivity.open(this, comic);
    }

    @Override
    public void showNotAvailable() {
        Snackbar.make(findViewById(android.R.id.content), R.string.generic_error, Snackbar.LENGTH_LONG).setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.initialize();
            }
        }).show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initializeDagger() {
        ((MyApplication) getApplication()).getComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeAdapter() {
        adapter = new ComicsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
