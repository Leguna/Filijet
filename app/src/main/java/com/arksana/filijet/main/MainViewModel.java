package com.arksana.filijet.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.arksana.filijet.data.Film;
import com.arksana.filijet.data.DataRepository;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    public DataRepository dataRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public void setData(int type) {
        dataRepository.getAllFilms(type);
    }

    public LiveData<ArrayList<Film>> getFilms() {
        return dataRepository.getFilms();
    }
}
