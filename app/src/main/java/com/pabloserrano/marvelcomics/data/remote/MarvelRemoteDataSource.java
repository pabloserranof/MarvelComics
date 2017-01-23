package com.pabloserrano.marvelcomics.data.remote;

import com.pabloserrano.marvelcomics.AppConstants;
import com.pabloserrano.marvelcomics.network.ApiService;
import com.pabloserrano.marvelcomics.data.MarvelDataSource;
import com.pabloserrano.marvelcomics.data.model.MarvelComics;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MarvelRemoteDataSource implements MarvelDataSource {

    @Inject
    ApiService apiService;

    public MarvelRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getMarvelComics(final GetMarvelCallback getMarvelCallback) {
        apiService.getComics(AppConstants.MARVEL_FORMAT, AppConstants.COMIC_SIZE).enqueue(new Callback<MarvelComics>() {
            @Override
            public void onResponse(Call<MarvelComics> call, Response<MarvelComics> response) {
                if (response.isSuccessful()) {
                    getMarvelCallback.onMarvelComicsLoaded(response.body());
                } else {
                    getMarvelCallback.onComicsNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<MarvelComics> call, Throwable t) {
                Timber.e("onFailure " + t);
                getMarvelCallback.onComicsNotAvailable();
            }
        });
    }
}
