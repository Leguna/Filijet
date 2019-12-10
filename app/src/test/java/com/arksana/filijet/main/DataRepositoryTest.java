package com.arksana.filijet.main;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arksana.filijet.data.DataRepository;
import com.arksana.filijet.data.Film;
import com.arksana.filijet.data.FilmData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DataRepositoryTest {

    private DataRepository dataRepository = mock(DataRepository.class);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailViewModel detailViewModel;
    private MainViewModel mainViewModel;

    @Before
    public void init() {
        dataRepository = mock(DataRepository.class);
        mainViewModel = new MainViewModel(new Application());
        detailViewModel = new DetailViewModel(new Application());
    }

    @Test
    public void dataRepositoryTest() {
        Observer observer = mock(Observer.class);
        mainViewModel.dataRepository = dataRepository;

        //Type 1
        ArrayList<Film> dummyFilms = FilmData.getListData(2);
        MutableLiveData<ArrayList<Film>> films = new MutableLiveData<>();
        films.setValue(dummyFilms);

        when(mainViewModel.dataRepository.getFilms()).thenReturn(films);
        mainViewModel.getFilms().observeForever(observer);
        verify(observer).onChanged(dummyFilms);


        //Type 2
        dummyFilms = FilmData.getListData(2);
        films = new MutableLiveData<>();
        films.setValue(dummyFilms);

        when(mainViewModel.dataRepository.getFilms()).thenReturn(films);
        mainViewModel.getFilms().observeForever(observer);
        verify(observer).onChanged(dummyFilms);

//        type = 1;
//        fakeFilms.setValue(FilmData.getListData(type));
//        films = dataRepository.getFilms().getValue();
//        when(dataRepository.getFilms()).thenReturn(fakeFilms);
//        fakeFilms.setValue(FilmData.getListData(type));
//        ArrayList<Film> films = dataRepository.getFilms().getValue();
//        assertNotNull(films);
//        assertEquals("Girl Under the Ketapang Tree", films.get(0).getJudul());
//        assertEquals("Glass", films.get(1).getJudul());
//        assertEquals(11, films.size());
//        films = dataRepository.getFilms().getValue();

//        type = 2;
//        fakeFilms.setValue(FilmData.getListData(type));
//        films = dataRepository.getFilms().getValue();
//        assertNotNull(films);
//        assertEquals("Arrow", films.get(0).getJudul());
//        assertEquals("The Flash", films.get(1).getJudul());
//        assertEquals(10, films.size());

    }


}