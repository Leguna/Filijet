package com.arksana.filijet.main;

import androidx.lifecycle.ViewModel;

import com.arksana.filijet.data.Film;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {

    public int selectedId;
    public ArrayList<Film> films;

    public Film getFilm() {
        return films.get(selectedId);
    }

    public int prev() {
        if (selectedId <= 0) return selectedId = films.size() - 1;
        else return --selectedId;
    }

    public int next() {
        if (selectedId >= films.size() - 1) return selectedId = 0;
        else return ++selectedId;
    }
}
