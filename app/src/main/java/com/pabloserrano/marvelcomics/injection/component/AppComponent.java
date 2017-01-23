package com.pabloserrano.marvelcomics.injection.component;

import com.pabloserrano.marvelcomics.injection.module.AppModule;
import com.pabloserrano.marvelcomics.injection.module.NetModule;
import com.pabloserrano.marvelcomics.injection.module.RepositoryModule;
import com.pabloserrano.marvelcomics.ui.activity.ComicDetailsActivity;
import com.pabloserrano.marvelcomics.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity target);
    void inject(ComicDetailsActivity target);
}
