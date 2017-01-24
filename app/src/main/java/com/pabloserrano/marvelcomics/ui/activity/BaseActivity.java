package com.pabloserrano.marvelcomics.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable @BindView(R.id.progress_bar)
    View loadingView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initializeButterKnife();
        initializeToolbar();
    }

    protected abstract int getLayoutId();

    @Override public void showLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override public void hideLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}
