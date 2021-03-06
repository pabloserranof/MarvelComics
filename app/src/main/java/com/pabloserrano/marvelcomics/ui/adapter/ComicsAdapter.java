/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pabloserrano.marvelcomics.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.pabloserrano.marvelcomics.R;
import com.pabloserrano.marvelcomics.data.model.Result;
import com.pabloserrano.marvelcomics.presenter.MainPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicViewHolder> implements Filterable {

    public interface OnComicsFiltered{
        void numberOfPages(int numberOfPages);
    }

    private OnComicsFiltered onComicsFiltered;

    private final MainPresenterImp presenter;
    private final List<Result> comicListDisplayed;
    private final List<Result> comicListBackUp;

    public ComicsAdapter(MainPresenterImp presenter) {
        this.presenter = presenter;
        this.comicListDisplayed = new ArrayList<>();
        this.comicListBackUp = new ArrayList<>();
    }

    public void setOnComicsFiltered(OnComicsFiltered callback){
        this.onComicsFiltered = callback;
    }

    public void addAll(List<Result> comicList) {
        this.comicListDisplayed.addAll(comicList);
        this.comicListBackUp.addAll(comicList);
    }

    public void addFiltered(List<Result> comicListFiltered, int numberOfPages) {
        this.comicListDisplayed.clear();
        this.comicListDisplayed.addAll(comicListFiltered);
        onComicsFiltered.numberOfPages(numberOfPages);
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_comic_list, parent, false);
        return new ComicViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        holder.render(comicListDisplayed.get(position));
    }

    @Override
    public int getItemCount() {
        return comicListDisplayed.size();
    }

    @Override
    public Filter getFilter() {
        return new FilterComics(this, comicListBackUp);
    }
}
