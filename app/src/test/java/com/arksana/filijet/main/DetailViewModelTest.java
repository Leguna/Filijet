package com.arksana.filijet.main;

import com.arksana.filijet.data.FilmData;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailViewModelTest {

    private DetailViewModel viewModel;

    @Before
    public void init() {
        viewModel = new DetailViewModel();
        viewModel.films = FilmData.getListData(1);
        viewModel.selectedId = 0;
    }

    @Test
    public void getFilm() {
        viewModel.films = FilmData.getListData(1);
        assertNotNull(viewModel.films);
        assertNotNull(viewModel.getFilm());
        assertEquals("Girl Under the Ketapang Tree", viewModel.getFilm().getJudul());
        assertEquals(11, viewModel.films.size());
        viewModel.films = FilmData.getListData(2);
        assertNotNull(viewModel.films);
        assertNotNull(viewModel.getFilm());
        assertEquals(10, viewModel.films.size());
        assertEquals("Arrow", viewModel.getFilm().getJudul());
    }

    @Test
    public void prev() {
        viewModel.selectedId = 0;
        viewModel.prev();
        assertEquals(10, viewModel.selectedId);
        viewModel.prev();
        assertEquals(9, viewModel.selectedId);
    }

    @Test
    public void next() {
        viewModel.selectedId = 10;
        viewModel.next();
        assertEquals(0, viewModel.selectedId);
        viewModel.next();
        assertEquals(1, viewModel.selectedId);
    }
}