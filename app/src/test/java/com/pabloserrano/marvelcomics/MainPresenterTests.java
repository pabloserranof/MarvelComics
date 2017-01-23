package com.pabloserrano.marvelcomics;

import com.pabloserrano.marvelcomics.data.MarvelRepositoryImpl;
import com.pabloserrano.marvelcomics.data.model.MarvelComics;
import com.pabloserrano.marvelcomics.presenter.MainPresenterImp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainPresenterTests {

    @Mock
    MarvelRepositoryImpl mockRepository;

    @Mock
    MainPresenterImp.View mockView;

    MainPresenterImp presenter;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<MarvelRepositoryImpl.GetMarvelCallback> getMarvelCallbackCaptor;

    @Spy
    private MarvelComics marvelComics = new MarvelComics();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenterImp(mockRepository);
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenComicListNotAvailableAndNotCallShowComics() {
        presenter.setView(mockView);
        mockView.showNotAvailable();
        verify(mockView, times(1)).showNotAvailable();
        verify(mockView, never()).showComics(marvelComics);
    }

    @Test
    public void shouldNotShowErrorMessageOnViewWhenCityListAvailableAndCallLoadCity() {
        presenter.setView(mockView);
        mockView.showComics(marvelComics);
        verify(mockView, never()).showNotAvailable();
        verify(mockView, times(1)).showComics(marvelComics);
    }
}