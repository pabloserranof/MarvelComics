package com.pabloserrano.marvelcomics.data;

import com.pabloserrano.marvelcomics.data.model.MarvelComics;

public interface MarvelDataSource {

    interface GetMarvelCallback {
        void onMarvelComicsLoaded(MarvelComics marvelComics);
        void onComicsNotAvailable();
    }

    void getMarvelComics(GetMarvelCallback getMarvelCallback);
}
