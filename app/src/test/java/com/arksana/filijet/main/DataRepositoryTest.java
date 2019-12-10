package com.arksana.filijet.main;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arksana.filijet.data.DataRepository;
import com.arksana.filijet.data.Film;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DataRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DataRepository dataRepository;
    private Observer observer;
    private MainViewModel mainViewModel;

    @Before
    public void init() {
        mainViewModel = mock(MainViewModel.class);
        dataRepository = mock(DataRepository.class);
        observer = mock(Observer.class);
    }

    @Test
    public void movieDataTest() {
        ArrayList<Film> dummyMovies = new ArrayList<>(GetData.getMovies(1));
        MutableLiveData<ArrayList<Film>> dummyMoviesLiveData = new MutableLiveData<>(dummyMovies);

        when(dataRepository.getFilms()).thenReturn(dummyMoviesLiveData);
        dataRepository.getFilms().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
        assertEquals(20, dummyMoviesLiveData.getValue().size());

        dummyMovies.addAll(GetData.getMovies(1));
        dummyMoviesLiveData.setValue(dummyMovies);
        when(dataRepository.getFilms()).thenReturn(dummyMoviesLiveData);
        assertEquals(40, dummyMoviesLiveData.getValue().size());
    }

    @Test
    public void tvShowsDataTest() {
        ArrayList<Film> dummyTVShows = new ArrayList<>(GetData.getMovies(2));
        MutableLiveData<ArrayList<Film>> dummyTVShowLiveData = new MutableLiveData<>(dummyTVShows);

        when(dataRepository.getFilms()).thenReturn(dummyTVShowLiveData);
        dataRepository.getFilms().observeForever(observer);
        verify(observer).onChanged(dummyTVShows);
        assertEquals(20, dummyTVShowLiveData.getValue().size());

        dummyTVShows.addAll(GetData.getMovies(2));
        dummyTVShowLiveData.setValue(dummyTVShows);
        when(dataRepository.getFilms()).thenReturn(dummyTVShowLiveData);
        assertEquals(40, dummyTVShowLiveData.getValue().size());
    }

    @Test
    public void favoriteDataTest() {
        ArrayList<Film> dummyFavorite = new ArrayList<>(GetData.getMovies(3));
        MutableLiveData<ArrayList<Film>> dummyFavoriteLiveData = new MutableLiveData<>(dummyFavorite);

        when(dataRepository.getFilms()).thenReturn(dummyFavoriteLiveData);
        dataRepository.getFilms().observeForever(observer);
        verify(observer).onChanged(dummyFavorite);
        assertEquals(14, dummyFavoriteLiveData.getValue().size());

        dummyFavorite.addAll(GetData.getMovies(3));
        dummyFavoriteLiveData.setValue(dummyFavorite);
        when(dataRepository.getFilms()).thenReturn(dummyFavoriteLiveData);
        assertEquals(28, dummyFavoriteLiveData.getValue().size());
    }


}