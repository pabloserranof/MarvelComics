package com.pabloserrano.marvelcomics;

import com.pabloserrano.marvelcomics.main.MainActivity;
import com.pabloserrano.marvelcomics.module.AppModule;
import com.pabloserrano.marvelcomics.module.NetModule;
import com.pabloserrano.marvelcomics.module.RepositoryModule;
import com.pabloserrano.marvelcomics.comicdetails.ComicDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity target);
    void inject(ComicDetailsActivity target);
}
