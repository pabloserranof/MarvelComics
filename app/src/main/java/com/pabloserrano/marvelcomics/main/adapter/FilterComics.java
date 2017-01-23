package com.pabloserrano.marvelcomics.main.adapter;

import android.widget.Filter;

import com.pabloserrano.marvelcomics.data.model.Result;

import java.util.LinkedList;
import java.util.List;

public class FilterComics extends Filter {

    private final ComicsAdapter comicsAdapter;
    private final List<Result> comicList;
    private final List<Result> comicListFiltered;

    public FilterComics(ComicsAdapter comicsAdapter, List<Result> comicList) {
        this.comicsAdapter = comicsAdapter;
        this.comicList = comicList;
        comicListFiltered = new LinkedList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence budget) {
        float budgetLimit = Float.valueOf(budget.toString());
        comicListFiltered.clear();
        final FilterResults results = new FilterResults();
        float currentAcumulatedPrice = 0;
        for (final Result comic : comicList) {
            currentAcumulatedPrice += comic.getPrices().get(0).getPrice();
            if (currentAcumulatedPrice <= budgetLimit) {
                comicListFiltered.add(comic);
            }
        }
        results.values = comicListFiltered;
        results.count = comicListFiltered.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        comicsAdapter.addFiltered((List<Result>) filterResults.values);
        comicsAdapter.notifyDataSetChanged();
    }
}
