package com.pabloserrano.marvelcomics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.data.model.Creators;
import com.pabloserrano.marvelcomics.data.model.Item;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.data.model.Thumbnail;
import com.pabloserrano.marvelcomics.network.ImageLoader;
import com.pabloserrano.marvelcomics.presenter.MainPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicViewHolder extends RecyclerView.ViewHolder {

    private final MainPresenterImp presenter;

    @BindView(R.id.comic_title)
    TextView comicTitle;
    @BindView(R.id.comic_thumbnail)
    ImageView thumbnailView;
    @BindView(R.id.comic_authors)
    TextView comicAuthors;
    @BindView(R.id.page_count)
    TextView pageCount;

    public ComicViewHolder(View itemView, MainPresenterImp presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(Result comic) {
        hookListeners(comic);
        renderComicPhoto(comic.getThumbnail());
        renderComicTitle(comic.getTitle());
        renderComicAuthors(comic.getCreators());
        renderComicPageCount(comic.getPageCount());
    }

    private void hookListeners(final Result result) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onComicClicked(result);
            }
        });
    }

    private void renderComicPhoto(Thumbnail thumbnail) {
        ImageLoader.loadThumbnail(getContext(), thumbnail, thumbnailView);
    }

    private void renderComicTitle(String title) {
        comicTitle.setText(title);
    }

    private void renderComicPageCount(int pageCount) {
        this.pageCount.setText(itemView.getResources().getString(R.string.pages) + pageCount);
    }

    private void renderComicAuthors(Creators creators) {
        StringBuilder authorsList = new StringBuilder();
        for (Item creator : creators.getItems()) {
            authorsList.append(creator.getName()).append(", ");
        }
        if (TextUtils.isEmpty(authorsList)) {
            this.comicAuthors.setText(itemView.getResources().getString(R.string.by) + itemView.getResources().getString(R.string.unknown));
        } else {
            this.comicAuthors.setText(itemView.getResources().getString(R.string.by) + authorsList);
        }
    }

    private Context getContext() {
        return itemView.getContext();
    }
}

