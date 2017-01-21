package com.pabloserrano.marvelcomics.comicdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.baseclasses.BaseActivity;
import com.pabloserrano.marvelcomics.baseclasses.Presenter;
import com.pabloserrano.marvelcomics.data.model.Result;

import timber.log.Timber;

public class ComicDetailsActivity extends BaseActivity implements Presenter.View {

    public static final String COMIC = "COMIC";

    private Result comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
    }

    private void initializeActivity(){
        comic = getIntent().getExtras().getParcelable(COMIC);
        Timber.e(comic.toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comic_details;
    }

    public static void open(Context context, Result comic) {
        Intent intent = new Intent(context, ComicDetailsActivity.class);
        intent.putExtra(COMIC, comic);
        context.startActivity(intent);
    }
}
