package com.arksana.filijet.main;

import androidx.lifecycle.ViewModel;

import com.arksana.filijet.data.Film;
import com.arksana.filijet.data.FilmData;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    public ArrayList<Film> films = new ArrayList<>();

    public ArrayList<Film> getFilms(int jenis) {
        return films = FilmData.getListData(jenis);
    }
}
