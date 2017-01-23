package com.pabloserrano.marvelcomics.presenter;

import com.pabloserrano.marvelcomics.data.MarvelDataSource;
import com.pabloserrano.marvelcomics.data.model.MarvelComics;
import com.pabloserrano.marvelcomics.data.model.Result;

import javax.inject.Inject;

public class MainPresenterImp extends Presenter<MainPresenterImp.View> {

    @Inject
    MarvelDataSource repository;

    public MainPresenterImp(MarvelDataSource marvelDataSource) {
        this.repository = marvelDataSource;
    }

    @Override
    public void initialize() {
        super.initialize();
        getComics();
    }

    public void getComics() {
        repository.getMarvelComics(new MarvelDataSource.GetMarvelCallback() {
            View view = getView();
            @Override
            public void onMarvelComicsLoaded(MarvelComics marvelComics) {
                view.hideLoading();
                view.showComics(marvelComics);
            }

            @Override
            public void onComicsNotAvailable() {
                view.hideLoading();
                view.showNotAvailable();
            }
        });
    }

    public void onComicClicked(Result comic) {
        getView().openComicScreen(comic);
    }

    public interface View extends Presenter.View {

        void showNotAvailable();

        void showComics(MarvelComics marvelComics);

        void openComicScreen(Result comic);
    }
}
