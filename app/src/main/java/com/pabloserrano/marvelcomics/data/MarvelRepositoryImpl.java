package com.pabloserrano.marvelcomics.data;

import android.support.annotation.NonNull;

public class MarvelRepositoryImpl implements MarvelDataSource {

    private MarvelDataSource marvelLocalDataSource;
    private MarvelDataSource marvelRemoteDataSource;

    public MarvelRepositoryImpl(MarvelDataSource marvelLocalDataSource,
                                MarvelDataSource marvelRemoteDataSource) {
        this.marvelLocalDataSource = marvelLocalDataSource;
        this.marvelRemoteDataSource = marvelRemoteDataSource;
    }

    @Override
    public void getMarvelComics(@NonNull GetMarvelCallback getMarvelCallback) {
        // Only reading from the remote datasource for now
        marvelRemoteDataSource.getMarvelComics(getMarvelCallback);
    }
}