package com.pabloserrano.marvelcomics;

import com.pabloserrano.marvelcomics.data.MarvelDataSource;
import com.pabloserrano.marvelcomics.data.MarvelRepositoryImpl;
import com.pabloserrano.marvelcomics.main.MainPresenterImp;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MainPresenterTests {

    @Mock
    MarvelRepositoryImpl repository;

    @Mock
    MainPresenterImp.View mockView;

    MainPresenterImp presenter;

    @Mock
    MarvelDataSource.GetMarvelCallback getForecastCallback;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<MarvelDataSource.GetMarvelCallback> getNewsCallback;

    @Spy
    private GuardianSearch guardianSearch = new GuardianSearch();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenterImp(repository);
    }

    // TODO
    /*@Test
    public void shouldShowErrorMessageOnViewWhenCityListNotAvailableAndNotCallLoadCitys() {
        presenter.setView(mockView);
        setNewsNotAvailable(repository);
        verify(mockView, times(1)).showNotAvailable();
        verify(mockView, never()).showComics(guardianSearch);
    }

    // TODO
    @Test
    public void shouldNotShowErrorMessageOnViewWhenCityListAvailableAndCallLoadCity() {
        presenter.setView(mockView);
        setNewsAvailable(repository);
        verify(mockView, never()).showNotAvailable();
        verify(mockView, times(1)).showComics(guardianSearch);
    }*/

    private void setNewsNotAvailable(MarvelDataSource forecastRepository) {
        verify(forecastRepository).getMarvelComics(getNewsCallback.capture(), eq(1));
        getNewsCallback.getValue().onComicsNotAvailable();
    }

    private void setNewsAvailable(MarvelDataSource forecastRepository) {
        verify(forecastRepository).getMarvelComics(getNewsCallback.capture(), eq(1));
        getNewsCallback.getValue().onMarvelComicsLoaded(guardianSearch);
    }
}