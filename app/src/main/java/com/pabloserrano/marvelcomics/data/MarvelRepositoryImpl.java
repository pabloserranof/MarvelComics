package com.pabloserrano.marvelcomics.data;

import android.support.annotation.NonNull;

public class MarvelRepositoryImpl implements MarvelDataSource {

    private MarvelDataSource guardianNewsLocalDataSource;
    private MarvelDataSource guardianNewsRemoteDataSource;

    public MarvelRepositoryImpl(MarvelDataSource guardianNewsLocalDataSource,
                                MarvelDataSource guardianNewsRemoteDataSource) {
        this.guardianNewsLocalDataSource = guardianNewsLocalDataSource;
        this.guardianNewsRemoteDataSource = guardianNewsRemoteDataSource;
    }

    @Override
    public void getMarvelComics(@NonNull GetMarvelCallback getCityListCallback) {
        // Only reading from the remote datasource for now
        guardianNewsRemoteDataSource.getMarvelComics(getCityListCallback);
    }
}