package com.pabloserrano.marvelcomics.module;

import com.pabloserrano.marvelcomics.api.ApiService;
import com.pabloserrano.marvelcomics.data.MarvelRepositoryImpl;
import com.pabloserrano.marvelcomics.data.local.MarvelLocalDataSource;
import com.pabloserrano.marvelcomics.data.remote.MarvelRemoteDataSource;
import com.pabloserrano.marvelcomics.main.MainPresenterImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public MarvelRepositoryImpl provideRepository(ApiService apiService) {
        return new MarvelRepositoryImpl(new MarvelLocalDataSource(), new MarvelRemoteDataSource(apiService));
    }

    @Singleton
    @Provides
    public MainPresenterImp provideMainPresenterImp(MarvelRepositoryImpl guardianNewsRepository) {
        return new MainPresenterImp(guardianNewsRepository);
    }
}
