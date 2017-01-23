package com.pabloserrano.marvelcomics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.presenter.MainPresenterImp;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicViewHolder extends RecyclerView.ViewHolder {

    private final MainPresenterImp presenter;

    @BindView(R.id.newsTitle)
    TextView comicTitle;
    @BindView(R.id.thumbnail)
    ImageView thumbnailView;

    public ComicViewHolder(View itemView, MainPresenterImp presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(Result comic) {
        hookListeners(comic);
        renderComicTitle(comic.getTitle());
        renderComicPhoto(comic.getThumbnail().getPath() + "."+ comic.getThumbnail().getExtension());
    }

    private void hookListeners(final Result result) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onComicClicked(result);
            }
        });
    }

    private void renderComicTitle(String title) {
        comicTitle.setText(title);
    }

    private void renderComicPhoto(String thumbnail) {
        try {
            Picasso.with(getContext()).load(thumbnail).fit().centerCrop().into(thumbnailView);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Context getContext() {
        return itemView.getContext();
    }
}

