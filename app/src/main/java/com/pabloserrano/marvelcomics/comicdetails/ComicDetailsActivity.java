package com.pabloserrano.marvelcomics.comicdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.baseclasses.BaseActivity;
import com.pabloserrano.marvelcomics.baseclasses.Presenter;
import com.pabloserrano.marvelcomics.data.model.Creators;
import com.pabloserrano.marvelcomics.data.model.Item;
import com.pabloserrano.marvelcomics.data.model.Price;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.data.model.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

public class ComicDetailsActivity extends BaseActivity implements Presenter.View {

    public static final String COMIC = "COMIC";

    @BindView(R.id.imageViewComic)
    ImageView imageComic;
    @BindView(R.id.comic_title)
    TextView title;
    @BindView(R.id.comic_price)
    TextView price;
    @BindView(R.id.comic_authors)
    TextView authors;
    @BindView(R.id.comic_page_count)
    TextView pageCount;
    @BindView(R.id.comic_description)
    TextView description;

    private Result comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        showComicData(comic);
    }

    private void initializeActivity() {
        comic = getIntent().getExtras().getParcelable(COMIC);
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

    private void showComicData(Result comic) {
        try {
            showPhoto(comic.getThumbnail());
            showTitle(comic.getTitle());
            showPrice(comic.getPrices());
            showAuthors(comic.getCreators());
            showPageCount(String.valueOf(comic.getPageCount()));
            showDescription(comic.getDescription());
        } catch (Exception e) {
            Timber.e(e.toString());
            e.printStackTrace();
        }
    }

    private void showPhoto(Thumbnail thumbnail) {
        Picasso.with(this).load(thumbnail.getPath() + "." + thumbnail.getExtension()).into(imageComic);
    }

    private void showTitle(String title) {
        this.title.setText(title);
    }

    private void showPrice(List<Price> prices) {
        this.price.setText(prices.get(0).getPrice() + getResources().getString(R.string.currency));
    }

    private void showAuthors(Creators creators) {
        StringBuilder authorsList = new StringBuilder();
        for (Item creator : creators.getItems()) {
            authorsList.append(creator.getName()).append(", ");
        }
        if (TextUtils.isEmpty(authorsList)) {
            this.authors.setText(getResources().getString(R.string.by) + getResources().getString(R.string.unknown));
        } else {
            this.authors.setText(getResources().getString(R.string.by) + authorsList);
        }
    }

    private void showPageCount(String pageCount) {
        this.pageCount.setText(getResources().getString(R.string.pages) + pageCount);
    }

    private void showDescription(String description) {
        this.description.setText(description);
    }
}
