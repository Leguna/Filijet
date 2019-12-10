package com.arksana.filijet.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.arksana.filijet.data.Film;
import com.arksana.filijet.data.DataRepository;

import java.util.ArrayList;

public class DetailViewModel extends AndroidViewModel {

    public int selectedId;
    public ArrayList<Film> films;
    public Film film;

    private DataRepository dataRepository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public void setFilm() {
        dataRepository.getFilmDetail(films, selectedId);
    }

    public LiveData<Film> getFilm(){
        return dataRepository.getFilm();
    }

    public void next() {
        if (selectedId >= films.size() - 1)  selectedId = 0;
        else  ++selectedId;
        setFilm();
    }

    public void prev() {
        if (selectedId <= 0)  selectedId = films.size() - 1;
        else  --selectedId;
        setFilm();
    }

}
