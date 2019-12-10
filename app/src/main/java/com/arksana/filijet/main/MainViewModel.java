package com.arksana.filijet.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.arksana.filijet.data.DataRepository;
import com.arksana.filijet.data.Film;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    DataRepository dataRepository;
    private int type;
    private int page = 1;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public void setData(int type) {
        this.type = type;
        dataRepository.getAllFilms(type, page);
    }

    public void moreData() {
        page++;
        dataRepository.getAllFilms(type, page);
    }

    public LiveData<ArrayList<Film>> getFilms() {
        return dataRepository.getFilms();
    }

    public LiveData<PagedList<Film>> getAllFavorite() {
        return new LivePagedListBuilder<>(
                dataRepository.getAllFavorite(), 10).build();
    }
}
