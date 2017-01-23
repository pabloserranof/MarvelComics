package com.pabloserrano.marvelcomics.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pabloserrano.marvelcomics.MyApplication;
import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.data.model.MarvelComics;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.presenter.MainPresenterImp;
import com.pabloserrano.marvelcomics.ui.adapter.ComicsAdapter;
import com.pabloserrano.marvelcomics.ui.fragment.DialogFilterFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainPresenterImp.View, DialogFilterFragment.OnFilterSelected, ComicsAdapter.OnComicsFiltered {

    @Inject
    MainPresenterImp presenter;

    @BindView(R.id.recycler_view_city_list)
    RecyclerView recyclerView;

    private ComicsAdapter adapter;
    private int currentBudget = -1;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_list:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag(DialogFilterFragment.TAG);
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFilterFragment newFragment = DialogFilterFragment.newInstance(currentBudget);
                newFragment.show(ft, DialogFilterFragment.TAG);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showComics(MarvelComics marvelComics) {
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
    public void onBudgetSelected(int budget) {
        currentBudget = budget;
        adapter.getFilter().filter(String.valueOf(budget));
    }

    @Override
    public void numberOfPages(int numberOfPages) {
        Snackbar.make(findViewById(android.R.id.content), numberOfPages + getResources().getString(R.string.you_can_read), Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        adapter.setOnComicsFiltered(this);
    }

    private void initializeRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
