package com.arksana.filijet.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainViewModelTest {

    MainViewModel viewModel;

    @Before
    public void init(){
        viewModel = new MainViewModel();
    }

    @Test
    public void getFilms() {
        viewModel.getFilms(1);
        assertNotNull(viewModel.films);
        assertNotNull(viewModel.getFilms(1));
        assertEquals("Girl Under the Ketapang Tree", viewModel.getFilms(1).get(0).getJudul());
        assertEquals(11,viewModel.films.size());
        viewModel.getFilms(2);
        assertNotNull(viewModel.films);
        assertNotNull(viewModel.getFilms(2));
        assertEquals("Arrow", viewModel.getFilms(2).get(0).getJudul());
        assertEquals(10,viewModel.films.size());
    }
}